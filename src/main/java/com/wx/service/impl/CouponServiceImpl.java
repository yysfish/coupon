package com.wx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wx.common.CouConst;
import com.wx.common.ResponseCodeException;
import com.wx.dao.CouponDAO;
import com.wx.dao.CouponGenreDAO;
import com.wx.entity.Coupon;
import com.wx.entity.CouponGenre;
import com.wx.service.CouponService;
import com.wx.util.JedisUtils;

@Service
public class CouponServiceImpl implements CouponService{

	@Autowired
	private CouponDAO couponDAO;
	
	@Autowired
	private CouponGenreDAO couponGenreDAO;
	
	@Override
	public void batchAdd(List<Coupon> list) throws Exception {
		couponDAO.batchAdd(list);
	}

	@Override
	public int updateCoupon(Coupon coupon) throws Exception {
		return couponDAO.updateCoupon(coupon);
	}

	@Override
	public List<Coupon> qryByPhoneId(int typeId,String phoneNo) throws Exception {
		Coupon coupon = new Coupon();
		coupon.setTypeId(typeId);
		coupon.setOwnerPhone(phoneNo);
		return qry(coupon);
	}

	@Override
	public List<Coupon> qry(Coupon coupon) throws Exception {
		return couponDAO.qry(coupon);
	}
	
	@Override
	public String grapCoupon(String shopOwner, String phoneNo) throws Exception {
		if (qry(shopOwner, phoneNo, CouConst.Code.HAS_GRAP) != null) {
			throw new ResponseCodeException("0012", "用户在该商户下已抢了优惠券");
		}
		String couponCode = (String) JedisUtils.rightPop(shopOwner);
		if (couponCode == null) {
			throw new ResponseCodeException("0004", "优惠券已抢完");
		}
		Coupon coupon = new Coupon();
		coupon.setCouponCode(couponCode);
		coupon.setShopOwner(shopOwner);
		coupon.setOwnerPhone(phoneNo);
		coupon.setCouponStatus(CouConst.Code.NEW);
		coupon.setTargetStatus(CouConst.Code.HAS_GRAP);
		//将券码对应的状态从0(新建)更新到1(已抢占)
		int idx = updateCoupon(coupon);
		if (idx != 1) {
			throw new ResponseCodeException("0005", "抢券失败，请稍后再试");
		}
		return couponCode;
	}
	
	@Override
	public String grapCoupon1(int typeId, String phoneNo) throws Exception {
		//根据typeId找到所属类别
		CouponGenre couponGenre = couponGenreDAO.qryById(typeId);
		if (couponGenre == null) {
			throw new ResponseCodeException("0002", "要抢的券码不存在");
		}
		//根据手机号和类型ID查询出用户已抢券码数
		List<Coupon> codeList = qryByPhoneId(typeId, phoneNo);
		//如果已抢的券码数量大于设置的数目，则不能再抢了
		if (codeList != null && codeList.size() > couponGenre.getPreNum()) {
			throw new ResponseCodeException("0003", "您已抢过该优惠券");
		}
		//从redis中获取券码
		String couponCode = (String) JedisUtils.rightPop(typeId + "");
		if (couponCode == null) {
			throw new ResponseCodeException("0004", "优惠券已抢完");
		}
		Coupon coupon = new Coupon();
		coupon.setCouponCode(couponCode);
		coupon.setTypeId(typeId);
		coupon.setOwnerPhone(phoneNo);
		coupon.setCouponStatus(CouConst.Code.NEW);
		coupon.setTargetStatus(CouConst.Code.HAS_GRAP);
		//将券码对应的状态从0(新建)更新到1(已抢占)
		int idx = updateCoupon(coupon);
		if (idx != 1) {
			throw new ResponseCodeException("0005", "抢券失败，请稍后再试");
		}
		return couponCode;
	}

	@Override
	public List<Coupon> qry(String shopOwner, String phoneNo, String targetStatus) throws Exception {
		Coupon coupon = new Coupon();
		coupon.setShopOwner(shopOwner);
		coupon.setOwnerPhone(phoneNo);
		coupon.setTargetStatus(targetStatus);
		return qry(coupon);
	}



}
