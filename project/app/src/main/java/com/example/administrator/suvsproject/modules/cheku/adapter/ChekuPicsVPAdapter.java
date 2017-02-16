package com.example.administrator.suvsproject.modules.cheku.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.suvsproject.modules.cheku.bean.ChekuPicsBean;

import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2016/11/17 0017.
 */
public class ChekuPicsVPAdapter extends PagerAdapter {

    List<ChekuPicsBean> data;
    Context context;

    public ChekuPicsVPAdapter(List<ChekuPicsBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView iv = new ImageView(context);
        x.image().bind(iv, data.get(position).getPhotourl());
        iv.setScaleType(ImageView.ScaleType.FIT_CENTER);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPageClickListener.onPageClick();
            }
        });
        container.addView(iv);
        return iv;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    OnPageClickListener onPageClickListener;

    public interface OnPageClickListener {
        public void onPageClick();
    }

    public void setOnPageClickListener(OnPageClickListener onPageClickListener) {
        this.onPageClickListener = onPageClickListener;
    }
}
