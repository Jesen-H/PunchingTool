package com.hjq.punching.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * @Describe：
 * @Date：2019-04-02
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(setLayout());
        initView();
        setListener();
    }

    protected abstract int setLayout();

    protected abstract void initView();

    protected abstract void setListener();

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
