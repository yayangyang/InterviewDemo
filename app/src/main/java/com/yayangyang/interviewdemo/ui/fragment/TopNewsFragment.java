package com.yayangyang.interviewdemo.ui.fragment;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yayangyang.interviewdemo.Bean.NewsBean;
import com.yayangyang.interviewdemo.R;
import com.yayangyang.interviewdemo.base.BaseRVFragment;
import com.yayangyang.interviewdemo.component.AppComponent;
import com.yayangyang.interviewdemo.component.DaggerMyComponent;
import com.yayangyang.interviewdemo.ui.activity.UserDetailActivity;
import com.yayangyang.interviewdemo.ui.adapter.TopNewsAdapter;
import com.yayangyang.interviewdemo.ui.contract.NewsContract;
import com.yayangyang.interviewdemo.ui.presenter.NewsPresenter;
import com.yayangyang.interviewdemo.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

public class TopNewsFragment extends BaseRVFragment<NewsPresenter,NewsBean.DataBean,BaseViewHolder>
        implements NewsContract.View,BaseQuickAdapter.OnItemChildClickListener{

    private ArrayList mArrayList=new ArrayList();

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        if(view.getId()==R.id.ll_userInfo){
            UserDetailActivity.startActivity(getActivity());
        }
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_top_news;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMyComponent.builder()
                .appComponent(appComponent)
                .build().inject(this);
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void configViews() {
        LogUtils.e("configViews");
        initAdapter(TopNewsAdapter.class,
                R.layout.item_top_news,mArrayList,true,true);
        mAdapter.setOnItemChildClickListener(this);
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
        LogUtils.e("onLoadMoreRequested"+page);
        mPresenter.getNews(page);
    }

    @Override
    public void showNews(List<NewsBean.DataBean> list,int page) {
        boolean isRefresh=page==1;
        if(isRefresh){
            if(list!=null){
                LogUtils.e("不为空"+list.size());
            }
            LogUtils.e("刷新");
            this.page=1;
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
    public void showError() {
        loaddingError();
    }

    @Override
    public void complete() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
