package com.wx.dao;

import java.util.List;

import com.wx.entity.Coupon;

public interface CouponDAO {

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
	 *查询券码
	 */
	public List<Coupon> qry(Coupon coupon) throws Exception;
	
	/**
	 * 根据类型ID查询所有的券码
	 * @param typeId
	 * @return
	 * @throws Exception
	 */
	public List<String> qryCousById(int typeId) throws Exception;
	
	/**
	 * 删除过期的券码
	 */
	public void del(int typeId) throws Exception;
	
}
