package com.wx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wx.common.BaseController;
import com.wx.common.ResponseCodeException;
import com.wx.common.ResponseModel;
import com.wx.entity.Coupon;
import com.wx.service.CouponService;

@RequestMapping("/cou")
@RestController
public class CouController extends BaseController{

	@Autowired
	private CouponService couponService;
	
	@RequestMapping("/grab")
	public ResponseModel grab(Integer typeId, String shopOwner, @RequestParam(required=true)String phoneNo) throws Exception {
		ResponseModel model = null;
		try {
			if (typeId == null && shopOwner == null) {
				throw new ResponseCodeException("0011", "入参有误");
			}
			if (shopOwner != null) {
				
			} else {
				model = getSuccessModel(couponService.grapCoupon1(typeId, phoneNo));				
			}
		} catch (Exception e) {
			logger.error("抢券异常:", e);
			model = getErrorModel(e);
		}
		return model;
	}
	
	@RequestMapping("/qryUserCous")
	public ResponseModel qryUserCous(@RequestParam(required=true)String phoneNo) throws Exception {
		ResponseModel model = null;
		try {
			Coupon coupon = new Coupon();
			coupon.setOwnerPhone(phoneNo);
			model = getSuccessModel(couponService.qry(coupon));
		} catch (Exception e) {
			logger.error("查询指定用户所抢的优惠券异常:", e);
			model = getErrorModel(e);
		}
		return model;
	}
}
