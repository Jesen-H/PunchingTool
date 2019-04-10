package com.hjq.punching.bean;

import java.util.List;

/**
 * @Describe：
 * @Date：2019-04-09
 */
public class RecordDay {

    private String name;
    private List<Days> daysList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Days> getDaysList() {
        return daysList;
    }

    public void setDaysList(List<Days> daysList) {
        this.daysList = daysList;
    }

    public static class Days{
        private String day;
        private boolean isPunch;

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public boolean isPunch() {
            return isPunch;
        }

        public void setPunch(boolean punch) {
            isPunch = punch;
        }
    }
}
