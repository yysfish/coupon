package com.wx.service;

import java.util.List;

import com.wx.entity.Coupon;

public interface CouponService {

	/**
	 * 批量增加
	 * @param list
	 * @throws Exception
	 */
	public void batchAdd(List<Coupon> list) throws Exception;
	
	/**
	 * 更新券码
	 * @param coupon
	 * @return
	 * @throws Exception
	 */
	public int updateCoupon(Coupon coupon) throws Exception;
	
	/**
	 * 根据手机号和类型ID查询券码
	 */
	public List<Coupon> qryByPhoneId(int typeId, String phoneNo) throws Exception;
	
	/**
	 * 根据条件查询所有
	 * @param coupon
	 * @return
	 * @throws Exception
	 */
	public List<Coupon> qry(Coupon coupon) throws Exception;
	
	public List<Coupon> qry(String shopOwner, String phoneNo,String isExpire) throws Exception;
	
	/**
	 * 根据券码类型抢券码
	 */
	public String grapCoupon1(int typeId, String phoneNo) throws Exception;
	
	/**
	 * 根据商户抢券码
	 * @param shopOwner
	 * @param phoneNo
	 * @return
	 * @throws Exception
	 */
	public String grapCoupon(String shopOwner, String phoneNo) throws Exception;
}
