package com.example.administrator.suvsproject.modules.MyLogin.activity.Collection;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.administrator.suvsproject.R;
import com.example.administrator.suvsproject.activity.BaseActivity;
import com.example.administrator.suvsproject.modules.MyLogin.adapter.CollectionFragmnetAdapter;
import com.example.administrator.suvsproject.modules.MyLogin.bean.Person.Logininfo;
import com.se7en.utils.SystemUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyCollection extends BaseActivity{

    private TabLayout tabLayout;
    private ViewPager viewPager;
    String[] titles={"新闻","帖子","版块"};
    private List<Fragment> fragmentList;
    private Logininfo logininfo;
    private String bbsinfo;
    private String[] urls;


    @Override
    protected void findViews() {
        tabLayout = (TabLayout) findViewById(R.id.mycollection_tab_view);
        viewPager = (ViewPager) findViewById(R.id.mycollection_view_pager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);


    }
    @Override
    protected void init() {

        try {
            logininfo = (Logininfo) SystemUtil.getSharedSerializable("loginfo");
            bbsinfo = logininfo.getBbsinfo();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        urls = new String[]{"http://cmsweb.fblife.com/ajax.php?c=newstwo&a=favoriteslist&type=json&took="+bbsinfo,
                "http://bbs.fblife.com/bbsapinew/favoritesthread.php?authcode="+bbsinfo
                ,"http://bbs.fblife.com/bbsapinew/favoritesforums.php?authcode="+bbsinfo};

        fragmentList = new ArrayList<>();

        //新闻fragment
        Fragment fragment1 = new CollectionFragment2();
        Bundle bundle1=new Bundle();
        bundle1.putString("title",titles[0]);
        bundle1.putString("url", urls[0]);
        fragment1.setArguments(bundle1);
        fragmentList.add(fragment1);

        //帖子fragment
            Fragment fragment2 = new CollectionFragment();
            Bundle bundle2=new Bundle();
            bundle2.putString("title",titles[1]);
            bundle2.putString("url", urls[1]);
            fragment2.setArguments(bundle2);
            fragmentList.add(fragment2);



        //版块fragment
        BankuaiFragment bankuaiFragment = new BankuaiFragment();
        Bundle bundle=new Bundle();
        bundle.putString("title",titles[2]);
        bundle.putString("url", urls[2]);
        bankuaiFragment.setArguments(bundle);

        fragmentList.add(bankuaiFragment);


    }
    @Override
    protected void initEvent() {
        findViewById(R.id.mycollect_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    @Override
    protected void loadData() {
        CollectionFragmnetAdapter adapter=new CollectionFragmnetAdapter(getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected int setLatoutId() {
        return R.layout.activity_my_collection;
    }

    @Override
    protected void requestNetData() {

    }
}
