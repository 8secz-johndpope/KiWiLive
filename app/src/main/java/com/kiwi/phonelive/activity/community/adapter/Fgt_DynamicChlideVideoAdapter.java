package com.kiwi.phonelive.activity.community.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.kiwi.phonelive.R;
import com.kiwi.phonelive.activity.community.bean.Fgt_VideoBean;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class Fgt_DynamicChlideVideoAdapter extends BaseAdapter {
    List<Fgt_VideoBean> mNameList;
    private LayoutInflater mInflater;
    private Context mContext;
    LinearLayout.LayoutParams params;

    public Fgt_DynamicChlideVideoAdapter(Context context, List<Fgt_VideoBean> nameList) {
        mNameList = nameList;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return mNameList.size();
    }

    public Object getItem(int position) {
        return mNameList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ItemViewTag viewTag;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_dynamiclmagechlidevideoadapter, null);
            viewTag = new ItemViewTag((RoundedImageView) convertView.findViewById(R.id.grid_icon));
            convertView.setTag(viewTag);
        } else {
            viewTag = (ItemViewTag) convertView.getTag();
        }
        Glide.with(mContext).load(mNameList.get(position).getVideo_img()).error(R.mipmap.icon_video_home_bottom).placeholder(R.mipmap.icon_video_home_bottom).into(viewTag.mIcon);
        return convertView;
    }

    class ItemViewTag {
        protected RoundedImageView mIcon;

        public ItemViewTag(RoundedImageView icon) {
            this.mIcon = icon;
        }
    }

}