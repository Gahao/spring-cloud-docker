package com.musketeers.common.utils;

import org.apache.commons.lang3.StringUtils;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期处理同居类
 */
public class DateTimeUtil {

    //str->Date
    //Date->str
    public static final String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";


    public static Date strToDate(String dateTimeStr,String formatStr) throws Exception{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatStr);
        return simpleDateFormat.parse(dateTimeStr);
    }

    public static String dateToStr(Date date,String formatStr){
        if(date == null){
            return StringUtils.EMPTY;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatStr);
        return simpleDateFormat.format(date);
    }

    public static Date strToDate(String dateTimeStr)throws Exception{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(STANDARD_FORMAT);
        return simpleDateFormat.parse(dateTimeStr);
    }

    public static String dateToStr(Date date){
        if(date == null){
            return StringUtils.EMPTY;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(STANDARD_FORMAT);
        return simpleDateFormat.format(date);
    }
}
