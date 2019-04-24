package com.kiwi.phonelive.activity.community;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Debug;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kiwi.phonelive.AppConfig;
import com.kiwi.phonelive.R;
import com.kiwi.phonelive.activity.AbsActivity;
import com.kiwi.phonelive.activity.VideoRecordActivity;
import com.kiwi.phonelive.activity.community.adapter.BusinessShopGridAdapter;
import com.kiwi.phonelive.activity.community.dialog.Dlg_Photograph;
import com.kiwi.phonelive.bean.UserBean;
import com.kiwi.phonelive.glide.ImgLoader;
import com.kiwi.phonelive.http.HttpCallback;
import com.kiwi.phonelive.http.HttpUtil;
import com.kiwi.phonelive.interfaces.ImageResultCallback;
import com.kiwi.phonelive.utils.ProcessImageUtil;
import com.kiwi.phonelive.utils.ProcessResultUtil;
import com.kiwi.phonelive.utils.ToastUtil;
import com.kiwi.phonelive.views.MyGridView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 社区详情。。列表发布
 */
public class Act_Publish_Post extends AbsActivity implements View.OnClickListener, AdapterView.OnItemClickListener, Dlg_Photograph.OnClick, BusinessShopGridAdapter.onBackImgShut {
    @Override
    protected int getLayoutId() {
        return R.layout.act_publish_post;
    }

    @Override
    protected void main() {
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        initView();
        initData();
    }

    private TextView title[] = new TextView[3];
    private LinearLayout llVideo;
    private EditText llText, tvTitle;
    private MyGridView myGridView;
    private String cm_id;

    public void initView() {
        findViewById(R.id.publish_ll3).setOnClickListener(this);
        findViewById(R.id.publish_ll2).setOnClickListener(this);
        findViewById(R.id.publish_ll1).setOnClickListener(this);
        findViewById(R.id.my_fabu).setOnClickListener(this);
        cm_id = getIntent().getStringExtra("cm_id");
        tvTitle = findViewById(R.id.publish_title);
        title[0] = findViewById(R.id.publish_tv1);
        title[1] = findViewById(R.id.publish_tv2);
        title[2] = findViewById(R.id.publish_tv3);
        llVideo = findViewById(R.id.ll_video);
        llVideo.setOnClickListener(this);
        llText = findViewById(R.id.ed_text);
        myGridView = findViewById(R.id.recycler_issue_column);

    }

    List<File> Carmer_file;
    Dlg_Photograph photo;
    private BusinessShopGridAdapter adapter;
    private ProcessImageUtil mImageUtil;
    private ProcessResultUtil mProcessResultUtil;

    public void initData() {
        mProcessResultUtil = new ProcessResultUtil(this);
        Carmer_file = new ArrayList<>();
        photo = new Dlg_Photograph(this, Act_Publish_Post.this);
        adapter = new BusinessShopGridAdapter(this, this);
        for (int i = 0; i < 1; i++) {
            File file1 = new File("");
            Carmer_file.add(file1);
        }
        adapter.setChannel_info(Carmer_file);
        myGridView.setAdapter(adapter);
        myGridView.setOnItemClickListener(this);
        mImageUtil = new ProcessImageUtil(this);
        mImageUtil.setImageResultCallback(new ImageResultCallback() {
            @Override
            public void beforeCamera() {

            }

            @Override
            public void onSuccess(File file) {
                if (file != null) {
                    Carmer_file.add(0, file);
                    if (Carmer_file.size() == 9) {
                        Carmer_file.remove(Carmer_file.size() - 1);
                    }
                    adapter.setChannel_info(Carmer_file);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure() {

            }
        });
        swche(0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.publish_ll1://点击视频
                swche(0);
                break;
            case R.id.publish_ll2://点击图片
                swche(1);
                break;
            case R.id.publish_ll3://点击短文
                swche(2);
                break;
            case R.id.my_fabu:
                if (TextUtils.isEmpty(tvTitle.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "请输入您要发布的标题！", Toast.LENGTH_SHORT).show();
                    return;
                }
                switch (page) {
                    case 0:

                        break;
                    case 1:
                        postFileImg();
                        break;
                    case 2:
                        postText();
                        break;
                }
                break;
            case R.id.ll_video://视频
                mProcessResultUtil.requestPermissions(new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA,
                        Manifest.permission.RECORD_AUDIO
                }, mStartVideoRunnable);
                break;
        }
    }

    private Runnable mStartVideoRunnable = new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent(mContext, VideoRecordActivity.class));
        }
    };
    private int page = 0;

    public void swche(int indext) {
        title[page].setSelected(false);
        title[indext].setSelected(true);
        page = indext;
        switch (indext) {
            case 0:
                llVideo.setVisibility(View.VISIBLE);
                llText.setVisibility(View.GONE);
                myGridView.setVisibility(View.GONE);
                break;
            case 1:
                llVideo.setVisibility(View.GONE);
                llText.setVisibility(View.GONE);
                myGridView.setVisibility(View.VISIBLE);
                break;
            case 2:
                llVideo.setVisibility(View.GONE);
                llText.setVisibility(View.VISIBLE);
                myGridView.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (Carmer_file.get(Carmer_file.size() - 1).getPath().equals("")) {
            photo.show();
        }
    }

    @Override
    public void onItem(int p) {
        switch (p) {
            case 1://拍照
                mImageUtil.getImageByCamera();
                photo.dismiss();
                break;
            case 2://从手机相册选择
                photo.dismiss();
                mImageUtil.getImageByAlumb();
                break;
        }
    }

    @Override
    public void onBackImgShut(int position) {
        Carmer_file.remove(position);
        if (Carmer_file.get(Carmer_file.size() - 1).getPath().equals("")) {

        } else {
            if (Carmer_file.size() < 8) {
                File file1 = new File("");
                Carmer_file.add(file1);
            }
        }
        adapter.notifyDataSetChanged();
    }

    /**
     * 上传图片加标题
     */
    public void postFileImg() {
        if (Carmer_file.get(Carmer_file.size() - 1).getPath().equals("")) {
            Carmer_file.remove(Carmer_file.size() - 1);
        }
        HttpUtil.updateImgText(Carmer_file, cm_id, tvTitle.getText().toString(), new HttpCallback() {
            @Override
            public void onSuccess(int code, String msg, String[] info) {
                if (code==0) {
                    Toast.makeText(getApplicationContext(), "发布成功！", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

    }

    /**
     * 上传视频加标题
     */
    public void postFileVideo() {
//        HttpUtil.updateVideoText(Carmer_file, cm_id, tvTitle.getText().toString(), new HttpCallback() {
//            @Override
//            public void onSuccess(int code, String msg, String[] info) {
//                if (msg.equals("添加成功！")) {
//                    finish();
//                }
//            }
//        });

    }

    /**
     * 上传文字
     */
    public void postText() {
        if (TextUtils.isEmpty(llText.getText().toString())) {
            Toast.makeText(getApplicationContext(), "请输入您需要发布的内容！", Toast.LENGTH_SHORT).show();
            return;
        }
        HttpUtil.updateText(cm_id, tvTitle.getText().toString(), llText.getText().toString(), new HttpCallback() {
            @Override
            public void onSuccess(int code, String msg, String[] info) {
                if (code == 0) {
                    Toast.makeText(getApplicationContext(), "发布成功！", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

    }
}