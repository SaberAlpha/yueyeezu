package com.example.administrator.suvsproject.modules.MyLogin.activity.Search;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.administrator.suvsproject.R;
import com.example.administrator.suvsproject.activity.BaseActivity;

public class SearchActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {


    private EditText editText;
    private View calenview;
    private String[] urls;
    private View clean;

    @Override
    protected void loadData() {

    }

    @Override
    protected void init() {
        urls = new String[]{"http://so.fblife.com/api/searchapi.php?fromtype=2",
                "http://so.fblife.com/api/searchapi.php?fromtype=1"
                ,"http://fb.fblife.com/openapi/index.php?mod=search&code=user&fromtype=7c383caa&fbtype=json&pagesize=10"};
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void findViews() {
        editText = (EditText) findViewById(R.id.mysearch_text);
        calenview = findViewById(R.id.myhome_search_canle);
        clean = findViewById(R.id.search_clean);

        clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
                clean.setVisibility(View.INVISIBLE);
            }
        });
        ((RadioGroup)findViewById(R.id.mysearch_radiogroup)).setOnCheckedChangeListener(this);

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clean.setVisibility(View.VISIBLE);

            }
        });
        calenview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected int setLatoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void requestNetData() {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if(TextUtils.isEmpty(editText.getText())){
            Toast.makeText(SearchActivity.this, "请输入搜索内容", Toast.LENGTH_SHORT).show();
            return;
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (checkedId){
            case R.id.mysearch_zixun:
                Fragment zixunfragment=new zixunFragment();
                Bundle bundle=new Bundle();
                bundle.putString("text", String.valueOf(editText.getText()));
                bundle.putString("url",urls[0]);
                zixunfragment.setArguments(bundle);
                transaction.add(R.id.mysearch_container,zixunfragment);
                break;
            case R.id.mysearch_luntan:
                Fragment luntafragment=new myluntanFragment();
                Bundle bundle1=new Bundle();
                bundle1.putString("text", String.valueOf(editText.getText()));
                luntafragment.setArguments(bundle1);
                bundle1.putString("url",urls[1]);
                transaction.add(R.id.mysearch_container,luntafragment);
                break;
            case R.id.mysearch_yonghu:
                Fragment yonghufragment=new yonghuFragment();
                Bundle bundle2=new Bundle();
                bundle2.putString("text", String.valueOf(editText.getText()));
                yonghufragment.setArguments(bundle2);
                bundle2.putString("url",urls[2]);
                transaction.add(R.id.mysearch_container,yonghufragment);
                break;

        }
        transaction.commit();
    }
}
