package com.yayangyang.interviewdemo.ui.contract;

import com.yayangyang.interviewdemo.Bean.NewsBean;
import com.yayangyang.interviewdemo.Bean.user.Login;
import com.yayangyang.interviewdemo.base.BaseContract;

import java.util.List;

/**
 * Created by Administrator on 2017/11/12.
 */

public interface LoginContract {

    interface View extends BaseContract.BaseView {
        void loginResult(Login login);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void login(String username,String password);
    }

}
