package com.example.administrator.suvsproject.modules.cheku.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.suvsproject.R;
import com.example.administrator.suvsproject.i.BaseCallBack;
import com.example.administrator.suvsproject.modules.cheku.adapter.ChekuPicsShowAdapter;
import com.example.administrator.suvsproject.modules.cheku.bean.ChekuPicsBean;
import com.example.administrator.suvsproject.modules.cheku.util.ChekuJsonParseUtil;
import com.example.administrator.suvsproject.net.HttpUtil;

import org.json.JSONException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/16 0016.
 */
public class ChekuPicsInteriorFragment extends Fragment {

    View view;
    RecyclerView cheku_pics_show_rv;
    ChekuPicsShowAdapter adapter;
    List<ChekuPicsBean> data;

    String url = "http://carport.fblife.com/carapi/getphotolist.php?pagesize=18&datatype=json&type=2&words=";
    int page = 1;
    String json;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cheku_pics_show, null);
        String words = getArguments().getString("words");
        url = url + words + "&page" + page;
        initView();
        init();
        initEvent();
        initData();
        return view;
    }


    private void initView() {

        cheku_pics_show_rv = (RecyclerView) view.findViewById(R.id.cheku_pics_show_rv);
    }


    private void init() {
        data = new ArrayList<>();
        adapter = new ChekuPicsShowAdapter(data, getContext());
        cheku_pics_show_rv.setLayoutManager(new GridLayoutManager(getContext(), 3));
        cheku_pics_show_rv.setAdapter(adapter);
    }


    private void initEvent() {

        adapter.setOnItemClickListener(new ChekuPicsShowAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent i = new Intent(getContext(), ChekuPicsShowActivity.class);
                i.putExtra("position", position);
                i.putExtra("json", json);

                Bundle b = new Bundle();
                b.putSerializable("list", (Serializable) data);
                i.putExtras(b);
                startActivity(i);
            }
        });

    }

    private void initData() {
        HttpUtil.doHttpReqeust1("GET", url, null, new BaseCallBack() {
            @Override
            public void success(Object result) {
                json = (String) result;
                try {
                    data.addAll(ChekuJsonParseUtil.getChekuPicsBean(json));
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
