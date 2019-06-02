package com.portal.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 系统配置读取.
 * @author	allen-zyg
 * @version 1.0.0
 */
public class Config {
	/**
	 * 索引库位置.
	 */
	private static String indexPath;
	/**
	 * 所搜最大值.
	 */
	private static int searchMax;
	/**
	 * 当前页.
	 */
	private static int pageSize;
	/**
	 * Properties。类
	 */
	private static Properties properties;

	/**
	 * 静态加载配置文件.
	 */
	static {
		properties = new Properties();
		InputStream in = Config.class.getResourceAsStream("/config.properties");
		try {
			properties.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (properties.containsKey("index.path")) {
			indexPath = properties.getProperty("index.path");
		}
		if (properties.containsKey("search.max")) {
			String max = properties.getProperty("search.max");
			searchMax = Integer.parseInt(max);
		}
		if (properties.containsKey("search.pagesize")) {
			String size = properties.getProperty("search.pagesize");
			pageSize = Integer.parseInt(size);
		}
	}

	/**
	 * 获得索引库位置.
	 * @return String.
	 */
	public static String getIndexPath() {
		return indexPath;
	}

	/**
	 * getSearchMax.
	 * @return int.
	 */
	public static int getSearchMax() {
		return searchMax;
	}
	/**
	 * getPageSize.
	 * @return int
	 */
	public static int getPageSize() {
		return pageSize;
	}
}

