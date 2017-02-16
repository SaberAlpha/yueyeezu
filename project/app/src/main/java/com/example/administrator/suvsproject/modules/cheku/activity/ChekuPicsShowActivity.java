package com.example.administrator.suvsproject.modules.cheku.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.administrator.suvsproject.R;
import com.example.administrator.suvsproject.modules.cheku.adapter.ChekuPicsVPAdapter;
import com.example.administrator.suvsproject.modules.cheku.bean.ChekuPicsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/17 0017.
 */
public class ChekuPicsShowActivity extends AppCompatActivity {
    ViewPager cheku_pics_activity_vp;
    TextView cheku_pics_activity_page;

    int currentPosition;
    int all;
    String name;

    List<ChekuPicsBean> data;
    ChekuPicsVPAdapter adapter;


    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheku_pics_show);

        initData();
        initView();
        init();
        initEvent();
    }

    private void initData() {
        data = new ArrayList<>();
        Intent i = getIntent();
        currentPosition = i.getIntExtra("position", 0);
        data.addAll((List<ChekuPicsBean>) i.getExtras().getSerializable("list"));
        all = data.size();
        name = data.get(0).getName();
    }

    private void initEvent() {
        adapter.setOnPageClickListener(new ChekuPicsVPAdapter.OnPageClickListener() {
            @Override
            public void onPageClick() {
                finish();
            }
        });

        cheku_pics_activity_vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                cheku_pics_activity_page.setText((position + 1) + "/" + all + "\t" + name);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void init() {
        cheku_pics_activity_page.setText((currentPosition + 1) + "/" + all + "\t" + name);
        adapter = new ChekuPicsVPAdapter(data, this);
        cheku_pics_activity_vp.setAdapter(adapter);
        cheku_pics_activity_vp.setCurrentItem(currentPosition);
    }

    private void initView() {
        cheku_pics_activity_vp = (ViewPager) findViewById(R.id.cheku_pics_activity_vp);
        cheku_pics_activity_page = (TextView) findViewById(R.id.cheku_pics_activity_page);
    }
}
