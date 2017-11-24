package com.yayangyang.interviewdemo.manager;

import com.yayangyang.interviewdemo.Bean.user.Login;
import com.yayangyang.interviewdemo.base.Constant;
import com.yayangyang.interviewdemo.utils.SharedPreferencesUtil;
import com.yayangyang.interviewdemo.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public class SettingManager {

    private volatile static SettingManager manager;

    public static SettingManager getInstance() {
        return manager != null ? manager : (manager = new SettingManager());
    }

    public boolean isFirstEnter(){
        return SharedPreferencesUtil.getInstance().getBoolean("isFirstEnter",true);
    }

    public void savaFirstEnter(boolean isFirstEnter){
        SharedPreferencesUtil.getInstance().putBoolean("isFirstEnter",isFirstEnter);
    }

    public Login getLoginInfo(){
        return SharedPreferencesUtil.getInstance().getObject("loginInfo",Login.class);
    }

    public void saveLoginInfo(Object loginInfo){
        SharedPreferencesUtil.getInstance().putObject("loginInfo",loginInfo);
    }

    public String getCookie(){
        return SharedPreferencesUtil.getInstance().getString("cookie","");
    }

    public void saveCookie(String cookie){
        SharedPreferencesUtil.getInstance().putString("cookie",cookie);
    }

}
