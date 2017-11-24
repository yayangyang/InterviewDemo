package com.yayangyang.interviewdemo.Bean;

import com.yayangyang.interviewdemo.Bean.base.Base;

/**
 * Created by Administrator on 2017/11/22.
 */

public class ChangeNickNameBean extends Base {

    /**
     * "code": "0",
     * "data": {
     * "avatar": "http://interview.jbangit.com/upload/grass.jpg",
     * "id": 10,
     * "nickname": "h",
     * "sex": 1,
     * "username": "13800000000",
     * "whatisup": "我可以送你回家吗，外面要下大雨了"
     * },
     * "message": "修改昵称成功"
     */

    public String code;
    public String message;

    public DataBean data;

    public class DataBean{
        public String avatar;
        public String id;
        public String nickname;
        public String sex;
        public String username;
        public String whatisup;
    }
}
