package com.kiwi.phonelive.activity.community.fgt;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.kiwi.phonelive.R;
import com.kiwi.phonelive.activity.community.Act_CommunityDetails;
import com.kiwi.phonelive.activity.community.Act_VideoDetile;
import com.kiwi.phonelive.activity.community.Act_VideoImgDetlie;
import com.kiwi.phonelive.activity.community.adapter.Fgt_DynamicAdapter;
import com.kiwi.phonelive.activity.community.adapter.Fgt_DynamicChlideAdapter;
import com.kiwi.phonelive.activity.community.adapter.Fgt_DynamicChlideVideoAdapter;
import com.kiwi.phonelive.activity.community.bean.DynamicBean;
import com.kiwi.phonelive.activity.community.bean.Fgt_DynamicBean;
import com.kiwi.phonelive.activity.community.bean.Fgt_VideoBean;
import com.kiwi.phonelive.activity.community.bean.UserEvent;
import com.kiwi.phonelive.bean.CommunitChlideBean;
import com.kiwi.phonelive.http.HttpCallback;
import com.kiwi.phonelive.http.HttpUtil;
import com.kiwi.phonelive.views.AntGrideVIew;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Arrays;
import java.util.List;

import cn.jpush.im.android.eventbus.EventBus;

/**
 * 社区动态详情页=》视频子列表
 */
public class Fgt_Video extends BaseFragment {
    @Override
    public View initView() {
        return View.inflate(mContext, R.layout.fgt_dynamicvideo, null);
    }

    private String cm_id;
    private AntGrideVIew grideVIew;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        grideVIew = rootView.findViewById(R.id.gv_DynamicPics);
        grideVIew.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, Act_VideoDetile.class);
                intent.putExtra("video", datas.get(position).getVideo());
                intent.putExtra("img", datas.get(position).getVideo_img());
                mContext.startActivity(intent);
            }
        });
        return rootView;
    }
    /**
     * 订阅的过程中，默认是在主线程中用到的
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(UserEvent event) {
        if (event.getType().equals("1")) {
            if (event.getSatus().equals("1")) {//下拉刷新

            } else if (event.getSatus().equals("2")) {//上拉加载更多
            }
        }

    }

    @Override
    public void initData() {
        EventBus.getDefault().register(this);
        cm_id = getActivity().getIntent().getStringExtra("cm_id");
        postBackData();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //注销注册
        EventBus.getDefault().unregister(this);
    }
    List<Fgt_VideoBean> datas;
    public void postBackData() {
        HttpUtil.getcommunity_video(cm_id, new HttpCallback() {
            @Override
            public void onSuccess(int code, String msg, String[] info) {
                datas = JSON.parseArray(Arrays.toString(info), Fgt_VideoBean.class);
                Fgt_DynamicChlideVideoAdapter adapter=new  Fgt_DynamicChlideVideoAdapter(getContext(),datas);
                grideVIew.setAdapter(adapter);
            }
        });
    }
}
