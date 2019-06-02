package com.cms.util;

import java.util.UUID;
/**
 * uuid.
 * @author allen-zyg.
 * @version 1.0.0
 */
public class UUIDUtil {
	/**
	 * 隐藏工具类的构造方法.
	 **/
	protected UUIDUtil() {
		throw new UnsupportedOperationException();
	}

	/**
	 * getid.
	 * @return  String.
	 */
	public static String getid() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
