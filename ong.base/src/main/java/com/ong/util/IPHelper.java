package com.ong.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 对IP的帮助类
 * @Description:  IP的帮助类
 * @Author:       Ong
 * @CreateDate:   2017-10-27 16:00:00
 * @E-mail:		  865208597@qq.com
 */
public class IPHelper {
	
	private static Logger logger = LoggerFactory.getLogger(IPHelper.class);
	
	public static String getIPAddressByWindows(){
		String ipAddr = new String();
		InetAddress localHost = null;
		try {
			localHost = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			logger.error("getIPAddressByWindows Exception ,return empty String {}",new Object[]{e});
			return ipAddr;
		}
		return localHost.getHostAddress();
	}
	
	public static String getIPAddress(){
		return getIPAddressByWindows();
	}
	
	/**
	 * 为点分十进制的IP地址填充指定字符串
	 * @param src		填充的字符串
	 * @param length	每个分支最大长度
	 * @return
	 */
	public static String getIPAddrPadStr(String src,int length){
		String ipAddress = getIPAddress();
		String[] split = ipAddress.split("\\.");
		StringBuffer sb = new StringBuffer();
		if(split == null || split.length == 0){
			return ipAddress;
		}
		
		for(int i=0;i<split.length;i++){
			String branchStr = split[i];
			if(branchStr.length()>length){
				branchStr = branchStr.substring(0,length);
			}else{
				branchStr = StringHelper.leftPadStr(branchStr, length-branchStr.length(), "0");
			}
			sb.append(branchStr);
		}
		return sb.toString();
	}
	
}
