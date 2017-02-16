package com.example.administrator.suvsproject.modules.cheku.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.suvsproject.R;
import com.example.administrator.suvsproject.modules.cheku.bean.ChekuCarZongshuBean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/16 0016.
 */
public class ChekuZongshuAdapter extends BaseAdapter {
    List<ChekuCarZongshuBean> data;
    Context context;

    public ChekuZongshuAdapter(List<ChekuCarZongshuBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ChekuZongshuViewHoilder holder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_cheku_zongshu, null);
            holder = new ChekuZongshuViewHoilder(view);
            view.setTag(holder);
        } else {
            holder = (ChekuZongshuViewHoilder) view.getTag();
        }
        holder.item_cheku_zongshu_name.setText(data.get(position).getName().get(0));
        holder.item_cheku_zongshu_c_fdj.setText(data.get(position).getC_fdj().get(0));
        holder.item_cheku_zongshu_c_bsx.setText(data.get(position).getC_bsx().get(0));
        double price = Integer.parseInt(data.get(position).getC_cszdj().get(0)) / 10000.0;
        holder.item_cheku_zongshu_c_cszdj.setText(price + "万");
        return view;
    }

    class ChekuZongshuViewHoilder {
        TextView item_cheku_zongshu_name, item_cheku_zongshu_c_fdj,
                item_cheku_zongshu_c_bsx, item_cheku_zongshu_c_cszdj;

        public ChekuZongshuViewHoilder(View itemview) {
            item_cheku_zongshu_name = (TextView) itemview.findViewById(R.id.item_cheku_zongshu_name);
            item_cheku_zongshu_c_fdj = (TextView) itemview.findViewById(R.id.item_cheku_zongshu_c_fdj);
            item_cheku_zongshu_c_bsx = (TextView) itemview.findViewById(R.id.item_cheku_zongshu_c_bsx);
            item_cheku_zongshu_c_cszdj = (TextView) itemview.findViewById(R.id.item_cheku_zongshu_c_cszdj);
        }
    }

}
