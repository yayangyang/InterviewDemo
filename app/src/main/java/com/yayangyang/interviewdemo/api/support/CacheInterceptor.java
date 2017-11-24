package com.yayangyang.interviewdemo.api.support;

import android.text.TextUtils;

import com.yayangyang.interviewdemo.app.ReaderApplication;
import com.yayangyang.interviewdemo.manager.SettingManager;
import com.yayangyang.interviewdemo.utils.AppUtils;
import com.yayangyang.interviewdemo.utils.LogUtils;
import com.yayangyang.interviewdemo.utils.NetworkUtils;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Retrofit2 Header拦截器。用于缓存数据
 */
public final class CacheInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        LogUtils.e("Network:"+NetworkUtils.isAvailable(AppUtils.getAppContext()));
        if (!NetworkUtils.isAvailable(AppUtils.getAppContext())) {
            original = original.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
            LogUtils.e("no network");
        }

        Response response = chain.proceed(original);

        if (NetworkUtils.isAvailable(AppUtils.getAppContext())) {
            int maxAge = 5 * 60; // 有网络时 设置缓存超时时间0个小时
            LogUtils.e("has network maxAge="+maxAge);
            response.newBuilder()
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                    .build();
        } else {
            LogUtils.e("network error");
            int maxStale = 60 * 60 * 24 * 28; // 无网络时，设置超时为4周
            LogUtils.e("has maxStale="+maxStale);
            response.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .removeHeader("Pragma")
                    .build();
            LogUtils.e("response build maxStale="+maxStale);
        }
        return response;
    }
}
