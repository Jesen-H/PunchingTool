package com.hjq.punching.module.detail;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.reflect.TypeToken;
import com.hjq.punching.MyApplication;
import com.hjq.punching.R;
import com.hjq.punching.adapter.PunchDetailAdapter;
import com.hjq.punching.base.BaseActivity;
import com.hjq.punching.base.BaseEvent;
import com.hjq.punching.bean.PunchDetail;
import com.hjq.punching.bean.RecordDay;
import com.hjq.punching.weight.Config;
import com.hjq.punching.weight.MyDividerItem;
import com.hjq.punching.weight.util.DateUtils;
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
    private PunchDetailAdapter detailAdapter;

    @Override
    protected int setLayout() {
        return R.layout.activity_detail;
    }

    @Override
    protected void initView() {
        String json = getPunch_detail(Config.PUNCH_RECORD_DATE);
        details = MyApplication.getGson().fromJson(json, new TypeToken<List<PunchDetail>>() {
        }.getType());

        dateView.setData(details);

        detailAdapter = new PunchDetailAdapter();
        rvDateDetail.setLayoutManager(new LinearLayoutManager(this));
        rvDateDetail.setAdapter(detailAdapter);
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
    public void onEvent(BaseEvent event) {
        super.onEvent(event);
        if (event.getString().equals(Config.PUNCH_DATE_DETAIL)) {
            List<PunchDetail.Days.DetailBean> detailBean = (List<PunchDetail.Days.DetailBean>) event.getObject();
            detailAdapter.setNewData(detailBean);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
