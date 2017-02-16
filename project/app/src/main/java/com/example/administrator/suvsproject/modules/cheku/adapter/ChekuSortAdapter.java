package com.example.administrator.suvsproject.modules.cheku.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.suvsproject.R;
import com.example.administrator.suvsproject.modules.cheku.bean.CarSortBean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/14 0014.
 */
public class ChekuSortAdapter extends RecyclerView.Adapter<ChekuSortAdapter.ChekuSortViewHolder> {

    List<CarSortBean> data;
    Context context;

    public ChekuSortAdapter(List<CarSortBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public ChekuSortViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cheku_sort, null);
        return new ChekuSortViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChekuSortViewHolder holder, final int position) {
        holder.item_cheku_sort_tv.setText(data.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(data.get(position).getUrl());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ChekuSortViewHolder extends RecyclerView.ViewHolder {

        TextView item_cheku_sort_tv;

        public ChekuSortViewHolder(View itemView) {
            super(itemView);
            item_cheku_sort_tv = (TextView) itemView.findViewById(R.id.item_cheku_sort_tv);
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(String url);
    }

    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
