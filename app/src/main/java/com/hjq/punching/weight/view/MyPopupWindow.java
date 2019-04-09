package com.hjq.punching.weight.view;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.PopupWindow;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hjq.punching.MyApplication;
import com.hjq.punching.R;
import com.hjq.punching.adapter.SettingAdapter;
import com.hjq.punching.bean.PunchRecord;
import com.hjq.punching.bean.SettingBean;
import com.hjq.punching.weight.Config;
import com.hjq.punching.weight.Events;
import com.hjq.punching.weight.MyDividerItem;
import com.hjq.punching.weight.util.EventUtils;
import com.hjq.punching.weight.util.SpUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Describe：
 * @Date：2019-04-03
 */
public class MyPopupWindow extends PopupWindow {
    private View view;
    private Context context;
    private ConstraintLayout constraint;
    private RecyclerView rv_setting;

    private SettingAdapter adapter;
    private int[] icon = new int[]{1, 2};
    private String[] name = new String[]{"创建打卡", "编辑"};

    public MyPopupWindow(Context context) {
        super(context);
        this.context = context;
        init(context);
    }

    private void init(Context context) {
        view = LayoutInflater.from(context).inflate(R.layout.layout_popupwindow, null, false);
        setContentView(view);
        initView(view);
    }

    private void initView(View view) {
        rv_setting = view.findViewById(R.id.rv_setting);
        constraint = view.findViewById(R.id.container);

        adapter = new SettingAdapter();
        rv_setting.setLayoutManager(new LinearLayoutManager(context));
        rv_setting.addItemDecoration(new MyDividerItem(context, DividerItemDecoration.VERTICAL));
        rv_setting.setAdapter(adapter);
        List<SettingBean> settingBeans = new ArrayList<>();
        for (int i = 0; i < icon.length; i++) {
            settingBeans.add(new SettingBean(icon[i], name[i]));
        }
        adapter.setNewData(settingBeans);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        List<PunchRecord> punchRecords = new ArrayList<>();
                        PunchRecord record = new PunchRecord();
                        record.setName("测试");
                        record.setPunch(false);
                        punchRecords.add(record);
                        EventUtils.post(Config.CREATE_RECORD, punchRecords);
                        dismiss();
                        break;
                }
            }
        });
    }

    public void show(ConstraintLayout myTitleBar) {
        ScaleAnimation scaleAnimation = (ScaleAnimation) AnimationUtils.loadAnimation(context, R.anim.animation);
        constraint.startAnimation(scaleAnimation);

        int[] location = new int[2];
        myTitleBar.getLocationOnScreen(location);

        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int view_width = view.getMeasuredWidth();

        int off_x = (int) (location[0] - (view_width * 0.5f));
        int off_y = 20;

        showAsDropDown(myTitleBar, 0, off_y);
    }
}
