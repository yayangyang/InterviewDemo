package com.yayangyang.interviewdemo.api;

import com.yayangyang.interviewdemo.Bean.ChangeNickNameBean;
import com.yayangyang.interviewdemo.Bean.CheckLogin;
import com.yayangyang.interviewdemo.Bean.NewsBean;
import com.yayangyang.interviewdemo.Bean.user.Login;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface InterviewApiService {

    @GET("/news")
    Observable<NewsBean> getNews(@Query("page") int page);

    @FormUrlEncoded
    @POST("/user/login")
    Observable<Login> login(@Field("username") String username,@Field("password") String password);

    @FormUrlEncoded
    @POST("/user/nickname")
    Observable<ChangeNickNameBean> changeNickName(@Field("nickname") String nickname);

    /**
     * 检查是否登录
     *
     * @param token token(记录登录的令牌)
     * @return
     */
    @GET("user/notification/count")
    Observable<CheckLogin> checkLogin(@Query("token") String token);
}