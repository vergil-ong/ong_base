package com.ong.util;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 流水号生成器
 * 
 * @Description: 流水号生成器<br/>
 * 				  流水号长度取决于并发量和机器运算能力<br/>
 *  			 2000并发 跑200W数据 需要流水长度至少22位
 * @Author: Ong
 * @CreateDate: 2017-10-27 14:00:00
 * @E-mail: 865208597@qq.com
 * 
 * 
 */
public class SerialNumHelper {
	
	private static Logger logger = LoggerFactory.getLogger(SerialNumHelper.class);
	
	private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger(-1);
	
	public static final int DEFAULT_DIGIT = 20;
	
	public static String LOCAL_IPADDRESS = null;
	
	static{
		String ipAddrPadStr = IPHelper.getIPAddrPadStr("0", 3);
		if(ipAddrPadStr == null || ipAddrPadStr.length()<3){
			LOCAL_IPADDRESS = null;
		}else{
			LOCAL_IPADDRESS = ipAddrPadStr.substring(ipAddrPadStr.length()-3, ipAddrPadStr.length());	
		}
		
	}
	
	/**
	 * 生成指定位数的流水号,如果小于20位,默认20位
	 * IP后三位+时间戳+流水号
	 * @param digit		指定位数
	 * @return
	 */
	public static String generateSerial(int digit){
		
		StringBuffer resultSBu = new StringBuffer();
		
		if(LOCAL_IPADDRESS == null){
			logger.error("生成流水号失败，本地IP地址获取失败[ip_address == null]");
			return resultSBu.toString();
		}
		
		if(digit < DEFAULT_DIGIT){
			digit = DEFAULT_DIGIT;
			logger.warn("流水号宽度不得小于{}位，已强制默认为{}位",new Object[]{DEFAULT_DIGIT,DEFAULT_DIGIT});
		}
		
		String dateFormatStr = "yyMMddHHmmss";
		digit -= 15;
		
		if(digit >= 8){
			dateFormatStr += "SSS";
			digit -= 3;
		}
		
		String atom = StringHelper.rightPadStr("1", digit, "0");
		int atomInt = Integer.parseInt(atom);
		
		int serialNum = 0;
	    int serialNumIndex = ATOMIC_INTEGER.incrementAndGet();
	    if (serialNumIndex > atomInt) {
	      serialNum = serialNumIndex % Integer.parseInt(atom);
	    } else {
	      serialNum = serialNumIndex;
	    }
	    String serialNumStr = Integer.valueOf(serialNum).toString();
		
	    resultSBu.append(LOCAL_IPADDRESS)
	    		.append(DateHelper.getDateString(dateFormatStr))
	    		.append(StringHelper.leftPadStr(serialNumStr, digit-serialNumStr.length(), "0"));
		
		return resultSBu.toString();
	}
	
}
