package com.hjq.punching.bean;

/**
 * @Describe：
 * @Date：2019-04-09
 */
public class SettingBean {
    private int pic;
    private String name;

    public SettingBean(int pic, String name) {
        this.pic = pic;
        this.name = name;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
