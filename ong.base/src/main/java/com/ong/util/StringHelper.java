package com.ong.util;

/**
 * 对String类的帮助类
 * 
 * @Description: String类的帮助类
 * @Author: Ong
 * @CreateDate: 2017-10-26 17:00:00
 * @E-mail: 865208597@qq.com
 */
public class StringHelper {
	
//	private static Logger logger = LoggerFactory.getLogger(StringHelper.class);

	private static final char LOWA = 'a';

	private static final char LOWZ = 'z';

	private static final char UPPERA = 'A';

	private static final char UPPERZ = 'X';

	public static final String EMPTYSTR = "";

	/**
	 * 首字母大写
	 * 
	 * @param str
	 * @return
	 */
	public static String upperFirstCase(String str) {
		char[] ch = str.toCharArray();
		if (ch[0] >= LOWA && ch[0] <= LOWZ) {
			ch[0] = (char) (ch[0] - 32);
		}
		return new String(ch);
	}

	/**
	 * 首字母小写
	 * 
	 * @param str
	 * @return
	 */
	public static String lowerFirstCase(String str) {
		char[] ch = str.toCharArray();
		if (ch[0] >= UPPERA && ch[0] <= UPPERZ) {
			ch[0] = (char) (ch[0] + 32);
		}
		return new String(ch);
	}

	/**
	 * 重复字符串
	 * 
	 * @param src
	 *            元数据
	 * @param repeats
	 *            重复次数
	 * @return
	 */
	public static String repeatStr(String src, int repeats) {
		if (src == null || repeats <= 0) {
			return src;
		}

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < repeats; i++) {
			sb.append(src);
		}

		return sb.toString();
	}

	/**
	 * 在数据源左侧填充指定次数字符串
	 * 
	 * @param src
	 *            源字符串
	 * @param repeats
	 *            指定次数
	 * @param single
	 *            指定字符串
	 * @return
	 */
	public static String leftPadStr(String src, int repeats, String single) {
		if (repeats <= 0) {
			return src;
		}
		String repeatStr = repeatStr(single, repeats);
		StringBuffer sb = new StringBuffer(repeatStr);
		sb.append(src);
		return sb.toString();
	}

	/**
	 * 在数据源右侧填充指定次数字符串
	 * 
	 * @param src
	 *            源字符串
	 * @param repeats
	 *            指定次数
	 * @param single
	 *            指定字符串
	 * @return
	 */
	public static String rightPadStr(String src, int repeats, String single) {
		if (repeats <= 0) {
			return src;
		}
		StringBuffer sb = new StringBuffer(src);
		String repeatStr = repeatStr(single, repeats);
		sb.append(repeatStr);
		return sb.toString();
	}

	public static boolean isEmpty(String s) {
		return (s == null) || (s.equals("")) || (s.toUpperCase().equals("NULL")) || (s.equals("undefined"));
	}

	public static boolean isEmpty(Object s) {
		return (s == null) || (isEmpty(s.toString()));
	}
	
}
