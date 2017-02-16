package com.example.administrator.suvsproject.modules.cheku.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.suvsproject.R;
import com.example.administrator.suvsproject.i.BaseCallBack;
import com.example.administrator.suvsproject.modules.cheku.adapter.ChekuSortDetailAdapter;
import com.example.administrator.suvsproject.modules.cheku.bean.CarBean;
import com.example.administrator.suvsproject.modules.cheku.util.ChekuJsonParseUtil;
import com.example.administrator.suvsproject.net.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/14 0014.
 */
public class ChekuSortDetailFragment extends Fragment {

    View view;
    TextView cheku_sort_detail_count;
    Button cheku_sort_detail_remove;

    List<CarBean> data;
    RecyclerView cheku_sort_detail_rv;
    ChekuSortDetailAdapter adapter;

    //数据接口url
    String url;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cheku_sort_detail, null);
        url = getArguments().getString("url");
        initView();
        init();
        initEvent();
        initData();
        return view;
    }

    private void init() {
        data = new ArrayList<>();
        adapter = new ChekuSortDetailAdapter(data, getContext());
        cheku_sort_detail_rv.setLayoutManager(new LinearLayoutManager(getContext()));
        cheku_sort_detail_rv.setAdapter(adapter);
    }

    private void initData() {
        HttpUtil.doHttpReqeust1("GET", url, null, new BaseCallBack() {
            @Override
            public void success(Object result) {
                String json = (String) result;

                try {
                    JSONObject object = new JSONObject(json);
                    cheku_sort_detail_count.setText("共" + object.getString("count") + "个筛选结果");

                    data.addAll(ChekuJsonParseUtil.getCarBeenListofSortDetail(json));
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(int errorCode, Object data) {
                Toast.makeText(getContext(), "加载数据失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initEvent() {
        cheku_sort_detail_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });


        adapter.setOnItemClickListener(new ChekuSortDetailAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(CarBean bean) {
                Intent i = new Intent(getContext(), ChekuCarInfoActivity.class);
                Bundle b = new Bundle();
                b.putSerializable("carbean", bean);
                i.putExtra("car", b);
                startActivity(i);
            }
        });
    }

    private void initView() {
        cheku_sort_detail_count = (TextView) view.findViewById(R.id.cheku_sort_detail_count);
        cheku_sort_detail_remove = (Button) view.findViewById(R.id.cheku_sort_detail_remove);
        cheku_sort_detail_rv = (RecyclerView) view.findViewById(R.id.cheku_sort_detail_rv);
    }
}
