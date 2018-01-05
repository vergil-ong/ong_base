package com.ong.util;

/**
 * 对设备的帮助类
 * @Description:  设备的帮助类
 * @Author:       Ong
 * @CreateDate:   2017-10-26 17:00:00
 * @E-mail:		  865208597@qq.com
 */
public class DeviceHelper {
	
	/*
	 * 设备名称
	 */
	public static final String ANDROID = "Android";
	public static final String IPHONE = "iPhone";
	public static final String IPAD = "iPad";
	public static final String IPOD = "iPod";
	public static final String WIN = "Win";
	public static final String MAC = "Mac";
	public static final String WINDOWSPHONE = "WindowsPhone";
	
	/**
	 * 未知设备
	 */
	public static final String UNKNOWN = "UNKNOWN";
	
	
	
	
	/**
	 * 判断是否为iphone
	 * @Description: 判断是否为iphone
	 * @param agent
	 * @return
	 */
	public static boolean isIPhone(String agent){
		agent = agent.toUpperCase();
		return agent.equals("IPHONE") || agent.contains("IPHONE") || agent.contains("CFNETWORK");
	}
	
	/**
	 * 判断是否为IPad
	 * @Description: 判断是否为IPad
	 * @param agent
	 * @return
	 */
	public static boolean isIPad(String agent){
		return agent.toUpperCase().contains("IPAD");
	}
	
	/**
	 * 判断是否为IPod
	 * @Description: 判断是否为IPod
	 * @param agent
	 * @return
	 */
	public static boolean isIPod(String agent){
		return agent.toUpperCase().contains("IPOD");
	}

	/**
	 * 判断是否为IOS
	 * @Description: 判断是否为IOS
	 * @param agent
	 * @return
	 */
	public static boolean isIOS(String agent){
		return isIPhone(agent) || isIPad(agent) || isIPod(agent);
	}

	/**
	 * 判断是否为Android
	 * @Description: 判断是否为Android
	 * @param agent
	 * @return
	 */
	public static boolean isAndroid(String agent){
		agent = agent.toUpperCase();
		return agent.equals("ANDROID") || agent.contains("ANDROID");
	}
	
	/**
	 * 判断是否为微信浏览器
	 * @Description: 判断是否为微信浏览器
	 * @param agent
	 * @return
	 */
	public static boolean isWechat(String agent){
		return agent.toUpperCase().contains("MICROMESSENGER");
	}
	
	/**
	 * 判断是否为WinPhone
	 * @Description: 判断是否为WinPhone
	 * @param agent
	 * @return
	 */
	public static boolean isWinPhone(String agent){
		return agent.toUpperCase().contains("WINDOWS PHONE");
	}
	
	/**
	 * 判断是否为Mac
	 * @Description: 判断是否为Mac
	 * @param agent
	 * @return
	 */
	public static boolean isMac(String agent){
		return agent.toUpperCase().contains("MACINTOSH");
	}
	
	/**
	 * 判断是否为WINDOWS
	 * @Description: 判断是否为WINDOWS
	 * @param agent
	 * @return
	 */
	public static boolean isWin(String agent){
		return agent.toUpperCase().contains("WINDOWS NT");
	}

	/**
	 * 判断是否为移动端
	 * @Description: 判断是否为移动端
	 * @param agent
	 * @return
	 */
	public static boolean isMobile(String agent){
		return isAndroid(agent) || isIOS(agent) || isWechat(agent);
	}

	/**
	 * @Title: getDeviceType
	 * @Description: 返回客户端设备类型
	 * @param agent
	 * @return
	 */
	public static String getDeviceType(String agent){
		if(isAndroid(agent)){
			return ANDROID;
		}
		if(isIPhone(agent)){
			return IPHONE;
		}
		if(isIPad(agent)){
			return IPAD;
		}
		if(isIPod(agent)){
			return IPOD;
		}
		if(isWin(agent)){
			return WIN;
		}
		if(isMac(agent)){
			return MAC;
		}
		if(isWinPhone(agent)){
			return WINDOWSPHONE;
		}
		return UNKNOWN;
	}
}
