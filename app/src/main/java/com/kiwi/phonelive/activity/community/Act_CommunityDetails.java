package com.kiwi.phonelive.activity.community;

import android.os.Build;
import android.view.WindowManager;

import com.kiwi.phonelive.R;
import com.kiwi.phonelive.activity.AbsActivity;

/**
 * 社区详情页
 */
public class Act_CommunityDetails extends AbsActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.act_communitydetails;
    }

    @Override
    protected void main() {
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); }
    }

}
