package com.example.administrator.suvsproject.modules.shouye.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.suvsproject.modules.shouye.bean.SlidesInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by asus on 2016/9/29.
 */
public class MyPagerAdapter extends PagerAdapter {


    private final Context context;
    private List<SlidesInfo> datas;

    public MyPagerAdapter(Context context, List<SlidesInfo> datas) {
        this.context = context;
        this.datas = datas;
    }

    //返回需要添加的页面的个数
    @Override
    public int getCount() {
        if (datas != null) {
            return Integer.MAX_VALUE;
            // return mDatas.size();
        }
        return datas.size();
    }


    //instantiateItem:加载每一个子视图，
    // 参数二：position当前需要加载的子视图的位置
    //参数一：是适配器被设置到的ViewPager对象
    //返回值：返回本次被加载的视图
    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        position = position % datas.size();
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Picasso.with(context).load(datas.get(position).getPhoto()).into(imageView);
        //需要自己将imageView加入ViewPager
        container.addView(imageView);

        return imageView;
    }



    /**
     * //删除不用的item的，
     * @param container 是ViewPager
     * @param position  参数position就是用不到的视图的位置，
     * @param object  要被删除的视图对象
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);

    }

    //判断instantiateItem返回的对象和实际显示的对象是否相同
    //直接返回view == object的结果即可
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
