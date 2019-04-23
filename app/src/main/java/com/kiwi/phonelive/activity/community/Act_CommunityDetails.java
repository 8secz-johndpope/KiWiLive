package com.kiwi.phonelive.activity.community;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.kiwi.phonelive.R;
import com.kiwi.phonelive.activity.AbsActivity;
import com.kiwi.phonelive.activity.community.bean.UserEvent;
import com.kiwi.phonelive.activity.community.fgt.BaseFragment;
import com.kiwi.phonelive.activity.community.fgt.Fgt_Dynamic;
import com.kiwi.phonelive.activity.community.fgt.Fgt_Video;
import com.kiwi.phonelive.bean.CommunitChlideBean;
import com.kiwi.phonelive.custom.ViewPagerIndicator;
import com.kiwi.phonelive.http.HttpCallback;
import com.kiwi.phonelive.http.HttpUtil;
import com.kiwi.phonelive.utils.ToastUtil;
import com.kiwi.phonelive.utils.WordUtil;
import com.makeramen.roundedimageview.RoundedImageView;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.im.android.eventbus.EventBus;

/**
 * 社区详情页
 */
public class Act_CommunityDetails extends AbsActivity implements View.OnClickListener {
    private ViewPagerIndicator mIndicator;
    public ViewPager mViewPager;
    private List<BaseFragment> fragments = new ArrayList<>();
    private SmartRefreshLayout mRefreshLayout;
    private String cm_id;
    private RoundedImageView avatar;
    private TextView name, community_desc, follow_status, post_count, user_count, notice;
    SVProgressHUD mSVProgressHUD;

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
        cm_id = getIntent().getStringExtra("cm_id");
        mSVProgressHUD = new SVProgressHUD(this);
        mSVProgressHUD.showWithStatus("请稍后...");
        avatar = findViewById(R.id.avatar);
        notice = findViewById(R.id.notice);
        notice.setOnClickListener(this);
        findViewById(R.id.my_Details).setOnClickListener(this);
        findViewById(R.id.communitydetails_fabu).setOnClickListener(this);
        name = findViewById(R.id.community_name);
        community_desc = findViewById(R.id.community_desc);
        mRefreshLayout = findViewById(R.id.refreshLayout);
        user_count = findViewById(R.id.user_count);
        follow_status = findViewById(R.id.follow_status);
        post_count = findViewById(R.id.post_count);
        mIndicator = findViewById(R.id.indicator);
        mViewPager = findViewById(R.id.viewPager);
    }

    int prePosition = 0;

    public void initData() {
        //        内容跟随偏移
        mRefreshLayout.setEnableHeaderTranslationContent(true);
        //设置 Header 为 Material风格
        mRefreshLayout.setRefreshHeader(new MaterialHeader(this).setShowBezierWave(false));
        //设置 Footer 为 球脉冲
        mRefreshLayout.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));
        mRefreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener() {
            @Override
            public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                EventBus.getDefault().post(new UserEvent(prePosition + "", "2"));
                refreshLayout.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                EventBus.getDefault().post(new UserEvent(prePosition + "", "1"));
                refreshLayout.finishRefresh();
            }
        });
        follow_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bean != null) {
                    setCircle_follow();
                }
            }
        });
        fragments.add(new Fgt_Dynamic());
        fragments.add(new Fgt_Video());
        mViewPager.setCurrentItem(0);
        mViewPager.setAdapter(new ContentAdapter(getSupportFragmentManager()));
        mViewPager.setOffscreenPageLimit(2);
        mIndicator.setTitles(new String[]{
                WordUtil.getString(R.string.dynamic_name),
                WordUtil.getString(R.string.video_name)
        });
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                prePosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mIndicator.setViewPager(mViewPager);
        postHader();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_Details://详情
                Intent intent1 = new Intent(Act_CommunityDetails.this, Act_CommunityChlideDetails.class);
                intent1.putExtra("cm_id", cm_id);
                intent1.putExtra("title", bean.getCommunity_name());
                startActivity(intent1);
                break;
            case R.id.notice://公告
                if (bean == null) {
                    return;
                }
                Intent intent = new Intent(Act_CommunityDetails.this, Act_AnnouncementDetails.class);
                intent.putExtra("notice", bean.getNotice());
                intent.putExtra("title", bean.getCommunity_name());
                startActivity(intent);
                break;
            case R.id.communitydetails_fabu://发布
                startActivity(new Intent(getApplicationContext(), Act_Publish_Post.class));
                break;
        }

    }

    /**
     * 内容页的适配器
     */
    private class ContentAdapter extends FragmentPagerAdapter {

        public ContentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

    CommunitChlideBean bean;

    public void postHader() {
        HttpUtil.getCommunity_info(cm_id, new HttpCallback() {
            @Override
            public void onSuccess(int code, String msg, String[] info) {
                mSVProgressHUD.dismiss();
                Gson gson = new Gson();
                bean = gson.fromJson(info[0], CommunitChlideBean.class);
                Glide.with(Act_CommunityDetails.this).load(bean.getAvatar_icon()).into(avatar);
                name.setText(bean.getCommunity_name());
                community_desc.setText(bean.getCommunity_desc());
                if (bean.getFollow_status() == 1) {
                    follow_status.setText("已关注");
                } else {
                    follow_status.setText("+ 关注");
                }
                post_count.setText(bean.getPost_count());
                user_count.setText(bean.getUser_count());
                notice.setText(bean.getNotice());
            }
        });
    }

    /**
     * 关注或取消关注
     */
    public void setCircle_follow() {
        HttpUtil.getCircle_follow(cm_id, new HttpCallback() {
            @Override
            public void onSuccess(int code, String msg, String[] info) {
                mSVProgressHUD.dismiss();
                if (code == 0) {
                    if (bean.getFollow_status() == 1) {//取消关注
                        bean.setFollow_status(0);
                        ToastUtil.show(msg);
                    } else {//设置关注
                        bean.setFollow_status(1);
                        ToastUtil.show(msg);
                    }
                    if (bean.getFollow_status() == 1) {
                        follow_status.setText("已关注");
                    } else {
                        follow_status.setText("+ 关注");
                    }
                } else {
                    if (bean.getFollow_status() == 1) {//取消关注
                        ToastUtil.show("取消关注失败！");
                    } else {//设置关注
                        ToastUtil.show("关注失败！");
                    }
                }

            }
        });
    }
}
