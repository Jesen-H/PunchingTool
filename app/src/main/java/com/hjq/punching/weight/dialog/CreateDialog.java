package com.hjq.punching.weight.dialog;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hjq.punching.R;
import com.hjq.punching.base.BaseDialog;

/**
 * @Describe：
 * @Date：2019-04-11
 */
public class CreateDialog extends BaseDialog implements View.OnClickListener {
    private EditText et_create;
    private TextView tv_cancel;
    private TextView tv_submit;

    private OnContentClickListener listener;

    public interface OnContentClickListener {
        void onContent(String str);
    }

    public void setOnContentClickListener(OnContentClickListener listener) {
        this.listener = listener;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_create;
    }

    @Override
    protected int getAnimation() {
        return R.style.MyDialog;
    }

    @Override
    protected void initView(View rootView) {
        et_create = rootView.findViewById(R.id.edit_create);
        tv_cancel = rootView.findViewById(R.id.tv_cancel);
        tv_submit = rootView.findViewById(R.id.tv_submit);

        tv_submit.setOnClickListener(this);
        tv_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                hideDialog();
                break;
            case R.id.tv_submit:
                if (listener != null) {
                    listener.onContent(et_create.getText().toString());
                }
                break;
        }
    }

    public void clearText() {
        et_create.setText("");
    }

    @Override
    protected boolean shouldHideBackground() {
        return false;
    }

    @Override
    protected boolean canCanceledOnTouchOutside() {
        return true;
    }

    @Override
    protected boolean isWindowWidthMatchParent() {
        return false;
    }

    public void showCenter(FragmentActivity activity) {
        showCenter(activity, "DIALOG_CREATE");
    }
}
