package com.example.administrator.suvsproject.modules.shouye.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by DQH on 2016/11/14.
 */
public class MyFragmentAdapter extends FragmentPagerAdapter {
    private final List<Fragment> fragmentList;
    public MyFragmentAdapter(FragmentManager fm , List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    //返回当前页面的标题
    @Override
    public CharSequence getPageTitle(int position) {
        String title = fragmentList.get(position).getArguments().getString("name");
        return  title;
    }
}
