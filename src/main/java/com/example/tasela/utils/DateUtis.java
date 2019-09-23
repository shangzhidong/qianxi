package com.example.tasela.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author jinmos
 * @date 2019-09-17 11:25
 * 格式化时间工具类
 */
public class DateUtis {

    private static final ThreadLocal<DateFormat> DATE_FORMATTER = new ThreadLocal<DateFormat>(){
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMddHHmmss");
        }
    };

    private static final ThreadLocal<DateFormat> DATE_TIME_FORMATTER = new ThreadLocal<DateFormat>(){
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };
    private static final ThreadLocal<DateFormat> DATE_YMD_FORMATTER = new ThreadLocal<DateFormat>(){
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };
    private static final ThreadLocal<DateFormat> DATE_HSM_FORMATTER = new ThreadLocal<DateFormat>(){
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("HH:mm:ss");
        }
    };
    private static final ThreadLocal<DateFormat> DATE_HM_FORMATTER = new ThreadLocal<DateFormat>(){
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("HH:mm");
        }
    };

    private static final ThreadLocal<DateFormat> DATE_YMDHM_FORMATTER = new ThreadLocal<DateFormat>(){
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm");
        }
    };

    /**
     * 时间转字符串
     * yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
    public static String DATETIMEFORMATTER(Date date){
        return DATE_TIME_FORMATTER.get().format(date);
    }

    /**
     * 时间转字符串
     * yyyy-MM-dd
     * @param date
     * @return
     */
    public  static String DATEYMDFORMATTER(Date date){
        return DATE_YMD_FORMATTER.get().format(date);
    }

    /**
     * 时间转字符串
     * HH:mm:ss
     * @param date
     * @return
     */
    public static String DATEHSMFORMATTER(Date date){
        return DATE_HSM_FORMATTER.get().format(date);
    }

    /**
     * 时间转字符串
     * HH:mm
     * @param date
     * @return
     */
    public static String DATEHMFORMATTER(Date date){
        return DATE_HM_FORMATTER.get().format(date);
    }

    /**
     * 时间转字符串
     * yyyy-MM-dd HH:mm
     * @param date
     * @return
     */
    public static String DATEYMDHMFORMATTER(Date date){
        return DATE_YMDHM_FORMATTER.get().format(date);
    }

    /**
     * 字符串转DATE
     * yyyy-MM-dd HH:mm:ss
     * @param str
     * @return
     * @throws ParseException
     */
    public static Date TOSTRINGDATETIMEFORMATTER(String str) throws ParseException {
        return DATE_TIME_FORMATTER.get().parse(str);
    }

    /**
     * 字符串转DATE
     * yyyy-MM-dd
     * @param str
     * @return
     * @throws ParseException
     */
    public static Date TOSTRINGDATEYMDFORMATTER(String str) throws ParseException {
        return DATE_YMD_FORMATTER.get().parse(str);
    }

    /**
     * 时间转字符串
     * yyyyMMddHHmmss
     * @param date
     * @return
     */
    public static String DATEFORMATTER(Date date){
        return DATE_FORMATTER.get().format(date);
    }

    /**
     * 获取时间戳
     * @return
     */
    public static String GETTIME(){
        return String.valueOf(System.currentTimeMillis());
    }
}
