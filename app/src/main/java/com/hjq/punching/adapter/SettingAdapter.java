package com.hjq.punching.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hjq.punching.R;
import com.hjq.punching.bean.SettingBean;

/**
 * @Describe：
 * @Date：2019-04-09
 */
public class SettingAdapter extends BaseQuickAdapter<SettingBean, BaseViewHolder> {
    private RequestOptions options;

    public SettingAdapter() {
        super(R.layout.item_setting);
        options = new RequestOptions().circleCrop().placeholder(R.drawable.kong).error(R.drawable.kong);
    }

    @Override
    protected void convert(BaseViewHolder helper, SettingBean item) {
        Glide.with(mContext).load(item.getPic()).apply(options).into((ImageView) helper.getView(R.id.image_setting));
        helper.setText(R.id.tv_setting, item.getName());
    }
}
