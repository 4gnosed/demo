/*
 * Copyright 2007 HUAPU (http://www.huapu.com) 
 */
package com.example.demo.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil
{
	public static final String PATTERN_DATE = "yyyy-MM-dd";
	public static final String PATTERN_DATE_TWO = "yyyyMMdd";
	public static final String PATTERN_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
	public static final String PATTERN_DATE_TIME_ONE = "yyyy-MM-dd'T'HH:mm:ss";
	public static final String PATTERN_DATE_TIME_FOUR = "yyyy-MM-dd HH:mm";
	public static final String PATTERN_DATE_TIME_NO_BLANK = "yyyyMMddHHmmss";
	public static final String PATTERN_DATE_TIME_TWO = "MMddHHmmss";
	public static final String PATTERN_DATE_TIME_THREE = "yyyyMMdd HH:mm:ss";
	public static final String PATTERN_DATE_TIME_MILL = "yyyy-MM-dd HH:mm:ss.SSS";
	public static final String PATTERN_DATE_TIME_MILL_TWO = "yyyyMMddHHmmssSSS";

	public static final String PATTERN_MONTH = "yyyy-MM";
	public static final String PATTERN_MONTH_TWO = "yyyyMM";
	
	public static final String PATTERN_TIME = "HH:mm:ss";
	public static final String PATTERN_TIME_TWO = "HHmmss";
	public static final String PATTERN_TIME_THREE = "HH:mm";

	private static final Integer TIME_UNIT_MILLSECOND = 1000;
	private static final Integer TIME_UNIT_MINUTE = TIME_UNIT_MILLSECOND * 60;
	private static final Integer TIME_UNIT_HOUR = TIME_UNIT_MINUTE * 60;
	private static final Integer TIME_UNIT_DAY = TIME_UNIT_HOUR * 24;

	public static String getSysDate()
	{
		return getDateStr(PATTERN_DATE);
	}
	
	public static Integer getCuSysDate(){
		return Integer.parseInt(getDateStr(PATTERN_DATE_TWO));
	}

	public static Integer getCuSysDate(Date date){
		return Integer.parseInt(getDateStr(date, PATTERN_DATE_TWO));
	}
	
	public static String getSysDateTimeStr(){
		return getDateStr(PATTERN_DATE_TIME_TWO);
	}
	
	public static String getSysDateTimeMillisForBussNo(){
		return getDateStr(PATTERN_DATE_TIME_MILL_TWO);
	}
	
	public static String getSysDateTime() {
		return getDateStr(PATTERN_DATE_TIME);
	}
	
	/**
	 * 获取当前日期 yyyyMMddHHmmss的字符串
	 */
	public static String getSysDateTimeNoblank() {
	        return getDateStr(PATTERN_DATE_TIME_NO_BLANK);
	}
	   
	public static String getSysDateTime(Date date) {
		return getDateStr(date, PATTERN_DATE_TIME);
	}
	
	public static String getSysDateTime(long dateMill) {
		return getDateStr(new Date(dateMill), PATTERN_DATE_TIME);
	}

	public static String getSysDateTimeMillis()
	{
		return getDateStr(PATTERN_DATE_TIME_MILL);
	}

	public static String getDateStr(String pattern) {
		return format(new Date(), pattern);
	}

	public static String getDateStr(Date date, String pattern) {
		return format(date, pattern);
	}

	public static String getDateTimeStr(Date date) {
		return format(date, PATTERN_DATE_TIME);
	}

	public static String getDateTimeMillStr(Date date) {
		return format(date, PATTERN_DATE_TIME_MILL);
	}

	public static String format(Date date, String pattern)
	{
		if (date == null) {
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.format(date);
	}
	/**
	 * 按照格式返回日期
	 * 
	 * @param aDateStr
	 */
	public static Date parseFormatDate(String aDateStr)
	{
		return parseFormatDate(aDateStr, PATTERN_DATE);
	}
	
	/**
	 * 返回时间 
	 * @param aDateStr
	 * @return
	 */
	public static Date parseFormatDateTime(String aDateStr)
	{
		return parseFormatDate(aDateStr, PATTERN_DATE_TIME);
	}
	
	/**
     * 返回时间 
     * @param aDateStr
     * @return
     */
    public static Date parseFormatDateTimeNoBlanks(String aDateStr)
    {
        return parseFormatDate(aDateStr, PATTERN_DATE_TIME_NO_BLANK);
    }

    /**
     * 返回时间 
     * @param aDateStr
     * @return
     */
    public static Date parseFormatDateTimeThree(String aDateStr)
    {
        return parseFormatDate(aDateStr, PATTERN_DATE_TIME_THREE);
    }
    
    

	/**
	 * 按照格式返回日期
	 * 
	 * @param aDateStr
	 * @param pattern
	 */
	public static Date parseFormatDate(String aDateStr, String pattern)
	{
		SimpleDateFormat smt = new SimpleDateFormat(pattern);
		Date ret;
		if (aDateStr == null || aDateStr.equals("")) {
			return null;
		}
		try
		{
			ret = smt.parse(aDateStr.trim());
		}
		catch (ParseException e)
		{
			e.printStackTrace();
			return null;
		}
		return ret;
	}
	/**
	 * 按照格式返回日期
	 *
	 * @param aDateStr
	 * @param pattern
	 */
	public static String formatParseDate(String aDateStr, String pattern)
	{
		String reset = null;
		SimpleDateFormat smt = new SimpleDateFormat(pattern);
		Date ret;
		if (aDateStr == null || aDateStr.equals("")) {
			return null;
		}
		try
		{
			ret = smt.parse(aDateStr.trim());
			reset = smt.format(ret);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
			return null;
		}
		return reset;
	}

	/**
	 * 取得指定月份的第一天
	 * 
	 * @param strdate
	 *            String
	 * @return String
	 */
	public static String getMonthBegin(String strdate)
	{
		Date date = parseFormatDate(strdate);
		return formatDateByFormat(date, "yy-MM") + "-01";
	}
	public static String getMonthBegin2(String strdate)
	{
		Date date = parseFormatDate(strdate);
		return formatDateByFormat(date, "yyyy-MM") + "-01";
	}

	/**
	 * 取得指定月份的最后一天
	 * 
	 * @param strdate
	 *            String
	 * @return String
	 */
	public static String getMonthEnd(String strdate)
	{
		Date date = parseFormatDate(getMonthBegin(strdate));
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		return formatDate(calendar.getTime());
	}

	public static String getMonthEnd2(String strdate)
	{
		Date date = parseFormatDate(getMonthBegin2(strdate));
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		return formatDate(calendar.getTime());
	}

	/**
	 * 常用的格式化日期
	 * 
	 * @param date
	 *            Date
	 * @return String
	 */
	public static String formatDate(Date date)
	{
		return formatDateByFormat(date, PATTERN_DATE);
	}

	/**
	 * 以指定的格式来格式化日期
	 * 
	 * @param date
	 *            Date
	 * @param format
	 *            String
	 * @return String
	 */
	public static String formatDateByFormat(Date date, String format)
	{
		String result = "";
		if (date != null && !"".equals(date))
		{
			try
			{
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				result = sdf.format(date);
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 得到当月倒数第i天的最后时间,精确到秒
	 * 
	 * @param date
	 */
	public static Date getLastDateOfMonth(Date date, int i)
	{
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DATE);
		try
		{
			c.setTime(sdf.parse(sdf.format(date)));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		c.add(Calendar.MONTH, 1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.add(Calendar.DAY_OF_MONTH, -i);
		c.set(Calendar.HOUR, 0);
		c.add(Calendar.SECOND, -1);
		return c.getTime();
	}

	/**
	 * 得到上月倒数第i天的最后时间,精确到秒
	 * 
	 * @param date
	 */
	public static Date getLastDateOfLastMonth(Date date, int i)
	{
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DATE);
		try
		{
			c.setTime(sdf.parse(sdf.format(date)));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.add(Calendar.DAY_OF_MONTH, -i);
		c.set(Calendar.HOUR, 0);
		c.add(Calendar.SECOND, -1);
		return c.getTime();
	}

	/**
	 * 得到月中的指定某天的日期
	 * 
	 * @param date
	 * @param i
	 * @return
	 */
	public static Date getDateOfMonth(Date date, int i)
	{
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DATE);
		try
		{
			c.setTime(sdf.parse(sdf.format(date)));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		c.set(Calendar.DAY_OF_MONTH, i);
		return c.getTime();
	}

	/**
	 * 得到年中的指定的某月 lu
	 * 
	 * @param date
	 * @param i
	 * @return
	 */
	public static Date getDateOfYear(Date date, int i)
	{
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DATE);
		try
		{
			c.setTime(sdf.parse(sdf.format(date)));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		c.set(Calendar.MONTH, i - 1);
		return c.getTime();
	}

	/**
	 * 得到所给时间当前季度的开始和结束时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date[] getSeason(Date date)
	{
		if (date != null)
		{
			Date[] season = new Date[2];
			try
			{
				int month = Integer.parseInt(new SimpleDateFormat("MM").format(date));
				String year = new SimpleDateFormat("yyyy").format(date);
				switch (month)
				{
					case 1:
					case 2:
					case 3:
						season[0] = new SimpleDateFormat(PATTERN_DATE).parse(year + "-1-1");
						season[1] = new SimpleDateFormat(PATTERN_DATE).parse(year + "-3-31");
						break;
					case 4:
					case 5:
					case 6:
						season[0] = new SimpleDateFormat(PATTERN_DATE).parse(year + "-4-1");
						season[1] = new SimpleDateFormat(PATTERN_DATE).parse(year + "-6-30");
						break;
					case 7:
					case 8:
					case 9:
						season[0] = new SimpleDateFormat(PATTERN_DATE).parse(year + "-7-1");
						season[1] = new SimpleDateFormat(PATTERN_DATE).parse(year + "-9-30");
						break;
					case 10:
					case 11:
					case 12:
						season[0] = new SimpleDateFormat(PATTERN_DATE).parse(year + "-10-1");
						season[1] = new SimpleDateFormat(PATTERN_DATE).parse(year + "-12-31");
				}
			}
			catch (Exception e)
			{
			}
			return season;
		}
		return null;
	}

	/**
	 * 得到所给日期的当月第一天和最后一天,精确到天
	 * 
	 * @param date
	 * @return
	 */
	public static Date[] getMonth(Date date)
	{
		Date[] dates = new Date[2];
		SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DATE);
		Calendar c = Calendar.getInstance();
		try
		{
			c.setTime(sdf.parse(sdf.format(date)));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		c.set(Calendar.DAY_OF_MONTH, 1);
		dates[0] = c.getTime();
		c.add(Calendar.MONTH, 1);
		c.add(Calendar.DAY_OF_MONTH, -1);
		dates[1] = c.getTime();
		return dates;
	}

	/**
	 * 得到相差relative天的日期,style决定了 取到的精度
	 * 
	 * @param now
	 * @param relative
	 * @param style
	 * @return
	 */
	public static Date getRelativeDate(Date now, int relative, String style)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(style);
		Calendar c = Calendar.getInstance();
		try
		{
			c.setTime(sdf.parse(sdf.format(now)));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		if (relative != 0)
		{
			c.add(Calendar.DAY_OF_MONTH, relative);
		}
		return c.getTime();
	}

	/**
	 * @param now
	 * @param relative
	 * @return
	 */
	public static Date getRelativeMonth(Date now, int relative)
	{
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		if (relative != 0)
		{
			c.add(Calendar.MONTH, relative);
		}
		return c.getTime();
	}

	/**
	 * @param now
	 * @param relative
	 * @param style
	 * @return
	 */
	public static String getRelativeMonth(Date now, int relative, String style)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(style);
		Calendar c = Calendar.getInstance();
		try
		{
			c.setTime(sdf.parse(sdf.format(now)));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		if (relative != 0)
		{
			c.add(Calendar.MONTH, relative);
		}
		return sdf.format(c.getTime());
	}

	/**
	 * @param now
	 * @param relative
	 * @param style
	 * @return
	 */
	public static String getRelativeMonth(String now, int relative, String style)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(style);
		Calendar c = Calendar.getInstance();
		try
		{
			c.setTime(sdf.parse(now));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		if (relative != 0)
		{
			c.add(Calendar.MONTH, relative);
		}
		return sdf.format(c.getTime());
	}

	/**
	 * 比较两个日期相差多少天,如果要获得较为准确的结果，应将日期规整，即将日期的时分秒设置为0:00点整点，避免2日期时间（时分秒）的差异造成计算偏差。
	 * @param startday	开始时间及较小的时间
	 * @param endday	结束时间及较大的时间
	 * @return
	 */
	public static Integer getIntervalDays(Date startday, Date endday)
	{
		Calendar fromCalendar = Calendar.getInstance();  
        fromCalendar.setTime(startday);  
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);  
        fromCalendar.set(Calendar.MINUTE, 0);  
        fromCalendar.set(Calendar.SECOND, 0);  
        fromCalendar.set(Calendar.MILLISECOND, 0);  
  
        Calendar toCalendar = Calendar.getInstance();  
        toCalendar.setTime(endday);  
        toCalendar.set(Calendar.HOUR_OF_DAY, 0);  
        toCalendar.set(Calendar.MINUTE, 0);  
        toCalendar.set(Calendar.SECOND, 0);  
        toCalendar.set(Calendar.MILLISECOND, 0);  
  
        return (int) ((toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24));
	}

	public static Integer getIntervalMonth(Date startDate, Date endday)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		int s = calendar.get(Calendar.MONTH);
		calendar.setTime(endday);
		return s - calendar.get(Calendar.MONTH);
	}

	public static List<String> getMonthsBetweenTwoDays(String startDateStr, String endDateStr)
	{
		Date startDate = parseFormatDate(startDateStr, "yyyy-MM");
		Date endDate = parseFormatDate(endDateStr, "yyyy-MM");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		List<String> dateList = new ArrayList<String>();
		while (startDate.before(endDate))
		{
			dateList.add(formatDateByFormat(startDate, "yyyyMM"));
			calendar.add(Calendar.MONTH, 1);
			startDate = calendar.getTime();
		}
		dateList.add(formatDateByFormat(endDate, "yyyyMM"));
		return dateList;
	}

	public static List<String> getMonthsBetweenTwoDaysDesc(String startDateStr, String endDateStr)
	{
		Date startDate = parseFormatDate(startDateStr, "yyyy-MM");
		Date endDate = parseFormatDate(endDateStr, "yyyy-MM");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(endDate);
		List<String> dateList = new ArrayList<String>();
		while (endDate.after(startDate))
		{
			dateList.add(formatDateByFormat(endDate, "yyyyMM"));
			calendar.add(Calendar.MONTH, -1);
			endDate = calendar.getTime();
		}
		dateList.add(formatDateByFormat(startDate, "yyyyMM"));
		return dateList;
	}

	/**
	 * yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static int getMonthEndInt(String date)
	{
		int endDayInt = -1;
		String endDay = getMonthEnd(date);
		if (null != endDay && !"".equals(endDay))
		{
			endDayInt = Integer.parseInt(endDay.substring(endDay.lastIndexOf("-") + 1));
		}
		return endDayInt;
	}
	
	/**
	 * 获取指定日期的上个月
	 * @param date
	 * @return
	 */
	public static Date getLastMothOfTheDay(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, -1);//取前一个月的同一天
		return cal.getTime();
	}

	public static void main(String[] args) throws ParseException
	{
		
		String datetime1 ="2020-08-26 16:55";
		String datetime2 ="2020-12-26 16:55";
		
		System.out.println(datetime2.compareTo(datetime1));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String result = sdf.format(addDay(new Date(),4));
		System.out.println(result);
//		Date lastToDay=DateUtil.addMonth(new Date(),-1);
//		
//		
////		Date lastToDay=DateUtil.getLastToDay(new Date());
//		String lastToDayStr=DateUtil.formatDateByFormat(lastToDay,DateUtil.PATTERN_DATE_TIME_FOUR);
//		Date lastToDay1 = DateUtil.parseFormatDate("2020-08-26 16:55", DateUtil.PATTERN_DATE_TIME_FOUR);
////		lastToDayStr=lastToDayStr+" 00:00:00";
//		System.out.println(lastToDayStr);
//		System.out.println("1111111111"+lastToDay1);

		// System.out.println(getMonthsBetweenTwoDaysDesc("2010-06","2010-09"));
		
//		System.out.println(parseFormatDate("20161231235958", "yyyyMMddHHmmss"));
//		System.out.println(getRelativeMonth(new Date(), -4, "yyyy-MM"));
//		System.out.println(getRelativeMonth(new Date(), -1, "yyyy-MM"));
//		System.out.println(getLastDateOfLastMonth(new Date(), 0));
//		System.out.println(getMonthEndInt("2010-02-01"));
//		System.out.println(formatDateByFormat((parseFormatDateTime("2010-02-01 10:59:00")),"yyyyMMddHHmmss"));
	}


	/**
	 * 上一天
	 */
	public static Date getLastDay(Date date)
	{
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DATE);
		try
		{
			c.setTime(sdf.parse(sdf.format(date)));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		c.add(Calendar.DAY_OF_MONTH, -1);
		return c.getTime();
	}

	/**
	 * 上两天
	 */
	public static Date getLastSecondDay(Date date)
	{
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DATE);
		try
		{
			c.setTime(sdf.parse(sdf.format(date)));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		c.add(Calendar.DAY_OF_MONTH, -3);
		return c.getTime();
	}
	/**
	 * 上两天
	 */
	public static Date getLastToDay(Date date)
	{
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DATE);
		try
		{
			c.setTime(sdf.parse(sdf.format(date)));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		c.add(Calendar.DAY_OF_MONTH, -2);
		return c.getTime();
	}
	
	public static Date getLastNumToDay(Date date, int num)
	{
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DATE);
		try
		{
			c.setTime(sdf.parse(sdf.format(date)));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		c.add(Calendar.DAY_OF_MONTH, num);
		return c.getTime();
	}
	/**
	 * 下一天
	 */
	public static Date getNextDay(Date date)
	{
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DATE);
		try
		{
			c.setTime(sdf.parse(sdf.format(date)));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		c.add(Calendar.DAY_OF_MONTH, 1);
		return c.getTime();
	}


	/**
	 * 下三天
	 */
	public static Date getNextThreeDay(Date date)
	{
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DATE);
		try
		{
			c.setTime(sdf.parse(sdf.format(date)));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		c.add(Calendar.DAY_OF_MONTH, 3);
		return c.getTime();
	}
	/**
	 * 日期转换成命令字符串
	 */
	public static String dateToString(String date)
	{
		String[] dateArr = date.split("-");
		int m = Integer.parseInt(dateArr[1]);
		StringBuffer result = new StringBuffer();
		switch (m)
		{
			case 1:
				result.append(dateArr[2]).append("JAN");
				break;
			case 2:
				result.append(dateArr[2]).append("FEB");
				break;
			case 3:
				result.append(dateArr[2]).append("MAR");
				break;
			case 4:
				result.append(dateArr[2]).append("APR");
				break;
			case 5:
				result.append(dateArr[2]).append("MAY");
				break;
			case 6:
				result.append(dateArr[2]).append("JUN");
				break;
			case 7:
				result.append(dateArr[2]).append("JUL");
				break;
			case 8:
				result.append(dateArr[2]).append("AUG");
				break;
			case 9:
				result.append(dateArr[2]).append("SEP");
				break;
			case 10:
				result.append(dateArr[2]).append("OCT");
				break;
			case 11:
				result.append(dateArr[2]).append("NOV");
				break;
			case 12:
				result.append(dateArr[2]).append("DEC");
				break;
			default:
				break;
		}
		return result.toString();
	}

	/**
	 * 获取当前年份 月份 日期
	 */
	public static int getCurrentMonth()
	{
		Calendar c = Calendar.getInstance();
		int month = c.get(Calendar.MONTH) + 1;
		return month;
	}
	//获取指定日期的月份
	public static int getCurrentMonth(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH) + 1;
        return month;
    }

	public static String getCostTime(long startTime, long endTime) {
		return getCostTime(endTime - startTime);
	}
	
	public static String getCostTime(Date startTime, Date endTime) {
		return getCostTime(startTime.getTime(), endTime.getTime());
	}
	
	/* 
	 * 毫秒转化时分秒毫秒 
	 */  
	public static String getCostTime(Long ms) {

		Long day = ms / TIME_UNIT_DAY;
		Long hour = (ms - day * TIME_UNIT_DAY) / TIME_UNIT_HOUR;
		Long minute = (ms - day * TIME_UNIT_DAY - hour * TIME_UNIT_HOUR) / TIME_UNIT_MINUTE;
		Long second = (ms - day * TIME_UNIT_DAY - hour * TIME_UNIT_HOUR - minute * TIME_UNIT_MINUTE) / TIME_UNIT_MILLSECOND;
		Long milliSecond = ms - day * TIME_UNIT_DAY - hour * TIME_UNIT_HOUR - minute * TIME_UNIT_MINUTE - second * TIME_UNIT_MILLSECOND;

		StringBuilder sb = new StringBuilder();
		if (day > 0) {
			sb.append(day + "天");
		}
		if (hour > 0) {
			sb.append(hour + "小时");
		}
		if (minute > 0) {
			sb.append(minute + "分");
		}
		if (second > 0) {
			sb.append(second + "秒");
		}
		if (milliSecond > 0) {
			sb.append(milliSecond + "毫秒");
		}
		return sb.toString();
	}
	
	//获取每月1号
    public static Date getFirstDayOfMonth(Date date,int num){
        
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);                
        rightNow.add(Calendar.MONTH,num);
        rightNow.set(Calendar.DAY_OF_MONTH, 1);
        return rightNow.getTime();
        
    }
    //获取周一
    public static Date getFirstDayOfWeek(Date date,int num){
        
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);                
        rightNow.add(Calendar.DAY_OF_WEEK_IN_MONTH ,num);
        rightNow.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return rightNow.getTime();
        
    }
    //获取每月16号   
    public static Date getMidDayOfMonth(Date date,int num){
        
        Calendar rightNow = Calendar.getInstance();
        
        rightNow.set(Calendar.DAY_OF_MONTH, 16);
        rightNow.set(Calendar.HOUR_OF_DAY,0);
        rightNow.set(Calendar.MINUTE, 0); 
        rightNow.set(Calendar.SECOND, 0); 
        rightNow.set(Calendar.MILLISECOND, 0);
        if(date.compareTo(rightNow.getTime())<0){            
        }else{
            rightNow.setTime(getFirstDayOfMonth(date,num));
        }
                    
        return rightNow.getTime();
        
    }
    /**
     * 获取num之后的日期
     * @param date
     * @param num
     * @return
     */
    public static Date addDays(Date date,int num){
        
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);       
         
        rightNow.add(Calendar.DAY_OF_YEAR,num);//
        
        return rightNow.getTime();
        
    }

    /**
     * 按月加
     * 
     * @param value
     * @return
     */
    public static final Date addMonth(Date date, int value) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.add(Calendar.MONTH, value);
        return now.getTime();
    }

    
    /**
     * 按秒加
     * 
     * @param value
     * @return
     */
    public static final Date addSecond(Date date, int value) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.add(Calendar.SECOND, value);
        return now.getTime();
    }
    
    /**
	 * 以指定的格式来格式化日期
	 * 
	 * @param date(毫秒)
	 *            String
	 * @param format
	 *            String
	 * @return String
	 */
	public static String formatDateByFormatMillisecond(String date, String format){
		//将毫秒转化为String格式的时间
		String dateStr = getSysDateTime(Long.parseLong(date));
		//将String类型转化为Date类型
		Date rightDate = (parseFormatDateTime(dateStr));
		//将Date类型的时间转化为特定格式的String类型
        return formatDateByFormat(rightDate,format);
        
    }
	

    public static String parseToDateStr(String str){
        String reg = "(\\d{4})(\\d{2})(\\d{2})(\\d{2})(\\d{2})(\\d{2})";
        return str.replaceAll(reg, "$1-$2-$3 $4:$5:$6");
    }
    
    /**
     * Date日期转换成String
     * 
     */
    public static final String date2Stringyyyymmdd(Date date) {
        if (date == null) {
            return "";
        }
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        return df.format(date);
    }
    /**
     * 按日加,指定日期
     * 
     * @param date
     * @param value
     * @return
     */
    public static final Date addDay(Date date, int value) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.add(Calendar.DAY_OF_YEAR, value);
        return now.getTime();
    }
    /**
     * 按日加,指定日期
     * 
     * @param date
     * @param value
     * @return
     */
    public static final Date addYear(Date date, int value) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.add(Calendar.YEAR, value);
        return now.getTime();
    }
    /**
     * 取一个日期，获取这一天是这个周的哪一天
     * @param date
     * @return
     */
    public static String getWeekOfDate(Date date) {
        String[] weekDays = {"7", "1", "2", "3", "4", "5", "6"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
			w = 0;
		}
        return weekDays[w];
    }

    /**
     * 返回指定日期格式的字符串 如：yyyyMMdd,yyyyMMddhhmmss
     * @param date
     * @param format
     * @return
     */
    public static String getDateFormat(Date date,String format){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }
    /**
     * String日期转换成Date(yyyymmdd)
     * 
     */
    public static final Date string2Dateyyyymmdd(String dateStr) {
        if (dateStr == null || dateStr.length() == 0)
            return null;
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        try {
            return df.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    /**
     * 根据 年、月 获取对应的月份 的 天数
     */
    public static int getDaysByYearMonth(int year, int month)
    {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

	/**
	 * 在一个日期的基础上加23小时59分59秒
	 *
	 */
	public static final Date addToThisDaysFinallySecond(Date date) {
		if(date==null){
			return null;
		}
		return new Date(date.getTime()+86400000-1000);
	}


	public static int getDays(String newDate, String oldDate) {
		//log.info("data1:{},date2:{}",newDate,oldDate);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = null;
		Date date2 = null;
		int a=0;
		try {
			date1 = format.parse(newDate);
			date2= format.parse(oldDate);
			a = (int) ((date1.getTime() - date2.getTime()) / (1000*3600*24));
			return a;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}
	/***
	 *
	 * @param date
	 * @param dateFormat : e.g:yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String formatDateByPattern(Date date,String dateFormat){
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		String formatTimeStr = null;
		if (date != null) {
			formatTimeStr = sdf.format(date);
		}
		return formatTimeStr;
	}

	public static String getCron(Date  date){
		String dateFormat="ss mm HH dd MM ? yyyy";
		return formatDateByPattern(date, dateFormat);
	}
	
	
	

}
