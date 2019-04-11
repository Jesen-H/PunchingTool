package com.hjq.punching.module.main;

import com.hjq.punching.adapter.PunchRecodeAdapter;
import com.hjq.punching.base.BaseView;

/**
 * @Describe：
 * @Date：2019-04-11
 */
public class MainContract {

    interface MainView extends BaseView {

    }

    interface MainPresenter {

        void clickPunch(PunchRecodeAdapter recodeAdapter, int position, String json);
    }
}
