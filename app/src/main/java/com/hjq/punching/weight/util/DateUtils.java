package com.hjq.punching.weight.util;

import com.hjq.punching.bean.RecordDay;

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
                    return 29;
                } else {
                    return 28;
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

    public static String getSystemDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return sdf.format(date);
    }

    public static List<RecordDay> getRecordDays(List<RecordDay> recordDays) {
        int year = getSystemYear();
        int month = getSystemMonth();
        int dateLongDay = getDateLongDay(year, month);
        RecordDay days = new RecordDay();
        days.setName(year + "年-" + month + "月");
        List<RecordDay.Days> daysList = new ArrayList<>();
        for (int k = 1; k <= dateLongDay; k++) {
            RecordDay.Days day = new RecordDay.Days();
            day.setDay(k + "");
            day.setPunch(false);
            daysList.add(day);
        }
        days.setDaysList(daysList);
        recordDays.add(days);
        return recordDays;
    }

    public static List<RecordDay> getRecordDays() {
        List<RecordDay> list = new ArrayList<>();
        int oldYear = 2018;
        int currentYear = getSystemYear();
        for (int i = oldYear; i <= currentYear; i++) {
            for (int j = 1; j <= 12; j++) {
                RecordDay days = new RecordDay();
                int dateLongDay = getDateLongDay(i, j);
                days.setName(i + "年-" + j + "月");
                List<RecordDay.Days> daysList = new ArrayList<>();
                for (int k = 1; k <= dateLongDay; k++) {
                    RecordDay.Days day = new RecordDay.Days();
                    day.setDay(k + "");
                    day.setPunch(false);
                    daysList.add(day);
                }
                days.setDaysList(daysList);
                list.add(days);
            }
        }
        return list;
    }
}
