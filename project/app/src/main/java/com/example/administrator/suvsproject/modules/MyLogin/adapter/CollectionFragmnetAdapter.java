package com.example.administrator.suvsproject.modules.MyLogin.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/11/13.
 */
public class CollectionFragmnetAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragmentList;

    public CollectionFragmnetAdapter(FragmentManager supportFragmentManager, List<Fragment> fragmentList) {
        super(supportFragmentManager);
        this.fragmentList =fragmentList;
    }


    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return  fragmentList.get(position).getArguments().getString("title");
    }
}
