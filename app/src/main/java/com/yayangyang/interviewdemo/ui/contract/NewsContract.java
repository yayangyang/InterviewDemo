package com.yayangyang.interviewdemo.ui.contract;

import com.yayangyang.interviewdemo.Bean.NewsBean;
import com.yayangyang.interviewdemo.base.BaseContract;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/12.
 */

public interface NewsContract {

    interface View extends BaseContract.BaseView {
        void showNews(List<NewsBean.DataBean> list,int page);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getNews(int page);
    }

}
