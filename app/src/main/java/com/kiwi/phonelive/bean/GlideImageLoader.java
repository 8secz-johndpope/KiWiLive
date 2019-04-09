package com.kiwi.phonelive.bean;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.kiwi.phonelive.R;
import com.youth.banner.loader.ImageLoader;

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        //Glide 加载图片简单用法

        Glide.with(context).load(path).placeholder(R.mipmap.bg_main_item_bottom).into(imageView);
    }
//        @Override
//    public ImageView createImageView(Context context) {
//        //圆角
//        return new RoundAngleImageView(context);
//    }
}
