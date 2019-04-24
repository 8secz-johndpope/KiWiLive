package com.kiwi.phonelive.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Debug;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bigkoo.svprogresshud.SVProgressHUD;
import com.bumptech.glide.Glide;
import com.kiwi.phonelive.R;
import com.kiwi.phonelive.activity.community.Act_ActiveUsers;
import com.kiwi.phonelive.activity.community.Act_CommunityChlideDetails;
import com.kiwi.phonelive.activity.community.Act_CommunityDetails;
import com.kiwi.phonelive.activity.community.Act_VideoImgDetlie;
import com.kiwi.phonelive.bean.CommunitChlideBean;
import com.kiwi.phonelive.bean.CommunitChlideBeanZhu;
import com.kiwi.phonelive.bean.GlideImageLoader;
import com.kiwi.phonelive.bean.SlideHaderBean;
import com.kiwi.phonelive.bean.UserBean;
import com.kiwi.phonelive.bean.VideoBean;
import com.kiwi.phonelive.glide.ImgLoader;
import com.kiwi.phonelive.http.HttpCallback;
import com.kiwi.phonelive.http.HttpUtil;
import com.kiwi.phonelive.utils.ToastUtil;
import com.kiwi.phonelive.utils.WordUtil;
import com.kiwi.phonelive.views.AntGrideVIew;
import com.makeramen.roundedimageview.RoundedImageView;
import com.youth.banner.Banner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by cxf on 2018/9/26.
 */

public class MainCommunityAdapter extends RefreshAdapter<CommunitChlideBeanZhu> {
    private static final int FIRST_LINE = 1;
    private static final int NORMAL = 0;
    private backItem backItem;

    public void setBackItem(MainCommunityAdapter.backItem backItem) {
        this.backItem = backItem;
    }

    Context context1;

    public MainCommunityAdapter(Context context) {
        super(context);
        context1 = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 || position == 1) {
            return FIRST_LINE;
        }
        return NORMAL;
    }

    @Override
    public int getItemCount() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }

    int idextPage = 0;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Vh(mInflater.inflate(R.layout.item_community, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder vh, int position) {
        ((Vh) vh).setData(mList.get(position), position);
    }

    class Vh extends RecyclerView.ViewHolder {
        private RoundedImageView haderImage, had1, had2, had3, had4, item_Image, img1;
        private TextView community_name, community_desc, follow_status, user_count, post_count;
        private AntGrideVIew myGridView;
        private LinearLayout llImg, ll_minImg, item_llbana;
        private RoundedImageView[] img6 = new RoundedImageView[7];
        private Banner banner;
        private RelativeLayout imgll;
        private TextView titleStatus[] = new TextView[6];

        public Vh(View itemView) {
            super(itemView);
            haderImage = itemView.findViewById(R.id.avatar);
            banner = itemView.findViewById(R.id.banner);
            community_name = itemView.findViewById(R.id.community_name);
            community_desc = itemView.findViewById(R.id.community_desc);
            follow_status = itemView.findViewById(R.id.follow_status);
            user_count = itemView.findViewById(R.id.user_count);
            post_count = itemView.findViewById(R.id.post_count);
            ll_minImg = itemView.findViewById(R.id.ll_minImg);
            item_llbana = itemView.findViewById(R.id.item_llbana);
            imgll = itemView.findViewById(R.id.haderImagell);
            had1 = itemView.findViewById(R.id.had1);
            had2 = itemView.findViewById(R.id.had2);
            had3 = itemView.findViewById(R.id.had3);
            had4 = itemView.findViewById(R.id.had4);
            myGridView = itemView.findViewById(R.id.gv_DynamicPics);
            item_Image = itemView.findViewById(R.id.item_Image);
            llImg = itemView.findViewById(R.id.item_img_ll);
            img6[0] = itemView.findViewById(R.id.item_img_lo6_1);
            img6[1] = itemView.findViewById(R.id.item_img_lo6_2);
            img6[2] = itemView.findViewById(R.id.item_img_lo6_3);
            img6[3] = itemView.findViewById(R.id.item_img_lo6_4);
            img6[4] = itemView.findViewById(R.id.item_img_lo6_5);
            img6[5] = itemView.findViewById(R.id.item_img_lo6_6);
            titleStatus[0] = itemView.findViewById(R.id.item_img_tv1);
            titleStatus[1] = itemView.findViewById(R.id.item_img_tv2);
            titleStatus[2] = itemView.findViewById(R.id.item_img_tv3);
            titleStatus[3] = itemView.findViewById(R.id.item_img_tv4);
            titleStatus[4] = itemView.findViewById(R.id.item_img_tv5);
            titleStatus[5] = itemView.findViewById(R.id.item_img_tv6);
        }

        List<String> image = new ArrayList<>();

        void setData(final CommunitChlideBeanZhu bean, final int position) {
            follow_status.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setCircle_follow(mList.get(position).getId(), position);
                }
            });
            if (position == 0) {//设置显示bana图
                image.clear();
                HttpUtil.getSlideHader(new HttpCallback() {
                    @Override
                    public void onSuccess(int code, String msg, String[] info) {
                        List<SlideHaderBean> datas = JSON.parseArray(Arrays.toString(info), SlideHaderBean.class);
                        for (int i = 0; i < datas.size(); i++) {
                            image.add(datas.get(i).getSlide_pic());
                        }
                        //设置图片加载器
                        banner.setImageLoader(new GlideImageLoader());
                        banner.setImages(image).start();
                    }
                });
                item_llbana.setVisibility(View.VISIBLE);
            } else {
                item_llbana.setVisibility(View.GONE);
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    backItem.backItem(position, bean);
                }
            });
            ImgLoader.displayAvatar(bean.getAvatar_icon(), haderImage);
            community_name.setText(bean.getCommunity_name());
            community_desc.setText(bean.getCommunity_desc());
            if (bean.getFollow_status() == 1) {
                follow_status.setText("已关注");
            } else {
                follow_status.setText("+ 关注");

            }
            llImg.setVisibility(View.VISIBLE);
            myGridView.setVisibility(View.GONE);
            item_Image.setVisibility(View.GONE);
            llImg.setVisibility(View.VISIBLE);
            for (int i = 0; i < titleStatus.length; i++) {
                titleStatus[i].setVisibility(View.GONE);
            }
            if (bean.getRecommend_info() != null) {
                for (int i = 0; i < bean.getRecommend_info().size(); i++) {
                    if (i < 6) {
                        if (bean.getRecommend_info().get(idextPage).getVideo_img() != null) {
                            ImgLoader.displayAvatar(bean.getRecommend_info().get(i).getVideo_img(), img6[i]);
                        } else {
                            ImgLoader.displayAvatar(bean.getRecommend_info().get(i).getImgs(), img6[i]);
                        }
                        img6[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                switch (v.getId()) {
                                    case R.id.item_img_lo6_1:
                                        idextPage = 0;
                                        break;
                                    case R.id.item_img_lo6_2:
                                        idextPage = 1;
                                        break;
                                    case R.id.item_img_lo6_3:
                                        idextPage = 2;
                                        break;
                                    case R.id.item_img_lo6_4:
                                        idextPage = 3;
                                        break;
                                    case R.id.item_img_lo6_5:
                                        idextPage = 4;
                                        break;
                                    case R.id.item_img_lo6_6:
                                        idextPage = 5;
                                        break;

                                }
                                Intent intent = new Intent(mContext, Act_VideoImgDetlie.class);
                                intent.putExtra("cm_id", bean.getCm_id() + "");
                                intent.putExtra("uid", bean.getRecommend_info().get(idextPage).getUid());
                                intent.putExtra("post_id", bean.getRecommend_info().get(idextPage).getPost_id());
                                if (bean.getRecommend_info().get(idextPage).getVideo_img() != null) {
                                    intent.putExtra("status", "video");
                                } else {
                                    intent.putExtra("status", "img");
                                }
                                mContext.startActivity(intent);
                            }
                        });
                        titleStatus[i].setVisibility(View.VISIBLE);
                        if (bean.getRecommend_info().get(idextPage).getVideo_img() != null) {
                            titleStatus[i].setText("视频");
                        } else {
                            titleStatus[i].setText("图片");
                        }
                    }
                }
            }
            user_count.setText("关注  " + bean.getUser_count());
            post_count.setText("帖子  " + bean.getUser_count());
            if (bean.getUser_info() == null) {
                had1.setVisibility(View.GONE);
                had2.setVisibility(View.GONE);
                had3.setVisibility(View.GONE);
                had4.setVisibility(View.GONE);
                return;
            }
            //设置小头像显示
            if (bean.getUser_info().size() == 1) {
                ImgLoader.displayAvatar(bean.getUser_info().get(0), had1);
                had1.setVisibility(View.VISIBLE);
                had2.setVisibility(View.GONE);
                had3.setVisibility(View.GONE);
                had4.setVisibility(View.GONE);
            } else if (bean.getUser_info().size() == 2) {
                ImgLoader.displayAvatar(bean.getUser_info().get(0), had1);
                ImgLoader.displayAvatar(bean.getUser_info().get(1), had2);
                had1.setVisibility(View.VISIBLE);
                had2.setVisibility(View.VISIBLE);
                had3.setVisibility(View.GONE);
                had4.setVisibility(View.GONE);
            } else if (bean.getUser_info().size() == 3) {
                ImgLoader.displayAvatar(bean.getUser_info().get(0), had1);
                ImgLoader.displayAvatar(bean.getUser_info().get(1), had2);
                ImgLoader.displayAvatar(bean.getUser_info().get(2), had3);
                had1.setVisibility(View.VISIBLE);
                had2.setVisibility(View.VISIBLE);
                had3.setVisibility(View.VISIBLE);
                had4.setVisibility(View.GONE);
            } else if (bean.getUser_info().size() >= 4) {
                ImgLoader.displayAvatar(bean.getUser_info().get(0), had1);
                ImgLoader.displayAvatar(bean.getUser_info().get(1), had2);
                ImgLoader.displayAvatar(bean.getUser_info().get(2), had3);
                ImgLoader.displayAvatar(bean.getUser_info().get(3), had4);
                had1.setVisibility(View.VISIBLE);
                had2.setVisibility(View.VISIBLE);
                had3.setVisibility(View.VISIBLE);
                had4.setVisibility(View.VISIBLE);
            }
            imgll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1 = new Intent(context1, Act_ActiveUsers.class);
                    intent1.putExtra("cm_id", bean.getCm_id());
                    intent1.putExtra("title", bean.getCommunity_name());
                    context1.startActivity(intent1);
                }
            });

        }
    }

    /**
     * 关注或取消关注
     *
     * @param cm_id
     * @param position
     */
    SVProgressHUD mSVProgressHUD;

    public void setCircle_follow(String cm_id, final int position) {
        mSVProgressHUD = new SVProgressHUD(mContext);
        mSVProgressHUD.showWithStatus("请稍后...");
        mSVProgressHUD.show();
        HttpUtil.getCircle_follow(cm_id, new HttpCallback() {
            @Override
            public void onSuccess(int code, String msg, String[] info) {
                mSVProgressHUD.dismiss();
                if (code == 0) {
                    if (mList.get(position).getFollow_status() == 1) {//取消关注
                        mList.get(position).setFollow_status(0);
                        notifyDataSetChanged();
                        ToastUtil.show(msg);
                    } else {//设置关注
                        mList.get(position).setFollow_status(1);
                        notifyDataSetChanged();
                        ToastUtil.show(msg);
                    }
                } else {
                    if (mList.get(position).getFollow_status() == 1) {//取消关注
                        ToastUtil.show("取消关注失败！");
                    } else {//设置关注
                        ToastUtil.show("关注失败！");
                    }
                }

            }
        });
    }

    public interface backItem {
        void backItem(int position, CommunitChlideBeanZhu bean);
    }
}
