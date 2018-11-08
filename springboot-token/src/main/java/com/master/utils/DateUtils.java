package com.master.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtils {
	private final static Logger logger = LoggerFactory.getLogger(DateUtils.class);
	private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");

	private final static SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");

	private final static SimpleDateFormat sdfMonth = new SimpleDateFormat("yyyy-MM-dd");

	private final static SimpleDateFormat sdfDays = new SimpleDateFormat("yyyyMMdd");

	private final static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private final static SimpleDateFormat sdfTimeHHMM = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	/**
	 * 获取当前系统时间毫秒数
	 * @return
	 */
	public static long GetSystemTime() {
		return new Date().getTime();
	}

	/**
	 * 获取YYYY格式
	 * 
	 * @return
	 */
	public static String getYear() {
		return sdfYear.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD格式
	 * 
	 * @return
	 */
	public static String getDay() {
		return sdfDay.format(new Date());
	}

	/**
	 * 获取YYYYMMDD格式
	 * 
	 * @return
	 */
	public static String getDays() {
		return sdfDays.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD HH:mm:ss格式
	 * 
	 * @return
	 */
	public static String getTime() {
		return sdfTime.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD HH:mm格式
	 * 
	 * @return
	 */
	public static String getHHMMTime() {
		return sdfTimeHHMM.format(new Date());
	}

	/**
	* @Title: compareDate
	* @Description:  (日期比较，如果s>=e 返回true 否则返回false)
	* @param s
	* @param e
	* @return boolean  
	* @throws
	* @author luguosui
	 */
	public static boolean compareDate(String s, String e) {
		if (fomatDate(s, null) == null || fomatDate(e, null) == null) {
			return false;
		}
		return fomatDate(s, null).getTime() >= fomatDate(e, null).getTime();
	}

	/**
	 * 格式化日期
	 * 
	 * @return
	 */
	public static Date fomatDate(String date, String pattern) {
		if (pattern == null) {
			pattern = "yyyy-MM-dd";
		}
		DateFormat fmt = new SimpleDateFormat(pattern);
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			logger.error("exception-info", e);
			return null;
		}
	}

	/**
	 * 校验日期是否合法
	 * 
	 * @return
	 */
	public static boolean isValidDate(String s) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			fmt.parse(s);
			return true;
		} catch (Exception e) {
			logger.error("exception-info", e);
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return false;
		}
	}

	public static int getDiffYear(String startTime, String endTime) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			int years = (int) (((fmt.parse(endTime).getTime() - fmt.parse(startTime).getTime()) / (1000 * 60 * 60 * 24)) / 365);
			return years;
		} catch (Exception e) {
			logger.error("exception-info", e);
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return 0;
		}
	}

	/**
	* <li>功能描述：时间相减得到天数
	* @param beginDateStr
	* @param endDateStr
	* @return
	* long 
	* @author Administrator
	*/
	public static long getDaySub(String beginDateStr, String endDateStr) {
		long day = 0;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date beginDate = null;
		Date endDate = null;

		try {
			beginDate = format.parse(beginDateStr);
			endDate = format.parse(endDateStr);
		} catch (ParseException e) {
			logger.error("exception-info", e);
		}
		day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
		//logger.debug("相隔的天数="+day);
		return day;
	}

	/**
	 *
	 * @Title: addByDate
	 * @author hp
	 * @date 2016年1月22日 上午10:56:39
	 * @Description: 日期相加
	 * @param srcdate
	 * @param days
	 * @return Date
	 *
	 */
	public static Date addByDate(Date srcdate, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(srcdate);
		calendar.add(Calendar.DATE, days);
		return calendar.getTime();
	}

	/**
	 * 根据指定的小时、分钟、秒速，获取时间
	 * @param date
	 * @param hour
	 * @param minute
	 * @param second
	 * @return
	 */
	public static Date getDateOfHMS(Date date, int hour, int minute, int second){
		try{
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.HOUR_OF_DAY, hour);
			calendar.set(Calendar.MINUTE, minute);
			calendar.set(Calendar.SECOND, second);
			return calendar.getTime();
		}catch (Exception e){
			return null;
		}
	}

	/**
	 * 得到n天之后的日期
	 * @param days
	 * @return
	 */
	public static String getAfterDayDate(String days) {
		int daysInt = Integer.parseInt(days);

		Calendar canlendar = Calendar.getInstance(); // java.util包
		canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
		Date date = canlendar.getTime();

		SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdfd.format(date);

		return dateStr;
	}

	/**
	 * 得到n天之后是周几
	 * @param days
	 * @return
	 */
	public static String getAfterDayWeek(String days) {
		int daysInt = Integer.parseInt(days);

		Calendar canlendar = Calendar.getInstance(); // java.util包
		canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
		Date date = canlendar.getTime();

		SimpleDateFormat sdf = new SimpleDateFormat("E");
		String dateStr = sdf.format(date);

		return dateStr;
	}

	/**
	 * 将字符串型的时间按照定义的格式
	 * 转化为时间戳，long型
	 * @param datestr
	 * @param format
	 * @return
	 */
	public static long dateToLong(String datestr, String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Date date = sdf.parse(datestr);
			return date.getTime();
		} catch (ParseException e) {
			logger.error("exception-info", e);
		}
		return 0;
	}

	/**
	 * 将long转换成指定格式的字符串
	 * @param datelong
	 * @param format
	 * @return
	 */
	public static String longToDate(long datelong, String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Date date = new Date();
			date.setTime(datelong);
			return sdf.format(date);
		} catch (Exception e) {
			return "";
		}

	}

	/**
	 * �����把日期转换成你需要的格式����pattern��ʽ���и�ʽ��
	 * @param d Date
	 * @param pattern String
	 * @return String
	 */
	public static String format(Date d, String pattern) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			return format.format(d);
		} catch (Exception e) {
			return "";
		}
	}

	public static Date parse(String date, String pattern) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			return format.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取两个日期间的月份
	 * @param beginDateStr
	 * @param endDateStr
	 * @return
	 */
	public static List<String> getBetweenMonth(String beginDateStr, String endDateStr) {
		List<String> arrMonth = new ArrayList<String>();
		if (getDaySub(beginDateStr, endDateStr) <= 30)
			return arrMonth;
		Calendar min = Calendar.getInstance();
		Calendar max = Calendar.getInstance();
		min.setTime(fomatDate(beginDateStr, "yyyy-MM"));
		min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);
		max.setTime(fomatDate(endDateStr, "yyyy-MM"));
		max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);
		Calendar curr = min;
		while (curr.before(max)) {
			arrMonth.add(sdfMonth.format(curr.getTime()));
			curr.add(Calendar.MONTH, 1);
		}
		return arrMonth;
	}

	/**
	 * 获取两个日期间的周数
	 * @param beginDateStr
	 * @param endDateStr
	 * @return
	 */
	public static List<String> getBetweenWeek(String beginDateStr, String endDateStr) {
		List<String> arrWeek = new ArrayList<String>();
		SimpleDateFormat sdfWeek = new SimpleDateFormat("E");
		Calendar min = Calendar.getInstance();
		Calendar max = Calendar.getInstance();
		min.setTime(fomatDate(beginDateStr, null));
		max.setTime(fomatDate(endDateStr, null));
		Calendar curr = min;
		while (curr.before(max)) {
			if ("星期日".equals(sdfWeek.format(curr.getTime()))) {
				arrWeek.add(sdfDay.format(curr.getTime()));
			}
			curr.add(Calendar.DATE, 1);
		}
		return arrWeek;
	}

	/**
	 * 获取两个日期间的天数
	 * @param beginDateStr
	 * @param endDateStr
	 * @return
	 */
	public static List<String> getBetweenDay(String beginDateStr, String endDateStr) {
		List<String> arrDay = new ArrayList<String>();
		Calendar min = Calendar.getInstance();
		Calendar max = Calendar.getInstance();
		min.setTime(fomatDate(beginDateStr, null));
		max.setTime(fomatDate(endDateStr, null));
		Calendar curr = min;
		while (curr.before(max)) {
			arrDay.add(sdfDay.format(curr.getTime()));
			curr.add(Calendar.DATE, 1);
		}
		arrDay.add(endDateStr);
		return arrDay;
	}

	/**
	 * 获取指定日期月份最后一天
	 * @param strDate
	 * @return
	 */
	public static String getMonthLastDay(String strDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fomatDate(strDate, null));
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		return sdfDay.format(calendar.getTime());
	}

	public static void main(String[] args) {
		logger.debug(getDays());
		logger.debug(getAfterDayWeek("3"));
		logger.debug(fomatDate(getTime(), "yyyy-MM-dd HH:mm:ss").toString());
		Date currentDate = new Date();
		logger.debug(getDateOfHMS(currentDate, 23,00,00).toString());
	}

}
