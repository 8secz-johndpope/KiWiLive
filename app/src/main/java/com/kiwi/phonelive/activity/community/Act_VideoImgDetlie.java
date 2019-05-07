package com.kiwi.phonelive.activity.community;

import android.net.Uri;
import android.os.Build;
import android.os.Debug;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.kiwi.phonelive.R;
import com.kiwi.phonelive.activity.AbsActivity;
import com.kiwi.phonelive.activity.community.adapter.VideoImgDetlieAdapter;
import com.kiwi.phonelive.activity.community.bean.Post_CommentBean;
import com.kiwi.phonelive.activity.community.bean.VideoImageDetalieBean;
import com.kiwi.phonelive.activity.community.bean.VideoImgDetileBean;
import com.kiwi.phonelive.adapter.CommunityGridViewAdapter;
import com.kiwi.phonelive.bean.CommunitChlideBeanZhu;
import com.kiwi.phonelive.http.HttpCallback;
import com.kiwi.phonelive.http.HttpUtil;
import com.kiwi.phonelive.utils.ToastUtil;
import com.kiwi.phonelive.views.AntGrideVIew;
import com.makeramen.roundedimageview.RoundedImageView;
import com.tencent.rtmp.ui.TXCloudVideoView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.jzvd.JZVideoPlayerStandard;

/**
 * 图片视频详情
 */
public class Act_VideoImgDetlie extends AbsActivity implements View.OnClickListener {
    private RecyclerView myRecyclerView;
    private EditText input;

    @Override
    protected int getLayoutId() {
        return R.layout.act_videoimgdetlie;
    }

    @Override
    protected void main() {
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        initView();
        initData();
    }

    private String cm_id, status, post_id;
    private View viewHader;
    private RoundedImageView view_img;
    private AntGrideVIew myGridViw;
    private RoundedImageView haderImage;
    private TextView name, number, addTime, follow_status, text, title, pinglunNumber;
    private JZVideoPlayerStandard myVideo;

    public void initView() {
        input = findViewById(R.id.videoimg_input);
        post_id = getIntent().getStringExtra("post_id");
        cm_id = getIntent().getStringExtra("cm_id");
        status = getIntent().getStringExtra("status");
        findViewById(R.id.videoimg_send).setOnClickListener(this);
        myRecyclerView = findViewById(R.id.video_recylcerview);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new VideoImgDetlieAdapter(getBaseContext());
        adapter.setNewData(datas);
        myRecyclerView.setAdapter(adapter);

        if (status.equals("video")) {//视频
            viewHader = LayoutInflater.from(getBaseContext()).inflate(R.layout.view_videodetlie, null);
            title = viewHader.findViewById(R.id.my_notice_title);
            myVideo = viewHader.findViewById(R.id.videoplayer);
        } else if (status.equals("img")) {//图片
            viewHader = LayoutInflater.from(getBaseContext()).inflate(R.layout.view_videoimgdetlie, null);
            title = viewHader.findViewById(R.id.my_notice_title);
            title.setText("图片详情");
            view_img = viewHader.findViewById(R.id.grid_icon);
            myGridViw = viewHader.findViewById(R.id.gv_DynamicPics);
        } else if (status.equals("text")){
            viewHader = LayoutInflater.from(getBaseContext()).inflate(R.layout.view_videotextdetlie, null);
        }
        haderImage = viewHader.findViewById(R.id.avatar);
        name = viewHader.findViewById(R.id.community_name);
        number = viewHader.findViewById(R.id.community_number);
        addTime = viewHader.findViewById(R.id.community_time);
        follow_status = viewHader.findViewById(R.id.follow_status);
        text = viewHader.findViewById(R.id.text);
        pinglunNumber = viewHader.findViewById(R.id.pinglun);

    }

    public void initData() {
        postHader();
//        post_comment();
    }
    /**
     * 获取头部信息
     */
    public void postHader() {
        HttpUtil.post_info(post_id, cm_id, new HttpCallback() {
            @Override
            public void onSuccess(int code, String msg, String[] info) {
                Gson gson = new Gson();

                VideoImgDetileBean bean = gson.fromJson(info[0], VideoImgDetileBean.class);
                if (status.equals("video")) {//视频
                    myVideo.setUp(bean.getVideo()
                            , JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "");
                    Glide.with(getBaseContext()).load(bean.getVideo_img()).into(myVideo.thumbImageView);
                } else if (status.equals("img")) {//图片、
                    if (bean.getImgs() == null) {
                        view_img.setVisibility(View.GONE);
                        myGridViw.setVisibility(View.GONE);
                        return;
                    }
                    if (bean.getImgs().size() == 1) {
                        view_img.setVisibility(View.VISIBLE);
                        myGridViw.setVisibility(View.GONE);
                        Glide.with(getBaseContext()).load(bean.getImgs().get(0)).into(view_img);
                    } else {
                        view_img.setVisibility(View.GONE);
                        myGridViw.setVisibility(View.VISIBLE);
                        CommunityGridViewAdapter adapter = new CommunityGridViewAdapter(mContext, bean.getImgs());
                        myGridViw.setAdapter(adapter);
                    }
                } else {


                }
                Glide.with(getBaseContext()).load(bean.getAvatar_thumb()).into(haderImage);
                name.setText(bean.getUser_nicename());
                number.setText(bean.getVisit_num() + "次浏览");
                if (bean.getText() == null) {
                    text.setText("");
                    text.setVisibility(View.GONE);
                } else {
                    text.setText(bean.getText() + "");
                }
                if (bean.getFollow_status() == 1) {
                    follow_status.setText("已关注");
                } else {
                    follow_status.setText("+ 关注");
                }
                adapter.addHeaderView(viewHader);
            }
        });
    }

    /**
     * 获取评论列表
     */
    List<VideoImageDetalieBean> datas = new ArrayList<>();
    private VideoImgDetlieAdapter adapter;
    public void post_comment() {
        datas.clear();
        HttpUtil.post_comment(post_id, new HttpCallback() {
            @Override
            public void onSuccess(int code, String msg, String[] info) {
                if (msg.equals("该帖子id暂无评论信息")) {
                    return;
                }
                List<VideoImageDetalieBean> data = JSON.parseArray(Arrays.toString(info), VideoImageDetalieBean.class);
                for (int i = 0; i < data.size(); i++) {
                    datas.add(data.get(i));
                }
                pinglunNumber.setText(data.size() + "");
                adapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * 评论当前列表
     *
     * @param text
     */
    public void CommunityComment(String text) {
        datas.clear();
        HttpUtil.CommunityComment(post_id, text, new HttpCallback() {
            @Override
            public void onSuccess(int code, String msg, String[] info) {
                if (msg.equals("添加成功！")) {
                    Toast.makeText(getApplicationContext(), "评论成功！", Toast.LENGTH_SHORT);
                    input.setText("");
                    post_comment();
                    return;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.videoimg_send://发送
                if (TextUtils.isDigitsOnly(input.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "您还没有输入要评论的信息！", Toast.LENGTH_SHORT);
                    return;
                }
                CommunityComment(input.getText().toString());
                break;
        }
    }
}
