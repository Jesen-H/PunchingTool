package com.hjq.punching.weight;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.hjq.punching.R;

/**
 * @Describe：
 * @Date：2019-04-02
 */
public class MyTitleBar extends ConstraintLayout {
    private ConstraintLayout constraint_back;

    private OnTitleBarClickListener listener;

    public interface OnTitleBarClickListener {
        void onBack();
    }

    public void setOnTitleBarClickListener(OnTitleBarClickListener listener) {
        this.listener = listener;
    }

    public MyTitleBar(Context context) {
        super(context);
        init(context, null);
    }

    public MyTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_tital_bar, this, true);
        initView(view);
        setListener();
    }

    private void initView(View view) {
        constraint_back = view.findViewById(R.id.container_back);
    }

    private void setListener() {
        constraint_back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onBack();
                }
            }
        });
    }
}
