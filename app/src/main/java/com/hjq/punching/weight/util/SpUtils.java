package com.hjq.punching.weight.util;

import com.hjq.punching.MyApplication;

/**
 * @Describe：
 * @Date：2019-04-03
 */
public class SpUtils {

    public static void putSp(String key, Object value) {
        if (value instanceof String) {
            MyApplication.getSp().edit().putString(key, (String) value).apply();
        } else if (value instanceof Integer) {
            MyApplication.getSp().edit().putInt(key, (Integer) value).apply();
        } else if (value instanceof Boolean) {
            MyApplication.getSp().edit().putBoolean(key, (Boolean) value).apply();
        }
    }
}
