package com.yayangyang.interviewdemo.Bean.user;


import com.yayangyang.interviewdemo.Bean.NewsBean;
import com.yayangyang.interviewdemo.Bean.base.Base;

import java.io.Serializable;

public class Login extends Base {

    /**
     * "code": "0",
     * "message": "\u767b\u5f55\u6210\u529f",
     * "data": {
     * "id": 10,
     * "username": "13800000000",
     * "nickname": "\u5e78\u798f\u7684\u732a",
     * "sex": 1,
     * "whatisup": "\u6211\u53ef\u4ee5\u9001\u4f60\u56de\u5bb6\u5417\uff0c\u5916\u9762\u8981\u4e0b\u5927\u96e8\u4e86",
     * "avatar": "http:\/\/interview.jbangit.com\/upload\/grass.jpg"
     }
     */

    public String code;
    public String message;

    public DataBean data;
    public static class DataBean implements Serializable{
        public String id;
        public String username;
        public String nickname;
        public String sex;
        public String whatisup;
        public String avatar;
    }

}
