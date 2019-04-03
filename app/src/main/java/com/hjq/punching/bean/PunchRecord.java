package com.hjq.punching.bean;

/**
 * @Describe：
 * @Date：2019-04-03
 */
public class PunchRecord {
    private String name;
    private boolean isPunch;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPunch() {
        return isPunch;
    }

    public void setPunch(boolean punch) {
        isPunch = punch;
    }
}
