package com.kiwi.phonelive.bean;

import java.util.List;

public class CommunitChlideBean {
            /**
             * id : 1
             * community_name : 葬爱家族222
             * community_desc : 葬爱家族22333
             * avatar_icon : https://dwz.cn/yhjyeT6l
             * imgs : [{"imgs":"https://dwz.cn/dY7IFC4z","uid":1},{"imgs":"https://dwz.cn/Iq90HyHC","uid":1},{"imgs":"https://dwz.cn/6GoOyy0w","uid":1},{"imgs":"https://dwz.cn/eYbY3fPb","uid":1},{"imgs":"https://dwz.cn/yhjyeT6l","uid":1},{"imgs":"https://dwz.cn/hc0roOLG","uid":1}]
             * user_count : 9
             * follow_status : 1
             * user_info : ["http://qiniu.enuos.net/20190325/5c9884edb300a.png","http://qiniu.enuos.net/20190325/5c9884edb300a.png","http://qiniu.enuos.net/20190325/5c98843b951a7.png","http://qiniu.enuos.net/20190325/5c9884304333c.png"]
             * post_count : 20
             */

            private String id;
            private String community_name;
            private String community_desc;
            private String avatar_icon;
            private String user_count;
            private int follow_status;
            private String post_count;
            private List<ImgsBean> imgs;
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

            public List<ImgsBean> getImgs() {
                return imgs;
            }

            public void setImgs(List<ImgsBean> imgs) {
                this.imgs = imgs;
            }

            public List<String> getUser_info() {
                return user_info;
            }

            public void setUser_info(List<String> user_info) {
                this.user_info = user_info;
            }

            public static class ImgsBean {
                /**
                 * imgs : https://dwz.cn/dY7IFC4z
                 * uid : 1
                 */

                private String imgs;
                private int uid;

                public String getImgs() {
                    return imgs;
                }

                public void setImgs(String imgs) {
                    this.imgs = imgs;
                }

                public int getUid() {
                    return uid;
                }

                public void setUid(int uid) {
                    this.uid = uid;
                }
            }
}
