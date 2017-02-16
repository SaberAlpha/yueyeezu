package com.example.administrator.suvsproject.modules.cheku.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.administrator.suvsproject.R;
import com.example.administrator.suvsproject.activity.LogActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by Administrator on 2016/11/15 0015.
 */
public class chekuFragment extends Fragment implements View.OnClickListener {


    View view;
    FrameLayout cheku_fl;
    ImageView cheku_mine, cheku_search;


    Button cheku_sort, cheku_price, cheku_country, cheku_size;

    //分类选择界面
    CheKuSortFragment csf;
    //分类选择后的详情界面
    ChekuSortDetailFragment csdf;

    FragmentManager fm;

    //判断分类是否可以显示界面
    Boolean canShow = true;
    //判断当前是分类fragment（111）还是点击分类之后的fragment（222）
    int page;
    //判断上次是哪个点击的
    int location = -1;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cheku, null);

        EventBus.getDefault().register(this);

        initView();
        init();
        initEvent();
        return view;
    }

    private void init() {
        fm = getChildFragmentManager();
        fm.beginTransaction().add(R.id.cheku_fl, new ChekuDefaultFragment()).commit();
    }


    /**
     * 初始化控件
     */
    private void initView() {
        cheku_fl = (FrameLayout) view.findViewById(R.id.cheku_fl);
        cheku_mine = (ImageView) view.findViewById(R.id.cheku_mine);
        cheku_search = (ImageView) view.findViewById(R.id.cheku_search);

        cheku_sort = (Button) view.findViewById(R.id.cheku_sort);
        cheku_price = (Button) view.findViewById(R.id.cheku_price);
        cheku_country = (Button) view.findViewById(R.id.cheku_country);
        cheku_size = (Button) view.findViewById(R.id.cheku_size);
    }


    /**
     * 监听事件
     */
    private void initEvent() {
        //跳转到个人界面
        cheku_mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), LogActivity.class));
            }
        });

        //搜索的监听事件
        cheku_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ChekuSearchActivity.class));
            }
        });


        cheku_sort.setOnClickListener(this);
        cheku_price.setOnClickListener(this);
        cheku_country.setOnClickListener(this);
        cheku_size.setOnClickListener(this);

    }


    @Subscribe
    public void getCheSortDetailUrl(String url) {
        FragmentTransaction ft3 = fm.beginTransaction();
        ft3.remove(csf);

        csdf = new ChekuSortDetailFragment();
        int id = csdf.getId();

        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        bundle.putInt("id", id);

        ft3.add(R.id.cheku_fl, csdf);
        csdf.setArguments(bundle);

        page = 222;
        ft3.commit();
    }


    //移除（清空筛选条件的实现）
    public void remove(int page) {
        FragmentTransaction ft5 = fm.beginTransaction();
        ft5.remove(csdf);
        ft5.commit();
        canShow = true;
    }


    //车型---尺寸的监听事件
    @Override
    public void onClick(View v) {

        if (!canShow) {
            //恢复按钮为未选中状态
            switch (location) {
                case 1:
                    cheku_sort.setBackgroundResource(R.drawable.car_models_unpress);
                    break;
                case 2:
                    cheku_price.setBackgroundResource(R.drawable.car_price_unpress);
                    break;
                case 3:
                    cheku_country.setBackgroundResource(R.drawable.car_countrys_unpress);
                    break;
                case 4:
                    cheku_size.setBackgroundResource(R.drawable.car_size_unpress);
                    break;
            }

            if (page == 111) {
                fm.beginTransaction().remove(csf).commit();
            }
            if (page == 222) {
                fm.beginTransaction().remove(csdf).commit();
            }
            canShow = true;
            return;
        }

        if (csf == null) {
            csf = new CheKuSortFragment();
        }

        Bundle b = new Bundle();
        switch (v.getId()) {
            case R.id.cheku_sort:
                b.putInt("value", 1);
                cheku_sort.setBackgroundResource(R.drawable.car_models_onpress);
                location = 1;
                break;
            case R.id.cheku_price:
                b.putInt("value", 2);
                cheku_price.setBackgroundResource(R.drawable.car_price_onpress);
                location = 2;
                break;
            case R.id.cheku_country:
                b.putInt("value", 3);
                cheku_country.setBackgroundResource(R.drawable.car_countrys_onpress);
                location = 3;
                break;
            case R.id.cheku_size:
                b.putInt("value", 4);
                cheku_size.setBackgroundResource(R.drawable.car_size_onpress);
                location = 4;
                break;
        }
        csf.setArguments(b);
        fm.beginTransaction().add(R.id.cheku_fl, csf).commit();
        page = 111;
        canShow = false;
    }
}
