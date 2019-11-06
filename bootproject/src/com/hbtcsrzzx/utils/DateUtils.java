package com.hbtcsrzzx.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.omg.PortableServer.ServantActivator;

import com.sun.xml.internal.ws.api.Cancelable;

public class DateUtils {
	public static String getCurrent() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		Date date = new Date();
		String str = simpleDateFormat.format(date);
		return str;
	}

	public static String getLastDay() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, Calendar.MONTH, Calendar.DATE - 1, 0, 0, 0);
		Date date = calendar.getTime();
		return simpleDateFormat.format(date);
	}

	public static String getNextDay() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, Calendar.MONTH, Calendar.DATE + 1, 0, 0, 0);
		Date date = calendar.getTime();
		return simpleDateFormat.format(date);
	}

	/**
	 * 字符串装格式化日期
	 * @param str
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static Date getStringConvertDate(String str, String format) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		Date date = dateFormat.parse(str);
		return date;

	}

	/**
	 * 日期格式化成字符串
	 * @param date
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static String getDateConvertString(Date date, String format) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		String string = dateFormat.format(date);
		return string;

	}
}
