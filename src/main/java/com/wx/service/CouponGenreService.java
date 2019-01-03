package com.wx.service;

import com.wx.entity.CouponGenre;

public interface CouponGenreService {

	/**
	 * 新增一种类型得优惠券
	 * @param couponType
	 * @throws Exception
	 */
	public void addCouponGenre(CouponGenre couponGenre) throws Exception;
	
	/**
	 * 发布已经生成的优惠券
	 * @param typeId
	 * @throws Exception
	 */
	public void gen(int typeId) throws Exception;
	
	/**
	 * 发布指定商户下的优惠券--商户下所有类型的券码放入一个队列下
	 * @param shouOwner
	 * @throws Exception
	 */
	public void publish(String shopOwner) throws Exception;
	
	/**
	 * 发布指定商户下的优惠券--商户下每中类型的券码各放入一个队列
	 * @param shopOwner
	 * @throws Exception
	 */
	public void publish1(String shopOwner) throws Exception;
	
	
	/**
	 * 删除过期的优惠券类型和券码
	 */
	public void delCouponGenre(int typeId) throws Exception;
	
}
