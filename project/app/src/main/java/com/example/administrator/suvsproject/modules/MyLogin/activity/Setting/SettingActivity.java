package com.example.administrator.suvsproject.modules.MyLogin.activity.Setting;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.suvsproject.R;
import com.example.administrator.suvsproject.activity.BaseActivity;

public class SettingActivity extends BaseActivity {

    @Override
    protected void findViews() {

    }

    @Override
    protected void init() {

    }

    @Override
    protected void initEvent() {
        findViewById(R.id.setting_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
    public void settingclick(View view){
        switch (view.getId()){
            case R.id.setting_unsaving:
                break;
            case R.id.setting_suggestion:
                break;
            case R.id.setting_updateversion:
                Toast.makeText(SettingActivity.this, "当前版本为最新版本", Toast.LENGTH_SHORT).show();
                break;
            case R.id.setting_about:
                Intent aboutintent=new Intent(this,AboutActivity.class);
                startActivity(aboutintent);
                break;
        }
    }

    @Override
    protected void loadData() {

    }


    @Override
    protected int setLatoutId() {
        return R.layout.activity_setting;

    }

    @Override
    protected void requestNetData() {

    }
}
