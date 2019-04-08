package com.kiwi.phonelive.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kiwi.phonelive.R;
import com.kiwi.phonelive.bean.CommunitChlideBean;
import com.kiwi.phonelive.bean.UserBean;
import com.kiwi.phonelive.bean.VideoBean;
import com.kiwi.phonelive.glide.ImgLoader;
import com.kiwi.phonelive.views.AntGrideVIew;
import com.makeramen.roundedimageview.RoundedImageView;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by cxf on 2018/9/26.
 */

public class MainCommunityAdapter extends RefreshAdapter<CommunitChlideBean> {

    private static final int FIRST_LINE = 1;
    private static final int NORMAL = 0;

    private View.OnClickListener mOnClickListener;

    public MainCommunityAdapter(Context context) {
        super(context);
        mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!canClick()) {
                    return;
                }
                Object tag = v.getTag();
                if (tag != null) {
                    int position = (int) tag;
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(mList.get(position), position);
                    }
                }
            }
        };
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 || position == 1) {
            return FIRST_LINE;
        }
        return NORMAL;
    }

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
        private LinearLayout llImg;
        private RoundedImageView[] img6 = new RoundedImageView[7];

        public Vh(View itemView) {
            super(itemView);
            haderImage = itemView.findViewById(R.id.avatar);
            community_name = itemView.findViewById(R.id.community_name);
            community_desc = itemView.findViewById(R.id.community_desc);
            follow_status = itemView.findViewById(R.id.follow_status);
            user_count = itemView.findViewById(R.id.user_count);
            post_count = itemView.findViewById(R.id.post_count);
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
            itemView.setOnClickListener(mOnClickListener);
        }

        void setData(CommunitChlideBean bean, int position) {
            ImgLoader.displayAvatar(bean.getAvatar_icon(), haderImage);
            community_name.setText(bean.getCommunity_name());
            community_desc.setText(bean.getCommunity_desc());
            if (bean.getFollow_status() == 1) {
                follow_status.setText("已关注");
            } else {
                follow_status.setText("+ 关注");
            }
            if (bean.getImgs().size() == 1) {//一张图片
                myGridView.setVisibility(View.GONE);
                item_Image.setVisibility(View.VISIBLE);
                llImg.setVisibility(View.GONE);
                ImgLoader.displayAvatar(bean.getImgs().get(0).getImgs(), item_Image);
            } else if (bean.getImgs().size() == 6) {//6张图片
                myGridView.setVisibility(View.GONE);
                item_Image.setVisibility(View.GONE);
                llImg.setVisibility(View.VISIBLE);
                for (int i = 0; i < bean.getImgs().size(); i++) {
                    ImgLoader.displayAvatar(bean.getImgs().get(i).getImgs(), img6[i]);
                }
            } else {//其它情况
                myGridView.setVisibility(View.VISIBLE);
                llImg.setVisibility(View.GONE);
                item_Image.setVisibility(View.GONE);
                CommunityGridViewAdapter adapter = new CommunityGridViewAdapter(mContext, bean.getImgs());
                myGridView.setAdapter(adapter);
            }
            user_count.setText("关注  " + bean.getUser_count());
            post_count.setText("帖子  " + bean.getUser_count());
            //设置小头像显示
            switch (bean.getUser_info().size()) {
                case 1:
                    ImgLoader.displayAvatar(bean.getUser_info().get(0), had1);
                    had1.setVisibility(View.VISIBLE);
                    had2.setVisibility(View.GONE);
                    had3.setVisibility(View.GONE);
                    had4.setVisibility(View.GONE);
                    break;
                case 2:
                    ImgLoader.displayAvatar(bean.getUser_info().get(0), had1);
                    ImgLoader.displayAvatar(bean.getUser_info().get(1), had2);
                    had1.setVisibility(View.VISIBLE);
                    had2.setVisibility(View.VISIBLE);
                    had3.setVisibility(View.GONE);
                    had4.setVisibility(View.GONE);
                    break;
                case 3:
                    ImgLoader.displayAvatar(bean.getUser_info().get(0), had1);
                    ImgLoader.displayAvatar(bean.getUser_info().get(1), had2);
                    ImgLoader.displayAvatar(bean.getUser_info().get(2), had3);
                    had1.setVisibility(View.VISIBLE);
                    had2.setVisibility(View.VISIBLE);
                    had3.setVisibility(View.VISIBLE);
                    had4.setVisibility(View.GONE);
                    break;
                case 5:
                    ImgLoader.displayAvatar(bean.getUser_info().get(0), had1);
                    ImgLoader.displayAvatar(bean.getUser_info().get(1), had2);
                    ImgLoader.displayAvatar(bean.getUser_info().get(2), had3);
                    ImgLoader.displayAvatar(bean.getUser_info().get(3), had4);
                    had1.setVisibility(View.VISIBLE);
                    had2.setVisibility(View.VISIBLE);
                    had3.setVisibility(View.VISIBLE);
                    had4.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }
}
