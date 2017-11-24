package com.yayangyang.interviewdemo.api;

import android.text.TextUtils;

import com.yayangyang.interviewdemo.Bean.ChangeNickNameBean;
import com.yayangyang.interviewdemo.Bean.CheckLogin;
import com.yayangyang.interviewdemo.Bean.NewsBean;
import com.yayangyang.interviewdemo.Bean.user.Login;
import com.yayangyang.interviewdemo.app.ReaderApplication;
import com.yayangyang.interviewdemo.base.Constant;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.FieldMap;
import retrofit2.http.Query;

public class InterviewApi {

    public static InterviewApi instance;

    private InterviewApiService service;

    public InterviewApi(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.API_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 添加Rx适配器
                .addConverterFactory(GsonConverterFactory.create()) // 添加Gson转换器
                .client(okHttpClient)
                .build();
        service = retrofit.create(InterviewApiService.class);
    }

    public static InterviewApi getInstance(OkHttpClient okHttpClient) {
        if (instance == null)
            instance = new InterviewApi(okHttpClient);
        return instance;
    }

    public Observable<NewsBean> getNews(int page) {
        return service.getNews(page);
    }

    public Observable<Login> login(String username,String password) {
        return service.login(username,password);
    }

    public Observable<ChangeNickNameBean> changeNickName(String nickname) {
        return service.changeNickName(nickname);
    }

    public Observable<CheckLogin> checkLogin(String token){
        return service.checkLogin(token);
    }

}
