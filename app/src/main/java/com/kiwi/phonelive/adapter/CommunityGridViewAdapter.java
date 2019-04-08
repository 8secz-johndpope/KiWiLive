package com.kiwi.phonelive.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.kiwi.phonelive.R;
import com.kiwi.phonelive.bean.CommunitChlideBean;
import com.kiwi.phonelive.glide.ImgLoader;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class CommunityGridViewAdapter extends BaseAdapter {
    private List<CommunitChlideBean.ImgsBean> mNameList;
    private LayoutInflater mInflater;
    private Context mContext;
    LinearLayout.LayoutParams params;

    public CommunityGridViewAdapter(Context context, List<CommunitChlideBean.ImgsBean> nameList) {
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
            convertView = mInflater.inflate(R.layout.item_communitygridviewadapter, null);
            viewTag = new ItemViewTag((RoundedImageView) convertView.findViewById(R.id.grid_icon));
            convertView.setTag(viewTag);
        } else {
            viewTag = (ItemViewTag) convertView.getTag();
        }
        ImgLoader.displayAvatar(mNameList.get(position).getImgs(), viewTag.mIcon);
        return convertView;
    }

    class ItemViewTag {
        protected RoundedImageView mIcon;

        public ItemViewTag(RoundedImageView icon) {
            this.mIcon = icon;
        }
    }

}