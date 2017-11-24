package com.yayangyang.interviewdemo.ui.contract;


import com.yayangyang.interviewdemo.base.BaseContract;

/**
 * Created by Administrator on 2017/11/5.
 */

public interface SplashContract {

    interface View extends BaseContract.BaseView{
        void showIsLogin(boolean isLogin);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T>{
        void checkLogin(String token);
    }
}
