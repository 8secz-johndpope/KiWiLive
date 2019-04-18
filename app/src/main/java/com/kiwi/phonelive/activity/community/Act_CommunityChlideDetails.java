package com.kiwi.phonelive.activity.community;

import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.google.gson.Gson;
import com.kiwi.phonelive.R;
import com.kiwi.phonelive.activity.AbsActivity;
import com.kiwi.phonelive.activity.community.bean.CommunitChlideDetailsBean;
import com.kiwi.phonelive.glide.ImgLoader;
import com.kiwi.phonelive.http.HttpCallback;
import com.kiwi.phonelive.http.HttpUtil;
import com.kiwi.phonelive.utils.ToastUtil;
import com.makeramen.roundedimageview.RoundedImageView;

public class Act_CommunityChlideDetails extends AbsActivity implements View.OnClickListener {
    @Override
    protected int getLayoutId() {
        return R.layout.act_communitychlidedetails;
    }

    private TextView title, title1, community_desc, follow_status;
    private String cm_id, stTitle;
    private RoundedImageView had1, had2, had3, had4, had5;
    SVProgressHUD mSVProgressHUD;

    @Override
    protected void main() {
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        initView();
        initData();
    }

    public void initView() {
        mSVProgressHUD = new SVProgressHUD(this);
        mSVProgressHUD.showWithStatus("请稍后...");
        had1 = findViewById(R.id.had1);
        had2 = findViewById(R.id.had2);
        had3 = findViewById(R.id.had3);
        had4 = findViewById(R.id.had4);
        had5 = findViewById(R.id.had5);
        follow_status = findViewById(R.id.follow_status);
        findViewById(R.id.haderImagell).setOnClickListener(this);
        follow_status.setOnClickListener(this);
        cm_id = getIntent().getStringExtra("cm_id");
        stTitle = getIntent().getStringExtra("title");
        title = findViewById(R.id.my_notice_title);
        community_desc = findViewById(R.id.community_desc);
        title1 = findViewById(R.id.title);
        title.setText(stTitle);
    }

    public void initData() {
        community_desc();

    }

    /**
     * 获取详情信息
     */
    CommunitChlideDetailsBean bean;

    public void community_desc() {
        HttpUtil.community_desc(cm_id, new HttpCallback() {
            @Override
            public void onSuccess(int code, String msg, String[] info) {
                mSVProgressHUD.dismiss();
                Gson gson = new Gson();
                bean = gson.fromJson(info[0], CommunitChlideDetailsBean.class);
                ImgLoader.displayAvatar(bean.getAvatar_icon(), had5);
                title1.setText(bean.getCommunity_name());
                community_desc.setText(bean.getCommunity_desc());
                if (bean.getActive_users() == null) {
                    had1.setVisibility(View.GONE);
                    had2.setVisibility(View.GONE);
                    had3.setVisibility(View.GONE);
                    had4.setVisibility(View.GONE);
                    return;
                }
                if (bean.getFollow_status() == 1) {
                    follow_status.setText("已关注");
                } else {
                    follow_status.setText("+ 关注");
                }
                //设置小头像显示
                switch (bean.getActive_users().size()) {
                    case 1:
                        ImgLoader.displayAvatar(bean.getActive_users().get(0).getAvatar_thumb(), had1);
                        had1.setVisibility(View.VISIBLE);
                        had2.setVisibility(View.GONE);
                        had3.setVisibility(View.GONE);
                        had4.setVisibility(View.GONE);
                        break;
                    case 2:
                        ImgLoader.displayAvatar(bean.getActive_users().get(0).getAvatar_thumb(), had1);
                        ImgLoader.displayAvatar(bean.getActive_users().get(1).getAvatar_thumb(), had2);
                        had1.setVisibility(View.VISIBLE);
                        had2.setVisibility(View.VISIBLE);
                        had3.setVisibility(View.GONE);
                        had4.setVisibility(View.GONE);
                        break;
                    case 3:
                        ImgLoader.displayAvatar(bean.getActive_users().get(0).getAvatar_thumb(), had1);
                        ImgLoader.displayAvatar(bean.getActive_users().get(1).getAvatar_thumb(), had2);
                        ImgLoader.displayAvatar(bean.getActive_users().get(2).getAvatar_thumb(), had3);
                        had1.setVisibility(View.VISIBLE);
                        had2.setVisibility(View.VISIBLE);
                        had3.setVisibility(View.VISIBLE);
                        had4.setVisibility(View.GONE);
                        break;
                    case 5:
                        ImgLoader.displayAvatar(bean.getActive_users().get(0).getAvatar_thumb(), had1);
                        ImgLoader.displayAvatar(bean.getActive_users().get(1).getAvatar_thumb(), had2);
                        ImgLoader.displayAvatar(bean.getActive_users().get(2).getAvatar_thumb(), had3);
                        ImgLoader.displayAvatar(bean.getActive_users().get(3).getAvatar_thumb(), had4);
                        had1.setVisibility(View.VISIBLE);
                        had2.setVisibility(View.VISIBLE);
                        had3.setVisibility(View.VISIBLE);
                        had4.setVisibility(View.VISIBLE);
                        break;
                }


            }
        });
    }

    /**
     * 关注或取消关注
     */
    public void setCircle_follow() {
        mSVProgressHUD.show();
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

    @Override
    public void onClick(View v) {
            switch (v.getId()){
                case R.id.haderImagell://跳转
                    Intent intent1 = new Intent(Act_CommunityChlideDetails.this, Act_ActiveUsers.class);
                    intent1.putExtra("cm_id",cm_id);
                    intent1.putExtra("title", bean.getCommunity_name());
                    startActivity(intent1);
                    break;
                case R.id.follow_status://关注
                    setCircle_follow();
                    break;


            }
    }
}
