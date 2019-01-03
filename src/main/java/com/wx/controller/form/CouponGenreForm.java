package com.wx.controller.form;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class CouponGenreForm implements Serializable{
	/**
	 * 券码名称
	 */
	@NotBlank(message = "券码名称不能为空")
	private String couponName;
	/**
	 * 优惠金额
	 */
	@NotNull(message = "优惠金额不能为空")
	private Double couponAmt;

	/**
	 * PER_NUM 每个人对一种类型的优惠券可以抢多少个  0-表示不限制
	 */
	private Integer preNum = 1;
	/**
	 * 满多少才能使用 0-表示无限制
	 */
	private Double useLimit;
	/**
	 * 每条券码的前缀
	 */
	@NotBlank(message = "券码的前缀不能为空")
	private String couponPre;
	/**
	 * 每条券码的长度
	 */
	@NotNull(message = "券码长度不能为空")
	private Integer couponLen;
	/**
	 * 券码总数量
	 */
	@NotNull(message = "券码总数量不能为空")
	private Integer totalNum;
	/**
	 * 所属商户
	 */
	@NotBlank(message = "所属商户不能为空")
	private String shopOwner;
	/**
	 * 结束日期
	 */
	@NotNull(message = "结束日期不能为空")
	private Date endDate;
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
	public Integer getPreNum() {
		return preNum;
	}
	public void setPreNum(Integer preNum) {
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
	public Integer getCouponLen() {
		return couponLen;
	}
	public void setCouponLen(Integer couponLen) {
		this.couponLen = couponLen;
	}
	public Integer getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	public String getShopOwner() {
		return shopOwner;
	}
	public void setShopOwner(String shopOwner) {
		this.shopOwner = shopOwner;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
