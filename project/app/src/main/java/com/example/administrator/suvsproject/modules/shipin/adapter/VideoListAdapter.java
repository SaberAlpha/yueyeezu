package com.example.administrator.suvsproject.modules.shipin.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.administrator.suvsproject.R;
import com.example.administrator.suvsproject.modules.shipin.bean.ShipinInfo;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by Nathen
 * On 2016/02/07 01:20
 */
public class VideoListAdapter extends BaseAdapter {
    public static final String TAG = "JieCaoVideoPlayer";
    private  List<ShipinInfo> list;
    private HashMap<Integer, View> hm = new HashMap<Integer, View>();
    Context context;
    public VideoListAdapter(Context context, List<ShipinInfo> datalist) {
        this.context = context;
        this.list =datalist;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.e(TAG, "why you always getview");

        ViewHolder viewHolder;
        if (hm.get(position) == null) {
            viewHolder = new ViewHolder();
            LayoutInflater mInflater = LayoutInflater.from(context);
            convertView = mInflater.inflate(R.layout.item_videoview, null);
            viewHolder.jcVideoPlayer = (JCVideoPlayerStandard) convertView.findViewById(R.id.videoplayer);
            hm.put(position, convertView);
            convertView.setTag(viewHolder);
        } else {
            convertView = hm.get(position);
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if(!TextUtils.isEmpty( list.get(position).getVideourl())){
            viewHolder.jcVideoPlayer.setUp(
                    list.get(position).getVideourl(), JCVideoPlayer.SCREEN_LAYOUT_LIST,
                    list.get(position).getTitle());
        }
        if(!TextUtils.isEmpty(list.get(position).getIcon())){
            Picasso.with(convertView.getContext())
                    .load(list.get(position).getIcon())
                    .into(viewHolder.jcVideoPlayer.thumbImageView);
        }else {
            viewHolder.jcVideoPlayer.thumbImageView.setImageResource(R.mipmap.ic_launcher);
        }
        return convertView;
    }
    class ViewHolder {
        JCVideoPlayerStandard jcVideoPlayer;
    }
}
