package com.kiwi.phonelive.views;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.kiwi.phonelive.Constants;
import com.kiwi.phonelive.R;
import com.kiwi.phonelive.activity.VideoPlayActivity;
import com.kiwi.phonelive.adapter.MainCommunityAdapter;
import com.kiwi.phonelive.adapter.MainHomeVideoAdapter;
import com.kiwi.phonelive.adapter.RefreshAdapter;
import com.kiwi.phonelive.bean.CommunitChlideBean;
import com.kiwi.phonelive.bean.VideoBean;
import com.kiwi.phonelive.custom.ItemDecoration;
import com.kiwi.phonelive.custom.RefreshView;
import com.kiwi.phonelive.event.VideoDeleteEvent;
import com.kiwi.phonelive.event.VideoScrollPageEvent;
import com.kiwi.phonelive.http.HttpCallback;
import com.kiwi.phonelive.http.HttpUtil;
import com.kiwi.phonelive.interfaces.LifeCycleAdapter;
import com.kiwi.phonelive.interfaces.OnItemClickListener;
import com.kiwi.phonelive.interfaces.VideoScrollDataHelper;
import com.kiwi.phonelive.utils.VideoStorge;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Arrays;
import java.util.List;

/**
 * Created by cxf on 2018/9/22.
 * 社区
 */

public class MainCommunitChlideViewHolder extends AbsMainChildTopViewHolder implements OnItemClickListener<VideoBean> {

    private MainCommunityAdapter mAdapter;

    public MainCommunitChlideViewHolder(Context context, ViewGroup parentView) {
        super(context, parentView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.view_communitchlide;
    }

    @Override
    public void init() {
        super.init();
        mRefreshView = (RefreshView) findViewById(R.id.refreshView);
        mRefreshView.setNoDataLayoutId(R.layout.view_no_data_live_communitchlide);
        mRefreshView.setLayoutManager(new LinearLayoutManager(mContext));
        mRefreshView.setDataHelper(new RefreshView.DataHelper<CommunitChlideBean>() {
            @Override
            public RefreshAdapter<CommunitChlideBean> getAdapter() {
                if (mAdapter == null) {
                    mAdapter = new MainCommunityAdapter(mContext);
                }
                return mAdapter;
            }

            @Override
            public void loadData(int p, HttpCallback callback) {
                HttpUtil.getCommunityList(p, callback);
            }

            @Override
            public List<CommunitChlideBean> processData(String[] info) {
                return JSON.parseArray(Arrays.toString(info), CommunitChlideBean.class);
            }

            @Override
            public void onRefresh(List<CommunitChlideBean> list) {

            }

            @Override
            public void onNoData(boolean noData) {

            }

            @Override
            public void onLoadDataCompleted(int dataCount) {
                if (dataCount < 10) {
                    mRefreshView.setLoadMoreEnable(false);
                } else {
                    mRefreshView.setLoadMoreEnable(true);
                }
            }
        });
    }

    @Override
    public void loadData() {
        if (!isFirstLoadData()) {
            return;
        }
        if (mRefreshView != null) {
            mRefreshView.initData();
        }
    }

    @Override
    public void onItemClick(VideoBean bean, int position) {
    }
}
