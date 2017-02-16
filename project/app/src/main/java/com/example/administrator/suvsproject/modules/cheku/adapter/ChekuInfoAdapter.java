package com.example.administrator.suvsproject.modules.cheku.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.suvsproject.R;
import com.example.administrator.suvsproject.modules.cheku.bean.ChekuInformationBean;
import com.example.administrator.suvsproject.modules.cheku.util.ChekuUtils;

import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2016/11/16 0016.
 */
public class ChekuInfoAdapter extends RecyclerView.Adapter<ChekuInfoAdapter.ChekuInfoViewHolder> {

    List<ChekuInformationBean> data;
    Context context;

    public ChekuInfoAdapter(List<ChekuInformationBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public ChekuInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cheku_info, null);
        return new ChekuInfoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChekuInfoViewHolder holder, int position) {

        holder.item_cheku_info_stitle.setText(data.get(position).getStitle());
        holder.item_cheku_info_description.setText(data.get(position).getDescription());
        holder.item_cheku_info_time.setText(ChekuUtils.getTime(data.get(position).getPublishtime()));
        x.image().bind(holder.item_cheku_info_iv, data.get(position).getPhoto());
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ChekuInfoViewHolder extends RecyclerView.ViewHolder {
        ImageView item_cheku_info_iv;
        TextView item_cheku_info_stitle, item_cheku_info_description, item_cheku_info_time;


        public ChekuInfoViewHolder(View itemView) {
            super(itemView);
            item_cheku_info_iv = (ImageView) itemView.findViewById(R.id.item_cheku_info_iv);
            item_cheku_info_stitle = (TextView) itemView.findViewById(R.id.item_cheku_info_stitle);
            item_cheku_info_description = (TextView) itemView.findViewById(R.id.item_cheku_info_description);
            item_cheku_info_time = (TextView) itemView.findViewById(R.id.item_cheku_info_time);

        }
    }
}
