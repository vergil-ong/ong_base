package com.ong.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.nio.channels.FileChannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 文件帮助类
 * 
 * @Description: 文件帮助类
 * @Author: Ong
 * @CreateDate: 2017-10-27 11:00:00
 * @E-mail: 865208597@qq.com
 */
public class FileHelper {
	
	private static Logger logger = LoggerFactory.getLogger(FileHelper.class);
	
	public static final String DEFAULT_CHARSET = "UTF-8";
	
	/**
	 * 以字节为单位读取文件，常用于读二进制文件，如图片、声音、影像等文件。
	 * 
	 * 以字节为单位读取文件内容，一次读一个字节
	 * 
	 * @param fileName
	 *            文件的名
	 */
	public static String readFileBySingleBytes(String fileName) {
		String resultStr = new String();
		StringBuilder sbuild = new StringBuilder();
		File file = new File(fileName);
		InputStream in = null;
		try {
//			logger.info("以字节为单位读取文件内容，一次读一个字节：");
			// 一次读一个字节
			in = new FileInputStream(file);
			int tempbyte;
			while ((tempbyte = in.read()) != -1) {
				sbuild.append(tempbyte);
			}
			in.close();
		} catch (IOException e) {
			logger.error("IOException is {}", e);
			e.printStackTrace();
			return resultStr;
		}
		resultStr = sbuild.toString();
		return resultStr;
	}

	/**
	 * 以字节为单位读取文件，常用于读二进制文件，如图片、声音、影像等文件。
	 * 
	 * 以字节为单位读取文件内容，一次读多个字节（100）
	 * 
	 * @param fileName
	 *            文件的名
	 */
	public static String readFileByMultipleBytes(String fileName) {
		String resultStr = new String();
		StringBuilder sbuild = new StringBuilder();
		InputStream in = null;
		try {
//			logger.info("以字节为单位读取文件内容，一次读多个字节：");
			// 一次读多个字节
			byte[] tempbytes = new byte[100];
			in = new FileInputStream(fileName);
			showAvailableBytes(in);
			// 读入多个字节到字节数组中，byteread为一次读入的字节数
			while ((in.read(tempbytes)) != -1) {
				sbuild.append(tempbytes);
			}
		} catch (Exception e1) {
			logger.error("Exception is {}", e1);
			e1.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e1) {
					logger.error("IOException is {}", e1);
				}
			}
		}
		resultStr = sbuild.toString();
		return resultStr;
	}
	/**
	 * 以行为单位读取文件，常用于读面向行的格式化文件
	 * 
	 * @param fileName
	 *            文件名
	 */
	public static String readFileByLines(String fileName) {
		return readFileByLines(fileName, DEFAULT_CHARSET);
	}

	/**
	 * 以行为单位读取文件，常用于读面向行的格式化文件
	 * 
	 * @param fileName
	 * @param charset
	 *            文件名
	 */
	public static String readFileByLines(String fileName, String charset) {
		String resultStr = new String();
		StringBuilder sbuild = new StringBuilder();
		File file = new File(fileName);
		
	    if ((!file.exists()) || (!file.isFile()))
	    {
	      logger.warn("读取文件内容错误，文件[{}]不存在", new Object[] { fileName });
	      return sbuild.toString();
	    }
		
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader reader = null;
		try {
//			logger.info("以行为单位读取文件内容，一次读一整行：");
			fis = new FileInputStream(file);
			isr = new InputStreamReader(fis, charset);
			reader = new BufferedReader(isr);
			String tempString = null;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				sbuild.append(tempString);
			}
			reader.close();
			resultStr = sbuild.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e1) {
				}
			}
			if (isr != null) {
				try {
					isr.close();
				} catch (IOException e1) {
				}
			}
		}

		return resultStr;
	}

	/**
	 * 以字符为单位读取文件，常用于读文本，数字等类型的文件 
	 * 
	 * 以字符为单位读取文件内容，一次读一个字节
	 * 
	 * @param fileName
	 *            文件名
	 */
	public static String readFileBySingleChars(String fileName) {
		String resultStr = new String();
		StringBuilder sbuild = new StringBuilder();
		File file = new File(fileName);
		Reader reader = null;
		try {
//			logger.info("以字符为单位读取文件内容，一次读一个字节：");
			// 一次读一个字符
			reader = new InputStreamReader(new FileInputStream(file));
			int tempchar;
			while ((tempchar = reader.read()) != -1) {
				// 对于windows下，rn这两个字符在一起时，表示一个换行。
				// 但如果这两个字符分开显示时，会换两次行。
				// 因此，屏蔽掉r，或者屏蔽n。否则，将会多出很多空行。
				if (((char) tempchar) != 'r') {
					sbuild.append((char) tempchar);
				}
			}
			reader.close();
			resultStr = sbuild.toString();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception {}", e);
		}
		return resultStr;
	}

	/**
	 * 以字符为单位读取文件，常用于读文本，数字等类型的文件
	 * 
	 *  以字符为单位读取文件内容，一次读多个字节（30）
	 * 
	 * @param fileName
	 *            文件名
	 */
	public static String readFileByMultipleChars(String fileName) {
		String resultStr = new String();
		StringBuilder sbuild = new StringBuilder();
		Reader reader = null;
		try {
//			logger.info("以字符为单位读取文件内容，一次读多个字节：");
			// 一次读多个字符
			char[] tempchars = new char[30];
			int charread = 0;
			reader = new InputStreamReader(new FileInputStream(fileName));
			// 读入多个字符到字符数组中，charread为一次读取字符数
			while ((charread = reader.read(tempchars)) != -1) {
				// 同样屏蔽掉r不显示
				if ((charread == tempchars.length) && (tempchars[tempchars.length - 1] != 'r')) {
					sbuild.append(tempchars);
				} else {
					for (int i = 0; i < charread; i++) {
						if (tempchars[i] == 'r') {
							continue;
						} else {
							sbuild.append(tempchars[i]);
						}
					}
				}
			}
			resultStr = sbuild.toString();
		} catch (Exception e1) {
			logger.error("Exception {}", e1);
			e1.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}

		return resultStr;
	}

	/**
	 * 显示输入流中还剩的字节数
	 * 
	 * @param in
	 */
	private static void showAvailableBytes(InputStream in) {
		try {
			logger.info("当前字节输入流中的字节数为:" + in.available());
		} catch (IOException e) {
			logger.error("IOException is {}", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * 写入指定文件
	 * 
	 * @param fileName
	 * @param content
	 * @param charset
	 */
	public static void writeContent(String fileName, String content) {
		writeContent(fileName, content, "utf-8");
	}

	/**
	 * 写入指定文件
	 * 
	 * @param fileName
	 * @param content
	 * @param charset
	 */
	public static void writeContent(String fileName, String content, String charset) {
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		PrintWriter out = null;
		String error = null;
		try {
			verifyDirectory(fileName, false, true);
			fos = new FileOutputStream(fileName);
			osw = new OutputStreamWriter(fos, charset);
			out = new PrintWriter(osw);
			out.print(content);
			logger.debug("写入文件内容成功[{}]", new Object[] { fileName });
			return;
		} catch (Exception e) {
			error = "写入文件内容错误[{}]";
			logger.error(error, new Object[] { fileName, e });
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (osw != null) {
					osw.close();
				}
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
				error = "关闭OutputStreamWriter错误[{}]";
				logger.error(error, new Object[] { fileName, e });
			}
		}
	}

	public static boolean verifyDirectory(String path, boolean isDirectory, boolean autoCreate) {
		File file = new File(path);
		return verifyDirectory(file, isDirectory, autoCreate);
	}

	public static boolean verifyDirectory(File file, boolean isDirectory, boolean autoCreate) {
		if (isDirectory) {
			if (file.isDirectory()) {
				return true;
			}
			if (autoCreate) {
				file.mkdirs();
				return true;
			}
			return false;
		}
		return verifyDirectory(file.getParentFile(), true, autoCreate);
	}

	/**
	 * 删除指定文件
	 * 
	 * @param fileName
	 */
	public static void remove(String fileName) {
		try {
			File file = new File(fileName);
			if (file.isDirectory()) {
				String[] filelist = file.list();
				for (int i = 0; i < filelist.length; i++) {
					File delfile = new File(fileName + File.separator + filelist[i]);
					if (delfile.isDirectory()) {
						remove(fileName + File.separator + filelist[i]);
					} else {
						delfile.delete();
						logger.debug("文件删除成功[{}{}{}]", new Object[] { fileName, File.separator, delfile.getName() });
					}
				}
			}
			file.delete();
			logger.debug("文件夹删除成功[{}]", new Object[] { fileName });
		} catch (Exception e) {
			String error = "删除文件（夹）错误[{}]";
			logger.error(error, new Object[] { fileName, e });
		}
	}

	public static boolean copyTo(String source, String target) {
		File srcFile = new File(source);
		return copyTo(srcFile, target);
	}

	public static boolean copyTo(File srcFile, String target) {
		if (!srcFile.exists()) {
			logger.error("复制文件失败，源文件[{}]不存在！", new Object[] { srcFile.getAbsolutePath() });
			return false;
		}
		if (!srcFile.isFile()) {
			logger.error("复制文件失败，源文件[{}]不是文件！", new Object[] { srcFile.getAbsolutePath() });
			return false;
		}
		File targetFile = new File(target);
		if (targetFile.exists()) {
			deleteFolder(targetFile.getAbsolutePath());
		} else if (!targetFile.getParentFile().exists()) {
			if (!targetFile.getParentFile().mkdirs()) {
				logger.error("复制文件失败，创建目标文件所在目录失败！", new Object[0]);
				return false;
			}
		}
		FileChannel fcin = null;
		FileInputStream fis = null;
		FileChannel fcout = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(srcFile);
			fos = new FileOutputStream(targetFile);
			fcin = fis.getChannel();
			fcout = fos.getChannel();
			fcin.transferTo(0L, fcin.size(), fcout);

			return true;
		} catch (FileNotFoundException e) {
			logger.error("复制文件错误[{} to {}]",
					new Object[] { srcFile.getAbsolutePath(), targetFile.getAbsolutePath(), e });
			return false;
		} catch (IOException e) {
			logger.error("复制文件错误[{} to {}]",
					new Object[] { srcFile.getAbsolutePath(), targetFile.getAbsolutePath(), e });
			return false;
		} finally {
			if (fcout != null) {
				try {
					fcout.close();
				} catch (IOException e) {
					logger.error("关闭FileOutputStream Channel错误[{}]", new Object[] { srcFile.getAbsolutePath(), e });
				}
			}
			if (fcin != null) {
				try {
					fcin.close();
				} catch (IOException e) {
					logger.error("关闭FileInputStream Channel错误[{}]", new Object[] { srcFile.getAbsolutePath(), e });
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					logger.error("关闭FileInputStream 错误[{}]", new Object[] { srcFile.getAbsolutePath(), e });
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					logger.error("关闭FileOutputStream 错误[{}]", new Object[] { target, e });
				}
			}
		}
	}

	private static boolean deleteFolder(String dir) {
		try {
			File file = new File(dir);
			if (!file.isDirectory()) {
				return file.delete();
			}
			String[] filelist = file.list();
			for (int i = 0; i < filelist.length; i++) {
				File delfile = new File(dir + File.separator + filelist[i]);
				if (!delfile.isDirectory()) {
					delfile.delete();
				} else {
					deleteFolder(dir + File.separator + filelist[i]);
				}
			}
			return file.delete();
		} catch (Exception e) {
			logger.error("删除文件（夹）[{}]出错！", new Object[] { dir });
		}
		return false;
	}

	public static boolean moveTo(String source, String target, boolean overlay) {
		File srcFile = new File(source);
		if (!srcFile.exists()) {
			logger.error("移动文件失败，源文件[{}]不存在！", new Object[] { source });
			return false;
		}
		if (!srcFile.isFile()) {
			logger.error("移动文件失败，源文件[{}]不是文件！", new Object[] { source });
			return false;
		}
		File targetFile = new File(target);
		if (targetFile.exists()) {
			if (overlay) {
				remove(targetFile.getAbsolutePath());
			} else {
				logger.error("移动文件失败，目标文件[{}]已存在！", new Object[] { target });
				return false;
			}
		} else if (!targetFile.getParentFile().exists()) {
			if (!targetFile.getParentFile().mkdirs()) {
				logger.error("移动文件失败，创建目标文件所在目录失败！", new Object[0]);
				return false;
			}
		}
		if (srcFile.renameTo(targetFile)) {
			return true;
		}
		logger.error("移动文件从[{}]到[{}]，失败！", new Object[] { source, target });
		return false;
	}
}
