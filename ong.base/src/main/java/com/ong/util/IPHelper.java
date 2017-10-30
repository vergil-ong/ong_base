package com.ong.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

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
	
	private static String OS_NAME="";
	
	public static final String OS_WIN = "windows";
	
	public static final String OS_LINUX = "linux";
	
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
	
	public static String getIPAddressByLinux(){
		String ipAddr = new String();
		List<String> ipAddressListByLinux = getIPAddressListByLinux();
		if(ipAddressListByLinux.size()>0){
			ipAddr = ipAddressListByLinux.get(ipAddressListByLinux.size()-1);
		}else{
			ipAddr = ipAddressListByLinux.get(0);
		}
        return ipAddr;
	}
	
	public static List<String> getIPAddressListByLinux(){
		List<String> ipaddrList = new ArrayList<String>();
		
		try {
			Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
			while(en.hasMoreElements()){
				NetworkInterface networkInterface = en.nextElement();
				String netIntName = networkInterface.getName();
				if(!netIntName.contains("docker")&&!netIntName.contains("lo")){
					Enumeration<InetAddress> enumIpAddr = networkInterface.getInetAddresses();
					while(enumIpAddr.hasMoreElements()){
						InetAddress inetAddress = enumIpAddr.nextElement();
						if(inetAddress.isLoopbackAddress()){
							continue;
						}
						String ipaddress = inetAddress.getHostAddress().toString();
						if (!ipaddress.contains("::") && !ipaddress.contains("0:0:") && !ipaddress.contains("fe80")) {
							ipaddrList.add(ipaddress);
							logger.debug("ipaddress is {}",ipaddress);
	                    }
					}
					
				}
			}
		} catch (SocketException e) {
			logger.error("获取系统IP失败，{},设置成本地IP 127.0.0.1",e);
//			ipAddr = "127.0.0.1";
		}
		if(ipaddrList.size() == 0){
			logger.error("获取系统IP失败,设置成本地IP 127.0.0.1");
			ipaddrList.add("127.0.0.1");
		}
		logger.debug("ipaddrList is {}",ipaddrList);
        return ipaddrList;
	}
	
	public static String getIPAddress(){
		if(OS_WIN.equals(checkOS())){
			return getIPAddressByWindows();
		}else if(OS_LINUX.equals(checkOS())){
			return getIPAddressByLinux();
		}
		
		return null;
		
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
	
	public static String getOSName(){
		if(StringHelper.isEmpty(OS_NAME)){
			OS_NAME = System.getProperty("os.name");
			logger.debug("current operation system is {}",OS_NAME);
		}
		return OS_NAME;
	}
	
	public static String refreshOSName(){
		OS_NAME = System.getProperty("os.name");
		logger.debug("current operation system is {}",OS_NAME);
		return OS_NAME;
	}
	
	public static String refreshOSName(String osName){
		OS_NAME = osName;
		return OS_NAME;
	}
	
	/**
	 * 判断操作系统类型<br/>
	 * 如果无法判断 则返回os_name
	 * @return
	 */
	public static String checkOS(){
		String osName = getOSName();
		return checkOS(osName);
	}
	
	/**
	 * 判断操作系统类型<br/>
	 * 如果无法判断 则返回os_name
	 * @param osName
	 * @return
	 */
	public static String checkOS(String osName){
		if(osName.toLowerCase().startsWith("win")){
			return OS_WIN;
		}else if(osName.toLowerCase().contains("linux")){
			return OS_LINUX;
		}
		return osName;
	}
}
