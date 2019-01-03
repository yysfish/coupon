package com.wx.dao;

import java.util.List;

import com.wx.entity.CouponGenre;

public interface CouponGenreDAO {

	/**
	 * 新增一种类型得优惠券
	 * @param couponType
	 * @throws Exception
	 */
	public void addCouponGenre(CouponGenre couponGenre) throws Exception;
	
	/**
	 * 更新优惠券类型
	 */
	public int updateCouponGenre(CouponGenre couponGenre) throws Exception;
	
	/**
	 * 查询所有的
	 * @return
	 * @throws Exception
	 */
	public List<CouponGenre> qryAll(CouponGenre couponGenre) throws Exception;
	
	/**
	 * 根据ID查询券码类型
	 * @param typeId
	 * @return
	 * @throws Exception
	 */
	public CouponGenre qryById(int typeId) throws Exception;
	
	/**
	 * 删除过期的优惠券类型
	 */
	public void delCouponGenre(int typeId) throws Exception;
}
