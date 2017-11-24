package com.yayangyang.interviewdemo.ui.presenter;

import android.util.Log;

import com.yayangyang.interviewdemo.Bean.NewsBean;
import com.yayangyang.interviewdemo.api.InterviewApi;
import com.yayangyang.interviewdemo.base.RxPresenter;
import com.yayangyang.interviewdemo.ui.contract.NewsContract;
import com.yayangyang.interviewdemo.utils.LogUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class NewsPresenter extends RxPresenter<NewsContract.View>
        implements NewsContract.Presenter {
    private InterviewApi interviewApi;

    @Inject
    public NewsPresenter(InterviewApi interviewApi) {
        this.interviewApi = interviewApi;
    }

    @Override
    public void getNews(final int page) {
        Disposable rxDisposable = interviewApi.getNews(page).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<NewsBean>() {
                            @Override
                            public void accept(NewsBean data) throws Exception {
                                LogUtils.e("getRecommend-accept");
                                if (mView != null) {
                                    LogUtils.e("eeeeeeeeeeeee");
                                    mView.showNews(data.data,page);
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
