package com.example.administrator.suvsproject.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2016/11/11 0011.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(setLatoutId());
        //初始化控件
        findViews();
        //初始化界面
        init();
        //初始化控件的点击事件
        initEvent();

        //加载数据
        loadData();

        //网络加载数据
        requestNetData();
    }

    protected abstract void loadData();

    protected abstract void init();

    protected abstract void initEvent();

    protected abstract void findViews();

    protected abstract int setLatoutId();

    protected abstract void requestNetData();
}
