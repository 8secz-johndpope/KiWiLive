package com.kiwi.phonelive.activity.community.bean;

public class VideoImageDetalieBean {

    /**
     * comment : 1111
     * add_time : 1554687502
     * avatar_thumb : http://qiniu.enuos.net/20190325/5c9884edb300a.png
     * user_nicename : admin
     */

    private String comment;
    private int add_time;
    private String avatar_thumb;
    private String user_nicename;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getAdd_time() {
        return add_time;
    }

    public void setAdd_time(int add_time) {
        this.add_time = add_time;
    }

    public String getAvatar_thumb() {
        return avatar_thumb;
    }

    public void setAvatar_thumb(String avatar_thumb) {
        this.avatar_thumb = avatar_thumb;
    }

    public String getUser_nicename() {
        return user_nicename;
    }

    public void setUser_nicename(String user_nicename) {
        this.user_nicename = user_nicename;
    }
}
