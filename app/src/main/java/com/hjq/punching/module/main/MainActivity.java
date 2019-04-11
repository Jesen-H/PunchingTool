package com.hjq.punching.module.main;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.reflect.TypeToken;
import com.hjq.punching.MyApplication;
import com.hjq.punching.R;
import com.hjq.punching.adapter.PunchRecodeAdapter;
import com.hjq.punching.base.BaseActivity;
import com.hjq.punching.base.BaseEvent;
import com.hjq.punching.bean.PunchRecord;
import com.hjq.punching.module.detail.DetailActivity;
import com.hjq.punching.weight.Config;
import com.hjq.punching.weight.dialog.CreateDialog;
import com.hjq.punching.weight.util.ToastUtils;
import com.hjq.punching.weight.view.MyPopupWindow;
import com.hjq.punching.weight.view.MyTitleBar;
import com.hjq.punching.weight.util.SpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainContract.MainView {
    private MainContract.MainPresenter mPresenter;

    public static boolean CURRENT_ACTIVITY = true;

    @BindView(R.id.title_bar)
    MyTitleBar titleBar;
    @BindView(R.id.rv_record)
    RecyclerView rvRecord;
    @BindView(R.id.tv_amount)
    TextView tvAmount;

    private String punch_record;
    private MyPopupWindow popupWindow;
    private int num = 0;
    private PunchRecodeAdapter recodeAdapter;
    private List<PunchRecord> recordList;

    private boolean isOpen = false;

    private CreateDialog dialog;

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mPresenter = new MainPresenter();
        ((MainPresenter) mPresenter).setView(this);

        recodeAdapter = new PunchRecodeAdapter();
        rvRecord.setLayoutManager(new LinearLayoutManager(this));
        rvRecord.setAdapter(recodeAdapter);

        punch_record = getPunch_record();
        if (!TextUtils.isEmpty(punch_record)) {
            recordList = new ArrayList<>();
            recordList = MyApplication.getGson().fromJson(punch_record, new TypeToken<List<PunchRecord>>() {
            }.getType());
            recodeAdapter.setNewData(recordList);
            num = recodeAdapter.getPunchNumber();
        }

        setText();
        setEmpty();
    }

    private void setEmpty() {
        View emptyView = getLayoutInflater().inflate(R.layout.item_empty_view, null);
        emptyView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        recodeAdapter.setEmptyView(emptyView);
    }

    @Override
    protected void setListener() {
        titleBar.setOnTitleBarClickListener(new MyTitleBar.OnTitleBarClickListener() {
            @Override
            public void onBack() {

            }

            @Override
            public void onSetting(ConstraintLayout setting) {
                if (popupWindow == null) {
                    popupWindow = new MyPopupWindow(MainActivity.this);
                }
                popupWindow.setOutsideTouchable(true);
                popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setBackgroundDrawable(new ColorDrawable());
                popupWindow.show(setting);
                if (isOpen){
                    popupWindow.dismiss();
                    isOpen = false;
                    return;
                }
                isOpen = true;
            }
        });
        recodeAdapter.setOnItemClickListener(new PunchRecodeAdapter.OnItemClickListener() {
            @Override
            public void onRecord(String name) {

            }
        });
        recodeAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.btn_card:
                        num++;
                        setText();
                        recodeAdapter.updatePunch(position);
                        String json = getPunch_detail(Config.PUNCH_RECORD_DATE);
                        mPresenter.clickPunch(recodeAdapter, position, json);
                        break;
                }
            }
        });
    }

    private void setText() {
        tvAmount.setText("我的打卡(" + num + "/" + recodeAdapter.getData().size() + ")");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onEvent(BaseEvent event) {
        super.onEvent(event);
        if (event.getString().equals(Config.CREATE_RECORD)) {
            if (dialog == null) {
                dialog = new CreateDialog();
            }
            dialog.showCenter(this);
            dialog.setOnContentClickListener(new CreateDialog.OnContentClickListener() {
                @Override
                public void onContent(String str) {
                    if (TextUtils.isEmpty(str)) {
                        ToastUtils.showToast("请输入名称~");
                        return;
                    }
                    for (PunchRecord record : recodeAdapter.getData()) {
                        if (record.getName().equals(str)) {
                            ToastUtils.showToast("存在此打卡~");
                            return;
                        }
                    }
                    List<PunchRecord> punchRecords = new ArrayList<>();
                    PunchRecord record = new PunchRecord();
                    record.setName(str);
                    record.setPunch(false);
                    punchRecords.add(record);
                    recodeAdapter.addData(punchRecords);
                    setText();
                    SpUtils.putSp(Config.PUNCH_RECORD, MyApplication.getGson().toJson(recodeAdapter.getData()));
                    dialog.clearText();
                    dialog.hideDialog();
                    rvRecord.smoothScrollToPosition(recodeAdapter.getData().size() - 1);
                }
            });
        } else if (event.getString().equals(Config.SELECT_DETAIL)) {
            String json = MyApplication.getSp().getString(Config.PUNCH_RECORD_DATE, "");
            if (!TextUtils.isEmpty(json)) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!CURRENT_ACTIVITY) {
            titleBar.setAnimation();
            CURRENT_ACTIVITY = true;
        }
    }

    @Override
    public void showToast() {

    }

    @Override
    public void showMsg() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
