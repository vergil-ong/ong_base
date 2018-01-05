package com.ong.util;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 对Map类的帮助类
 * @Description:  Map类的帮助类
 * @Author:       Ong
 * @CreateDate:   2017-10-26 17:00:00
 * @E-mail:		  865208597@qq.com
 */
public class MapHelper {
	
	private static Logger logger = LoggerFactory.getLogger(MapHelper.class);
	
	private static Class<?> defaultMapClazz = HashMap.class;
	
	
	/**
	 * return if map is null
	 * @param map
	 * @return
	 */
	public static boolean isMapNull(Map<?,?> map){
		return map == null;
	}
	
	/**
	 * return map is null or empty
	 * @param map
	 * @return
	 */
	public static boolean isMapEmpty(Map<?,?> map){
		return isMapNull(map)||map.isEmpty();
	}
	
	/**
	 * get Instance of Map if it`s null
	 * @param <P>
	 * @param <T>
	 * @param map
	 * @return
	 */
	public static <T, P> Map<T,P> getInstanceIfNull(Map<T, P> map){
		
		return getInstanceIfNull(map, defaultMapClazz);
	}
	
	/**
	 * get Instance of Map if it`s null
	 * @param <T>
	 * @param <P>
	 * @param map
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T, P> Map<T,P> getInstanceIfNull(Map<T, P> map, Class<?> clazz){
		Map<T,P> resultMap = null;
		/**
		 * 1. check if map is null
		 * 	1.1 map is not null 
		 * 		return map
		 * 	1.2 map is null
		 * 		return new Instance Class
		 */
		
		//1. check if map is null
		if(!isMapNull(map)){
			//1.1 map is not null  return map
			return map;
		}
		
		//return new Instance Class
		try {
			resultMap = (Map<T, P>) clazz.newInstance();
		} catch (Exception e1) {
			logger.error("newInstance the class {} ,Exception is {}",new Object[]{clazz,e1.getMessage(),e1});
			try {
				resultMap = (Map<T, P>) defaultMapClazz.newInstance();
			} catch (Exception e2) {
				logger.error("newInstance the defaultMapClazz {} ,Exception is {}",new Object[]{defaultMapClazz,e2.getMessage(),e2});
				resultMap = new HashMap<T, P>();
			}
		} 
		
		return resultMap;
	}
}
