package com.example.administrator.suvsproject.modules.cheku.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.suvsproject.R;
import com.example.administrator.suvsproject.modules.cheku.bean.ChekuPicsBean;

import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2016/11/16 0016.
 */
public class ChekuPicsShowAdapter extends RecyclerView.Adapter<ChekuPicsShowAdapter.ChekuPicsShowViewHolder> {

    List<ChekuPicsBean> data;
    Context context;

    public ChekuPicsShowAdapter(List<ChekuPicsBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public ChekuPicsShowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cheku_pics_show, null);
        return new ChekuPicsShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChekuPicsShowViewHolder holder, final int position) {
        x.image().bind(holder.item_cheku_pics_show_iv, data.get(position).getPhotourl());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ChekuPicsShowViewHolder extends RecyclerView.ViewHolder {

        ImageView item_cheku_pics_show_iv;

        public ChekuPicsShowViewHolder(View itemView) {
            super(itemView);
            item_cheku_pics_show_iv = (ImageView) itemView.findViewById(R.id.item_cheku_pics_show_iv);
        }
    }


    public interface OnItemClickListener {
        public void onItemClick(int postion);
    }

    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}
