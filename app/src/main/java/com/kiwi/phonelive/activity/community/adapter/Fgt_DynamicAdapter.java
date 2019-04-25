package com.kiwi.phonelive.activity.community.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kiwi.phonelive.R;
import com.kiwi.phonelive.activity.community.Act_VideoImgDetlie;
import com.kiwi.phonelive.activity.community.bean.Fgt_DynamicBean;
import com.kiwi.phonelive.views.AntGrideVIew;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class Fgt_DynamicAdapter extends BaseMultiItemQuickAdapter<Fgt_DynamicBean, BaseViewHolder> {
    private Context context;
    private String cm_id;
    public Fgt_DynamicAdapter(List<Fgt_DynamicBean> data, Context context1,String cm_id1) {
        super(data);
        this.context = context1;
        cm_id=cm_id1;
        addItemType(Fgt_DynamicBean.Stats1, R.layout.item_dynamic_noimg);//无图无视频
        addItemType(Fgt_DynamicBean.Stats2, R.layout.item_dynamic_imgvideo);//有视频
        addItemType(Fgt_DynamicBean.Stats3, R.layout.item_dynamic_gridimg);//1图片
        addItemType(Fgt_DynamicBean.Stats4, R.layout.item_dynamic_img);//多图片
    }

    @Override
    protected void convert(BaseViewHolder helper, final Fgt_DynamicBean item) {
        switch (helper.getItemViewType()) {
            case Fgt_DynamicBean.Stats1://无图无视频
                helper.setText(R.id.item_img_title, item.getTitle())
                        .setText(R.id.name, item.getUser_nicename());
                helper.addOnClickListener(R.id.my_text_item);
                Glide.with(context).load(item.getAvatar_thumb()).placeholder(R.mipmap.bg_test).fitCenter().into((RoundedImageView) helper.getView(R.id.img_square_header));
                break;
            case Fgt_DynamicBean.Stats2://有视频
                helper.setText(R.id.item_img_title, item.getTitle())
                        .setText(R.id.name, item.getUser_nicename());
                helper.addOnClickListener(R.id.item_videoImage);
                Glide.with(context).load(item.getVideo_img()).placeholder(R.mipmap.bg_test).fitCenter().into((RoundedImageView) helper.getView(R.id.item_videoImage));
                Glide.with(context).load(item.getAvatar_thumb()).placeholder(R.mipmap.bg_test).fitCenter().into((RoundedImageView) helper.getView(R.id.img_square_header));
                break;
            case Fgt_DynamicBean.Stats3://1图片
                helper.addOnClickListener(R.id.myImgMax);
                helper.setText(R.id.item_img_title, item.getTitle())
                        .setText(R.id.name, item.getUser_nicename());
                if (item.getImgs() != null) {
                    Glide.with(context).load(item.getImgs().get(0)).placeholder(R.mipmap.bg_test).fitCenter().into((RoundedImageView) helper.getView(R.id.item_videoImage));
                }
                Glide.with(context).load(item.getAvatar_thumb()).placeholder(R.mipmap.bg_test).fitCenter().into((RoundedImageView) helper.getView(R.id.img_square_header));
                break;
            case Fgt_DynamicBean.Stats4://多图片
                helper.setText(R.id.item_img_title, item.getTitle())
                        .setText(R.id.name, item.getUser_nicename());
                Glide.with(context).load(item.getAvatar_thumb()).placeholder(R.mipmap.bg_test).fitCenter().into((RoundedImageView) helper.getView(R.id.img_square_header));
                if (item.getImgs() != null) {
                    AntGrideVIew grideVIew = helper.getView(R.id.gv_DynamicPics);
                    Fgt_DynamicChlideAdapter adapter = new Fgt_DynamicChlideAdapter(context, item.getImgs());
                    grideVIew.setAdapter(adapter);
                    grideVIew.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(mContext, Act_VideoImgDetlie.class);
                            intent.putExtra("cm_id", cm_id + "");
                            intent.putExtra("uid", item.getUid());
                            intent.putExtra("post_id", item.getId());
                            intent.putExtra("status", "img");
                            mContext.startActivity(intent);
                        }
                    });
                }
                helper.addOnClickListener(R.id.item_imgGridview);
                break;
        }


    }
}
