package com.yayangyang.interviewdemo.component;


import com.yayangyang.interviewdemo.ui.activity.ChangeNickNameActivity;
import com.yayangyang.interviewdemo.ui.activity.LoginActivity;
import com.yayangyang.interviewdemo.ui.activity.SplashActivity;
import com.yayangyang.interviewdemo.ui.activity.UserDetailActivity;
import com.yayangyang.interviewdemo.ui.fragment.MineFragment;
import com.yayangyang.interviewdemo.ui.fragment.TopNewsFragment;

import dagger.Component;

/**
 * Created by Administrator on 2017/11/5.
 */

@Component(dependencies = AppComponent.class)
public interface MyComponent {

    TopNewsFragment inject(TopNewsFragment topNewsFragment);

    LoginActivity inject(LoginActivity loginActivity);

    UserDetailActivity inject(UserDetailActivity userDetailActivity);

    ChangeNickNameActivity inject(ChangeNickNameActivity changeNickNameActivity);

    SplashActivity inject(SplashActivity splashActivity);

}
