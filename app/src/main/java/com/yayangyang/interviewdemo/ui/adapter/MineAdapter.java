package com.yayangyang.interviewdemo.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yayangyang.interviewdemo.Bean.user.Login;

import java.util.List;

/**
 * Created by Administrator on 2017/11/22.
 */

public class MineAdapter extends BaseQuickAdapter<Login.DataBean,BaseViewHolder> {

    public MineAdapter(int layoutResId, @Nullable List<Login.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Login.DataBean item) {

    }
}
