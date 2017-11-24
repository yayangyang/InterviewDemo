package com.yayangyang.interviewdemo.ui.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import com.yayangyang.interviewdemo.R;
import com.yayangyang.interviewdemo.app.ReaderApplication;
import com.yayangyang.interviewdemo.base.BaseActivity;
import com.yayangyang.interviewdemo.base.Constant;
import com.yayangyang.interviewdemo.component.AppComponent;
import com.yayangyang.interviewdemo.ui.fragment.MineFragment;
import com.yayangyang.interviewdemo.ui.fragment.TopNewsFragment;
import com.yayangyang.interviewdemo.utils.LogUtils;
import com.yayangyang.interviewdemo.utils.ToastUtils;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    private Fragment[] mFragments=new Fragment[2];

    @BindView(R.id.fl_content)
    FrameLayout fl_content;
    @BindView(R.id.rg_group)
    RadioGroup rg_group;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void initToolBar() {

    }

    @Override
    public void initDatas() {
        changeFragment(0);
    }

    @Override
    public void configViews() {

        rg_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.top_news){
                    ToastUtils.showToast("topnews");
                    changeFragment(0);
                }else if(checkedId==R.id.mine){
                    ToastUtils.showToast("mine");
                    changeFragment(1);
                }
            }
        });
    }

    public void changeFragment(int num){
        if(ReaderApplication.sLogin!=null){
            LogUtils.e(ReaderApplication.sLogin.message+"wwwwwwww");
        }
        LogUtils.e(ReaderApplication.Cookie+"wwwwwwww");
        if((num==1&&ReaderApplication.sLogin==null)||
                (num==1&& TextUtils.isEmpty(ReaderApplication.Cookie))){
            startActivityForResult(new Intent(this,LoginActivity.class), 0);
            return;
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(mFragments[num]==null){
            if(num==0){
                mFragments[num]=new TopNewsFragment();
            }else if(num==1){
                mFragments[num]=new MineFragment();
            }
            transaction.add(R.id.fl_content,mFragments[num]);
        }
        for(int i=0;i<mFragments.length;i++){
            if(mFragments[i]!=null&&i!=num){
                transaction.hide(mFragments[i]);
            }else if(i==num){
                transaction.show(mFragments[i]);
            }
        }
        transaction.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==Constant.LOGIN_SUCCESS){
            changeFragment(1);
        }else if(resultCode==Constant.LOGIN_FAIL){
            RadioButton radioButton = (RadioButton) rg_group.getChildAt(0);
            radioButton.setChecked(true);
        }
    }
}
