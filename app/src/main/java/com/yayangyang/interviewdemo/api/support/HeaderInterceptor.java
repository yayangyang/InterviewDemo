package com.yayangyang.interviewdemo.api.support;

import android.text.TextUtils;

import com.yayangyang.interviewdemo.app.ReaderApplication;
import com.yayangyang.interviewdemo.manager.SettingManager;
import com.yayangyang.interviewdemo.utils.AppUtils;
import com.yayangyang.interviewdemo.utils.DeviceUtils;
import com.yayangyang.interviewdemo.utils.LogUtils;
import com.yayangyang.interviewdemo.utils.SharedPreferencesUtil;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Retrofit2 Header拦截器。用于保存和设置Cookies
 *
 * @author yuyh.
 * @date 16/8/6.
 */
public final class HeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        String url = original.url().toString();
        if (!TextUtils.isEmpty(ReaderApplication.Cookie)) {
            Request request = original.newBuilder()
//                    .addHeader("method", "post")
//                    .addHeader("content-type", "application/www-urlencode")
//                    .addHeader("Set-Cookie",ReaderApplication.Cookie)
//                    .addHeader("PHPSESSID",ReaderApplication.Cookie)
                    .addHeader("Cookie",ReaderApplication.Cookie)//传Cookie时key是"Cookie"
                    .build();
            LogUtils.e(ReaderApplication.Cookie+"wwwwwwwwwwwwwwwww");
            return chain.proceed(request);
        }
        Response proceed = chain.proceed(original);

        if(url.contains("/login")){
            ReaderApplication.Cookie=proceed.header("Set-Cookie");
//            ReaderApplication.Cookie = ReaderApplication.Cookie.replace("7200", "7200");
            LogUtils.e(ReaderApplication.Cookie+"1111111111");
            SettingManager.getInstance().saveCookie(ReaderApplication.Cookie);
        }
        return proceed;
    }
}
