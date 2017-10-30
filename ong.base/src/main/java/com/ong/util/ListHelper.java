package com.ong.util;

import java.util.List;

/**
 * 对List类的帮助类
 * @Description:  List类的帮助类
 * @Author:       Ong
 * @CreateDate:   2017-10-26 17:00:00
 * @E-mail:		  865208597@qq.com
 */
public class ListHelper {
	/**
	 * return if List is null
	 * @param map
	 * @return
	 */
	public static boolean isListNull(List<?> list){
		return list == null;
	}
	
	/**
	 * return List is null or empty
	 * @param map
	 * @return
	 */
	public static boolean isListEmpty(List<?> list){
		return isListNull(list)||list.isEmpty();
	}
}
