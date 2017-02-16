package com.example.administrator.suvsproject.activity;

import android.content.Intent;
import android.view.View;

import com.example.administrator.suvsproject.R;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends BaseActivity {


    @Override
    protected void loadData() {

    }

    @Override
    protected void init() {
              Timer timer=new Timer();
              timer.schedule(new TimerTask() {
                  @Override
                  public void run() {
                      Intent intent=new Intent(WelcomeActivity.this,MainActivity.class);
                      startActivity(intent);
                      finish();
                  }
              },2000);

    }

    @Override
    protected void initEvent() {
        findViewById(R.id.welome_jump).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WelcomeActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void findViews() {

    }

    @Override
    protected int setLatoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void requestNetData() {

    }
}
