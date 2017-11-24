package com.yayangyang.interviewdemo.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;


import com.yayangyang.interviewdemo.R;
import com.yayangyang.interviewdemo.app.ReaderApplication;
import com.yayangyang.interviewdemo.base.BasePermissionActivity;
import com.yayangyang.interviewdemo.component.AppComponent;
import com.yayangyang.interviewdemo.manager.SettingManager;
import com.yayangyang.interviewdemo.utils.LogUtils;
import com.yayangyang.interviewdemo.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends BasePermissionActivity{

    @BindView(R.id.tvSkip)
    TextView tvSkip;

    private int count=0;

    private Runnable allow,refuse;
    private String[] permissionCollection={
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE
    };

    private Handler mHandler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            LogUtils.e("handleMessage");
            if(msg.what==0){
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }

//            ReaderApplication.Cookie="";
//            ReaderApplication.sLogin=null;
//            SettingManager.getInstance().saveLoginInfo(ReaderApplication.sLogin);
//            SettingManager.getInstance().saveCookie(ReaderApplication.Cookie);

            finish();
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        if (SettingManager.getInstance().isFirstEnter()) {
            init();
            requeset();
        } else {
            goHome();
        }
    }

    private void init() {
        allow=new Runnable(){
            @Override
            public void run() {
                //同意
                LogUtils.e("同意");

                count++;
                if(count==permissionCollection.length){
                    goHome();
                }
            }
        };
        refuse=new Runnable(){
            @Override
            public void run() {
                //拒绝
                LogUtils.e("拒绝");

                count++;
                if(count==permissionCollection.length){
                    goHome();
                }
            }
        };
    }

    private void requeset() {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//6.0后诸多权限才需要申请
            LogUtils.e("eeeeeeeeeeeeee");
            ToastUtils.showToast("同意权限,不然可能有错误");
            requestPermission(0, permissionCollection,allow,refuse);
        }else{
            LogUtils.e("goHome");
            goHome();
        }

        SettingManager.getInstance().savaFirstEnter(false);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        LogUtils.e("onActivityResult");
        super.onActivityResult(requestCode, resultCode, data);
    }

    private synchronized void goHome() {
        mHandler.sendEmptyMessageDelayed(0,1000);
    }

    public void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
