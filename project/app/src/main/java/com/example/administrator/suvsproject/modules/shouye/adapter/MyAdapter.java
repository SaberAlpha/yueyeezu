package com.example.administrator.suvsproject.modules.shouye.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.suvsproject.R;
import com.example.administrator.suvsproject.modules.shouye.bean.BaseInfo;
import com.example.administrator.suvsproject.modules.shouye.bean.NewInfo_ad;
import com.example.administrator.suvsproject.modules.shouye.bean.NewInfo_news;
import com.example.administrator.suvsproject.modules.shouye.bean.NewInfo_photo;
import com.example.administrator.suvsproject.modules.shouye.bean.ShipinInfo;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

/**
 * Created by DQH on 2016/11/18.
 */
public class MyAdapter extends BaseAdapter {

    private final String path;
    Context context;
    private final int TYPE_PHOTO = 0;
    private final int TYPE_NEWS = 1;
    private final int TYPE_BBS = 2;
    private final int TYPE_AD= 3;
    private final int TYPE_VIDEO = 4;

    List<BaseInfo> list;
    private final HashMap<String, Integer> hashMap;

    public MyAdapter(Context context, List<BaseInfo> list, String path) {
        this.context = context;
        this.list = list;
        this.path = path;
        hashMap = new HashMap<String,Integer>();
        hashMap.put("photo",TYPE_PHOTO);
        hashMap.put("news",TYPE_NEWS);
        hashMap.put("bbs",TYPE_BBS);
        hashMap.put("ad",TYPE_AD);
        hashMap.put("video",TYPE_VIDEO);
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
        int type = hashMap.get(list.get(position).getItemtype());
        BaseViewHolder viewHolder = null;
        if (convertView == null) {
            switch (type) {
                case TYPE_PHOTO:
                    convertView = LayoutInflater.from(context).inflate(R.layout.adapter_type_photo, parent, false);
                    viewHolder = new Type_photoViewHolder(convertView);
                    break;
                case TYPE_NEWS:
                    convertView = LayoutInflater.from(context).inflate(R.layout.adapter_type_news, parent, false);
                    viewHolder = new Type_newsViewHolder(convertView);
                    break;
                case TYPE_BBS:
                    convertView = LayoutInflater.from(context).inflate(R.layout.adapter_type_bbs, parent, false);
                    viewHolder = new Type_bbsViewHolder(convertView);
                    break;
                case TYPE_AD:
                    convertView = LayoutInflater.from(context).inflate(R.layout.adapter_type_ad, parent, false);
                    viewHolder = new Type_adViewHolder(convertView);
                    break;
                case TYPE_VIDEO:
                    convertView = LayoutInflater.from(context).inflate(R.layout.adapter_type_video, parent, false);
                    viewHolder = new Type_videoViewHolder(convertView);
                    break;
            }
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (BaseViewHolder) convertView.getTag();
    }
        //TODO 填充数据
        BaseInfo info = list.get(position);
        switch (type) {
            case TYPE_PHOTO:
                Type_photoData(viewHolder, info);
                break;
            case TYPE_NEWS:
                Type_newsData(viewHolder, info);
                break;
            case TYPE_BBS:
                Type_bbsData(viewHolder, info);
                break;
            case TYPE_AD:
                Type_adData(viewHolder, info);
                break;
            case TYPE_VIDEO:
                Type_videoData(viewHolder, info);
                break;
        }
        return convertView;
    }

    private void Type_videoData(BaseViewHolder viewHolder, BaseInfo info) {
        Type_videoViewHolder holder = (Type_videoViewHolder)viewHolder;
        ShipinInfo newsInfo = (ShipinInfo)info;
        holder.typevideo_title.setText(newsInfo.getTitle());
        Picasso.with(context).load(Uri.parse(newsInfo.getPhoto())).into(holder.typevideo_photo);

    }

    private void Type_adData(BaseViewHolder viewHolder, BaseInfo info) {
        Type_adViewHolder holder = (Type_adViewHolder) viewHolder;
        NewInfo_ad newsInfo = (NewInfo_ad) info;
        Picasso.with(context).load(Uri.parse(newsInfo.getAdphoto())).into(holder.typead_photo);

    }

    private void Type_bbsData(BaseViewHolder viewHolder, BaseInfo info) {
        Type_bbsViewHolder holder = (Type_bbsViewHolder) viewHolder;
        NewInfo_news newsInfo = (NewInfo_news) info;
        Picasso.with(context).load(Uri.parse(newsInfo.getPhoto())).into(holder.typebbs_photo);
        Picasso.with(context).load(Uri.parse(newsInfo.getAvatar())).into(holder.typebbs_icon);
        holder.typebbs_name.setText(newsInfo.getAuthor());
        holder.typebbs_title.setText(newsInfo.getTitle());
        long time = System.currentTimeMillis()-Long.valueOf(newsInfo.getPublishtime());
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String time1 = sdf.format(time);
        holder.typebbs_time.setText(time1);

    }

    private void Type_newsData(BaseViewHolder viewHolder, BaseInfo info) {
        Type_newsViewHolder holder = (Type_newsViewHolder) viewHolder;
        NewInfo_news newsInfo = (NewInfo_news) info;
        Picasso.with(context).load(Uri.parse(newsInfo.getPhoto())).into(holder.typenews_photo);
        holder.typenews_title.setText(newsInfo.getTitle());
        long time = System.currentTimeMillis()-Long.valueOf(newsInfo.getPublishtime());
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String time1 = sdf.format(time);
        holder.typenews_time.setText(time1);
        if("".equals(path)){
            holder.typenews_read.setText(newsInfo.getChannelname());
        }else{
            holder.typenews_read.setText(newsInfo.getScancount());
        }
    }

    private void Type_photoData(BaseViewHolder viewHolder, BaseInfo info) {
        Type_photoViewHolder holder = (Type_photoViewHolder) viewHolder;
        NewInfo_photo newsInfo = (NewInfo_photo) info;
        holder.typephoto_title.setText(newsInfo.getTitle());
        String[] photoes = newsInfo.getPhotoes();
        Picasso.with(context).load(Uri.parse(photoes[0])).into(holder.typephoto_one);
        Picasso.with(context).load(Uri.parse(photoes[1])).into(holder.typephoto_two);
        Picasso.with(context).load(Uri.parse(photoes[2])).into(holder.typephoto_three);
    }

    /**
     * 返回所有类型的数量
     *
     * @return
     */
    @Override
    public int getViewTypeCount() {
        return 5;
    }


    /**
     * 返回某个位置的item类型
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {

     return hashMap.get(list.get(position).getItemtype());

    }

    private class BaseViewHolder {
    }

    private class Type_photoViewHolder extends BaseViewHolder {

        TextView typephoto_title;
        ImageView typephoto_two,typephoto_three,typephoto_one;

        public Type_photoViewHolder(View convertView) {
            typephoto_title = (TextView) convertView.findViewById(R.id.tv_typephoto_title);
            typephoto_one = (ImageView) convertView.findViewById(R.id.iv_typephoto_one);
            typephoto_two = (ImageView) convertView.findViewById(R.id.iv_typephoto_two);
            typephoto_three = (ImageView) convertView.findViewById(R.id.iv_typephoto_three);

        }
    }

    private class Type_newsViewHolder extends BaseViewHolder {
        TextView typenews_title,typenews_read,typenews_time;
        ImageView typenews_photo;
        public Type_newsViewHolder(View convertView) {
            if("".equals(path)){
                convertView.findViewById(R.id.iv_typenews_icon).setVisibility(View.GONE);
            }else{
                convertView.findViewById(R.id.iv_typenews_icon).setVisibility(View.VISIBLE);
            }
            typenews_title = (TextView)convertView.findViewById(R.id.tv_typenews_title);
            typenews_photo = (ImageView) convertView.findViewById(R.id.iv_typenews);
            typenews_read = (TextView)convertView.findViewById(R.id.tv_typenews_read);
            typenews_time = (TextView)convertView.findViewById(R.id.tv_typenews_time);
        }
    }

    private class Type_bbsViewHolder extends BaseViewHolder {
        TextView typebbs_title,typebbs_name,typebbs_time;
        ImageView typebbs_icon,typebbs_photo;
        public Type_bbsViewHolder(View convertView) {
            typebbs_icon = (ImageView)convertView.findViewById(R.id.shouye_typebbs_icon);
            typebbs_title = (TextView)convertView.findViewById(R.id.shouye_typebbs_title);
            typebbs_name= (TextView)convertView.findViewById(R.id.shouye_typebbs_name);
            typebbs_time= (TextView)convertView.findViewById(R.id.shouye_typebbs_time);
            typebbs_photo = (ImageView)convertView.findViewById(R.id.shouye_typebbs);
        }
    }

    private class Type_adViewHolder extends BaseViewHolder {

        ImageView typead_photo;
        public Type_adViewHolder(View convertView) {
            typead_photo = (ImageView)convertView.findViewById(R.id.shouye_typead);
        }
    }

    private class Type_videoViewHolder extends BaseViewHolder {
        ImageView typevideo_photo;
        TextView typevideo_title;
        public Type_videoViewHolder(View convertView) {
            typevideo_title = (TextView)convertView.findViewById(R.id.shouye_typevideo_title);
            typevideo_photo = (ImageView)convertView.findViewById(R.id.shouye_typevideo_photo);
        }
    }
}
