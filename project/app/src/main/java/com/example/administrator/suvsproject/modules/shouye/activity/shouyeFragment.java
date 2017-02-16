package com.example.administrator.suvsproject.modules.shouye.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.suvsproject.R;
import com.example.administrator.suvsproject.SUVsApplication;
import com.example.administrator.suvsproject.activity.BaseFragment;
import com.example.administrator.suvsproject.activity.LogActivity;
import com.example.administrator.suvsproject.modules.shouye.adapter.MyFragmentAdapter;
import com.example.administrator.suvsproject.modules.shouye.bean.TabsInfo;
import com.example.administrator.suvsproject.modules.shouye.net.OkhttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/11/11 0011.
 */
public class shouyeFragment extends BaseFragment {
    private View mine;
    private ImageView title_add;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<TabsInfo> listTabs;
    private TabsInfo info;
    private ArrayList<Fragment> fragmentList;
    private String urlTitles = "http://cmsweb.fblife.com/ajax.php?c=appnews&a=getNavigation&type=json";

    @Override
    protected void findViews(View view) {
        mine = view.findViewById(R.id.my_information_shouye);
        title_add = (ImageView) view.findViewById(R.id.shouye_iv_add);
        /**
         * 设置 Mode:
         *      tabLayout.setTabMode(TabLayout.MODE_FIXED);
         *  有两种值：
         *      MODE_SCROLLABLE:tab的内容超过屏幕宽度支持横向水平滑动
         *      MODE_FIXED:tab的内容超过屏幕宽度不支持横向水平滑动
         */
        tabLayout = (TabLayout) view.findViewById(R.id.shouye_tablayout);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        viewPager = (ViewPager) view.findViewById(R.id.redian_view_pager);

    }

    @Override
    protected void initEvent() {
        mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), LogActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void init() {

    }

    @Override
    protected void loadData() {
        try {
            OkhttpUtils.get(urlTitles, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String result = response.body().string();
                    if(!result.isEmpty()) {
                        try {
                            listTabs = new ArrayList<>();
                            JSONObject jsonObject = new JSONObject(result);
                            JSONArray array = jsonObject.getJSONArray("datainfo");
                            for (int i = 0; i < array.length(); i++) {
                                try {
                                    info = new TabsInfo();
                                    JSONObject subJson = array.getJSONObject(i);
                                    info.setName(subJson.getString("name"));
                                    info.setPath(subJson.getString("path"));
                                    listTabs.add(info);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        SUVsApplication.getHandler().post(new Runnable() {
                            @Override
                            public void run() {
                                fragmentList = new ArrayList<>();
                                for (int i = 0; i < listTabs.size(); i++) {
                                    Fragment fragment = new MyListFragment();
                                    Bundle bundle1 = new Bundle();
                                    bundle1.putString("name",listTabs.get(i).getName());
                                    bundle1.putString("path",listTabs.get(i).getPath());
                                    fragment.setArguments(bundle1);
                                    fragmentList.add(fragment);
                                }
                                MyFragmentAdapter adapter1 = new MyFragmentAdapter(getFragmentManager(),fragmentList);
                                viewPager.setAdapter(adapter1);
                                tabLayout.setupWithViewPager(viewPager);
                            }
                        });
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void requestNetData() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_shouye;
    }
}
