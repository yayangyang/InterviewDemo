package com.yayangyang.interviewdemo.app;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;

import com.yayangyang.interviewdemo.Bean.user.Login;
import com.yayangyang.interviewdemo.base.Constant;
import com.yayangyang.interviewdemo.component.AppComponent;
import com.yayangyang.interviewdemo.component.DaggerAppComponent;
import com.yayangyang.interviewdemo.manager.SettingManager;
import com.yayangyang.interviewdemo.module.AppModule;
import com.yayangyang.interviewdemo.module.InterviewApiModule;
import com.yayangyang.interviewdemo.utils.AppUtils;
import com.yayangyang.interviewdemo.utils.LogUtils;
import com.yayangyang.interviewdemo.utils.SharedPreferencesUtil;

import okhttp3.Cookie;

public class ReaderApplication extends Application {

    private static ReaderApplication sInstance;
    private AppComponent appComponent;


    public static Login sLogin;
    public static String Cookie;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        initCompoent();
        AppUtils.init(this);
//        CrashHandler.getInstance().init(this);
        initPrefs();
        initNightMode();
        //initHciCloud();

        initLoginInfo();
    }

    private void initLoginInfo() {
//        mTencent = Tencent.createInstance("222222", AppUtils.getAppContext());
        sLogin= SettingManager.getInstance().getLoginInfo();
        Cookie=SettingManager.getInstance().getCookie();
    }

    public static ReaderApplication getsInstance() {
        return sInstance;
    }

    private void initCompoent() {
        appComponent = DaggerAppComponent.builder()
                .interviewApiModule(new InterviewApiModule())
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    /**
     * 初始化SharedPreference
     */
    protected void initPrefs() {
        SharedPreferencesUtil.init(getApplicationContext(), getPackageName() + "_preference", Context.MODE_MULTI_PROCESS);
    }

    protected void initNightMode() {
        boolean isNight = SharedPreferencesUtil.getInstance().getBoolean(Constant.ISNIGHT, false);
        LogUtils.d("isNight=" + isNight);
        if (isNight) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

}
