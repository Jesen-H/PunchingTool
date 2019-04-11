package com.hjq.punching.module.detail;

import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.hjq.punching.MyApplication;
import com.hjq.punching.R;
import com.hjq.punching.adapter.PunchDetailAdapter;
import com.hjq.punching.base.BaseActivity;
import com.hjq.punching.base.BaseEvent;
import com.hjq.punching.bean.PunchDetail;
import com.hjq.punching.module.main.MainActivity;
import com.hjq.punching.weight.Config;
import com.hjq.punching.weight.dialog.LoadingDialog;
import com.hjq.punching.weight.view.MyDateView;
import com.hjq.punching.weight.view.MyTitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.chad.library.adapter.base.BaseQuickAdapter.SCALEIN;

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
    private LoadingDialog dialog;

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
        dateView.getData();

        detailAdapter = new PunchDetailAdapter();
        rvDateDetail.setLayoutManager(new LinearLayoutManager(this));
        rvDateDetail.setAdapter(detailAdapter);

        setEmpty();
        detailAdapter.openLoadAnimation(SCALEIN);
        detailAdapter.isFirstOnly(false);
    }

    @Override
    protected void setListener() {
        titleBar.setOnTitleBarClickListener(new MyTitleBar.OnTitleBarClickListener() {
            @Override
            public void onBack() {
                MainActivity.CURRENT_ACTIVITY = false;
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }

            @Override
            public void onSetting(ConstraintLayout setting) {

            }
        });
    }

    private void setEmpty() {
        View emptyView = getLayoutInflater().inflate(R.layout.item_empty_view, null);
        emptyView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        TextView textView = emptyView.findViewById(R.id.tv_empty);
        textView.setText("当日无打卡记录~");
        detailAdapter.setEmptyView(emptyView);
    }

    @Override
    public void onEvent(final BaseEvent event) {
        super.onEvent(event);
        if (event.getString().equals(Config.PUNCH_DATE_DETAIL)) {
            if (dialog == null) {
                dialog = new LoadingDialog();
            }
            dialog.showCenter(this);
            dialog.setOnDismissListener(new LoadingDialog.OnDismissListener() {
                @Override
                public void onDismiss() {
                    dialog.dismiss();
                    if (event.getObject() == null) {
                        detailAdapter.setNewData(new ArrayList<PunchDetail.Days.DetailBean>());
                        return;
                    }
                    List<PunchDetail.Days.DetailBean> detailBean = (List<PunchDetail.Days.DetailBean>) event.getObject();
                    detailAdapter.setNewData(detailBean);
                }
            });

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
