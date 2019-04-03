package com.kiwi.phonelive.views;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.kiwi.phonelive.R;
import com.kiwi.phonelive.adapter.ViewPagerAdapter;
import com.kiwi.phonelive.interfaces.MainAppBarExpandListener;
import com.kiwi.phonelive.utils.WordUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 视频
 */

public class MainVideoViewHolder extends AbsMainParentViewHolder {

    public MainVideoViewHolder(Context context, ViewGroup parentView) {
        super(context, parentView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.view_main_near;
    }

    @Override
    public void init() {
        super.init();
        mViewHolders = new AbsMainChildTopViewHolder[1];
//        mViewHolders[0] = new MainNearNearViewHolder(mContext, mViewPager);//附近
        mViewHolders[0] = new MainHomeVideoViewHolder(mContext, mViewPager);
        MainAppBarExpandListener expandListener = new MainAppBarExpandListener() {
            @Override
            public void onExpand(boolean expand) {
                if (mViewPager != null) {
                    mViewPager.setCanScroll(expand);
                    mViewHolders[mViewPager.getCurrentItem()].setCanRefresh(expand);
                }
            }
        };
        List<View> list = new ArrayList<>();
        for (AbsMainChildTopViewHolder vh : mViewHolders) {
            vh.setAppBarExpandListener(expandListener);
            list.add(vh.getContentView());
        }
        mViewPager.setAdapter(new ViewPagerAdapter(list));
        mIndicator.setTitles(new String[]{
                WordUtil.getString(R.string.video)
        });
        mIndicator.setViewPager(mViewPager);
        mViewHolders[0].addTopView(mTopView);
    }

}
