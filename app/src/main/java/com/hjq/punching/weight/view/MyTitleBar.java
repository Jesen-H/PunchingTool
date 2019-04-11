package com.hjq.punching.weight.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.hjq.punching.R;

/**
 * @Describe：
 * @Date：2019-04-02
 */
public class MyTitleBar extends ConstraintLayout {
    private ConstraintLayout constraint_back;
    private ConstraintLayout constraint_setting;
    private ImageView imageSetting;
    private TextView tvTitle;

    private String mTitleText;
    private boolean isShowSettingImage;
    private boolean isShowBackImage;

    private OnTitleBarClickListener listener;

    public interface OnTitleBarClickListener {
        void onBack();

        void onSetting(ConstraintLayout setting);
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

        initAttrs(context, attrs);
    }

    private void initView(View view) {
        constraint_back = view.findViewById(R.id.container_back);
        constraint_setting = view.findViewById(R.id.container_setting);
        imageSetting = view.findViewById(R.id.image_setting);
        tvTitle = view.findViewById(R.id.tv_title);
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
        imageSetting.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onSetting(constraint_setting);
                }
            }
        });
    }

    public void setAnimation() {
        Animation animation = new TranslateAnimation(-300, 0, 0, 0);
        setAnimations(animation);
        tvTitle.setAnimation(animation);
    }

    private void setAnimations(Animation animation) {
        animation.setDuration(1000);
        animation.setInterpolator(new BounceInterpolator());
        animation.setFillEnabled(true);//使其可以填充效果从而不回到原地
        animation.setFillAfter(true);//不回到起始位置
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return;
        }
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyTitleBar);
        if (ta != null) {
            mTitleText = ta.getString(R.styleable.MyTitleBar_title_text);
            isShowSettingImage = ta.getBoolean(R.styleable.MyTitleBar_show_setting_image, false);
            isShowBackImage = ta.getBoolean(R.styleable.MyTitleBar_show_back_image, false);
            ta.recycle();
        }
        tvTitle.setText(mTitleText);
        constraint_setting.setVisibility(isShowSettingImage ? View.VISIBLE : View.GONE);
        constraint_back.setVisibility(isShowBackImage ? View.VISIBLE : View.GONE);
    }
}
