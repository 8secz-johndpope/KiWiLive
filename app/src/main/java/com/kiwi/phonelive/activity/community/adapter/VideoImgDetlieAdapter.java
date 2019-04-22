package com.kiwi.phonelive.activity.community.adapter;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kiwi.phonelive.R;
import com.kiwi.phonelive.activity.community.bean.VideoImageDetalieBean;
import com.kiwi.phonelive.utils.DateUtils;
import com.makeramen.roundedimageview.RoundedImageView;

import java.text.ParseException;

public class VideoImgDetlieAdapter extends BaseQuickAdapter<VideoImageDetalieBean, BaseViewHolder> {
    private Context context1;

    public VideoImgDetlieAdapter(Context context) {
        super(R.layout.item_videoimgdetlie);
        context1 = context;
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, VideoImageDetalieBean item) {
        Glide.with(context1).load(item.getAvatar_thumb()).into((RoundedImageView) viewHolder.getView(R.id.avatar));
        try {
            viewHolder.setText(R.id.community_name, item.getUser_nicename())
                    .setText(R.id.community_context, item.getComment())
                    .setText(R.id.community_desc, DateUtils.showTimeText(DateUtils.getDate(item.getAdd_time() + "", DateUtils.YMDHMS_BREAK_HALF)));
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }
}
