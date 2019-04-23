package com.kiwi.phonelive.bean;

import java.io.Serializable;
import java.util.List;

public class CommunitChlideBeanZhu implements Serializable {


    /**
     * id : 1
     * community_name : 葬爱家族222
     * notice : 2月15日-2月18日（除夕-正月初三）暂停顺丰特惠、顺丰干配、物流普运的偏远流向收寄服务 (偏远流向：全国与云南，海南，广西，贵州，吉林，黑龙江，内蒙，新疆，宁夏，甘肃，青海，西藏互寄，除省内互寄)；

     2月15日-2月21日（除夕-正月初六）暂停高铁极速达京沪线收寄服务；

     二、时效（服务）调整

     2月5日-2月21日（腊月二十-正月初六）大陆地区主要流向（除偏远区域外的省会城市和副省级城市互寄）不加时；除主要流向外其他流向根据距离远近加时0.5-2天；

     2月15日-2月21日（除夕-正月初六）大陆至港澳正常时效基础上加时2-5天；

     2月12日-2月21日（腊月二十七-正月初六）大陆与台湾互寄流向的快件受台湾海关春节放假影响，正常时效基础上加时3-9天。

     三、服务费提醒
     * community_desc : 葬爱家族22333
     * avatar_icon : https://dwz.cn/yhjyeT6l
     * user_count : 11
     * cm_id : 1
     * uid : 1
     * follow_status : 1
     * user_info : ["http://qiniu.kiwiapp.vip/20190325/5c9884edb300a.png","http://qiniu.kiwiapp.vip/20190325/5c9884edb300a.png","http://qiniu.kiwiapp.vip/20190325/5c98843b951a7.png","http://qiniu.kiwiapp.vip/20190325/5c9884304333c.png","http://qiniu.kiwiapp.vip/20190422144120_c7ba88a87226a3481a809f24f196e24a?imageView2/2/w/200/h/200","http://thirdqq.qlogo.cn/100","http://qiniu.kiwiapp.vip/20190419111643_5fa29f6a25d803ee3989c800e1ecd307?imageView2/2/w/200/h/200","http://qiniu.kiwiapp.vip/20190422203341_e26d77b0c0442e0ab2cb600078121e67?imageView2/2/w/200/h/200","http://qiniu.kiwiapp.vip/20190325/5c98873b65eb1.png","http://thirdqq.qlogo.cn/100","http://qiniu.kiwiapp.vip/20190410143941_b0c120c09b9614f33f52971b83cb457c?imageView2/2/w/200/h/200"]
     * post_count : 69
     * rand_type : 3
     * recommend_info : [{"post_id":"2","video":"http://47.254.192.108:18080/jimat/upload/image/201901/12a1157883ae44dcb1118b28f099a4b9.mp4","video_img":"http://qiniu.kiwiapp.vip/20190325/5c9884edb300a.png","uid":24764,"rand_type":3},{"post_id":"3","video":"http://47.254.192.108:18080/jimat/upload/image/201901/12a1157883ae44dcb1118b28f099a4b9.mp4","video_img":"http://qiniu.kiwiapp.vip/20190325/5c9884edb300a.png","uid":24764,"rand_type":3},{"post_id":"4","video":"http://47.254.192.108:18080/jimat/upload/image/201901/12a1157883ae44dcb1118b28f099a4b9.mp4","video_img":"http://qiniu.kiwiapp.vip/20190325/5c9884edb300a.png","uid":24764,"rand_type":3},{"post_id":"69","video":"http://qiniu.kiwiapp.vip/20190418180954_e48605a2927df61a3496fb23ef3a8770","video_img":"http://qiniu.kiwiapp.vip/20190418180954_bbec5cca20a78cb63e4238cb49bfef19","uid":24764,"rand_type":3},{"post_id":"74","video":"http://qiniu.kiwiapp.vip/20190419142849_5b881212cb952c5f59d939168a978769","video_img":"http://qiniu.kiwiapp.vip/20190419142849_e73101985ca384cb938030b7adf0a507","uid":24764,"rand_type":3},{"post_id":"75","video":"http://qiniu.kiwiapp.vip/20190419155302_7cf3a8023ce568a699f082e9ab53a593","video_img":"http://qiniu.kiwiapp.vip/20190419155302_694ecff150847932eace8158f073a034","uid":24764,"rand_type":3}]
     */

    private String id;
    private String community_name;
    private String notice;
    private String community_desc;
    private String avatar_icon;
    private String user_count;
    private String cm_id;
    private String uid;
    private int follow_status;
    private String post_count;
    private int rand_type;
    private List<String> user_info;
    private List<RecommendInfoBean> recommend_info;

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

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
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

    public String getPost_count() {
        return post_count;
    }

    public void setPost_count(String post_count) {
        this.post_count = post_count;
    }

    public int getRand_type() {
        return rand_type;
    }

    public void setRand_type(int rand_type) {
        this.rand_type = rand_type;
    }

    public List<String> getUser_info() {
        return user_info;
    }

    public void setUser_info(List<String> user_info) {
        this.user_info = user_info;
    }

    public List<RecommendInfoBean> getRecommend_info() {
        return recommend_info;
    }

    public void setRecommend_info(List<RecommendInfoBean> recommend_info) {
        this.recommend_info = recommend_info;
    }

    public static class RecommendInfoBean implements Serializable{
        /**
         * post_id : 2
         * video : http://47.254.192.108:18080/jimat/upload/image/201901/12a1157883ae44dcb1118b28f099a4b9.mp4
         * video_img : http://qiniu.kiwiapp.vip/20190325/5c9884edb300a.png
         * uid : 24764
         * rand_type : 3
         */

        private String post_id;
        private String video;
        private String video_img;
        private int uid;
        private int rand_type;
        private String imgs;

        public String getImgs() {
            return imgs;
        }

        public void setImgs(String imgs) {
            this.imgs = imgs;
        }

        public String getPost_id() {
            return post_id;
        }

        public void setPost_id(String post_id) {
            this.post_id = post_id;
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
