package com.yayangyang.interviewdemo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.yayangyang.interviewdemo.Bean.ChangeNickNameBean;
import com.yayangyang.interviewdemo.R;
import com.yayangyang.interviewdemo.app.ReaderApplication;
import com.yayangyang.interviewdemo.base.BaseActivity;
import com.yayangyang.interviewdemo.base.Constant;
import com.yayangyang.interviewdemo.component.AppComponent;
import com.yayangyang.interviewdemo.component.DaggerMyComponent;
import com.yayangyang.interviewdemo.ui.contract.ChangeNickNameContract;
import com.yayangyang.interviewdemo.ui.fragment.MineFragment;
import com.yayangyang.interviewdemo.ui.presenter.ChangeNickNamePresenter;
import com.yayangyang.interviewdemo.utils.NetworkUtils;
import com.yayangyang.interviewdemo.utils.ToastUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/11/22.
 */

public class ChangeNickNameActivity extends BaseActivity implements ChangeNickNameContract.View{

    @BindView(R.id.et_nickname)
    EditText et_nickname;
    @BindView(R.id.bt_save)
    Button bt_save;
    @BindView(R.id.progressbar)
    ProgressBar progressbar;

    @Inject
    ChangeNickNamePresenter mPresenter;

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context,ChangeNickNameActivity.class));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_chage_nickname;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMyComponent.builder()
                .appComponent(appComponent)
                .build().inject(this);
    }

    @Override
    public void initToolBar() {
        mCommonToolbar.setTitle("");
        mCommonToolbar.setNavigationIcon(R.drawable.ab_back);
    }

    @Override
    public void initDatas() {
        mPresenter.attachView(this);
    }

    @Override
    public void configViews() {

    }

    @Override
    public void showError() {
        if(!NetworkUtils.isAvailable(this)){
            ToastUtils.showToast("网络异常");
        }
        progressbar.setVisibility(View.GONE);
    }

    @Override
    public void complete() {
        progressbar.setVisibility(View.GONE);
    }

    @Override
    public void changeNickNameSuccess(ChangeNickNameBean data) {
        //需要登录的操作,若是cookie过期需要将login和cookie设置为初始值
        //保存昵称
        ReaderApplication.sLogin.data.nickname=et_nickname.getText().toString();
        ToastUtils.showToast(data.message);
        setResult(Constant.CHANGE_DATA);
        finish();
    }

    @OnClick(R.id.bt_save)
    public void save(){
        bt_save.setEnabled(false);
        progressbar.setVisibility(View.VISIBLE);
        mPresenter.changeNickName(et_nickname.getText().toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter!=null){
            mPresenter.detachView();
        }
    }

}
