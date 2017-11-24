package com.yayangyang.interviewdemo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yayangyang.interviewdemo.Bean.NewsBean;
import com.yayangyang.interviewdemo.R;
import com.yayangyang.interviewdemo.base.BaseRVActivity;
import com.yayangyang.interviewdemo.component.AppComponent;
import com.yayangyang.interviewdemo.component.DaggerMyComponent;
import com.yayangyang.interviewdemo.ui.adapter.TopNewsAdapter;
import com.yayangyang.interviewdemo.ui.contract.UserDetailContract;
import com.yayangyang.interviewdemo.ui.presenter.NewsPresenter;
import com.yayangyang.interviewdemo.ui.presenter.UserDetailPresenter;
import com.yayangyang.interviewdemo.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/11/22.
 */

public class UserDetailActivity extends BaseRVActivity<NewsBean.DataBean,BaseViewHolder>
        implements UserDetailContract.View{

    @Inject
    UserDetailPresenter mPresenter;

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context,UserDetailActivity.class));
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_detail;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMyComponent.builder()
                .appComponent(appComponent)
                .build().inject(this);
    }

    @Override
    public void initToolBar() {
        mCommonToolbar.setTitle("");
        mCommonToolbar.setNavigationIcon(R.drawable.ab_back);
    }

    @Override
    public void initDatas() {
        mPresenter.attachView(this);
    }

    @Override
    public void configViews() {
        LogUtils.e("configViews");
        initAdapter(TopNewsAdapter.class,
                R.layout.item_top_news,new ArrayList(),true,true);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        mAdapter.setEnableLoadMore(false);
        mPresenter.getNews(page);
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.getNews(page);
    }

    @Override
    public void showError() {
        loaddingError();
    }

    @Override
    public void complete() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showNews(List<NewsBean.DataBean> list, int page) {
        boolean isRefresh=page==1;
        if(isRefresh){
            if(list!=null){
                LogUtils.e("不为空"+list.size());
            }
            LogUtils.e("刷新");
            this.page=1;
            mAdapter.setHeaderView(View.inflate(this,R.layout.view_user_info_head,null));
            mAdapter.getData().clear();
            mAdapter.setEmptyView(inflate);
            mRecyclerView.scrollToPosition(0);
            mAdapter.setNewData(list);
            this.page++;
        }else if(!isRefresh&&(list==null||list.isEmpty())){
            LogUtils.e("loadMoreEnd");
            mAdapter.loadMoreEnd();
            LogUtils.e("loadMoreEnd");
        }else{
            mAdapter.loadMoreComplete();
            mAdapter.addData(list);
            LogUtils.e("loadMoreComplete"+list.size());
            this.page++;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
