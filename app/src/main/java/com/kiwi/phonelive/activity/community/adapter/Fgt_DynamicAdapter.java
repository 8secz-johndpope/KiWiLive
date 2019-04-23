package com.kiwi.phonelive.activity.community.adapter;

import android.content.Context;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kiwi.phonelive.R;
import com.kiwi.phonelive.activity.community.bean.Fgt_DynamicBean;
import com.kiwi.phonelive.views.AntGrideVIew;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class Fgt_DynamicAdapter extends BaseMultiItemQuickAdapter<Fgt_DynamicBean, BaseViewHolder> {
    private Context context;

    public Fgt_DynamicAdapter(List<Fgt_DynamicBean> data, Context context1) {
        super(data);
        this.context = context1;
        addItemType(Fgt_DynamicBean.Stats1, R.layout.item_dynamic_noimg);//无图无视频
        addItemType(Fgt_DynamicBean.Stats2, R.layout.item_dynamic_imgvideo);//有视频
        addItemType(Fgt_DynamicBean.Stats3, R.layout.item_dynamic_gridimg);//1图片
        addItemType(Fgt_DynamicBean.Stats4, R.layout.item_dynamic_img);//多图片
    }

    @Override
    protected void convert(BaseViewHolder helper, Fgt_DynamicBean item) {
        switch (helper.getItemViewType()) {
            case Fgt_DynamicBean.Stats1://无图无视频

                helper.setText(R.id.item_img_title, item.getTitle())
                        .setText(R.id.name, item.getUser_nicename());

                Glide.with(context).load(item.getAvatar_thumb()).placeholder(R.mipmap.bg_test).into((RoundedImageView) helper.getView(R.id.img_square_header));
                break;
            case Fgt_DynamicBean.Stats2://有视频
                helper.setText(R.id.item_img_title, item.getTitle())
                        .setText(R.id.name, item.getUser_nicename());
                helper.addOnClickListener(R.id.item_videoImage);
                Glide.with(context).load(item.getVideo_img()).placeholder(R.mipmap.bg_test).into((RoundedImageView) helper.getView(R.id.item_videoImage));
                Glide.with(context).load(item.getAvatar_thumb()).placeholder(R.mipmap.bg_test).into((RoundedImageView) helper.getView(R.id.img_square_header));
                break;
            case Fgt_DynamicBean.Stats3://1图片
                helper.setText(R.id.item_img_title, item.getTitle())
                        .setText(R.id.name, item.getUser_nicename());
                if (item.getImgs() != null) {
                    Glide.with(context).load(item.getImgs().get(0)).placeholder(R.mipmap.bg_test).into((RoundedImageView) helper.getView(R.id.item_videoImage));
                }
                Glide.with(context).load(item.getAvatar_thumb()).placeholder(R.mipmap.bg_test).into((RoundedImageView) helper.getView(R.id.img_square_header));
                break;
            case Fgt_DynamicBean.Stats4://多图片
                helper.setText(R.id.item_img_title, item.getTitle())
                        .setText(R.id.name, item.getUser_nicename());
                Glide.with(context).load(item.getAvatar_thumb()).placeholder(R.mipmap.bg_test).into((RoundedImageView) helper.getView(R.id.img_square_header));
                if (item.getImgs() != null) {
                    AntGrideVIew grideVIew = helper.getView(R.id.gv_DynamicPics);
                    Fgt_DynamicChlideAdapter adapter = new Fgt_DynamicChlideAdapter(context, item.getImgs());
                    grideVIew.setAdapter(adapter);
                }

                break;
        }


    }
}
