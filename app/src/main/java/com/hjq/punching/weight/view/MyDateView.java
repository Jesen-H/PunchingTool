package com.hjq.punching.weight.view;

import android.content.Context;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hjq.punching.R;
import com.hjq.punching.bean.PunchDetail;
import com.hjq.punching.bean.RecordDay;
import com.hjq.punching.weight.Config;
import com.hjq.punching.weight.util.DateUtils;
import com.hjq.punching.weight.util.EventUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Describe：
 * @Date：2019-04-09
 */
public class MyDateView extends ConstraintLayout {
    private RecyclerView rv_date;
    private TextView tv_date;
    private ImageView next;
    private ImageView last;
    private Context mContext;

    private int year = 0;
    private int month = 0;
    private List<PunchDetail> punchDetails;
    private SimpleDateFormat sdf;
    private String time = "";

    private BaseQuickAdapter<RecordDay, BaseViewHolder> adapter;

    public MyDateView(Context context) {
        super(context);
        init(context, null);
    }

    public MyDateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.layout_date_view, this, true);
        initView(view);
        setListener();
    }

    private void initView(View view) {
        year = DateUtils.getSystemYear();
        month = DateUtils.getSystemMonth();

        rv_date = view.findViewById(R.id.rv_date);
        tv_date = view.findViewById(R.id.tv_date);
        next = view.findViewById(R.id.image_next);
        last = view.findViewById(R.id.image_last);

        tv_date.setText(DateUtils.getSystemYear() + "年-" + DateUtils.getSystemMonth() + "月");
        rv_date.setLayoutManager(new GridLayoutManager(mContext, 8, LinearLayoutManager.VERTICAL, false));
        rv_date.setAdapter(adapter = new BaseQuickAdapter<RecordDay, BaseViewHolder>(R.layout.item_date) {
            @Override
            protected void convert(BaseViewHolder helper, RecordDay item) {
                int normal = getResources().getColor(R.color.color_333333);
                int record = getResources().getColor(R.color.color_009C84);
                helper.setTextColor(R.id.tv_day, item.isPunch() ? record : normal).setText(R.id.tv_day, item.getDay());
            }
        });
    }

    public void setData(List<PunchDetail> punchDetails) {
        this.punchDetails = punchDetails;
    }

    public void putData(int year, int month) {
        String time = year + "年-" + month + "月";
        int size = DateUtils.getDateLongDay(year, month);

        PunchDetail punchDetail = null;
        for (PunchDetail detail : punchDetails) {
            if (detail.getDate().equals(time)) {
                punchDetail = detail;
            }
        }
        List<RecordDay> list = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            RecordDay recordDay = new RecordDay();
            recordDay.setPunch(false);
            if (punchDetail != null) {
                for (int j = 0; j < punchDetail.getDays().size(); j++) {
                    if (Integer.parseInt(punchDetail.getDays().get(j).getDay()) == i) {
                        recordDay.setPunch(true);
                        break;
                    }
                }
            }
            recordDay.setDay(i + "");
            list.add(recordDay);
        }
        adapter.setNewData(list);
    }

    private void setListener() {
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                for (PunchDetail detail : punchDetails) {
                    if (detail.getDate().equals(tv_date.getText().toString())) {
                        String day = adapter.getData().get(position).getDay();
                        for (PunchDetail.Days days : detail.getDays()) {
                            if (days.getDay().equals(day)) {
                                EventUtils.post(Config.PUNCH_DATE_DETAIL, days.getDetailBeans());
                                return;
                            }
                        }
                    }
                }
            }
        });

        next.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (year == DateUtils.getSystemYear()) {
                    if (month == DateUtils.getSystemMonth()) {
                        return;
                    }
                }
                if (month == 12) {
                    month = 1;
                    year++;
                } else {
                    month++;
                }
                putData(year, month);
                tv_date.setText(year + "年-" + month + "月");
            }
        });
        last.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (year == 2018 && month == 1) {
                    return;
                }
                if (month == 1) {
                    month = 12;
                    year--;
                } else {
                    month--;
                }
                putData(year, month);
                tv_date.setText(year + "年-" + month + "月");
            }
        });
    }
}