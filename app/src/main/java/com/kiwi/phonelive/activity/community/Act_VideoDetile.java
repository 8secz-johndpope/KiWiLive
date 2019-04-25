package com.kiwi.phonelive.activity.community;

import android.os.Build;
import android.view.View;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.kiwi.phonelive.R;
import com.kiwi.phonelive.activity.AbsActivity;

import cn.jzvd.JZVideoPlayerStandard;

/**
 * 视频播放页
 */
public class Act_VideoDetile extends AbsActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.act_videodetile;
    }

    @Override
    protected void main() {
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        initView();
        initData();
    }

    private String video, img;
    private JZVideoPlayerStandard myVideo;

    public void initView() {
        video = getIntent().getStringExtra("video");
        img = getIntent().getStringExtra("img");
        myVideo = findViewById(R.id.videoplayer);
        myVideo.setUp(video
                , JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "");
        Glide.with(getBaseContext()).load(img).into(myVideo.thumbImageView);
    }

    public void initData() {
        findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
