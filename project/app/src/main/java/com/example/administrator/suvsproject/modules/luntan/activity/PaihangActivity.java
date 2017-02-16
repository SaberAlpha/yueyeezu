package com.example.administrator.suvsproject.modules.luntan.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.example.administrator.suvsproject.R;
import com.example.administrator.suvsproject.activity.BaseActivity;

public class PaihangActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    private ImageView backImg;
    private RadioGroup radioGroup;
    private int lastIndex = -1;
    private Fragment[] fragments = new Fragment[3];

    @Override
    protected void loadData() {

    }

    @Override
    protected void init() {
        addFragment(0);
    }

    @Override
    protected void initEvent() {
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        radioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    protected void findViews() {
        backImg = (ImageView) findViewById(R.id.back_luntan);
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
    }

    @Override
    protected int setLatoutId() {
        return R.layout.activity_paihang;
    }

    @Override
    protected void requestNetData() {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int position = 0;
        switch (checkedId) {
            case R.id.zhuti_rb:
                position = 0;
                break;
            case R.id.chexing_rb:
                position = 1;
                break;
            case R.id.dadui_rb:
                position = 2;
                break;
        }
        addFragment(position);
    }

    private void addFragment(int position) {
        if (lastIndex == position) {
            return;
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (lastIndex != -1) {
            transaction.hide(fragments[lastIndex]);
        }

        if (fragments[position] == null) {
            switch (position) {
                case 0:
                    fragments[0] = new ZhuTiFragment();
                    break;
                case 1:
                    fragments[1] = new CheXingFragment();
                    break;
                case 2:
                    fragments[2] = new DaDuiFragment();
                    break;
            }
            transaction.add(R.id.paihang_frag_layout,fragments[position]);
        }else {
            transaction.show(fragments[position]);
        }
        lastIndex = position;
        transaction.commit();
    }


}
