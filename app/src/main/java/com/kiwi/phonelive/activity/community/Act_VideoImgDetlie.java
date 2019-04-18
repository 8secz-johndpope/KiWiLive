package com.kiwi.phonelive.activity.community;

import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.kiwi.phonelive.R;
import com.kiwi.phonelive.activity.AbsActivity;
import com.kiwi.phonelive.activity.community.bean.Post_CommentBean;
import com.kiwi.phonelive.bean.CommunitChlideBeanZhu;
import com.kiwi.phonelive.http.HttpCallback;
import com.kiwi.phonelive.http.HttpUtil;
import com.kiwi.phonelive.utils.ToastUtil;

import java.util.Arrays;
import java.util.List;

/**
 * 图片视频详情
 */
public class Act_VideoImgDetlie extends AbsActivity {
    private RecyclerView myRecyclerView;

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

    private String cm_id, status;

    public void initView() {
        cm_id = getIntent().getStringExtra("cm_id");
        status = getIntent().getStringExtra("status");
        myRecyclerView = findViewById(R.id.video_recylcerview);
        if (status.equals("video")) {//视频

        } else {//图片


        }

    }

    public void initData() {
        postHader();
        post_comment();
    }


    /**
     * 获取头部信息
     */
    public void postHader() {
        HttpUtil.post_info(cm_id, new HttpCallback() {
            @Override
            public void onSuccess(int code, String msg, String[] info) {

            }
        });
    }


    /**
     * 获取评论列表
     */
    public void post_comment() {
        HttpUtil.post_comment(cm_id, new HttpCallback() {
            @Override
            public void onSuccess(int code, String msg, String[] info) {
                List<Post_CommentBean> datas = JSON.parseArray(Arrays.toString(info), Post_CommentBean.class);
                Log.e("aa", "--------获取评论列表==" + datas.size());
            }
        });
    }
}
