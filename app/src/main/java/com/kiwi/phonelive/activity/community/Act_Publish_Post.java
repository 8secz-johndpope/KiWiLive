package com.kiwi.phonelive.activity.community;

import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.kiwi.phonelive.R;
import com.kiwi.phonelive.activity.AbsActivity;
import com.kiwi.phonelive.activity.community.adapter.GridImageAdapter;
import com.kiwi.phonelive.views.FullyGridLayoutManager;

/**
 * 社区详情。。列表发布
 */
public class Act_Publish_Post extends AbsActivity implements View.OnClickListener {
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
    private EditText llText;
    private RecyclerView llRecyclerView;
    public void initView() {
        findViewById(R.id.publish_ll3).setOnClickListener(this);
        findViewById(R.id.publish_ll2).setOnClickListener(this);
        findViewById(R.id.publish_ll1).setOnClickListener(this);
        title[0] = findViewById(R.id.publish_tv1);
        title[1] = findViewById(R.id.publish_tv2);
        title[2] = findViewById(R.id.publish_tv3);
        llVideo = findViewById(R.id.ll_video);
        llText = findViewById(R.id.ed_text);
        llRecyclerView = findViewById(R.id.recycler_issue_column);

    }
    GridImageAdapter mGridImageAdapter;
//    private List<LocalMedia> selectList = new ArrayList<>();
    public void initData() {
//        themeId = R.style.picture_default_style;
        swche(0);
        FullyGridLayoutManager manager = new FullyGridLayoutManager(Act_Publish_Post.this, 3, GridLayoutManager.VERTICAL, false);
        llRecyclerView.setLayoutManager(manager);
//        mGridImageAdapter = new GridImageAdapter(Act_Publish_Post.this, onAddPicClickListener);
//        llRecyclerView.setAdapter(mGridImageAdapter);
//        mGridImageAdapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(int position, View v) {
//                if (selectList.size() > 0) {
//                    LocalMedia media = selectList.get(position);
//                    String pictureType = media.getPictureType();
//                    int mediaType = PictureMimeType.pictureToVideo(pictureType);
//                    switch (mediaType) {
//                        case 1:
//                            // 预览图片 可自定长按保存路径
//                            PictureSelector.create(Act_Publish_Post.this).themeStyle(themeId).openExternalPreview(position, selectList);
//                            break;
//                        case 2:
//                            // 预览视频
//                            PictureSelector.create(Act_Publish_Post.this).externalPictureVideo(media.getPath());
//                            break;
//                        case 3:
//                            // 预览音频
//                            PictureSelector.create(Act_Publish_Post.this).externalPictureAudio(media.getPath());
//                            break;
//                    }
//                }
//            }
//        });
    }
//    private int chooseMode = PictureMimeType.ofImage();
    private int maxSelectNum = 9;
    private int themeId;
    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
//            PictureSelector.create(Act_Publish_Post.this)
//                    .openGallery(chooseMode)
//                    .maxSelectNum(maxSelectNum)// 最大图片选择数量
//                    .theme(themeId)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
//                    .minSelectNum(1)// 最小选择数量
//                    .imageSpanCount(4)// 每行显示个数
//                    .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
//                    .selectionMode(PictureConfig.MULTIPLE)
//                    .isCamera(true)
//                    .imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
//                    .synOrAsy(true)//同步true或异步false 压缩 默认同步
//                    .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
//                    .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
//                    .minimumCompressSize(100)// 小于100kb的图片不压缩
//                    .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
        }
    };
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK) {
//            switch (requestCode) {
//                case PictureConfig.CHOOSE_REQUEST:
//                    // 图片选择结果回调
//                    List<LocalMedia> selectList1 = PictureSelector.obtainMultipleResult(data);
//                    for (LocalMedia media : selectList1) {
//                        Log.i("图片-----》", media.getPath());
//                        selectList.add(media);
//                    }
//                    mGridImageAdapter.setList(selectList);
//                    mGridImageAdapter.notifyDataSetChanged();
//                    break;
//            }
//        }
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
        }
    }

    private int page = 0;

    public void swche(int indext) {
        title[page].setSelected(false);
        title[indext].setSelected(true);
        page = indext;

        switch (indext){
            case 0:
                llVideo.setVisibility(View.VISIBLE);
                llText.setVisibility(View.GONE);
                llRecyclerView.setVisibility(View.GONE);
                break;
            case 1:
                llVideo.setVisibility(View.GONE);
                llText.setVisibility(View.GONE);
                llRecyclerView.setVisibility(View.VISIBLE);
                break;
            case 2:
                llVideo.setVisibility(View.GONE);
                llText.setVisibility(View.VISIBLE);
                llRecyclerView.setVisibility(View.GONE);
                break;
        }
    }
}
