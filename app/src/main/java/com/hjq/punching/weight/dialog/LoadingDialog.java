package com.hjq.punching.weight.dialog;

import android.os.CountDownTimer;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.hjq.punching.R;
import com.hjq.punching.base.BaseDialog;

/**
 * @Describe：加载弹窗
 * @Date：2019-03-13
 */
public class LoadingDialog extends BaseDialog {

    private OnDismissListener listener;

    public interface OnDismissListener{
        void onDismiss();
    }

    public void setOnDismissListener(OnDismissListener listener){
        this.listener = listener;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_loading;
    }

    @Override
    protected int getAnimation() {
        return R.style.CustomDialogStyle;
    }

    @Override
    protected void initView(View rootView) {
        timer.start();
    }

    private CountDownTimer timer = new CountDownTimer(1000,1000) {
        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            if (listener != null){
                listener.onDismiss();
            }
        }
    };

    @Override
    protected boolean shouldHideBackground() {
        return true;
    }

    @Override
    protected boolean canCanceledOnTouchOutside() {
        return false;
    }

    @Override
    protected boolean isWindowWidthMatchParent() {
        return false;
    }

    public void showCenter(FragmentActivity activity) {
        showCenter(activity, "DIALOG_LOADING");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
