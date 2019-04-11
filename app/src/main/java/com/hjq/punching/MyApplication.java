package com.hjq.punching;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

/**
 * @Describe：
 * @Date：2019-04-02
 */
public class MyApplication extends Application {

    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private static SharedPreferences sp;
    private static Gson gson;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();
        gson = new Gson();
        sp = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
    }

    public static SharedPreferences getSp() {
        return sp;
    }

    public static Gson getGson() {
        return gson;
    }

    public static Context getContext() {
        return context;
    }
}
