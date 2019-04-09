package com.hjq.punching.module.detail;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;

import com.google.gson.reflect.TypeToken;
import com.hjq.punching.MyApplication;
import com.hjq.punching.R;
import com.hjq.punching.base.BaseActivity;
import com.hjq.punching.bean.PunchDetail;
import com.hjq.punching.bean.RecordDay;
import com.hjq.punching.weight.Config;
import com.hjq.punching.weight.view.MyDateView;
import com.hjq.punching.weight.view.MyTitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Describe：
 * @Date：2019-04-02
 */
public class DetailActivity extends BaseActivity {

    @BindView(R.id.title_bar)
    MyTitleBar titleBar;
    @BindView(R.id.date_view)
    MyDateView dateView;
    @BindView(R.id.rv_date_detail)
    RecyclerView rvDateDetail;

    private List<PunchDetail> details;

    @Override
    protected int setLayout() {
        return R.layout.activity_detail;
    }

    @Override
    protected void initView() {
        details = MyApplication.getGson().fromJson(Config.PUNCH_RECORD_DATE, new TypeToken<List<PunchDetail>>() {
        }.getType());

        List<String> list = new ArrayList<>();
        for (PunchDetail pd : details) {
            list.add(pd.getDays());
        }
        dateView.setData(dayList);
    }

    @Override
    protected void setListener() {
        titleBar.setOnTitleBarClickListener(new MyTitleBar.OnTitleBarClickListener() {
            @Override
            public void onBack() {
                finish();
            }

            @Override
            public void onSetting(ConstraintLayout setting) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
