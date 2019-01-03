package com.wx.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wx.common.CouConst;
import com.wx.common.ResponseCodeException;
import com.wx.dao.CouponDAO;
import com.wx.dao.CouponGenreDAO;
import com.wx.entity.Coupon;
import com.wx.entity.CouponGenre;
import com.wx.service.CouponGenreService;
import com.wx.util.CouponGenUtil;
import com.wx.util.JedisUtils;

@Service
public class CouponGenreServiceImpl implements CouponGenreService{
	
	@Autowired
	private CouponGenreDAO  couponGenreDAO;
	@Autowired
	private CouponDAO couponDAO;
	
	@Override
	public void addCouponGenre(CouponGenre couponGenre) throws Exception {
		// 做券码前缀的校验
		CouponGenre newcou = new CouponGenre();
		newcou.setCouponPre(couponGenre.getCouponPre());
		newcou.setShopOwner(couponGenre.getShopOwner());
		if (couponGenreDAO.qryAll(newcou) != null) {
			throw new ResponseCodeException("0010", "同一商户下每种类型的券码前缀必须不一样");
		}
		couponGenreDAO.addCouponGenre(couponGenre);
	}

	@Override
	public void gen(int typeId) throws Exception {
		CouponGenre couponGenre = new CouponGenre();
		couponGenre.setTypeId(typeId);
		List<CouponGenre> list = couponGenreDAO.qryAll(couponGenre);
		if (list == null || list.isEmpty()) {
			throw new ResponseCodeException("0001", "要发布的券码不存在");
		}
		couponGenre = list.get(0);
		if (!CouConst.Genre.UNREALSED.equals(couponGenre.getIsExpire())) {
			throw new ResponseCodeException("0007", "要发布的券码已发布或已过期");
		}
		
		couponGenre.setIsExpire(CouConst.Genre.HAS_GEN);
		couponGenreDAO.updateCouponGenre(couponGenre);
		// 生成券码 		
		Set<String> set = CouponGenUtil.gen(couponGenre.getCouponPre(), couponGenre.getTotalNum(), couponGenre.getCouponLen());
		List<Coupon> codeList = new ArrayList<Coupon>();
		for(String code : set) {
			Coupon coupon = new Coupon();
			coupon.setCouponCode(code);
			coupon.setTypeId(typeId);
			coupon.setCouponStatus(CouConst.Code.NEW);
			coupon.setShopOwner(couponGenre.getShopOwner());
			codeList.add(coupon);
		}
		//更新入库
		couponDAO.batchAdd(codeList);
	}

	@Override
	public void publish(String shopOwner) throws Exception {
		List<CouponGenre> list = getCouList(shopOwner);
		for(CouponGenre cou: list) {
			if (CouConst.Genre.UNREALSED.equals(cou.getIsExpire())) {
				throw new ResponseCodeException("0009", "还存在未发布的券码");
			}
		}
		Coupon coupon = new Coupon();
		coupon.setShopOwner(shopOwner);
		List<Coupon> lsit = couponDAO.qry(coupon);
		List<String> codeList = new ArrayList<String>();
		for(Coupon coupon2:lsit) {
			codeList.add(coupon2.getCouponCode());
		}
		//塞入redis队列
		JedisUtils.leftPushAll(shopOwner, codeList);
	}
	
	@Override
	public void publish1(String shopOwner) throws Exception {
		List<CouponGenre> list = getCouList(shopOwner);
		for(CouponGenre cou: list) {
			int typeId = cou.getTypeId();
			JedisUtils.leftPushAll(typeId + "", couponDAO.qryCousById(typeId));
		}
	}
	
	@Override
	public void delCouponGenre(int typeId) throws Exception {
		couponGenreDAO.delCouponGenre(typeId);
		couponDAO.del(typeId);
	}
	
	private List<CouponGenre> getCouList(String shopOwner) throws Exception {
		CouponGenre couponGenre = new CouponGenre();
		couponGenre.setShopOwner(shopOwner);
		return couponGenreDAO.qryAll(couponGenre);
	}
}
