package com.yayangyang.interviewdemo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yayangyang.interviewdemo.Bean.user.Login;
import com.yayangyang.interviewdemo.R;
import com.yayangyang.interviewdemo.app.ReaderApplication;
import com.yayangyang.interviewdemo.base.BaseActivity;
import com.yayangyang.interviewdemo.base.Constant;
import com.yayangyang.interviewdemo.component.AppComponent;
import com.yayangyang.interviewdemo.transform.GlideRoundTransform;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/11/22.
 */

public class UserInfoActivity extends BaseActivity {

    @BindView(R.id.iv_avatar)
    ImageView iv_avatar;
    @BindView(R.id.tv_phone_number)
    TextView tv_phone_number;
    @BindView(R.id.tv_nickname)
    TextView tv_nickname;
    @BindView(R.id.tv_gender)
    TextView tv_gender;
    @BindView(R.id.tv_signature)
    TextView tv_signature;

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context,UserInfoActivity.class));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_info;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void initToolBar() {
        mCommonToolbar.setTitle("");
        mCommonToolbar.setNavigationIcon(R.drawable.ab_back);
    }

    @Override
    public void initDatas() {
        mCommonToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Constant.CHANGE_DATA);
                finish();
            }
        });
    }

    @Override
    public void configViews() {
        if(ReaderApplication.sLogin!=null&&ReaderApplication.sLogin.data!=null){
            Login.DataBean data = ReaderApplication.sLogin.data;
            Glide.with(mContext).load(data.avatar)
                    .placeholder(R.drawable.avatar_default) .transform(new GlideRoundTransform
                    (mContext,6)).into(iv_avatar);
            tv_nickname.setText(data.nickname);
            tv_gender.setText(data.sex.equals("1")?"男":"女");
            tv_signature.setText(data.whatisup);
        }
    }

    @OnClick(R.id.rl_nickname)
    public void changeNickName(){
        startActivityForResult(new Intent(this,ChangeNickNameActivity.class),0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== Constant.CHANGE_DATA){
            tv_nickname.setText(ReaderApplication.sLogin.data.nickname);
        }
    }

}
