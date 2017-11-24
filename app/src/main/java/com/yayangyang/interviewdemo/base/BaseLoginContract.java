package com.yayangyang.interviewdemo.base;

import com.yayangyang.interviewdemo.Bean.user.Login;

/**
 * Created by Administrator on 2017/11/5.
 */

public interface BaseLoginContract {
    interface View extends BaseContract.BaseView{
        void loginSuccess(Login login);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T>{
        void login(String uid, String token, String platform);
    }
}
