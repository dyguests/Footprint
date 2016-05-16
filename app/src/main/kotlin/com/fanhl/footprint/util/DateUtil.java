package com.fanhl.footprint.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by fanhl on 15/8/6.
 */
public class DateUtil {
    public static final String FORMAT_TIMESTAMP = "MMM dd, yyyy HH:mm:ss a";
    public static final String FORMAT_LONG      = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_SHORT     = "yyyy-MM-dd";
    public static final String FORMAT_SHORT2    = "yyyy/MM/dd";
    public static final String FORMAT_NUMBER    = "yyyyMMdd";
    public static final String FORMAT_CN        = "yyyy年MM月dd日 HH:mm:ss";
    public static final String FORMAT_CN_SHORT  = "yyyy年MM月dd日";
    public static final String FORMAT_TIME      = "HH:mm";
    public static final String FORMAT_yyyyMM    = "yyyy-MM";
    public static final String FORMAT_hms       = "HH:mm:ss";
    public static final String FORMAT_LONG_     = "yyyy-MM-dd HH_mm_ss";
    public static final String FORMAT_hms_      = "HH_mm_ss";

    /**
     * templete: FORMAT_STR -> Date
     *
     * @param str
     * @param format
     * @return
     */
    public static Date str2date(String str, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date             date             = null;
        try {
            date = simpleDateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * templete: Date -> FORMAT_STR
     *
     * @param date
     * @param format
     * @return
     */
    public static String date2str(Date date, String format) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern(format);
        return sdf.format(date);
    }

    /**
     * templete: "yyyy-MM-dd HH:mm:ss" -> Date
     *
     * @param str
     * @return
     */
    public static Date long2date(String str) {
        return str2date(str, FORMAT_LONG);
    }

    /**
     * templete: "yyyy-MM-dd" -> Date
     *
     * @param str
     * @return
     */
    public static Date short2date(String str) {
        return str2date(str, FORMAT_SHORT);
    }

    /**
     * templete: "yyyyMMdd" -> Date
     *
     * @param str
     * @return
     */
    public static Date number2date(String str) {
        return str2date(str, FORMAT_NUMBER);
    }

    /**
     * templete: "yyyy年MM月dd日" -> Date
     *
     * @param str
     * @return
     */
    public static Date cnshort2date(String str) {
        return str2date(str, FORMAT_CN_SHORT);
    }

    /**
     * templete: "Sep 15, 2014 12:00:01 AM" -> Date
     *
     * @param str
     * @return
     */
    public static Date gson2date(String str) {
        return str2date(str, FORMAT_TIMESTAMP);
    }

    /**
     * templete: Date -> "yyyy-MM-dd HH:mm:ss"
     *
     * @param date
     * @return
     */
    public static String date2long(Date date) {
        return date2str(date, FORMAT_LONG);
    }

    /**
     * templete: Date -> "Sep 15, 2014 12:00:01 AM"
     *
     * @param date
     * @return
     */
    public static String date2gson(Date date) {
        return date2str(date, FORMAT_TIMESTAMP);
    }

    /**
     * templete: Date -> "yyyy-MM-dd"
     *
     * @param date
     * @return
     */
    public static String date2short(Date date) {
        return date2str(date, FORMAT_SHORT);
    }

    /**
     * templete: String -> "yyyyMMdd"
     *
     * @param date
     * @return
     */
    public static String date2number(Date date) {
        return date2str(date, FORMAT_NUMBER);
    }

    /**
     * templete: String -> "yyyy年MM月dd日 HH:mm:ss"
     *
     * @param date
     * @return
     */
    public static String date2cn(Date date) {
        return date2str(date, FORMAT_CN);
    }

    /**
     * templete: String -> "yyyy年MM月dd日"
     *
     * @param date
     * @return
     */
    public static String date2cnshort(Date date) {
        return date2str(date, FORMAT_CN_SHORT);
    }

    /**
     * templete: String -> "HH:mm:ss"
     *
     * @param date
     * @return
     */
    public static String date2hms(Date date) {
        return date2str(date, FORMAT_hms);
    }


    public static Date addDay(Date date, int step) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, step);
        return calendar.getTime();
    }

    public static Date addMouth(Date date, int step) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, step);
        return calendar.getTime();
    }

    public static Date addYear(Date date, int step) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, step);
        return calendar.getTime();
    }

    /**
     * 取得一个月开始的一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayInMonth(Date date) {
        Date firstDate = null;
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.DAY_OF_MONTH, 1);    //设置为第一天
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            firstDate = new Date(calendar.getTimeInMillis());
        }
        return firstDate;
    }

    /**
     * 取得下一个月开始的一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayInNextMonth(Date date) {
        Date firstDate = null;
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.DAY_OF_MONTH, 1);    //设置为第一天
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);

            calendar.add(Calendar.MONTH, 1);            //月份数增1
            firstDate = new Date(calendar.getTimeInMillis());
        }
        return firstDate;
    }

    /**
     * 取得一个月结束的一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayInMonth(Date date) {
        Date lastDate = null;
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.MONTH, 1);            //月份数增1
            calendar.set(Calendar.DAY_OF_MONTH, 1);    //设置日期设置为月份的1号
            calendar.add(Calendar.DATE, -1);            //日期减1，返回上月的最后一天
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            lastDate = new Date(calendar.getTimeInMillis());
        }
        return lastDate;
    }

    /**
     * 获取当前日期是星期几<br>
     *
     * @param date
     * @return 当前日期是星期几
     */
    public static String getWeekCnOfDate(Date date) {
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal      = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * @param milliseconds
     */
    public static Date second2date(long milliseconds) {
        return new Date(milliseconds);
    }

    public static String date2time(Date date) {
        return date2str(date, FORMAT_TIME);
    }

    /**
     * 是否为当天
     *
     * @param date
     * @return
     */
    public static boolean isToday(Date date) {
        if (date == null) return false;
        Date today = new Date();
        return DateUtil.date2short(today).equals(DateUtil.date2short(date));
    }

    /**
     * fromDate是否大于toDate（最小单位为天）
     *
     * @param fromDate
     * @param toDate
     * @return
     */
    public static boolean isAfterByDay(Date fromDate, Date toDate) {
        if (fromDate == null) return false;
        if (toDate == null) return true;
        Date fromDate2 = short2date(date2short(fromDate));
        Date toDate2   = short2date(date2short(toDate));

        return fromDate2.getTime() > toDate2.getTime();

//        if (fromDate.getYear() > toDate.getYear()) {
//            return true;
//        } else if (fromDate.getYear() < toDate.getYear()) {
//            return false;
//        }else if (fromDate.getMonth() > toDate.getMonth()) {
//            return true;
//        } else if (fromDate.getMonth() < toDate.getMonth()) {
//            return false;
//        }if (fromDate.getDay() > toDate.getDay()) {
//            return true;
//        } else/* if (fromDate.getDay() < toDate.getDay())*/ {
//            return false;
//        }
    }
}
