package com.yayangyang.interviewdemo.ui.fragment;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yayangyang.interviewdemo.R;
import com.yayangyang.interviewdemo.app.ReaderApplication;
import com.yayangyang.interviewdemo.base.BaseFragment;
import com.yayangyang.interviewdemo.base.Constant;
import com.yayangyang.interviewdemo.component.AppComponent;
import com.yayangyang.interviewdemo.transform.GlideRoundTransform;
import com.yayangyang.interviewdemo.ui.activity.ChangeNickNameActivity;
import com.yayangyang.interviewdemo.ui.activity.UserInfoActivity;
import com.yayangyang.interviewdemo.utils.LogUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/11/22.
 */

public class MineFragment extends BaseFragment{

    @BindView(R.id.iv_avatar)
    ImageView iv_avatar;
    @BindView(R.id.tv_nickname)
    TextView tv_nickname;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void attachView() {

    }

    @Override
    public void initDatas() {

    }

    @Override
    public void configViews() {
        LogUtils.e("configViews");
        if(ReaderApplication.sLogin.data!=null){
            Glide.with(mContext).load(ReaderApplication.sLogin.data.avatar)
                    .placeholder(R.drawable.avatar_default) .transform(new GlideRoundTransform
                    (mContext,6)).into(iv_avatar);
        }
    }

    @OnClick(R.id.iv_user_info)
    public void userInfo(){
        startActivityForResult(new Intent(getActivity(),UserInfoActivity.class),0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== Constant.CHANGE_DATA){
            tv_nickname.setText(ReaderApplication.sLogin.data.nickname);
        }
    }
}
