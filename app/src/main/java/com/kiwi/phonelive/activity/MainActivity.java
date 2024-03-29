package com.kiwi.phonelive.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kiwi.phonelive.AppConfig;
import com.kiwi.phonelive.Constants;
import com.kiwi.phonelive.R;
import com.kiwi.phonelive.adapter.ViewPagerAdapter;
import com.kiwi.phonelive.bean.BonusBean;
import com.kiwi.phonelive.bean.ConfigBean;
import com.kiwi.phonelive.bean.LiveBean;
import com.kiwi.phonelive.custom.TabButtonGroup;
import com.kiwi.phonelive.dialog.MainStartDialogFragment;
import com.kiwi.phonelive.http.HttpCallback;
import com.kiwi.phonelive.http.HttpConsts;
import com.kiwi.phonelive.http.HttpUtil;
import com.kiwi.phonelive.im.ImMessageUtil;
import com.kiwi.phonelive.im.ImPushUtil;
import com.kiwi.phonelive.im.ImUnReadCountEvent;
import com.kiwi.phonelive.interfaces.CommonCallback;
import com.kiwi.phonelive.interfaces.MainAppBarLayoutListener;
import com.kiwi.phonelive.interfaces.MainStartChooseCallback;
import com.kiwi.phonelive.presenter.CheckLivePresenter;
import com.kiwi.phonelive.utils.DialogUitl;
import com.kiwi.phonelive.utils.DpUtil;
import com.kiwi.phonelive.utils.LiveStorge;
import com.kiwi.phonelive.utils.LocationUtil;
import com.kiwi.phonelive.utils.ProcessResultUtil;
import com.kiwi.phonelive.utils.ToastUtil;
import com.kiwi.phonelive.utils.VersionUtil;
import com.kiwi.phonelive.utils.VideoStorge;
import com.kiwi.phonelive.utils.WordUtil;
import com.kiwi.phonelive.views.AbsMainViewHolder;
import com.kiwi.phonelive.views.BonusViewHolder;
import com.kiwi.phonelive.views.MainCommunityViewHolder;
import com.kiwi.phonelive.views.MainHomeViewHolder;
import com.kiwi.phonelive.views.MainListViewHolder;
import com.kiwi.phonelive.views.MainMeViewHolder;
import com.kiwi.phonelive.views.MainNearViewHolder;
import com.kiwi.phonelive.views.MainVideoViewHolder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AbsActivity {

    private ViewGroup mRootView;
    private TabButtonGroup mTabButtonGroup;
    private ViewPager mViewPager;
    private AbsMainViewHolder[] mViewHolders;
    private View mBottom;
    private int mDp70;
    private ProcessResultUtil mProcessResultUtil;
    private CheckLivePresenter mCheckLivePresenter;
    private boolean mLoad;
    private long mLastClickBackTime;//上次点击back键的时间
    private ImageView btn_start;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    protected void main() {
        btn_start = findViewById(R.id.btn_start);
        boolean showInvite = getIntent().getBooleanExtra(Constants.SHOW_INVITE, false);
        mRootView = (ViewGroup) findViewById(R.id.rootView);
        mTabButtonGroup = (TabButtonGroup) findViewById(R.id.tab_group);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPager.setOffscreenPageLimit(4);
        mViewHolders = new AbsMainViewHolder[4];
        mViewHolders[0] = new MainHomeViewHolder(mContext, mViewPager);
//        mViewHolders[1] = new MainNearViewHolder(mContext, mViewPager);//附近
        mViewHolders[1] = new MainVideoViewHolder(mContext, mViewPager);//视频
//        mViewHolders[2] = new MainListViewHolder(mContext, mViewPager);//排行
        mViewHolders[2]=new MainCommunityViewHolder(mContext,mViewPager);//社区
        mViewHolders[3] = new MainMeViewHolder(mContext, mViewPager);
        List<View> list = new ArrayList<>();
        MainAppBarLayoutListener appBarLayoutListener = new MainAppBarLayoutListener() {
            @Override
            public void onOffsetChanged(float rate) {
                float curY = mBottom.getTranslationY();
                float targetY = rate * mDp70;
                if (curY != targetY) {
                    mBottom.setTranslationY(targetY);
                }
            }
        };
        for (AbsMainViewHolder vh : mViewHolders) {
            vh.setAppBarLayoutListener(appBarLayoutListener);
            addAllLifeCycleListener(vh.getLifeCycleListenerList());
            list.add(vh.getContentView());
        }
        btn_start.setVisibility(View.VISIBLE);
        mViewPager.setAdapter(new ViewPagerAdapter(list));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0||position == 1) {
                    btn_start.setVisibility(View.VISIBLE);
                } else {
                    btn_start.setVisibility(View.GONE);
                }
                for (int i = 0, length = mViewHolders.length; i < length; i++) {
                    if (i == position) {
                        mViewHolders[i].setShowed(true);
                    } else {
                        mViewHolders[i].setShowed(false);
                    }
                }
                mViewHolders[position].loadData();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mTabButtonGroup.setViewPager(mViewPager);
        mDp70 = DpUtil.dp2px(70);
        mBottom = findViewById(R.id.bottom);
        mProcessResultUtil = new ProcessResultUtil(this);
        EventBus.getDefault().register(this);
        checkVersion();
        if (showInvite) {
            showInvitationCode();
        }
        requestBonus();
        loginIM();
        AppConfig.getInstance().setLaunched(true);
    }

    public void mainClick(View v) {
        if (!canClick()) {
            return;
        }
        switch (v.getId()) {
            case R.id.btn_start:
                showStartDialog();
                break;
            case R.id.btn_search:
                SearchActivity.forward(mContext);
                break;
            case R.id.btn_msg:
                ChatActivity.forward(mContext);
                break;
        }
    }

    private void showStartDialog() {
        MainStartDialogFragment dialogFragment = new MainStartDialogFragment();
        dialogFragment.setMainStartChooseCallback(mMainStartChooseCallback);
        dialogFragment.show(getSupportFragmentManager(), "MainStartDialogFragment");

//        mProcessResultUtil.requestPermissions(new String[]{
//                Manifest.permission.READ_EXTERNAL_STORAGE,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                Manifest.permission.CAMERA,
//                Manifest.permission.RECORD_AUDIO
//        }, mStartLiveRunnable);

    }

    private MainStartChooseCallback mMainStartChooseCallback = new MainStartChooseCallback() {
        @Override
        public void onLiveClick() {
            mProcessResultUtil.requestPermissions(new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA,
                    Manifest.permission.RECORD_AUDIO
            }, mStartLiveRunnable);
        }

        @Override
        public void onVideoClick() {
            mProcessResultUtil.requestPermissions(new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA,
                    Manifest.permission.RECORD_AUDIO
            }, mStartVideoRunnable);
        }
    };

    private Runnable mStartLiveRunnable = new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent(mContext, LiveAnchorActivity.class));
        }
    };


    private Runnable mStartVideoRunnable = new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent(mContext, VideoRecordActivity.class));
        }
    };

    /**
     * 检查版本更新
     */
    private void checkVersion() {
        AppConfig.getInstance().getConfig(new CommonCallback<ConfigBean>() {
            @Override
            public void callback(ConfigBean configBean) {
                if (configBean != null) {
                    if (configBean.getMaintainSwitch() == 1) {//开启维护
                        DialogUitl.showSimpleTipDialog(mContext, WordUtil.getString(R.string.main_maintain_notice), configBean.getMaintainTips());
                    }
                    if (!VersionUtil.isLatest(configBean.getVersion())) {
                        VersionUtil.showDialog(mContext, configBean.getUpdateDes(), configBean.getDownloadApkUrl());
                    }
                }
            }
        });
    }

    /**
     * 填写邀请码
     */
    private void showInvitationCode() {
        DialogUitl.showSimpleInputDialog(mContext, WordUtil.getString(R.string.main_input_invatation_code), new DialogUitl.SimpleCallback() {
            @Override
            public void onConfirmClick(final Dialog dialog, final String content) {
                if (TextUtils.isEmpty(content)) {
                    ToastUtil.show(R.string.main_input_invatation_code);
                    return;
                }
                HttpUtil.setDistribut(content, new HttpCallback() {
                    @Override
                    public void onSuccess(int code, String msg, String[] info) {
                        if (code == 0 && info.length > 0) {
                            ToastUtil.show(JSON.parseObject(info[0]).getString("msg"));
                            dialog.dismiss();
                        } else {
                            ToastUtil.show(msg);
                        }
                    }
                });
            }
        });
    }
    /**
     * 签到奖励
     */
    private void requestBonus() {
        HttpUtil.requestBonus(new HttpCallback() {
            @Override
            public void onSuccess(int code, String msg, String[] info) {
                if (code == 0 && info.length > 0) {
                    JSONObject obj = JSON.parseObject(info[0]);
                    if (obj.getIntValue("bonus_switch") == 0) {
                        return;
                    }
                    int day = obj.getIntValue("bonus_day");
                    if (day <= 0) {
                        return;
                    }
                    List<BonusBean> list = JSON.parseArray(obj.getString("bonus_list"), BonusBean.class);
                    BonusViewHolder bonusViewHolder = new BonusViewHolder(mContext, mRootView);
                    bonusViewHolder.setData(list, day, obj.getString("count_day"));
                    bonusViewHolder.show();
                }
            }
        });
    }

    /**
     * 登录IM
     */
    private void loginIM() {
        String uid = AppConfig.getInstance().getUid();
        ImMessageUtil.getInstance().loginJMessage(uid);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!mLoad) {
            mLoad = true;
            MainHomeViewHolder mvh = (MainHomeViewHolder) mViewHolders[0];
            if (ImPushUtil.getInstance().isClickNotification()) {//MainActivity是点击通知打开的
                ImPushUtil.getInstance().setClickNotification(false);
                int notificationType = ImPushUtil.getInstance().getNotificationType();
                switch (notificationType) {
                    case Constants.JPUSH_TYPE_LIVE:
                        mvh.setCurrentPage(0);
                        break;
                    case Constants.JPUSH_TYPE_MESSAGE:
                        mvh.setCurrentPage(1);
                        ChatActivity.forward(mContext);
                        break;
                }
            } else {
                mvh.setCurrentPage(1);
            }
            getLocation();
        }
    }

    /**
     * 获取所在位置
     */
    private void getLocation() {
        mProcessResultUtil.requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, new Runnable() {
            @Override
            public void run() {
                LocationUtil.getInstance().startLocation();
            }
        });
    }


    @Override
    protected void onDestroy() {
        if (mTabButtonGroup != null) {
            mTabButtonGroup.cancelAnim();
        }
        EventBus.getDefault().unregister(this);
        HttpUtil.cancel(HttpConsts.GET_CONFIG);
        HttpUtil.cancel(HttpConsts.REQUEST_BONUS);
        HttpUtil.cancel(HttpConsts.GET_BONUS);
        HttpUtil.cancel(HttpConsts.SET_DISTRIBUT);
        if (mCheckLivePresenter != null) {
            mCheckLivePresenter.cancel();
        }
        LocationUtil.getInstance().stopLocation();
        if (mProcessResultUtil != null) {
            mProcessResultUtil.release();
        }
        AppConfig.getInstance().setGiftList(null);
        AppConfig.getInstance().setLaunched(false);
        LiveStorge.getInstance().clear();
        VideoStorge.getInstance().clear();
        super.onDestroy();
    }

    public static void forward(Context context) {
        forward(context, false);
    }

    public static void forward(Context context, boolean showInvite) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(Constants.SHOW_INVITE, showInvite);
        context.startActivity(intent);
    }

    /**
     * 观看直播
     */
    public void watchLive(LiveBean liveBean, String key, int position) {
        if (mCheckLivePresenter == null) {
            mCheckLivePresenter = new CheckLivePresenter(mContext);
        }
        mCheckLivePresenter.watchLive(liveBean, key, position);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onImUnReadCountEvent(ImUnReadCountEvent e) {
        String unReadCount = e.getUnReadCount();
        if (!TextUtils.isEmpty(unReadCount)) {
            ((MainHomeViewHolder) mViewHolders[0]).setUnReadCount(unReadCount);
            ((MainNearViewHolder) mViewHolders[1]).setUnReadCount(unReadCount);
        }
    }

    @Override
    public void onBackPressed() {
        long curTime = System.currentTimeMillis();
        if (curTime - mLastClickBackTime > 2000) {
            mLastClickBackTime = curTime;
            ToastUtil.show(R.string.main_click_next_exit);
            return;
        }
        super.onBackPressed();
    }
}
