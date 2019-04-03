package com.hjq.punching.base;

/**
 * @Describe：EventBus事件监听
 * @date：2019-01-04
 */
public class BaseEvent {
    private String string;
    private Object object;

    /* 只响应事件 */
    public BaseEvent(String string) {
        this.string = string;
    }

    /* 响应事件并携带值 */
    public BaseEvent(String string, Object object) {
        this.string = string;
        this.object = object;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
