package com.example.administrator.suvsproject.modules.MyLogin.activity;

import android.view.View;

import com.example.administrator.suvsproject.R;
import com.example.administrator.suvsproject.activity.BaseActivity;

public class SiXin extends BaseActivity {

    @Override
    protected void loadData() {

    }

    @Override
    protected void init() {

    }

    @Override
    protected void initEvent() {
        findViewById(R.id.message_back).setOnClickListener(new View.OnClickListener() {
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
        return R.layout.activity_si_xin;
    }

    @Override
    protected void requestNetData() {

    }
}
