package com.ong.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日期帮助类
 * 
 * @Description: 日期帮助类
 * @Author: Ong
 * @CreateDate: 2017-10-27 11:00:00
 * @E-mail: 865208597@qq.com
 */
public class DateHelper {

	private static Logger logger = LoggerFactory.getLogger(DateHelper.class);

	public static final String FORMAT_YMD1 = "yyyy-MM-dd";
	public static final String FORMAT_YMD2 = "yyyy/MM/dd";
	public static final String FORMAT_YMD3 = "yyyy年MM月dd日";
	public static final String FORMAT_YMDHMS1 = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_YMDHMS2 = "yyyy-MM-dd hh:mm:ss";
	public static final String FORMAT_YMDHMS3 = "yyyy年MM月dd日HH时mm分ss秒";

	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
	// public static final String[] DATE_PATTERNS = { "yyyy-MM-dd" };
	// public static final String[] CONVERT_DATE_PATTERNS = { "yyyy-MM-dd",
	// "yyyy/MM/dd", "yyyy年MM月dd日", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd hh:mm:ss"
	// };

	/**
	 * 生成日期
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static Date buildDate(int year, int month, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day);
		return calendar.getTime();
	}

	/**
	 * 字符串转日期格式
	 * 
	 * @param param
	 * @return
	 */
	public static Date stringToDate(String param) {
		return stringToDate(param, null);
	}

	/**
	 * 字符串转日期格式
	 * 
	 * @param param
	 * @return
	 */
	public static Date stringToDate(String param, String partten) {
		if ((param == null) || (param == "") || (param.indexOf("null") >= 0)) {
			return null;
		}
		if (partten == null) {
			partten = DEFAULT_DATE_FORMAT;
		}
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat(partten);
		param = param.replaceAll("年", "-").replaceAll("月", "-").replaceAll("日", "").replaceAll("/", "-")
				.replaceAll("\\.", "-");
		param = param.replaceAll("T", " ").replaceAll("时", ":").replaceAll("分", ":").replaceAll("秒", "");
		try {
			date = sdf.parse(param);
		} catch (ParseException e) {
			logger.error("String[{}]转换为Date类型错误", new Object[] { param, e });
		}
		return new Date(date.getTime());
	}

	/**
	 * 获取日期相差天数
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static int getDayMargin(Date start, Date end) {
		return getDayMargin(start.getTime(), end.getTime());
	}

	/**
	 * 获取日期相差天数
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static int getDayMargin(String start, String end) {
		return getDayMargin(stringToDate(start).getTime(), stringToDate(end).getTime());
	}

	/**
	 * 获取日期相差天数
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static int getDayMargin(long start, long end) {
		Long margin = Long.valueOf(end - start);
		margin = Long.valueOf(margin.longValue() / 86400000L);
		return margin.intValue();
	}

	/**
	 * 获取当前日期
	 * 
	 * @return
	 */
	public static Date getNowDate() {
		Calendar calendar = Calendar.getInstance();
		return calendar.getTime();
	}

	/**
	 * 获取默认格式日期字符串
	 * 
	 * @return
	 */
	public static String getDateString() {
		return getDateString(DEFAULT_DATE_FORMAT);
	}

	/**
	 * 获取指定格式日期字符串
	 * 
	 * @return
	 */
	public static String getDateString(Date date, String partten) {
		if (date == null) {
			date = getNowDate();
		}
		String result = null;
		SimpleDateFormat formatter = new SimpleDateFormat(partten);
		result = formatter.format(date);
		return result;
	}

	/**
	 * 获取指定格式日期字符串
	 * 
	 * @param time
	 * @param partten
	 * @return
	 */
	public static String getDateString(Long time, String partten) {
		return getDateString(new Date(time.longValue()), partten);
	}

	/**
	 * 获取指定格式日期字符串
	 * 
	 * @param partten
	 * @return
	 */
	public static String getDateString(String partten) {
		return getDateString(getNowDate(), partten);
	}

	/**
	 * 获取指定年月的天数
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getDayCountOfMonth(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, 0);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	public static Date getFirstDateOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	public static Date getFirstDayOfMonth(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	public static Date getFirstDayOfQuarter(int year, int quarter) {
		int month = 0;
		if (quarter > 4) {
			return null;
		}
		month = (quarter - 1) * 3 + 1;

		return getFirstDayOfMonth(year, month);
	}

	public static Date getFirstDayOfYear(int year) {
		return getFirstDayOfMonth(year, 1);
	}

	public static Date getLastDateOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return getPrevDate(calendar.getTime(), 1);
	}

	public static Date getLastDayOfMonth(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return getPrevDate(calendar.getTime(), 1);
	}

	public static Date getLastDayOfQuarter(int year, int quarter) {
		int month = 0;
		if (quarter > 4) {
			return null;
		}
		month = quarter * 3;

		return getLastDayOfMonth(year, month);
	}

	public static Date getLastDayOfYear(int year) {
		return getLastDayOfMonth(year, 12);
	}

	public static Date getNextDate(Date date, int next) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, day + next);
		return calendar.getTime();
	}

	public static String getNextDateString(Date date, int next, String partten) {
		return getDateString(getNextDate(date, next), partten);
	}

	public static Date getNextYear(Date currentDate, int next) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(currentDate);
		cal.add(Calendar.YEAR, next);
		return cal.getTime();
	}

	public static String getNextYearString(Date currentDate, int next) {
		Date nextYear = getNextYear(currentDate, next);
		return getDateString(nextYear, "yyyy");
	}

	public static Date getPrevDate(Date date, int Prev) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, day - Prev);
		return calendar.getTime();
	}

	public static String getPrevDateString(Date date, int Prev, String partten) {
		return getDateString(getPrevDate(date, Prev), partten);
	}

	public static Date getWeekFirstDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - dayOfWeek + 2);
		return calendar.getTime();
	}

	public static Date getWeekLastDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - dayOfWeek + 2);
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 6);
		return calendar.getTime();
	}

	public static String chineseDateStr() {
		return new SimpleDateFormat(FORMAT_YMDHMS3).format(new Date());
	}

	public static String getCurrTimeStr() {
		return getDateString(DEFAULT_DATE_FORMAT);
	}

	public static long getTimeDifference(long statrTime, long endTime) {
		return endTime - statrTime;
	}
}
