package com.example.administrator.suvsproject.modules.cheku.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.suvsproject.R;
import com.example.administrator.suvsproject.i.BaseCallBack;
import com.example.administrator.suvsproject.modules.cheku.adapter.ChekuInfoAdapter;
import com.example.administrator.suvsproject.modules.cheku.bean.ChekuInformationBean;
import com.example.administrator.suvsproject.modules.cheku.util.ChekuJsonParseUtil;
import com.example.administrator.suvsproject.net.HttpUtil;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/15 0015.
 */
public class ChekuInformationFragment extends Fragment {

    View view;
    RecyclerView cheku_info_rv;
    ChekuInfoAdapter adapter;
    List<ChekuInformationBean> data;

    String url = "http://carport.fblife.com/carapi/getnewslist.php?pagesize=20&datatype=json&words=";
    int page = 1;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cheku_information, null);
        String words = getArguments().getString("words");
        url = url + words + "&page" + page;
        initView();
        init();
        initEvent();
        initData();
        return view;
    }


    private void initView() {

        cheku_info_rv = (RecyclerView) view.findViewById(R.id.cheku_info_rv);
    }


    private void init() {
        data = new ArrayList<>();
        adapter = new ChekuInfoAdapter(data, getContext());
        cheku_info_rv.setLayoutManager(new LinearLayoutManager(getContext()));
        cheku_info_rv.setAdapter(adapter);
    }


    private void initEvent() {

    }

    private void initData() {
        HttpUtil.doHttpReqeust1("GET", url, null, new BaseCallBack() {
            @Override
            public void success(Object result) {
                String json = (String) result;
                try {
                    data.addAll(ChekuJsonParseUtil.getChekuInfoBean(json));
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
