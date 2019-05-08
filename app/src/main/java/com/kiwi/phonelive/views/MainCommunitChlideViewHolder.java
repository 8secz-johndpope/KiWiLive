package com.kiwi.phonelive.views;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.bigkoo.svprogresshud.SVProgressHUD;
import com.kiwi.phonelive.R;
import com.kiwi.phonelive.activity.community.Act_CommunityDetails;
import com.kiwi.phonelive.adapter.MainCommunityAdapter;
import com.kiwi.phonelive.adapter.RefreshAdapter;
import com.kiwi.phonelive.bean.CommunitChlideBeanZhu;
import com.kiwi.phonelive.custom.ItemDecoration;
import com.kiwi.phonelive.custom.RefreshView;
import com.kiwi.phonelive.http.HttpCallback;
import com.kiwi.phonelive.http.HttpUtil;

import java.util.Arrays;
import java.util.List;

/**
 * Created by cxf on 2018/9/22.
 * 社区
 */
public class MainCommunitChlideViewHolder extends AbsMainChildTopViewHolder implements MainCommunityAdapter.backItem {

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
        mRefreshView.setLayoutManager(new GridLayoutManager(mContext, 1, GridLayoutManager.VERTICAL, false));
        ItemDecoration decoration = new ItemDecoration(mContext, 0x00000000, 5, 5);
        decoration.setOnlySetItemOffsetsButNoDraw(true);
        mRefreshView.setDataHelper(new RefreshView.DataHelper<CommunitChlideBeanZhu>() {
            @Override
            public RefreshAdapter<CommunitChlideBeanZhu> getAdapter() {
                if (mAdapter == null) {
                    mAdapter = new MainCommunityAdapter(mContext);
                    mAdapter.setBackItem(MainCommunitChlideViewHolder.this);
                }
                return mAdapter;
            }

            @Override
            public void loadData(int p, HttpCallback callback) {

                HttpUtil.getCommunityList(p, callback);
            }

            @Override
            public List<CommunitChlideBeanZhu> processData(String[] info) {
                return JSON.parseArray(Arrays.toString(info), CommunitChlideBeanZhu.class);
            }

            @Override
            public void onRefresh(List<CommunitChlideBeanZhu> list) {

            }

            @Override
            public void onNoData(boolean noData) {

            }

            @Override
            public void onLoadDataCompleted(int dataCount) {

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
    public void backItem(int position, CommunitChlideBeanZhu bean) {
        Intent intent = new Intent(mContext, Act_CommunityDetails.class);
        intent.putExtra("cm_id", bean.getCm_id() + "");
        mContext.startActivity(intent);
    }
}
