package com.example.administrator.suvsproject.modules.cheku.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.suvsproject.R;
import com.example.administrator.suvsproject.modules.cheku.bean.CarBean;

import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2016/11/15 0015.
 */
public class ChekuSortDetailAdapter extends RecyclerView.Adapter<ChekuSortDetailAdapter.ChekuSortDetailViewHolder> {

    List<CarBean> data;
    Context context;

    public ChekuSortDetailAdapter(List<CarBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public ChekuSortDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cheku_sort_detail, null);
        return new ChekuSortDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChekuSortDetailViewHolder holder, final int position) {
        holder.item_cheku_sort_detail_name.setText(data.get(position).getName());
        holder.item_cheku_sort_detail_size.setText(data.get(position).getSize());
        double min = Integer.parseInt(data.get(position).getSeries_price_min()) / 10000.0;
        double max = Integer.parseInt(data.get(position).getSeries_price_max()) / 10000.0;

        holder.item_cheku_sort_detail_price.setText(min + "-" + max + "万");
        x.image().bind(holder.item_cheku_sort_detail_iv, data.get(position).getPhoto());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(data.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ChekuSortDetailViewHolder extends RecyclerView.ViewHolder {

        ImageView item_cheku_sort_detail_iv;
        TextView item_cheku_sort_detail_name;
        TextView item_cheku_sort_detail_size;
        TextView item_cheku_sort_detail_price;

        public ChekuSortDetailViewHolder(View itemView) {
            super(itemView);
            item_cheku_sort_detail_iv = (ImageView) itemView.findViewById(R.id.item_cheku_sort_detail_iv);
            item_cheku_sort_detail_name = (TextView) itemView.findViewById(R.id.item_cheku_sort_detail_name);
            item_cheku_sort_detail_size = (TextView) itemView.findViewById(R.id.item_cheku_sort_detail_size);
            item_cheku_sort_detail_price = (TextView) itemView.findViewById(R.id.item_cheku_sort_detail_price);
        }
    }


    public interface OnItemClickListener {
        public void onItemClick(CarBean bean);
    }

    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
