package com.kiwi.phonelive.activity.community.bean;

public class CommunityDetailsBean {

    /**
     * avatar_icon : https://dwz.cn/yhjyeT6l
     * cm_id : 1
     * community_desc : 葬爱家族22333
     * community_name : 葬爱家族222
     * follow_status : 0
     * notice : 2月15日-2月18日（除夕-正月初三）暂停顺丰特惠、顺丰干配、物流普运的偏远流向收寄服务 (偏远流向：全国与云南，海南，广西，贵州，吉林，黑龙江，内蒙，新疆，宁夏，甘肃，青海，西藏互寄，除省内互寄)；

     2月15日-2月21日（除夕-正月初六）暂停高铁极速达京沪线收寄服务；

     二、时效（服务）调整

     2月5日-2月21日（腊月二十-正月初六）大陆地区主要流向（除偏远区域外的省会城市和副省级城市互寄）不加时；除主要流向外其他流向根据距离远近加时0.5-2天；

     2月15日-2月21日（除夕-正月初六）大陆至港澳正常时效基础上加时2-5天；

     2月12日-2月21日（腊月二十七-正月初六）大陆与台湾互寄流向的快件受台湾海关春节放假影响，正常时效基础上加时3-9天。

     三、服务费提醒
     * post_count : 37
     * user_count : 5
     */

    private String avatar_icon;
    private int cm_id;
    private String community_desc;
    private String community_name;
    private int follow_status;
    private String notice;
    private String post_count;
    private String user_count;

    public String getAvatar_icon() {
        return avatar_icon;
    }

    public void setAvatar_icon(String avatar_icon) {
        this.avatar_icon = avatar_icon;
    }

    public int getCm_id() {
        return cm_id;
    }

    public void setCm_id(int cm_id) {
        this.cm_id = cm_id;
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

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getPost_count() {
        return post_count;
    }

    public void setPost_count(String post_count) {
        this.post_count = post_count;
    }

    public String getUser_count() {
        return user_count;
    }

    public void setUser_count(String user_count) {
        this.user_count = user_count;
    }
}
