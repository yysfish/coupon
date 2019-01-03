package com.wx.task;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.wx.common.CouConst;
import com.wx.dao.CouponDAO;
import com.wx.dao.CouponGenreDAO;
import com.wx.entity.Coupon;
import com.wx.entity.CouponGenre;
import com.wx.util.JedisUtils;

@Component
public class CouponExpiredTask {

	@Autowired
	private CouponGenreDAO couponGenreDAO;
	@Autowired
	private CouponDAO couponDAO;
	
	/**
	 * 定时任务 每天晚上1点执行 查询所有的券码类型 如果结束时间=当前日期 则券码类型和券码的状态都置为过期
	 */
	@Scheduled(cron="0 0 1 * * ?")
	public void run() {
		CouponGenre couponGenre = new CouponGenre();
		//查询所有已发布的券码类型
		couponGenre.setIsExpire(CouConst.Genre.HAS_GEN);
		try {
			List<CouponGenre> list = couponGenreDAO.qryAll(couponGenre);
			for(CouponGenre coupon:list) {
				if (!coupon.getEndDate().after(new Date())) {
					coupon.setIsExpire(CouConst.Genre.HAS_EXPIRED);
					//将券码类型更新为过期
					couponGenreDAO.updateCouponGenre(couponGenre);
					Coupon cou = new Coupon();
					cou.setTypeId(coupon.getTypeId());
					cou.setTargetStatus(CouConst.Code.EXPIRED);
					//将对应类型的券码全部更新为过期
					couponDAO.updateCoupon(cou);
					//删除掉redis中过期的券码
					JedisUtils.del(coupon.getTypeId() + "");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
