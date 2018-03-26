package com.zr.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Miste on 3/26/2018.
 * 常用静态工具类
 */
public class Utils {

    /**
     * 字符串到日期
     * @param str_date 日期字符串数据
     * @param format 日期格式化代码
     * @return
     */
    public static Date strToDate(String str_date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = simpleDateFormat.parse(str_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  date;
    }

    /**
     * 字符串到日期
     * @param str_date 只能格式化yyyy-MM-dd
     * @return
     */
    public static Date strToDate(String str_date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = simpleDateFormat.parse(str_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  date;
    }

    /**
     * 日期到字符串,默认格式为"yyyy-MM-dd HH:mm:ss"
     * @param date
     * @return
     */
    public static String dateToStr(Date date) {
        SimpleDateFormat tmp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return tmp.format(date);
    }

    /**
     *
     * @param date
     * @param format
     * @return
     */
    public static String dateToStr(Date date, String format) {
        SimpleDateFormat tmp = new SimpleDateFormat(format);
        return tmp.format(date);
    }
}
