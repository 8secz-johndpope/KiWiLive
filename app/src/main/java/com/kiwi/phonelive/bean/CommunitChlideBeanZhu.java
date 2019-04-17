package com.kiwi.phonelive.bean;

import java.util.List;

public class CommunitChlideBeanZhu {

    /**
     * id : 1
     * community_name : 葬爱家族222
     * community_desc : 葬爱家族22333
     * avatar_icon : https://dwz.cn/yhjyeT6l
     * user_count : 5
     * cm_id : 1
     * uid : 1
     * follow_status : 0
     * recommend_info : [{"video":"http://47.254.192.108:18080/jimat/upload/image/201901/12a1157883ae44dcb1118b28f099a4b9.mp4","video_img":"http://qiniu.kiwiapp.vip/20190325/5c9884edb300a.png","uid":1,"rand_type":3},{"video":"http://47.254.192.108:18080/jimat/upload/image/201901/12a1157883ae44dcb1118b28f099a4b9.mp4","video_img":"http://qiniu.kiwiapp.vip/20190325/5c9884edb300a.png","uid":1,"rand_type":3},{"video":"http://47.254.192.108:18080/jimat/upload/image/201901/12a1157883ae44dcb1118b28f099a4b9.mp4","video_img":"http://qiniu.kiwiapp.vip/20190325/5c9884edb300a.png","uid":1,"rand_type":3}]
     * user_info : ["http://qiniu.kiwiapp.vip/20190325/5c9884edb300a.png","http://qiniu.kiwiapp.vip/20190325/5c98843b951a7.png","http://qiniu.kiwiapp.vip/20190325/5c9884304333c.png","http://qiniu.kiwiapp.vip/20190312155451_7e60d2d05fa145aa73739dbc502eb1f7?imageView2/2/w/200/h/200","http://thirdqq.qlogo.cn/100"]
     */

    private String id;
    private String community_name;
    private String community_desc;
    private String avatar_icon;
    private String user_count;
    private String cm_id;
    private String uid;
    private int follow_status;
    private List<RecommendInfoBean> recommend_info;
    private List<String> user_info;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommunity_name() {
        return community_name;
    }

    public void setCommunity_name(String community_name) {
        this.community_name = community_name;
    }

    public String getCommunity_desc() {
        return community_desc;
    }

    public void setCommunity_desc(String community_desc) {
        this.community_desc = community_desc;
    }

    public String getAvatar_icon() {
        return avatar_icon;
    }

    public void setAvatar_icon(String avatar_icon) {
        this.avatar_icon = avatar_icon;
    }

    public String getUser_count() {
        return user_count;
    }

    public void setUser_count(String user_count) {
        this.user_count = user_count;
    }

    public String getCm_id() {
        return cm_id;
    }

    public void setCm_id(String cm_id) {
        this.cm_id = cm_id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getFollow_status() {
        return follow_status;
    }

    public void setFollow_status(int follow_status) {
        this.follow_status = follow_status;
    }

    public List<RecommendInfoBean> getRecommend_info() {
        return recommend_info;
    }

    public void setRecommend_info(List<RecommendInfoBean> recommend_info) {
        this.recommend_info = recommend_info;
    }

    public List<String> getUser_info() {
        return user_info;
    }

    public void setUser_info(List<String> user_info) {
        this.user_info = user_info;
    }

    public static class RecommendInfoBean {
        /**
         * video : http://47.254.192.108:18080/jimat/upload/image/201901/12a1157883ae44dcb1118b28f099a4b9.mp4
         * video_img : http://qiniu.kiwiapp.vip/20190325/5c9884edb300a.png
         * uid : 1
         * rand_type : 3
         */

        private String video;
        private String video_img;
        private int uid;
        private int rand_type;

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }

        public String getVideo_img() {
            return video_img;
        }

        public void setVideo_img(String video_img) {
            this.video_img = video_img;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getRand_type() {
            return rand_type;
        }

        public void setRand_type(int rand_type) {
            this.rand_type = rand_type;
        }
    }
}
