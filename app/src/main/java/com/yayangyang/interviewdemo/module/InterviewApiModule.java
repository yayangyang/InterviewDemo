package com.yayangyang.interviewdemo.module;


import com.yayangyang.interviewdemo.api.InterviewApi;
import com.yayangyang.interviewdemo.api.support.CacheInterceptor;
import com.yayangyang.interviewdemo.api.support.HeaderInterceptor;
import com.yayangyang.interviewdemo.base.Constant;


import java.io.File;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

@Module
public class InterviewApiModule {

    @Provides
    public OkHttpClient provideOkHttpClient() {

//        LoggingInterceptor logging = new LoggingInterceptor(new Logger());
//        logging.setLevel(LoggingInterceptor.Level.BODY);
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        //设置缓存路径
        File httpCacheDirectory = new File(Constant.PATH_RESPONSES, "/responses");
        //设置缓存 20M
        Cache cache = new Cache(httpCacheDirectory, 20 * 1024 * 1024);

        OkHttpClient.Builder builder = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true) // 失败重发
                .addInterceptor(new HeaderInterceptor())
//                .addInterceptor(new CacheInterceptor())//没缓存
//                .addNetworkInterceptor(new CacheInterceptor())
                .addInterceptor(logging)
                .cache(cache);
        return builder.build();
    }

    @Provides
    protected InterviewApi provideInterviewService(OkHttpClient okHttpClient) {
        return InterviewApi.getInstance(okHttpClient);
    }
}