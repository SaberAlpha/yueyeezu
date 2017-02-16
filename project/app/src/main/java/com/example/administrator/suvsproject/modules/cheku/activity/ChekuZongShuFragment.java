package com.example.administrator.suvsproject.modules.cheku.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.suvsproject.R;
import com.example.administrator.suvsproject.i.BaseCallBack;
import com.example.administrator.suvsproject.modules.cheku.adapter.ChekuZongshuAdapter;
import com.example.administrator.suvsproject.modules.cheku.bean.CarBean;
import com.example.administrator.suvsproject.modules.cheku.bean.ChekuCarZongshuBean;
import com.example.administrator.suvsproject.modules.cheku.util.ChekuJsonParseUtil;
import com.example.administrator.suvsproject.net.HttpUtil;

import org.json.JSONException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/15 0015.
 */
public class ChekuZongShuFragment extends Fragment {

    ListView cheku_zongshu_lv;
    ChekuZongshuAdapter adapter;
    List<ChekuCarZongshuBean> data;
    CarBean bean;

    //头布局视图
    View headerview;
    ImageView headerview_cheku_zongshu_iv;
    TextView headerview_cheku_zongshu_name;
    TextView headerview_cheku_zongshu_size;
    TextView headerview_cheku_zongshu_price;


    View view;
    String url = "http://carport.fblife.com/carapi/getmodelalllist.php?datatype=json&words=";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cheku_zongshu, null);
        url = url + getArguments().getString("words");
        bean = (CarBean) getArguments().getSerializable("bean");

        initView();
        init();
        initData();
        return view;
    }


    private void init() {
        x.image().bind(headerview_cheku_zongshu_iv, bean.getPhoto());
        headerview_cheku_zongshu_name.setText(bean.getName());
        headerview_cheku_zongshu_size.setText(bean.getSize());

        headerview_cheku_zongshu_price.setText(Integer.parseInt(bean.getSeries_price_min()) / 10000.0 + "-" + Integer.parseInt(bean.getSeries_price_max()) / 10000.0);

        cheku_zongshu_lv.addHeaderView(headerview);
        data = new ArrayList<>();
        adapter = new ChekuZongshuAdapter(data, getContext());
        cheku_zongshu_lv.setAdapter(adapter);

    }

    private void initView() {
        cheku_zongshu_lv = (ListView) view.findViewById(R.id.cheku_zongshu_lv);
        headerview = LayoutInflater.from(getContext()).inflate(R.layout.headerview_cheku_zongshu, null);
        headerview_cheku_zongshu_iv = (ImageView) headerview.findViewById(R.id.headerview_cheku_zongshu_iv);
        headerview_cheku_zongshu_name = (TextView) headerview.findViewById(R.id.headerview_cheku_zongshu_name);
        headerview_cheku_zongshu_size = (TextView) headerview.findViewById(R.id.headerview_cheku_zongshu_size);
        headerview_cheku_zongshu_price = (TextView) headerview.findViewById(R.id.headerview_cheku_zongshu_price);
    }

    private void initData() {
        HttpUtil.doHttpReqeust1("GET", url, null, new BaseCallBack() {
            @Override
            public void success(Object result) {
                String json = (String) result;
                try {
                    data.addAll(ChekuJsonParseUtil.getChekuZongshuBean(json));
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(int errorCode, Object data) {

            }
        });
    }

}
