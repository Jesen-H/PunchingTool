package com.hjq.punching.module.main;

import android.text.TextUtils;

import com.google.gson.reflect.TypeToken;
import com.hjq.punching.MyApplication;
import com.hjq.punching.adapter.PunchRecodeAdapter;
import com.hjq.punching.bean.PunchDetail;
import com.hjq.punching.weight.Config;
import com.hjq.punching.weight.util.DateUtils;
import com.hjq.punching.weight.util.SpUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Describe：
 * @Date：2019-04-11
 */
public class MainPresenter implements MainContract.MainPresenter {
    private MainContract.MainView view;

    public void setView(MainContract.MainView view){
        this.view = view;
    }

    @Override
    public void clickPunch(PunchRecodeAdapter recodeAdapter,int position ,String json) {
        SpUtils.putSp(Config.PUNCH_RECORD, MyApplication.getGson().toJson(recodeAdapter.getData()));

        String name = recodeAdapter.getData().get(position).getName();
        List<PunchDetail> detailList;
        PunchDetail.Days.DetailBean bean = new PunchDetail.Days.DetailBean();

        bean.setName(name);
        bean.setTime(DateUtils.getSystemDate());
        bean.setRemark("");

        if (!TextUtils.isEmpty(json)) {
            String date = DateUtils.getSystemYear() + "年-" + DateUtils.getSystemMonth() + "月";
            detailList = MyApplication.getGson().fromJson(json, new TypeToken<List<PunchDetail>>() {
            }.getType());

            boolean isDayExist = false;
            for (PunchDetail pd : detailList) {
                if (pd.getDate().equals(date)) {
                    for (PunchDetail.Days days : pd.getDays()) {
                        if (days.getDay().equals(DateUtils.getDays() + "")) {
                            List<PunchDetail.Days.DetailBean> detail = days.getDetailBeans();
                            detail.add(bean);
                            isDayExist = true;
                            break;
                        }
                    }
                    if (!isDayExist) {
                        List<PunchDetail.Days> daysList = pd.getDays();
                        PunchDetail.Days days = new PunchDetail.Days();
                        days.setDay(DateUtils.getDays() + "");

                        List<PunchDetail.Days.DetailBean> detail = new ArrayList<>();
                        detail.add(bean);

                        days.setDetailBeans(detail);
                        daysList.add(days);
                    }
                    break;
                }
            }
        } else {
            detailList = new ArrayList<>();
            List<PunchDetail.Days> daysList = new ArrayList<>();
            List<PunchDetail.Days.DetailBean> detailBeanList = new ArrayList<>();

            detailBeanList.add(bean);

            PunchDetail.Days days = new PunchDetail.Days();
            days.setDay(DateUtils.getDays() + "");
            days.setDetailBeans(detailBeanList);
            daysList.add(days);

            PunchDetail punchDetail = new PunchDetail();
            punchDetail.setDate(DateUtils.getSystemYear() + "年-" + DateUtils.getSystemMonth() + "月");
            punchDetail.setDays(daysList);

            detailList.add(punchDetail);
        }

        SpUtils.putSp(Config.PUNCH_RECORD_DATE, MyApplication.getGson().toJson(detailList));
    }
}
