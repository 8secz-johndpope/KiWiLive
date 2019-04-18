package com.kiwi.phonelive.activity.community.bean;

public class Active_usersBean {

    /**
     * uid : 3
     * user_nicename :
     * avatar_thumb : http://qiniu.kiwiapp.vip/20190325/5c9884edb300a.png
     * signature :
     * follow_status : 0
     */

    private String uid;
    private String user_nicename;
    private String avatar_thumb;
    private String signature;
    private int follow_status;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUser_nicename() {
        return user_nicename;
    }

    public void setUser_nicename(String user_nicename) {
        this.user_nicename = user_nicename;
    }

    public String getAvatar_thumb() {
        return avatar_thumb;
    }

    public void setAvatar_thumb(String avatar_thumb) {
        this.avatar_thumb = avatar_thumb;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public int getFollow_status() {
        return follow_status;
    }

    public void setFollow_status(int follow_status) {
        this.follow_status = follow_status;
    }
}
