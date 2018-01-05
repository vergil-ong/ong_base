package com.ong.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 类型帮助类
 * 
 * @Description: 类型帮助类
 * @Author: Ong
 * @CreateDate: 2017-10-27 14:00:00
 * @E-mail: 865208597@qq.com
 */
public class TypeHelper {

	private static Logger logger = LoggerFactory.getLogger(TypeHelper.class);

	public static String toString(Object s, String defaultValue) {
		if ((s == null) || (StringHelper.isEmpty(s.toString()))) {
			return defaultValue;
		}
		if (s.getClass().isArray()) {
			try {
				String str = StringHelper.EMPTYSTR;
				Object[] arr = (Object[]) s;
				for (int i = 0; i < arr.length; i++) {
					str = str + arr[i];
					if (i < arr.length - 1) {
						str = str + ",";
					}
				}
				return str;
			} catch (Exception e) {
				logger.error("类型转换错误，自动返回默认值：{}",
						new Object[] { StringHelper.isEmpty(defaultValue) ? "\"\"" : defaultValue, e });
			}
		}
		return s.toString();
	}

	public static String toString(Object s) {
		return toString(s, "");
	}

	public static String toString(String[] arr) {
		return toString(arr, ",");
	}

	public static String toString(String[] arr, String sep) {
		if (arr == null) {
			return "";
		}
		String s = "";
		for (int i = 0; i < arr.length; i++) {
			s = s + arr[i] + (i < arr.length - 1 ? sep : "");
		}
		return s;
	}

	public static Long toLong(Object value, Long defaultValue) {
		try {
			if (StringHelper.isEmpty(value)) {
				return defaultValue;
			}
			return Long.valueOf(toString(value));
		} catch (Exception e) {
			logger.error("类型转换错误，自动返回默认值：{}", new Object[] { defaultValue, e });
		}
		return defaultValue;
	}

	public static Long toLong(Object value) {
		return toLong(value, null);
	}

	public static Double toDouble(Object value, Double defaultValue) {
		try {
			if (StringHelper.isEmpty(value)) {
				return defaultValue;
			}
			return Double.valueOf(toString(value));
		} catch (Exception e) {
			logger.error("类型转换错误，自动返回默认值：{}", new Object[] { defaultValue, e });
		}
		return defaultValue;
	}

	public static Double toDouble(Object value) {
		return toDouble(value, null);
	}

	public static Integer toInteger(Object value, Integer defaultValue) {
		try {
			if (StringHelper.isEmpty(value)) {
				return defaultValue;
			}
			return Integer.valueOf(toString(value));
		} catch (Exception e) {
			logger.error("类型转换错误，自动返回默认值：{}", new Object[] { defaultValue, e });
		}
		return defaultValue;
	}

	public static Integer toInteger(Object value) {
		return toInteger(value, null);
	}

	public static Float toFloat(Object value, Float defaultValue) {
		try {
			if (StringHelper.isEmpty(value)) {
				return defaultValue;
			}
			return Float.valueOf(toString(value));
		} catch (Exception e) {
			logger.error("类型转换错误，自动返回默认值：{}", new Object[] { defaultValue, e });
		}
		return defaultValue;
	}

	public static Float toFloat(Object value) {
		return toFloat(value, null);
	}

	public static int toInt(Object value, int defaultValue) {
		if ((value instanceof Integer)) {
			return ((Integer) value).intValue();
		}
		if ((value instanceof Long)) {
			return ((Long) value).intValue();
		}
		return toInteger(value + "", Integer.valueOf(defaultValue)).intValue();
	}

	public static int toInt(Object value) {
		return toInt(value, 0);
	}

	public static Short toShort(Object value, Short defaultValue) {
		try {
			if (StringHelper.isEmpty(value)) {
				return defaultValue;
			}
			return Short.valueOf(toString(value, "").split("\\.")[0]);
		} catch (Exception e) {
			logger.error("类型转换错误，自动返回默认值：{0}", new Object[] { defaultValue, e });
		}
		return defaultValue;
	}

	public static Short toShort(Object value) {
		return toShort(value, null);
	}

}
