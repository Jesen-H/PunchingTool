package com.hjq.punching.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hjq.punching.R;
import com.hjq.punching.bean.PunchDetail;

/**
 * @Describe：
 * @Date：2019-04-10
 */
public class PunchDetailAdapter extends BaseQuickAdapter<PunchDetail.Days.DetailBean, BaseViewHolder> {

    public PunchDetailAdapter() {
        super(R.layout.item_detail);
    }

    @Override
    protected void convert(BaseViewHolder helper, PunchDetail.Days.DetailBean item) {
        helper.setText(R.id.tv_name, item.getName()).setText(R.id.tv_time, item.getTime());
    }
}
