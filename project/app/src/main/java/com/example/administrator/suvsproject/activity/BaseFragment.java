package com.example.administrator.suvsproject.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/11/11 0011.
 */
public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

    View view=inflater.inflate(setLayoutId(),container,false);

        //初始化控件
        findViews(view);
        //初始化界面
        init();
        //初始化控件的点击事件
        initEvent();
        //缓存加载数据
        loadData();
        //网络加载数据
        requestNetData();
        return view;

    }
    // 初始化控件
    protected abstract void findViews(View view);

    // 初始化控件的点击事件
    protected abstract void initEvent();

    // 初始化界面
    protected abstract void init();

    // 加载数据
    protected abstract void loadData();
    protected abstract void requestNetData();


    protected abstract int setLayoutId();
}
