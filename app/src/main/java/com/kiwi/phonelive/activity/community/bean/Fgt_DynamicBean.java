package com.kiwi.phonelive.activity.community.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

public class Fgt_DynamicBean implements MultiItemEntity {
    public static final int Stats1 = 1;
    public static final int Stats2 = 2;
    public static final int Stats3 = 3;
    public static final int Stats4 = 4;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * id : 2
     * title : 葬爱家族1
     * addtime : 1555395622
     * type : 3
     * visit_num : 144
     * avatar_thumb : http://qiniu.kiwiapp.vip/20190325/5c9884edb300a.png
     * user_nicename : admin
     * comment_text : [{"comment":"不错不错不错","add_time":"2019-04-02 16:41:26"},{"comment":"aaaaaaaaaa","add_time":"2019-04-03 14:51:11"},{"comment":"这种","add_time":"2019-04-03 18:11:28"},{"comment":"这种","add_time":"2019-04-03 18:11:29"},{"comment":"我们都是这样","add_time":"2019-04-04 09:11:28"}]
     * comment_count : 8
     * video : http://47.254.192.108:18080/jimat/upload/image/201901/12a1157883ae44dcb1118b28f099a4b9.mp4
     * video_img : http://qiniu.kiwiapp.vip/20190325/5c9884edb300a.png
     * text : []
     * imgs : ["http://qiniu.kiwiapp.vip/2019-03-18/5c8f2d3b362e5.jpg","http://qiniu.kiwiapp.vip/2019-03-18/5c8f2d3b6252c.png","http://qiniu.kiwiapp.vip/2019-03-18/5c8f2d3b74cdb.jpg"]
     */
    private String uid;
    private String id;
    private String title;
    private int addtime;
    private String type;
    private String visit_num;
    private String avatar_thumb;
    private String user_nicename;
    private String comment_count;
    private String video;
    private String video_img;
    private List<DynamicBean.PostInfoBean.CommentTextBean> comment_text;
    private List<?> text;
    private List<String> imgs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAddtime() {
        return addtime;
    }

    public void setAddtime(int addtime) {
        this.addtime = addtime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVisit_num() {
        return visit_num;
    }

    public void setVisit_num(String visit_num) {
        this.visit_num = visit_num;
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

    public String getComment_count() {
        return comment_count;
    }

    public void setComment_count(String comment_count) {
        this.comment_count = comment_count;
    }

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

    public List<DynamicBean.PostInfoBean.CommentTextBean> getComment_text() {
        return comment_text;
    }

    public void setComment_text(List<DynamicBean.PostInfoBean.CommentTextBean> comment_text) {
        this.comment_text = comment_text;
    }

    public List<?> getText() {
        return text;
    }

    public void setText(List<?> text) {
        this.text = text;
    }

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }

    public static class CommentTextBean {
        /**
         * comment : 不错不错不错
         * add_time : 2019-04-02 16:41:26
         */

        private String comment;
        private String add_time;

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }
    }

    private int type1;

    public Fgt_DynamicBean(int type) {
        this.type1 = type;
    }

    @Override
    public int getItemType() {
        return type1;
    }
}
