package com.example.administrator.suvsproject.modules.cheku.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.suvsproject.R;
import com.example.administrator.suvsproject.modules.cheku.adapter.ChekuSortAdapter;
import com.example.administrator.suvsproject.modules.cheku.bean.CarSortBean;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/14 0014.
 */
public class CheKuSortFragment extends Fragment {

    RecyclerView cheku_sort_rv;
    TextView cheku_sort_all;
    View view;
    //数据
    List<CarSortBean> data;
    List<CarSortBean> sortList, priceList, countryList, sizeList;
    //判断是哪个点击
    int value;
    //recyclerview适配器
    ChekuSortAdapter adapter;
    //用户的选择
    String url = null;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cheku_sort, null);

        initView();
        value = getArguments().getInt("value");
        initData(value);
        init();
        initEvent();

        return view;
    }


    private void initView() {
        cheku_sort_all = (TextView) view.findViewById(R.id.cheku_sort_all);
        cheku_sort_rv = (RecyclerView) view.findViewById(R.id.cheku_sort_rv);
    }

    private void initData(int value) {
        data = new ArrayList<>();
        sortList = new ArrayList<>();
        sortList.add(new CarSortBean("全领域SUV", "http://carport.fblife.com/carapi/getsortserieslist.php?pagesize=20&datatype=json&type=1&page=1"));
        sortList.add(new CarSortBean("城市SUV", "http://carport.fblife.com/carapi/getsortserieslist.php?pagesize=20&datatype=json&type=2&page=1"));
        sortList.add(new CarSortBean("硬派SUV", "http://carport.fblife.com/carapi/getsortserieslist.php?pagesize=20&datatype=json&type=3&page=1"));
        sortList.add(new CarSortBean("跨界SUV", "http://carport.fblife.com/carapi/getsortserieslist.php?pagesize=20&datatype=json&type=4&page=1"));
        sortList.add(new CarSortBean("皮卡", "http://carport.fblife.com/carapi/getsortserieslist.php?pagesize=20&datatype=json&type=5&page=1"));

        priceList = new ArrayList<>();
        priceList.add(new CarSortBean("10万以下", "http://carport.fblife.com/carapi/getsortserieslist.php?pagesize=20&datatype=json&&&country=0&price=1&page=1"));
        priceList.add(new CarSortBean("10-20万", "http://carport.fblife.com/carapi/getsortserieslist.php?pagesize=20&datatype=json&&&country=0&price=2&page=1"));
        priceList.add(new CarSortBean("20-30万", "http://carport.fblife.com/carapi/getsortserieslist.php?pagesize=20&datatype=json&&&country=0&price=3&page=1"));
        priceList.add(new CarSortBean("30-50万", "http://carport.fblife.com/carapi/getsortserieslist.php?pagesize=20&datatype=json&&&country=0&price=4&page=1"));
        priceList.add(new CarSortBean("50-100万", "http://carport.fblife.com/carapi/getsortserieslist.php?pagesize=20&datatype=json&&&country=0&price=5&page=1"));
        priceList.add(new CarSortBean("100万以上", "http://carport.fblife.com/carapi/getsortserieslist.php?pagesize=20&datatype=json&&&country=0&price=6&page=1"));

        countryList = new ArrayList<>();
        countryList.add(new CarSortBean("中国", "http://carport.fblife.com/carapi/getsortserieslist.php?pagesize=20&datatype=json&&&country=1&page=1"));
        countryList.add(new CarSortBean("德国", "http://carport.fblife.com/carapi/getsortserieslist.php?pagesize=20&datatype=json&&&country=2&page=1"));
        countryList.add(new CarSortBean("日本", "http://carport.fblife.com/carapi/getsortserieslist.php?pagesize=20&datatype=json&&&country=3&page=1"));
        countryList.add(new CarSortBean("美国", "http://carport.fblife.com/carapi/getsortserieslist.php?pagesize=20&datatype=json&&&country=4&page=1"));
        countryList.add(new CarSortBean("韩国", "http://carport.fblife.com/carapi/getsortserieslist.php?pagesize=20&datatype=json&&&country=5&page=1"));
        countryList.add(new CarSortBean("法国", "http://carport.fblife.com/carapi/getsortserieslist.php?pagesize=20&datatype=json&&&country=6&page=1"));
        countryList.add(new CarSortBean("英国", "http://carport.fblife.com/carapi/getsortserieslist.php?pagesize=20&datatype=json&&&country=7&page=1"));
        countryList.add(new CarSortBean("意大利", "http://carport.fblife.com/carapi/getsortserieslist.php?pagesize=20&datatype=json&&&country=8&page=1"));
        countryList.add(new CarSortBean("瑞典", "http://carport.fblife.com/carapi/getsortserieslist.php?pagesize=20&datatype=json&&&country=9&page=1"));
        countryList.add(new CarSortBean("其他", "http://carport.fblife.com/carapi/getsortserieslist.php?pagesize=20&datatype=json&&&country=10&page=1"));

        sizeList = new ArrayList<>();
        sizeList.add(new CarSortBean("全尺寸", "http://carport.fblife.com/carapi/getsortserieslist.php?pagesize=20&datatype=json&&&type=5&size=1&page=1"));
        sizeList.add(new CarSortBean("重型", "http://carport.fblife.com/carapi/getsortserieslist.php?pagesize=20&datatype=json&&&type=5&size=2&page=1"));
        sizeList.add(new CarSortBean("大型", "http://carport.fblife.com/carapi/getsortserieslist.php?pagesize=20&datatype=json&&&type=5&size=3&page=1"));
        sizeList.add(new CarSortBean("中型", "http://carport.fblife.com/carapi/getsortserieslist.php?pagesize=20&datatype=json&&&type=5&size=4&page=1"));
        sizeList.add(new CarSortBean("微型", "http://carport.fblife.com/carapi/getsortserieslist.php?pagesize=20&datatype=json&&&type=5&size=5&page=1"));

        switch (value) {
            case 1:
                data.addAll(sortList);
                cheku_sort_all.setText("全部车型");
                break;
            case 2:
                data.addAll(priceList);
                cheku_sort_all.setText("全部价格");
                break;
            case 3:
                data.addAll(countryList);
                cheku_sort_all.setText("全部国家");
                break;
            case 4:
                data.addAll(sizeList);
                cheku_sort_all.setText("全部尺寸");
                break;
        }

    }

    private void init() {
        adapter = new ChekuSortAdapter(data, getContext());
        cheku_sort_rv.setLayoutManager(new GridLayoutManager(getContext(), 2));
        cheku_sort_rv.setAdapter(adapter);
    }

    private void initEvent() {
        cheku_sort_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (value) {
                    case 1:
                        url = "http://carport.fblife.com/carapi/getsortserieslist.php?pagesize=20&datatype=json&type=0&page=1";
                        break;
                    case 2:
                        url = "http://carport.fblife.com/carapi/getsortserieslist.php?pagesize=20&datatype=json&&&country=0&price=0&page=1";
                        break;
                    case 3:
                        url = "http://carport.fblife.com/carapi/getsortserieslist.php?pagesize=20&datatype=json&&&country=0&page=1";
                        break;
                    case 4:
                        url = "http://carport.fblife.com/carapi/getsortserieslist.php?pagesize=20&datatype=json&&&type=5&size=0&page=1";
                        break;
                }

                EventBus.getDefault().post(url);
            }
        });


        adapter.setOnItemClickListener(new ChekuSortAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String url) {
                EventBus.getDefault().post(url);
            }
        });
    }
}
