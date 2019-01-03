package com.wx.util;

import java.util.HashSet;
import java.util.Set;

import com.wx.common.ResponseCodeException;

public class CouponGenUtil {

	public static final char[] T = new char[] {
			'0','1','2','3','4','5','6','7','8','9',
			'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
			'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'
	};
	
	/**
	 * 生成券码
	 * @param pre 券码前缀
	 * @param totalNum 生成的券码总数量
	 * @param len 券码的长度
	 * @return
	 * @throws Exception
	 */
	public static Set<String> gen(String pre, int totalNum, int len) throws Exception {
		Set<String> set = new HashSet<String>(totalNum);
		StringBuilder sb = null;
		int preLen = getAndValid(pre, totalNum, len);
		do {
			sb = new StringBuilder(pre);
			for(int i = 0; i < len - preLen; i ++ ) {
				sb.append(T[getRandom()]);
			}
			
		} while (set.add(sb.toString()) && set.size() < len);
		
		return set;
	}
	
	private static int getAndValid(String pre, int totalNum, int len) {
		int preLen = pre==null ? 0 : pre.length();
		if (preLen * 3 > len) {
			throw new ResponseCodeException("0006", "前缀的长度不能超过总长度的三分之一");
		}
		if (totalNum >= 1000) {
			throw new ResponseCodeException("0008", "券码总数不能超过100条");
		}
		return preLen;
	}
	
	/**
	 * 获取 [0, 62)直接随机整数
	 * @return
	 */
	public static int getRandom() {
		return (int)(Math.random()*62);
	}
}
