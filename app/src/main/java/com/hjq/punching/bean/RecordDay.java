package com.hjq.punching.bean;

/**
 * @Describe：
 * @Date：2019-04-09
 */
public class RecordDay {
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
