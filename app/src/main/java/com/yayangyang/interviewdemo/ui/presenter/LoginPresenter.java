package com.yayangyang.interviewdemo.ui.presenter;

import com.yayangyang.interviewdemo.Bean.user.Login;
import com.yayangyang.interviewdemo.api.InterviewApi;
import com.yayangyang.interviewdemo.base.RxPresenter;
import com.yayangyang.interviewdemo.ui.contract.LoginContract;
import com.yayangyang.interviewdemo.utils.LogUtils;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter extends RxPresenter<LoginContract.View>
        implements LoginContract.Presenter {
    private InterviewApi interviewApi;

    @Inject
    public LoginPresenter(InterviewApi interviewApi) {
        this.interviewApi = interviewApi;
    }

    @Override
    public void login(String username, String password) {
        Disposable rxDisposable = interviewApi.login(username, password).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<Login>() {
                            @Override
                            public void accept(Login login) throws Exception {
                                LogUtils.e("getRecommend-accept");
                                if (mView != null) {
                                    LogUtils.e("eeeeeeeeeeeee");
                                    mView.loginResult(login);
                                }
                            }
                        },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable e) throws Exception {
                                LogUtils.e("getRecommendList", e.toString());
                                if(mView!=null){
                                    mView.showError();
                                }
                            }
                        },
                        new Action() {
                            @Override
                            public void run() throws Exception {
                                LogUtils.e("complete");
                                if(mView!=null){
                                    mView.complete();
                                }
                            }
                        }
                );
        addDisposable(rxDisposable);
    }
}
