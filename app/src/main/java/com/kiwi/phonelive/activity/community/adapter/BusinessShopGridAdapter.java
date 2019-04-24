package com.kiwi.phonelive.activity.community.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.kiwi.phonelive.R;
import java.io.File;
import java.util.List;

public class BusinessShopGridAdapter extends BaseAdapter {
    private Context context;
    private List<File> channel_info;
    private onBackImgShut onBackImgShut;

    public List<File> getChannel_info() {
        return channel_info;
    }

    public void setChannel_info(List<File> channel_info) {
        this.channel_info = channel_info;
    }

    public BusinessShopGridAdapter(Context context, onBackImgShut onBackImgShut1) {
        this.context = context;
        this.onBackImgShut = onBackImgShut1;
    }

    @Override
    public int getCount() {
        return channel_info.size();
    }

    @Override
    public Object getItem(int position) {
        return channel_info.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_businessshopgv, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (channel_info.get(position).getPath().equals("")) {

            Glide.with(context).load(R.mipmap.icon_buadd).into(viewHolder.ivChannel);
            viewHolder.shutdown.setVisibility(View.GONE);
        } else {
            viewHolder.shutdown.setVisibility(View.VISIBLE);
            Glide.with(context).load(channel_info.get(position)).into(viewHolder.ivChannel);
            viewHolder.shutdown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackImgShut.onBackImgShut(position);
                }
            });
        }
        return convertView;
    }

    class ViewHolder {
        protected ImageView ivChannel, shutdown;

        public ViewHolder(View convertView) {
            ivChannel = (ImageView) convertView.findViewById(R.id.iv_channel);
            shutdown = convertView.findViewById(R.id.iv_shutdown);
        }
    }

    public interface onBackImgShut {
        void onBackImgShut(int position);
    }
}