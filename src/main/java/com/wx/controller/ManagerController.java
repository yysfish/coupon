package com.wx.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wx.common.BaseController;
import com.wx.common.ResponseModel;
import com.wx.controller.form.CouponGenreForm;
import com.wx.entity.CouponGenre;
import com.wx.service.CouponGenreService;

@RequestMapping("/man")
@RestController
public class ManagerController extends BaseController{

	@Autowired
	private CouponGenreService couponGenreService;
	
	@RequestMapping("/add")
	public ResponseModel add(@Valid CouponGenreForm form) throws Exception {
		ResponseModel model = null;
		try {
			CouponGenre couponGenre = new CouponGenre();
			couponGenre.setCouponAmt(form.getCouponAmt());
			couponGenre.setCouponLen(form.getCouponLen());
			couponGenre.setCouponName(form.getCouponName());
			couponGenre.setCouponPre(form.getCouponPre());
			couponGenre.setEndDate(form.getEndDate());
			couponGenre.setPreNum(form.getPreNum());
			couponGenre.setShopOwner(form.getShopOwner());
			couponGenre.setTotalNum(form.getTotalNum());
			if (form.getUseLimit() != null) {
				couponGenre.setUseLimit(form.getUseLimit());				
			}
			if (form.getPreNum() != null) {				
				couponGenre.setPreNum(form.getPreNum());
			}
			couponGenreService.addCouponGenre(couponGenre);
			model = getSuccessModel();
		} catch (Exception e) {
			logger.error("添加新的券码类型异常:", e);
			model = getErrorModel(e);
		}
		return model;
	}
	
	@RequestMapping("/gen")
	public ResponseModel gen(@RequestParam(required = true) int typeId) throws Exception {
		ResponseModel model = null;
		try {
			couponGenreService.gen(typeId);
			model = getSuccessModel();
		} catch (Exception e) {
			logger.error("生成指定类型的券码异常", e);
			model = getErrorModel(e);
		}
		return model;
	}
	
	@RequestMapping("/publish")
	public ResponseModel publish(@RequestParam(required = true) String shopOwner, @RequestParam(required = true) String wholePublish) throws Exception {
		ResponseModel model = null;
		try {
			//1-整体发布，一个商户下有10和30元的优惠券，将这两种类型券码放入一个队列
			//抢的时候只能用shopOwner抢，得到的是10和30其中一种
			//分开发布 是将10和30两种分别放入两个队列，这种只能获取其中类型的优惠券 ，只能通过优惠券类型去抢
			if ("1".equals(wholePublish)) {
				couponGenreService.publish(shopOwner);
			} else {				
				couponGenreService.publish1(shopOwner);
			}
			model = getSuccessModel();
		} catch (Exception e) {
			logger.error("发布商户的券码失败", e);
			model = getErrorModel(e);
		}
		return model;
	}
	
	@RequestMapping("/del")
	public ResponseModel del(@RequestParam(required = true) int typeId) throws Exception {
		ResponseModel model = null;
		try {
			couponGenreService.delCouponGenre(typeId);
			model = getSuccessModel();
		} catch (Exception e) {
			logger.error("删除过期的券码失败", e);
			model = getErrorModel(e);
		}
		return model;
	}
}
