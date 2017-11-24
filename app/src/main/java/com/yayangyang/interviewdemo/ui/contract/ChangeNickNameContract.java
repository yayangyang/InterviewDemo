package com.yayangyang.interviewdemo.ui.contract;

import com.yayangyang.interviewdemo.Bean.ChangeNickNameBean;
import com.yayangyang.interviewdemo.Bean.NewsBean;
import com.yayangyang.interviewdemo.base.BaseContract;

import java.util.List;

/**
 * Created by Administrator on 2017/11/12.
 */

public interface ChangeNickNameContract {

    interface View extends BaseContract.BaseView {
        void changeNickNameSuccess(ChangeNickNameBean data);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void changeNickName(String nickname);
    }

}
