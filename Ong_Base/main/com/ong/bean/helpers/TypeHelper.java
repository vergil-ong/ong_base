package com.ong.bean.helpers;

import java.util.List;
import java.util.Map;

/**
 * 对数据类型的基本帮助类
 * 
 * @Description: Bean类的帮助类
 * @Author: Ong
 * @CreateDate: 2017-05-20 12:00:00
 * @E-mail: 865208597@qq.com
 */
public class TypeHelper {
	
	public static boolean isComparable(Object value) {
		return (value == null) || ((value instanceof Comparable));
	}

	public static boolean isNumber(Object value) {
		if (value == null) {
			return false;
		}
		if ((value instanceof Number)) {
			return true;
		}
		if ((value instanceof String)) {
			try {
				Double.parseDouble((String) value);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		}
		return false;
	}

	public static boolean isArray(Object value) {
		return (value != null) && (value.getClass().isArray());
	}

	public static boolean isList(Object value) {
		return value instanceof List;
	}

	public static boolean isMap(Object value) {
		return value instanceof Map;
	}
	
}
