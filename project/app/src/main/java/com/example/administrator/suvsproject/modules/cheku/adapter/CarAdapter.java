package com.example.administrator.suvsproject.modules.cheku.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.example.administrator.suvsproject.R;
import com.example.administrator.suvsproject.modules.cheku.bean.CarBean;
import com.example.administrator.suvsproject.util.ImageLoaderUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class CarAdapter extends BaseAdapter implements SectionIndexer {
    private List<CarBean> list = null;
    private Context mContext;

    public CarAdapter(Context mContext, List<CarBean> list) {
        this.mContext = mContext;
        this.list = list;
    }


    public int getCount() {
        return this.list.size();
    }

    public Object getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup arg2) {
        ViewHolder viewHolder = null;
        final CarBean mContent = list.get(position);
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_cheku_default, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        // ���position��ȡ���������ĸ��Char asciiֵ
        int section = getSectionForPosition(position);

        // ���ǰλ�õ��ڸ÷�������ĸ��Char��λ�� ������Ϊ�ǵ�һ�γ���
        if (position == getPositionForSection(section)) {
            viewHolder.car_fwords.setVisibility(View.VISIBLE);
            viewHolder.car_fwords.setText(mContent.getFwords());
        } else {
            viewHolder.car_fwords.setVisibility(View.GONE);
        }

        viewHolder.car_name.setText(mContent.getName());
        //带有缓存
        ImageLoader.getInstance().displayImage(mContent.getPhoto(), viewHolder.car_photo,
                ImageLoaderUtil.getFadeInOption(100, false, false, false));
        //x.image().bind(viewHolder.car_photo, mContent.getPhoto());

        return view;

    }

    final static class ViewHolder {
        TextView car_fwords;
        TextView car_name;
        ImageView car_photo;

        public ViewHolder(View view) {
            car_photo = (ImageView) view.findViewById(R.id.car_photo);
            car_fwords = (TextView) view.findViewById(R.id.car_fwords);
            car_name = (TextView) view.findViewById(R.id.car_name);

        }
    }

    /**
     * ���ListView�ĵ�ǰλ�û�ȡ���������ĸ��Char asciiֵ
     */
    public int getSectionForPosition(int position) {
        return list.get(position).getFwords().charAt(0);
    }

    /**
     * ��ݷ��������ĸ��Char asciiֵ��ȡ���һ�γ��ָ�����ĸ��λ��
     */
    public int getPositionForSection(int section) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = list.get(i).getFwords();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }

        return -1;
    }

    /**
     * ��ȡӢ�ĵ�����ĸ����Ӣ����ĸ��#���档
     *
     * @param str
     * @return
     */
    private String getAlpha(String str) {
        String sortStr = str.trim().substring(0, 1).toUpperCase();
        // ������ʽ���ж�����ĸ�Ƿ���Ӣ����ĸ
        if (sortStr.matches("[A-Z]")) {
            return sortStr;
        } else {
            return "#";
        }
    }

    @Override
    public Object[] getSections() {
        return null;
    }
}