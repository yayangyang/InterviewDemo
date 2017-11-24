package com.yayangyang.interviewdemo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import com.yayangyang.interviewdemo.Bean.user.Login;
import com.yayangyang.interviewdemo.R;
import com.yayangyang.interviewdemo.app.ReaderApplication;
import com.yayangyang.interviewdemo.base.BaseActivity;
import com.yayangyang.interviewdemo.base.Constant;
import com.yayangyang.interviewdemo.component.AppComponent;
import com.yayangyang.interviewdemo.component.DaggerMyComponent;
import com.yayangyang.interviewdemo.manager.SettingManager;
import com.yayangyang.interviewdemo.ui.contract.LoginContract;
import com.yayangyang.interviewdemo.ui.presenter.LoginPresenter;
import com.yayangyang.interviewdemo.utils.MD5Encoder;
import com.yayangyang.interviewdemo.utils.NetworkUtils;
import com.yayangyang.interviewdemo.utils.ToastUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/11/22.
 */

public class LoginActivity extends BaseActivity implements LoginContract.View{

    @BindView(R.id.et_phone_number)
    EditText et_phone_number;
    @BindView(R.id.et_password)
    EditText et_password;

    @Inject
    LoginPresenter mPresenter;

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context,LoginActivity.class));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMyComponent.builder()
                .appComponent(appComponent)
                .build().inject(this);
    }

    @Override
    public void initToolBar() {

    }

    @Override
    public void initDatas() {
        mPresenter.attachView(this);
    }

    @Override
    public void configViews() {

    }

    @OnClick(R.id.iv_close)
    public void close(){
        setResult(Constant.LOGIN_FAIL);
        finish();
    }

    @OnClick(R.id.bt_login)
    public void login(){
        String phoneNumber="";
        String password="";
        try {
            phoneNumber=et_phone_number.getText().toString();
            password= MD5Encoder.encode(et_password.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mPresenter.login(phoneNumber, password);//13800000000,123456
    }

    @Override
    public void showError() {
        if(!NetworkUtils.isAvailable(this)){
            ToastUtils.showToast("网络异常");
        }
    }

    @Override
    public void complete() {

    }

    @Override
    public void loginResult(Login login) {
        if(login.code.equals("0")){
            setResult(Constant.LOGIN_SUCCESS);
            ReaderApplication.sLogin=login;
            SettingManager.getInstance().saveLoginInfo(ReaderApplication.sLogin);
            finish();
        }else{
            ToastUtils.showToast(login.message);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter!=null){
            mPresenter.attachView(this);
        }
    }
}
