package com.yayangyang.interviewdemo.ui.presenter;

import com.yayangyang.interviewdemo.Bean.ChangeNickNameBean;
import com.yayangyang.interviewdemo.Bean.NewsBean;
import com.yayangyang.interviewdemo.api.InterviewApi;
import com.yayangyang.interviewdemo.base.RxPresenter;
import com.yayangyang.interviewdemo.ui.contract.ChangeNickNameContract;
import com.yayangyang.interviewdemo.utils.LogUtils;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/11/22.
 */

public class ChangeNickNamePresenter extends RxPresenter<ChangeNickNameContract.View>
        implements ChangeNickNameContract.Presenter{

    private InterviewApi interviewApi;

    @Inject
    public ChangeNickNamePresenter(InterviewApi interviewApi) {
        this.interviewApi = interviewApi;
    }

    @Override
    public void changeNickName(String nickname) {
        Disposable rxDisposable = interviewApi.changeNickName(nickname).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<ChangeNickNameBean>() {
                            @Override
                            public void accept(ChangeNickNameBean data) throws Exception {
                                LogUtils.e("getRecommend-accept");
                                if (mView != null) {
                                    LogUtils.e("eeeeeeeeeeeee");
                                    mView.changeNickNameSuccess(data);
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
