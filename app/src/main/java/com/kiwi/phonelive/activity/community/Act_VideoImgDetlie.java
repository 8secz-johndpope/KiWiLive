package com.kiwi.phonelive.activity.community;

import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.kiwi.phonelive.R;
import com.kiwi.phonelive.activity.AbsActivity;
import com.kiwi.phonelive.activity.community.adapter.VideoImgDetlieAdapter;
import com.kiwi.phonelive.activity.community.bean.Post_CommentBean;
import com.kiwi.phonelive.activity.community.bean.VideoImageDetalieBean;
import com.kiwi.phonelive.adapter.CommunityGridViewAdapter;
import com.kiwi.phonelive.bean.CommunitChlideBeanZhu;
import com.kiwi.phonelive.http.HttpCallback;
import com.kiwi.phonelive.http.HttpUtil;
import com.kiwi.phonelive.utils.ToastUtil;
import com.kiwi.phonelive.views.AntGrideVIew;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 图片视频详情
 */
public class Act_VideoImgDetlie extends AbsActivity {
    private RecyclerView myRecyclerView;
    private CommunitChlideBeanZhu beanZhu;

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
    private TextView name;

    public void initView() {
        post_id = getIntent().getStringExtra("post_id");
        cm_id = getIntent().getStringExtra("cm_id");
        status = getIntent().getStringExtra("status");
        myRecyclerView = findViewById(R.id.video_recylcerview);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new VideoImgDetlieAdapter(getBaseContext());
        adapter.setNewData(datas);
        myRecyclerView.setAdapter(adapter);
        beanZhu = (CommunitChlideBeanZhu) getIntent().getSerializableExtra("bean");
        if (status.equals("video")) {//视频


        } else {//图片
            viewHader = LayoutInflater.from(this).inflate(R.layout.view_videoimgdetlie, null);
            view_img = viewHader.findViewById(R.id.grid_icon);
            myGridViw = viewHader.findViewById(R.id.gv_DynamicPics);
            if (beanZhu.getUser_info().size() == 1) {
                view_img.setVisibility(View.VISIBLE);
                myGridViw.setVisibility(View.GONE);
                Glide.with(this).load(beanZhu.getUser_info().get(0)).into(view_img);
            } else {
                view_img.setVisibility(View.GONE);
                myGridViw.setVisibility(View.VISIBLE);
                CommunityGridViewAdapter adapter = new CommunityGridViewAdapter(mContext, beanZhu.getUser_info());
                myGridViw.setAdapter(adapter);
            }
        }
        haderImage = viewHader.findViewById(R.id.avatar);
        name = viewHader.findViewById(R.id.community_name);

        adapter.addHeaderView(viewHader);
    }

    public void initData() {
        postHader();
        post_comment();
    }

    /**
     * 获取头部信息
     */
    public void postHader() {
        HttpUtil.post_info(post_id, cm_id, new HttpCallback() {
            @Override
            public void onSuccess(int code, String msg, String[] info) {
//                Log.e("aa", "------------onSuccess==" + info[0]);
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
                adapter.notifyDataSetChanged();
            }
        });
    }
    /**
     * 评论当前列表
     * @param text
     */
    public void CommunityComment(String text) {
        datas.clear();
        HttpUtil.CommunityComment(post_id, text, new HttpCallback() {
            @Override
            public void onSuccess(int code, String msg, String[] info) {
            }
        });
    }
}
