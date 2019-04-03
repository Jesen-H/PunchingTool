package com.hjq.punching.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hjq.punching.R;
import com.hjq.punching.bean.PunchRecord;

/**
 * @Describe：
 * @Date：2019-04-03
 */
public class PunchRecodeAdapter extends BaseQuickAdapter<PunchRecord, BaseViewHolder> {

    public PunchRecodeAdapter() {
        super(R.layout.item_record);
    }

    @Override
    protected void convert(BaseViewHolder helper, PunchRecord item) {
        helper.getView(R.id.btn_card).setVisibility(item.isPunch() ? View.GONE : View.VISIBLE);
        helper.setText(R.id.tv_record_name, item.getName())
                .addOnClickListener(R.id.btn_card);
    }

    public void updatePunch(int position) {
        getData().get(position).setPunch(true);
        notifyItemChanged(position);
    }

    public int getPunchNumber() {
        int num = 0;
        for (PunchRecord record : getData()){
            if (record.isPunch()){
                num++;
            }
        }
        return num;
    }
}
