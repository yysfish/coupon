package com.wx.entity;

import java.io.Serializable;
import java.util.Date;

public class CouponGenre implements Serializable{

	/**
	 * 券码类型ID
	 */
	private int typeId;
	/**
	 * 券码名称
	 */
	private String couponName;
	/**
	 * 满多少才能使用 0-表示无限制
	 */
	private Double couponAmt;
	
	/**
	 * PER_NUM 每个人对一种类型的优惠券可以抢多少个  0-表示不限制
	 */
	private int preNum = 1;
	/**
	 * 
	 */
	private Double useLimit;
	/**
	 * 每条券码的前缀
	 */
	private String couponPre;
	/**
	 * 每条券码的长度
	 */
	private int couponLen;
	/**
	 * 券码总数量
	 */
	private int totalNum;
	/**
	 * 所属商户
	 */
	private String shopOwner;
	/**
	 * 创建日期
	 */
	private Date createDate;
	/**
	 * 结束日期
	 */
	private Date endDate;
	
	/**
	 * 是否过期 0-未发布  1-已生成 2-已过期
	 */
	private String isExpire;
	
	/**
	 * 创建人
	 */
	private String creator;
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getCouponName() {
		return couponName;
	}
	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	public Double getCouponAmt() {
		return couponAmt;
	}
	public void setCouponAmt(Double couponAmt) {
		this.couponAmt = couponAmt;
	}
	public int getPreNum() {
		return preNum;
	}
	public void setPreNum(int preNum) {
		this.preNum = preNum;
	}
	public Double getUseLimit() {
		return useLimit;
	}
	public void setUseLimit(Double useLimit) {
		this.useLimit = useLimit;
	}
	public String getCouponPre() {
		return couponPre;
	}
	public void setCouponPre(String couponPre) {
		this.couponPre = couponPre;
	}
	public int getCouponLen() {
		return couponLen;
	}
	public void setCouponLen(int couponLen) {
		this.couponLen = couponLen;
	}
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	public String getShopOwner() {
		return shopOwner;
	}
	public void setShopOwner(String shopOwner) {
		this.shopOwner = shopOwner;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getIsExpire() {
		return isExpire;
	}
	public void setIsExpire(String isExpire) {
		this.isExpire = isExpire;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
}
