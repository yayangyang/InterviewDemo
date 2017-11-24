package com.yayangyang.interviewdemo.Bean;

import com.yayangyang.interviewdemo.Bean.base.Base;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/11/22.
 */

public class NewsBean extends Base {
    public String code;
    public String message;

    public String page;
    public String page_size;
    public String total_count;

    public ArrayList<DataBean> data;

    public static class DataBean{
        public String vote_count;
        public String reply_count;
        public String title;
        public String desc;
        public String image;
        public String post_time;
    }
}
