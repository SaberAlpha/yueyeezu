package com.example.administrator.suvsproject.modules.MyLogin.activity;

import android.view.View;

import com.example.administrator.suvsproject.R;
import com.example.administrator.suvsproject.activity.BaseActivity;

public class MyFriend extends BaseActivity{


    @Override
    protected void loadData() {

    }

    @Override
    protected void init() {

    }

    @Override
    protected void initEvent() {
            findViewById(R.id.myfriend_back).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
    }

    @Override
    protected void findViews() {

    }

    @Override
    protected int setLatoutId() {
        return R.layout.activity_my_friend;
    }

    @Override
    protected void requestNetData() {

    }
}
