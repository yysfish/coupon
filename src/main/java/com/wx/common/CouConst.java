package com.wx.common;

/**
 * 券码相关常量
 * @author 005
 *
 */
public interface CouConst {

	public interface Genre {
		/**
		 * 0-未发布
		 */
		public static final String UNREALSED = "0";
		/**
		 * 1-已生成
		 */
		public static final String HAS_GEN = "1";
		/**
		 * 2-已过期
		 */
		public static final String HAS_EXPIRED = "2";
	}
	
	public interface Code {
		/**
		 * 0-新建  
		 */
		public static final String NEW = "0";
		/**
		 * 1-已被抢
		 */
		public static final String HAS_GRAP = "1";
		/**
		 * 2-已使用
		 */
		public static final String USED = "2";
		/**
		 *  3-已过期
		 */
		public static final String EXPIRED = "3";
	}
}
