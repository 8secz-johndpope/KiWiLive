package com.kiwi.phonelive.activity.community;

import android.content.Intent;
import android.os.Build;
import android.os.Debug;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bigkoo.svprogresshud.SVProgressHUD;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kiwi.phonelive.R;
import com.kiwi.phonelive.activity.AbsActivity;
import com.kiwi.phonelive.activity.community.adapter.ActiveUsersAdapter;
import com.kiwi.phonelive.activity.community.bean.Active_usersBean;
import com.kiwi.phonelive.bean.CommunitChlideBeanZhu;
import com.kiwi.phonelive.http.HttpCallback;
import com.kiwi.phonelive.http.HttpUtil;
import com.kiwi.phonelive.utils.ToastUtil;

import java.util.Arrays;
import java.util.List;

/**
 * 活跃用户列表
 */
public class Act_ActiveUsers extends AbsActivity implements BaseQuickAdapter.OnItemChildClickListener {
    private String cm_id, title;
    SVProgressHUD mSVProgressHUD;

    @Override
    protected int getLayoutId() {
        return R.layout.act_activeusers;
    }

    @Override
    protected void main() {
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        initView();
        initData();
    }

    private TextView tvTitle;
    private RecyclerView myRecyclerView;

    public void initView() {
        mSVProgressHUD = new SVProgressHUD(this);
        mSVProgressHUD.showWithStatus("请稍后...");
        cm_id = getIntent().getStringExtra("cm_id");
        title = getIntent().getStringExtra("title");
        tvTitle = findViewById(R.id.my_notice_title);
        myRecyclerView = findViewById(R.id.ActiveUsers_RecyclerView);
        tvTitle.setText(title);
    }

    public void initData() {
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        active_users();

    }

    List<Active_usersBean> datas;
    private ActiveUsersAdapter activeUsersAdapter;

    /**
     * 获取列表
     */
    public void active_users() {
        HttpUtil.active_users(cm_id, new HttpCallback() {
            @Override
            public void onSuccess(int code, String msg, String[] info) {
                mSVProgressHUD.dismiss();
                datas = JSON.parseArray(Arrays.toString(info), Active_usersBean.class);
                activeUsersAdapter = new ActiveUsersAdapter(datas, Act_ActiveUsers.this);
                myRecyclerView.setAdapter(activeUsersAdapter);
                activeUsersAdapter.setOnItemChildClickListener(Act_ActiveUsers.this);
            }
        });
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.follow_status://关注
                setCircle_follow(position);
                break;
            case R.id.account:
//                Intent intent = new Intent(mContext, Act_CommunityDetails.class);
//                intent.putExtra("cm_id", datas.get(position).get.getId() + "");
//                mContext.startActivity(intent);
                break;



        }
    }

    /**
     * 关注或取消关注
     */
    public void setCircle_follow(final int position) {
        mSVProgressHUD.show();
        HttpUtil.getCircle_follow(cm_id, new HttpCallback() {
            @Override
            public void onSuccess(int code, String msg, String[] info) {
                mSVProgressHUD.dismiss();
                if (code == 0) {
                    if (datas.get(position).getFollow_status() == 1) {//取消关注
                        datas.get(position).setFollow_status(0);
                        ToastUtil.show(msg);
                    } else {//设置关注
                        datas.get(position).setFollow_status(1);
                        ToastUtil.show(msg);
                    }
                } else {
                    if (datas.get(position).getFollow_status() == 1) {//取消关注
                        ToastUtil.show("取消关注失败！");
                    } else {//设置关注
                        ToastUtil.show("关注失败！");
                    }
                }
                activeUsersAdapter.notifyDataSetChanged();

            }
        });
    }
}
