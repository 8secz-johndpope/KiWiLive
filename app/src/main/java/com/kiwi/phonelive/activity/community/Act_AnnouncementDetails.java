package com.kiwi.phonelive.activity.community;

import android.os.Build;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.kiwi.phonelive.R;
import com.kiwi.phonelive.activity.AbsActivity;

public class Act_AnnouncementDetails extends AbsActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.act_announcementdetails;
    }

    private String notice, tvTitle;
    private TextView tvNotice, title;

    @Override
    protected void main() {
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        initView();
        initData();
    }

    public void initView() {
        tvNotice = findViewById(R.id.my_notice);
        title = findViewById(R.id.my_notice_title);
        notice = getIntent().getStringExtra("notice");
        tvTitle = getIntent().getStringExtra("title");
        tvNotice.setText(notice);
        title.setText(tvTitle);
    }

    public void initData() {
    }
}
