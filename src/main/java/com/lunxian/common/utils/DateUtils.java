package com.lunxian.common.utils;

import org.apache.commons.lang.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 日期处理
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月21日 下午12:53:33
 */
public class DateUtils {
	/** 时间格式(yyyy-MM-dd) */
	public final static String DATE_PATTERN = "yyyy-MM-dd";
	/** 时间格式(yyyy-MM-dd HH:mm:ss) */
	public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    public static String format(Date date, String pattern) {
        if(date != null){
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }
     private static String[] parsePatterns = {
         "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
         "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
         "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

     /**
      * 得到当前日期字符串 格式（yyyy-MM-dd）
      */
     public static String getDate() {
          return getDate("yyyy-MM-dd");
     }

     /**
      * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
      */
     public static String getDate(String pattern) {
          return DateFormatUtils.format(new Date(), pattern);
     }

     /**
      * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
      */
     public static String formatDate(Date date, Object... pattern) {
          String formatDate = null;
          if (pattern != null && pattern.length > 0) {
               formatDate = DateFormatUtils.format(date, pattern[0].toString());
          } else {
               formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
          }
          return formatDate;
     }

     /**
      * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
      */
     public static String formatDateTime(Date date) {
          return formatDate(date, "yyyy-MM-dd HH:mm:ss");
     }

     /**
      * 得到当前时间字符串 格式（HH:mm:ss）
      */
     public static String getTime() {
          return formatDate(new Date(), "HH:mm:ss");
     }

     /**
      * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
      */
     public static String getDateTime() {
          return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
     }

     /**
      * 得到当前年份字符串 格式（yyyy）
      */
     public static String getYear() {
          return formatDate(new Date(), "yyyy");
     }

     /**
      * 得到当前月份字符串 格式（MM）
      */
     public static String getMonth() {
          return formatDate(new Date(), "MM");
     }

     /**
      * 得到当天字符串 格式（dd）
      */
     public static String getDay() {
          return formatDate(new Date(), "dd");
     }

     /**
      * 得到当前星期字符串 格式（E）星期几
      */
     public static String getWeek() {
          return formatDate(new Date(), "E");
     }

 

     /**
      * 获取过去的天数
      *
      * @param date
      * @return
      */
     public static long pastDays(Date date) {
          long t = new Date().getTime() - date.getTime();
          return t / (24 * 60 * 60 * 1000);
     }

     /**
      * 获取过去的小时
      *
      * @param date
      * @return
      */
     public static long pastHour(Date date) {
          long t = new Date().getTime() - date.getTime();
          return t / (60 * 60 * 1000);
     }

     /**
      * 获取过去的分钟
      *
      * @param date
      * @return
      */
     public static long pastMinutes(Date date) {
          long t = new Date().getTime() - date.getTime();
          return t / (60 * 1000);
     }

     /**
      * 转换为时间（天,时:分:秒.毫秒）
      *
      * @param timeMillis
      * @return
      */
     public static String formatDateTime(long timeMillis) {
          long day = timeMillis / (24 * 60 * 60 * 1000);
          long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
          long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
          long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
          long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
          return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "." + sss;
     }

     /**
      * 获取两个日期之间的天数
      *
      * @param before
      * @param after
      * @return
      */
     public static double getDistanceOfTwoDate(Date before, Date after) {
          long beforeTime = before.getTime();
          long afterTime = after.getTime();
          return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
     }

     /**
      * 获取当年的第一天
      *
      * @return
      */
     public static Date getCurrYearFirst() {
          Calendar currCal = Calendar.getInstance();
          int currentYear = currCal.get(Calendar.YEAR);
          return getYearFirst(currentYear);
     }

     /**
      * 获取当年的最后一天
      *
      * @return
      */
     public static Date getCurrYearLast() {
          Calendar currCal = Calendar.getInstance();
          int currentYear = currCal.get(Calendar.YEAR);
          return getYearLast(currentYear);
     }

     /**
      * 获取某年第一天日期
      *
      * @param year 年份
      * @return Date
      */
     public static Date getYearFirst(int year) {
          Calendar calendar = Calendar.getInstance();
          calendar.clear();
          calendar.set(Calendar.YEAR, year);
          Date currYearFirst = calendar.getTime();
          return currYearFirst;
     }

     /**
      * 获取某年最后一天日期
      *
      * @param year 年份
      * @return Date
      */
     public static Date getYearLast(int year) {
          Calendar calendar = Calendar.getInstance();
          calendar.clear();
          calendar.set(Calendar.YEAR, year);
          calendar.roll(Calendar.DAY_OF_YEAR, -1);
          Date currYearLast = calendar.getTime();

          return currYearLast;
     }

     /**
      * 获取当月的第一天
      *
      * @return
      */
     public static Date getCurrMonthFirst() {
          Calendar calendar = Calendar.getInstance();
          calendar.set(Calendar.DAY_OF_MONTH, calendar
              .getActualMinimum(Calendar.DAY_OF_MONTH));
          Date currYearLast = calendar.getTime();
          return currYearLast;
     }

     /**
      * 获取当月的最后一天
      *
      * @return
      */
     public static Date getCurrMonthLast() {
          Calendar calendar = Calendar.getInstance();
          calendar.set(Calendar.DAY_OF_MONTH, calendar
              .getActualMaximum(Calendar.DAY_OF_MONTH));
          Date currYearLast = calendar.getTime();
          return currYearLast;
     }

     /**
      * 根据开始时间和结束时间返回时间段内的时间集合
      *
      * @param beginDate
      * @param endDate
      * @return List
      */
     public static List<Date> getDatesBetweenTwoDate(Date beginDate, Date endDate) {
          List<Date> lDate = new ArrayList<Date>();
          if (beginDate.compareTo(endDate) == 0) {
               lDate.add(beginDate);
               return lDate;
          }
          lDate.add(beginDate);// 把开始时间加入集合
          Calendar cal = Calendar.getInstance();
          // 使用给定的 Date 设置此 Calendar 的时间
          cal.setTime(beginDate);
          boolean bContinue = true;
          while (bContinue) {
               // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
               cal.add(Calendar.DAY_OF_MONTH, 1);
               // 测试此日期是否在指定日期之后
               if (endDate.after(cal.getTime())) {
                    lDate.add(cal.getTime());
               } else {
                    break;
               }
          }
          lDate.add(endDate);// 把结束时间加入集合
          return lDate;
     }

     /**
      * 天数+1
      *
      * @param date
      * @return
      */
     public static Date dateGoDay(Date date) {
          Calendar c = Calendar.getInstance();
          c.setTime(date);
          c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
          Date tomorrow = c.getTime();
          return tomorrow;
     }


     /**
      * 天数 -1
      *
      * @param date
      * @return
      */
     public static Date dateRmDay(Date date) {
          Calendar c = Calendar.getInstance();
          c.setTime(date);
          c.add(Calendar.DAY_OF_MONTH, -1);// 今天-1天
          Date tomorrow = c.getTime();
          return tomorrow;
     }

     /**
      * @param args
      * @throws ParseException
      */
     public static void main(String[] args) throws ParseException {
          dateGoDay(new Date());

          System.out.println(dateGoDay(new Date()) + "" + dateRmDay(new Date()));
          // System.out.println(formatDate(getCurrMonthFirst()) + "-" + formatDate(getCurrMonthLast()));
          //System.out.print(formatDate(getCurrYearFirst())+"-"+formatDate(getCurrYearLast()));
//		System.out.println(formatDate(parseDate("2010/3/6")));
//		System.out.println(getDate("yyyy年MM月dd日 E"));
//		long time = new Date().getTime()-parseDate("2012-11-19").getTime();
//		System.out.println(time/(24*60*60*1000));
     }
}
