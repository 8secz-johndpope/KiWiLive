package com.kiwi.phonelive.activity.community;

import android.os.Build;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;

import com.kiwi.phonelive.R;
import com.kiwi.phonelive.activity.AbsActivity;
import com.kiwi.phonelive.adapter.ViewPagerAdapter;
import com.kiwi.phonelive.custom.ViewPagerIndicator;
import com.kiwi.phonelive.utils.WordUtil;
import com.kiwi.phonelive.views.AbsMainChildTopViewHolder;
import com.kiwi.phonelive.views.MainHomeFollowViewHolder;
import com.kiwi.phonelive.views.MainHomeLiveViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 社区详情页
 */
public class Act_CommunityDetails extends AbsActivity {
    private ViewPagerIndicator mIndicator;
    private ViewPager mViewPager;

    @Override
    protected int getLayoutId() {
        return R.layout.act_communitydetails;
    }

    @Override
    protected void main() {
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        initView();
        initData();
    }

    public void initView() {
        mIndicator = findViewById(R.id.indicator);
        mViewPager = findViewById(R.id.viewPager);

    }

    protected AbsMainChildTopViewHolder[] mViewHolders;

    public void initData() {
        List<View> list = new ArrayList<>();
        mViewHolders = new AbsMainChildTopViewHolder[2];
        mViewHolders[0] = new MainHomeFollowViewHolder(mContext, mViewPager);
        mViewHolders[1] = new MainHomeLiveViewHolder(mContext, mViewPager);
        list.add(mViewHolders[0].getContentView());
        list.add(mViewHolders[1].getContentView());
        mViewPager.setAdapter(new ViewPagerAdapter(list));
        mIndicator.setTitles(new String[]{
                WordUtil.getString(R.string.dynamic_name),
                WordUtil.getString(R.string.video_name)
        });
        mIndicator.setViewPager(mViewPager);

    }

}
