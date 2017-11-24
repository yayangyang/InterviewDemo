package com.yayangyang.interviewdemo.component;

import android.content.Context;


import com.yayangyang.interviewdemo.api.InterviewApi;
import com.yayangyang.interviewdemo.module.AppModule;
import com.yayangyang.interviewdemo.module.InterviewApiModule;

import dagger.Component;

/**
 * @author yuyh.
 * @date 2016/8/3.
 */
@Component(modules = {AppModule.class, InterviewApiModule.class})
public interface AppComponent {

    Context getContext();

    InterviewApi getInterviewApi();

}