package com.kiwi.phonelive.activity.community.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kiwi.phonelive.R;
import com.kiwi.phonelive.activity.community.bean.Active_usersBean;
import com.kiwi.phonelive.glide.ImgLoader;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class ActiveUsersAdapter extends BaseQuickAdapter<Active_usersBean, BaseViewHolder> {
    private Context context;

    public ActiveUsersAdapter(@Nullable List<Active_usersBean> data, Context context1) {
        super(R.layout.item_activeusers, data);
        this.context = context1;
    }

    @Override
    protected void convert(BaseViewHolder helper, Active_usersBean item) {
        ImgLoader.displayAvatar(item.getAvatar_thumb(), (RoundedImageView) helper.getView(R.id.avatar));
        helper.setText(R.id.ActiveUsersAdapter_name, item.getUser_nicename())
                .setText(R.id.ActiveUsersAdapter_desc, item.getSignature());
        if (item.getFollow_status() == 1) {
            helper.setText(R.id.follow_status, "已关注");
        } else {
            helper.setText(R.id.follow_status, "+ 关注");
        }
        helper.addOnClickListener(R.id.follow_status);
        helper.addOnClickListener(R.id.user_geren);
    }
}
