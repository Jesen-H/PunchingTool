package com.hjq.punching.weight.util;

import android.widget.Toast;

import com.hjq.punching.MyApplication;

/**
 * @Describe：
 * @Date：2019-04-11
 */
public class ToastUtils {

    public static void showToast(String str) {
        Toast.makeText(MyApplication.getContext(), str, Toast.LENGTH_SHORT).show();
    }
}
