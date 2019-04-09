package com.hjq.punching.weight.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @Describe：
 * @Date：2019-04-09
 */
public class DateUtils {
    private static GregorianCalendar calendar = (GregorianCalendar) GregorianCalendar.getInstance();

    public static int getDateLongDay(int year, int month) {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                if (calendar.isLeapYear(year)) {
                    return  29;
                } else {
                    return  28;
                }
        }
        return 0;
    }

    public static int getSystemYear() {
        Calendar date = Calendar.getInstance();
        return date.get(Calendar.YEAR);
    }

    public static int getSystemMonth() {
        Calendar date = Calendar.getInstance();
        return date.get(Calendar.MONTH);
    }

    public static int getDays() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String time = sdf.format(date);
        return Integer.parseInt(time.substring(time.lastIndexOf("-") + 1, time.length()));
    }

    public static String getSystemDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return sdf.format(date);
    }
}
