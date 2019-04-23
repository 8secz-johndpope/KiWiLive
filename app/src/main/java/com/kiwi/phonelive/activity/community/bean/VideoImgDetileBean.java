package com.kiwi.phonelive.activity.community.bean;

import java.util.List;

public class VideoImgDetileBean {
            /**
             * addtime : 1556021192
             * visit_num : 9
             * uid : 24784
             * type : 2
             * comment_count : 0
             * avatar_thumb : http://qiniu.kiwiapp.vip/20190422203341_e26d77b0c0442e0ab2cb600078121e67?imageView2/2/w/200/h/200
             * user_nicename : 手机用户9320
             * follow_status : 1
             * imgs : ["http://qiniu.kiwiapp.vip/2019-03-18/5c8f2d3b362e5.jpg","http://qiniu.kiwiapp.vip/2019-03-18/5c8f2d3b6252c.png","http://qiniu.kiwiapp.vip/2019-03-18/5c8f2d3b74cdb.jpg"]
             * text : null
             * video : http://47.254.192.108:18080/jimat/upload/image/201901/12a1157883ae44dcb1118b28f099a4b9.mp4
             * video_img : http://qiniu.kiwiapp.vip/20190325/5c9884edb300a.png
             */

            private int addtime;
            private int visit_num;
            private String uid;
            private String type;
            private String comment_count;
            private String avatar_thumb;
            private String user_nicename;
            private int follow_status;
            private Object text;
            private String video;
            private String video_img;
            private List<String> imgs;

            public int getAddtime() {
                return addtime;
            }

            public void setAddtime(int addtime) {
                this.addtime = addtime;
            }

            public int getVisit_num() {
                return visit_num;
            }

            public void setVisit_num(int visit_num) {
                this.visit_num = visit_num;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getComment_count() {
                return comment_count;
            }

            public void setComment_count(String comment_count) {
                this.comment_count = comment_count;
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

            public int getFollow_status() {
                return follow_status;
            }

            public void setFollow_status(int follow_status) {
                this.follow_status = follow_status;
            }

            public Object getText() {
                return text;
            }

            public void setText(Object text) {
                this.text = text;
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

            public List<String> getImgs() {
                return imgs;
            }

            public void setImgs(List<String> imgs) {
                this.imgs = imgs;
            }
}
