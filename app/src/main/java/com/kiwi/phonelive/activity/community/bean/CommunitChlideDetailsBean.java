package com.kiwi.phonelive.activity.community.bean;

import java.util.List;

public class CommunitChlideDetailsBean {

    /**
     * active_users : [{"avatar_thumb":"http://qiniu.kiwiapp.vip/20190325/5c9884edb300a.png","uid":"3"},{"avatar_thumb":"http://qiniu.kiwiapp.vip/20190325/5c98843b951a7.png","uid":"24777"},{"avatar_thumb":"http://qiniu.kiwiapp.vip/20190325/5c9884304333c.png","uid":"24778"},{"avatar_thumb":"http://qiniu.kiwiapp.vip/20190312155451_7e60d2d05fa145aa73739dbc502eb1f7?imageView2/2/w/200/h/200","uid":"24763"},{"avatar_thumb":"http://thirdqq.qlogo.cn/100","uid":"24775"}]
     * avatar_icon : https://dwz.cn/yhjyeT6l
     * community_desc : 葬爱家族22333
     * community_name : 葬爱家族222
     * follow_status : 0
     */

    private String avatar_icon;
    private String community_desc;
    private String community_name;
    private int follow_status;
    private List<ActiveUsersBean> active_users;

    public String getAvatar_icon() {
        return avatar_icon;
    }

    public void setAvatar_icon(String avatar_icon) {
        this.avatar_icon = avatar_icon;
    }

    public String getCommunity_desc() {
        return community_desc;
    }

    public void setCommunity_desc(String community_desc) {
        this.community_desc = community_desc;
    }

    public String getCommunity_name() {
        return community_name;
    }

    public void setCommunity_name(String community_name) {
        this.community_name = community_name;
    }

    public int getFollow_status() {
        return follow_status;
    }

    public void setFollow_status(int follow_status) {
        this.follow_status = follow_status;
    }

    public List<ActiveUsersBean> getActive_users() {
        return active_users;
    }

    public void setActive_users(List<ActiveUsersBean> active_users) {
        this.active_users = active_users;
    }

    public static class ActiveUsersBean {
        /**
         * avatar_thumb : http://qiniu.kiwiapp.vip/20190325/5c9884edb300a.png
         * uid : 3
         */

        private String avatar_thumb;
        private String uid;

        public String getAvatar_thumb() {
            return avatar_thumb;
        }

        public void setAvatar_thumb(String avatar_thumb) {
            this.avatar_thumb = avatar_thumb;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }
    }
}
