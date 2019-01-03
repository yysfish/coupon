package com.wx.entity;

import java.util.Date;

public class Coupon {
	
	/**
	 * 主键
	 */
	private int couponId;
	
	/**
	 * 当前券码所属 券码类型的ID
	 */
	private int typeId;
	
	/**
	 * 所属商户
	 */
	private String shopOwner;

	/**
	 * 券码
	 */
	private String couponCode;
	/**
	 * 券码状态 0-新建 1-已被抢 2-已使用 3-已过期
	 */
	private String couponStatus;
	/**
	 * 拥有者手机号
	 */
	private String ownerPhone;
	
	/**
	 * 创建日期
	 */
	private Date createDate;
	/**
	 * 抢占日期
	 */
	private Date grapDate;
	/**
	 * 使用日期
	 */
	private Date useDate;
	/**
	 * 过期日期
	 */
	private Date expireDate;
	
	/**
	 * 非DB字段 要更新到的券码状态或者是要查询的某种状态
	 */
	private String targetStatus;
	
	/**
	 * 非DB字段 查询一页数据的大小
	 */
	private String limit;
	
	
	public int getCouponId() {
		return couponId;
	}
	public void setCouponId(int couponId) {
		this.couponId = couponId;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getShopOwner() {
		return shopOwner;
	}
	public void setShopOwner(String shopOwner) {
		this.shopOwner = shopOwner;
	}
	public String getCouponCode() {
		return couponCode;
	}
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	public String getCouponStatus() {
		return couponStatus;
	}
	public void setCouponStatus(String couponStatus) {
		this.couponStatus = couponStatus;
	}
	public String getOwnerPhone() {
		return ownerPhone;
	}
	public void setOwnerPhone(String ownerPhone) {
		this.ownerPhone = ownerPhone;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getGrapDate() {
		return grapDate;
	}
	public void setGrapDate(Date grapDate) {
		this.grapDate = grapDate;
	}
	public Date getUseDate() {
		return useDate;
	}
	public void setUseDate(Date useDate) {
		this.useDate = useDate;
	}
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	public String getTargetStatus() {
		return targetStatus;
	}
	public void setTargetStatus(String targetStatus) {
		this.targetStatus = targetStatus;
	}
	public String getLimit() {
		return limit;
	}
	public void setLimit(String limit) {
		this.limit = limit;
	}
}
