package com.yayangyang.interviewdemo.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yayangyang.interviewdemo.Bean.NewsBean;
import com.yayangyang.interviewdemo.R;
import com.yayangyang.interviewdemo.base.Constant;
import com.yayangyang.interviewdemo.transform.GlideRoundTransform;
import com.yayangyang.interviewdemo.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/13.
 */

public class TopNewsAdapter extends BaseQuickAdapter<NewsBean.DataBean,BaseViewHolder> {
    public TopNewsAdapter(int layoutResId, @Nullable List<NewsBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsBean.DataBean item) {
        LogUtils.e("convert+++++++++++++++");
        ImageView view =helper.getView(R.id.iv_news);
        Glide.with(mContext).load(item.image)
                .placeholder(R.drawable.avatar_default) .transform(new GlideRoundTransform
                (mContext,6)).into(view);
        helper.setText(R.id.tv_title,item.title);
        helper.setText(R.id.tv_content,item.desc);
        helper.addOnClickListener(R.id.ll_userInfo);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        LogUtils.e("onBindViewHolder");
        super.onBindViewHolder(holder, position);
    }
}
