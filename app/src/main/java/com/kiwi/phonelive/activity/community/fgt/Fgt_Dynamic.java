package com.kiwi.phonelive.activity.community.fgt;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.kiwi.phonelive.R;
import com.kiwi.phonelive.activity.community.Act_CommunityDetails;
import com.kiwi.phonelive.activity.community.Act_VideoImgDetlie;
import com.kiwi.phonelive.activity.community.adapter.Fgt_DynamicAdapter;
import com.kiwi.phonelive.activity.community.bean.DynamicBean;
import com.kiwi.phonelive.activity.community.bean.Fgt_DynamicBean;
import com.kiwi.phonelive.activity.community.bean.UserEvent;
import com.kiwi.phonelive.bean.CommunitChlideBean;
import com.kiwi.phonelive.http.HttpCallback;
import com.kiwi.phonelive.http.HttpUtil;

import cn.jpush.im.android.eventbus.EventBus;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 社区动态详情页=》动态子列表
 */
public class Fgt_Dynamic extends BaseFragment implements BaseQuickAdapter.OnItemChildClickListener {
    private String cm_id;
    private RecyclerView myRecyclerview;

    @Override
    public View initView() {
        return View.inflate(mContext, R.layout.fgt_dynamic, null);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        myRecyclerview = rootView.findViewById(R.id.myRecyclerView);
        return rootView;
    }

    /**
     * 订阅的过程中，默认是在主线程中用到的
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(UserEvent event) {
        if (event.getType().equals("0")) {
            if (event.getSatus().equals("1")) {//下拉刷新
                datas.clear();
                com_page = 1;
                postBackData();
            } else if (event.getSatus().equals("2")) {//上拉加载更多
                com_page++;
                postBackData();
            }
        }
    }

    @Override
    public void initData() {
        EventBus.getDefault().register(this);
        cm_id = getActivity().getIntent().getStringExtra("cm_id");
        myRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        postBackData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //注销注册
        EventBus.getDefault().unregister(this);
    }

    Fgt_DynamicBean fgt_dynamicBean;
    private int com_page = 1;
    List<Fgt_DynamicBean> datas = new ArrayList<>();
    private Fgt_DynamicAdapter adapter;

    public void postBackData() {
        HttpUtil.getCommunity_get_post(com_page, cm_id, new HttpCallback() {
            @Override
            public void onSuccess(int code, String msg, String[] info) {
                Gson gson = new Gson();
                DynamicBean bean = gson.fromJson(info[0], DynamicBean.class);
                for (int i = 0; i < bean.getPost_info().size(); i++) {
                    if (bean.getPost_info().get(i).getVideo() == null && bean.getPost_info().get(i).getImgs() == null) {//无图无视频
                        fgt_dynamicBean = new Fgt_DynamicBean(Fgt_DynamicBean.Stats1);
                    } else if (bean.getPost_info().get(i).getVideo() != null) {//有视频
                        fgt_dynamicBean = new Fgt_DynamicBean(Fgt_DynamicBean.Stats2);
                    } else if (bean.getPost_info().get(i).getImgs() != null) {
                        if (bean.getPost_info().get(i).getImgs().size() == 1) {//1图片
                            fgt_dynamicBean = new Fgt_DynamicBean(Fgt_DynamicBean.Stats3);
                        } else {//多图片
                            fgt_dynamicBean = new Fgt_DynamicBean(Fgt_DynamicBean.Stats4);
                        }
                    }
                    fgt_dynamicBean.setId(bean.getPost_info().get(i).getId());
                    fgt_dynamicBean.setTitle(bean.getPost_info().get(i).getTitle());
                    fgt_dynamicBean.setAddtime(bean.getPost_info().get(i).getAddtime());
                    fgt_dynamicBean.setType(bean.getPost_info().get(i).getType());
                    fgt_dynamicBean.setVisit_num(bean.getPost_info().get(i).getVisit_num());
                    fgt_dynamicBean.setAvatar_thumb(bean.getPost_info().get(i).getAvatar_thumb());
                    fgt_dynamicBean.setUser_nicename(bean.getPost_info().get(i).getUser_nicename());
                    fgt_dynamicBean.setComment_text(bean.getPost_info().get(i).getComment_text());
                    fgt_dynamicBean.setComment_count(bean.getPost_info().get(i).getComment_count());
                    fgt_dynamicBean.setVideo_img(bean.getPost_info().get(i).getVideo_img());
                    fgt_dynamicBean.setImgs(bean.getPost_info().get(i).getImgs());
                    datas.add(fgt_dynamicBean);
                }
                if (adapter == null) {
                    adapter = new Fgt_DynamicAdapter(datas, getContext());
                    adapter.setOnItemChildClickListener(Fgt_Dynamic.this);
                    myRecyclerview.setAdapter(adapter);
                } else {
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.item_videoImage:
//                Intent intent = new Intent(mContext, Act_VideoImgDetlie.class);
//                intent.putExtra("cm_id", cm_id+ "");
//                intent.putExtra("uid", datas.get(position).getId().getUid());
//                intent.putExtra("post_id",datas.get(position).getPost_id());
//                if (datas.get(position).getVideo_img() != null) {
//                    intent.putExtra("status", "video");
//                } else {
//                    intent.putExtra("status", "img");
//                }
//                mContext.startActivity(intent);
                break;
        }
    }

}
