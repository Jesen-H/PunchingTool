package com.hjq.punching.bean;

import java.util.List;

/**
 * @Describe：
 * @Date：2019-04-09
 */
public class PunchDetail {
    private String date;
    private List<Days> days;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Days> getDays() {
        return days;
    }

    public void setDays(List<Days> days) {
        this.days = days;
    }

    public static class Days {
        private String day;
        private List<DetailBean> detailBeans;

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public List<DetailBean> getDetailBeans() {
            return detailBeans;
        }

        public void setDetailBeans(List<DetailBean> detailBeans) {
            this.detailBeans = detailBeans;
        }

        public static class DetailBean {
            private String name;
            private String time;
            private String remark;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }
        }
    }
}
