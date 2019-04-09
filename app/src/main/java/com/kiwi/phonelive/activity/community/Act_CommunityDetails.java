package com.kiwi.phonelive.activity.community;

import android.os.Build;
import android.view.WindowManager;

import com.kiwi.phonelive.R;
import com.kiwi.phonelive.activity.AbsActivity;
import com.kiwi.phonelive.custom.ViewPagerIndicator;
import com.kiwi.phonelive.views.AbsMainChildTopViewHolder;

/**
 * 社区详情页
 */
public class Act_CommunityDetails extends AbsActivity {
    private ViewPagerIndicator indicator;

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
        indicator = findViewById(R.id.indicator);

    }

    public void initData() {


    }

}
