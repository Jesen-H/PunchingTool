package com.hjq.punching.weight.view;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.PopupWindow;

import com.hjq.punching.R;

/**
 * @Describe：
 * @Date：2019-04-03
 */
public class MyPopupWindow extends PopupWindow {
    private View view;
    private Context context;
    private ConstraintLayout constraint;
    private RecyclerView rv_setting;

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
    }

    public void show(ConstraintLayout myTitleBar) {
        ScaleAnimation scaleAnimation = (ScaleAnimation) AnimationUtils.loadAnimation(context, R.anim.animation);
        constraint.startAnimation(scaleAnimation);
        showAsDropDown(myTitleBar,0,20);
    }
}
