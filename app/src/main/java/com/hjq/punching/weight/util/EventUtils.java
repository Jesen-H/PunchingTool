package com.hjq.punching.weight.util;

import com.hjq.punching.base.BaseEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * @Describe：
 * @Date：2019-04-03
 */
public class EventUtils {

    public static void post(String string) {
        EventBus.getDefault().post(new BaseEvent(string));
    }

    public static void post(String string, Object object) {
        EventBus.getDefault().post(new BaseEvent(string, object));
    }
}
