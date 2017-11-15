package com.wmd.sqlitetest.edwardsqlitetest.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 时间：2017/11/15/13：53
 * 作者：MingDe_Wu
 * 网站：https://github.com/Edwardwmd
 * 作用：工具类
 * 声明：版权归作者所有
 */

public class ToolsUtils {
    public static long stringToLong(String strTime, String formatType) {
        Date date = null; // String类型转成date类型
        try {
            date = stringToDate(strTime, formatType);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date == null) {
            return 0;
        } else {
            long currentTime = dateToLong(date); // date类型转成long类型
            return currentTime;
        }
    }

    public static Date stringToDate(String strTime, String formatType)
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        date = formatter.parse(strTime);
        return date;
    }

    // date要转换的date类型的时间
    public static long dateToLong(Date date) {
        return date.getTime();
    }

    public static String getCurrentData() {
        return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new java.util.Date());
    }

    public static long getRandomNum(long currentNum) {
        Random random = new Random(currentNum);
        long currNum = random.nextLong();
        return currNum;
    }
}
