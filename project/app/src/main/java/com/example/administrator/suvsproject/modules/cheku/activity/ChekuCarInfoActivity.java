package com.example.administrator.suvsproject.modules.cheku.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.administrator.suvsproject.R;
import com.example.administrator.suvsproject.modules.cheku.bean.CarBean;
import com.example.administrator.suvsproject.modules.luntan.activity.LastReplyActivity;

/**
 * Created by Administrator on 2016/11/15 0015.
 */
public class ChekuCarInfoActivity extends AppCompatActivity {

    ImageView cheku_carinfo_back;
    TextView cheku_carinfo_title;
    RadioGroup cheku_carinfo_rg;
    RadioButton cheku_carinfo_rb_zongshu, cheku_carinfo_rb_pics, cheku_carinfo_rb_information;
    FrameLayout cheku_carinfo_fl;

    FragmentManager fm;
    ChekuZongShuFragment czf;
    ChekuPicsFragment cpf;
    ChekuInformationFragment cif;

    //传来的数据对象
    CarBean bean;

    //综述
    String words;
    String fid;
    //记录点击论坛的前一个点击radiobutton是哪个
    int location = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carinfo);

        bean = (CarBean) getIntent().getBundleExtra("car").getSerializable("carbean");

        words = bean.getWords();
        fid = bean.getFid();


        initView();
        init();
        initEvent();
    }


    //初始化控件
    private void initView() {
        cheku_carinfo_back = (ImageView) findViewById(R.id.cheku_carinfo_back);
        cheku_carinfo_title = (TextView) findViewById(R.id.cheku_carinfo_title);
        cheku_carinfo_rg = (RadioGroup) findViewById(R.id.cheku_carinfo_rg);
        cheku_carinfo_fl = (FrameLayout) findViewById(R.id.cheku_carinfo_fl);

        cheku_carinfo_rb_zongshu = (RadioButton) findViewById(R.id.cheku_carinfo_rb_zongshu);
        cheku_carinfo_rb_pics = (RadioButton) findViewById(R.id.cheku_carinfo_rb_pics);
        cheku_carinfo_rb_information = (RadioButton) findViewById(R.id.cheku_carinfo_rb_information);
    }

    private void init() {
        cheku_carinfo_title.setText(bean.getName());
        fm = getSupportFragmentManager();
        czf = new ChekuZongShuFragment();
        Bundle b = new Bundle();
        b.putString("words", words);
        b.putSerializable("bean", bean);
        czf.setArguments(b);
        fm.beginTransaction().add(R.id.cheku_carinfo_fl, czf).commit();
    }

    //监听事件
    private void initEvent() {
        cheku_carinfo_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        cheku_carinfo_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.cheku_carinfo_rb_zongshu:
                        if (location == 0) {
                            return;
                        }
                        if (location == 1) {
                            fm.beginTransaction().remove(cpf).commit();
                        }
                        if (location == 2) {
                            fm.beginTransaction().remove(cif).commit();
                        }
                        location = 0;
                        break;

                    case R.id.cheku_carinfo_rb_pics:
                        if (cpf == null) {
                            cpf = new ChekuPicsFragment();
                        }
                        Bundle c = new Bundle();
                        c.putString("words", words);
                        cpf.setArguments(c);

                        if (location == 0) {
                            fm.beginTransaction().add(R.id.cheku_carinfo_fl, cpf).commit();
                        }
                        if (location == 2) {
                            fm.beginTransaction().remove(cif).add(R.id.cheku_carinfo_fl, cpf).commit();
                        }
                        location = 1;
                        break;

                    case R.id.cheku_carinfo_rb_information:
                        if (cif == null) {
                            cif = new ChekuInformationFragment();
                        }

                        Bundle d = new Bundle();
                        d.putString("words", words);
                        cif.setArguments(d);

                        if (location == 0) {
                            fm.beginTransaction().add(R.id.cheku_carinfo_fl, cif).commit();
                        }

                        if (location == 1) {
                            fm.beginTransaction().remove(cpf).add(R.id.cheku_carinfo_fl, cif).commit();
                        }
                        location = 2;
                        break;

                    case R.id.cheku_carinfo_rb_luntuan:
                        Intent i = new Intent(ChekuCarInfoActivity.this, LastReplyActivity.class);
                        Bundle b = new Bundle();
                        b.putString("fid", fid);
                        b.putInt("tag", 3);
                        i.putExtras(b);
                        startActivity(i);
                        if (location == 0) {
                            cheku_carinfo_rb_zongshu.setChecked(true);
                        }
                        if (location == 1) {
                            cheku_carinfo_rb_pics.setChecked(true);
                        }
                        if (location == 2) {
                            cheku_carinfo_rb_information.setChecked(true);
                        }
                        break;
                }

            }
        });


    }


}
