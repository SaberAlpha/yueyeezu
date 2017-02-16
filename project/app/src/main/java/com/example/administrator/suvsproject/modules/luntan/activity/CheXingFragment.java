package com.example.administrator.suvsproject.modules.luntan.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.administrator.suvsproject.R;
import com.example.administrator.suvsproject.activity.BaseFragment;
import com.example.administrator.suvsproject.adapter.CommonAdapter;
import com.example.administrator.suvsproject.adapter.ViewHolder;
import com.example.administrator.suvsproject.i.BaseCallBack;
import com.example.administrator.suvsproject.modules.luntan.bean.CheXingInfo;
import com.example.administrator.suvsproject.modules.luntan.dao.LunTanDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/14 0014.
 */
public class CheXingFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private ListView listView;
    private List<CheXingInfo> list;
    private CommonAdapter<CheXingInfo> adapter;
    private ProgressBar progressBar;

    @Override
    protected void findViews(View view) {
        listView = (ListView) view.findViewById(R.id.list_view);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
    }

    @Override
    protected void initEvent() {
        listView.setOnItemClickListener(this);
    }

    @Override
    protected void init() {
        list = new ArrayList<>();
        adapter = new CommonAdapter<CheXingInfo>(getContext(), list, R.layout.adpter_chexing_info) {
            @Override
            public void convert(ViewHolder helper, int position, CheXingInfo item) {
                TextView positionView = helper.getView(R.id.position_tv);
                if (position < 3) {
                    positionView.setTextColor(Color.RED);
                }
                helper.setText(R.id.position_tv, position + 1 + "");
                helper.setText(R.id.title_tv, item.getTitle());
                helper.setText(R.id.num_tv, item.getNum() + "贴");

                CheckBox shoucangCb = helper.getView(R.id.shoucang_cb);
                shoucangCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            buttonView.setBackgroundResource(R.drawable.forum_collect_down);
                        } else {
                            buttonView.setBackgroundResource(R.drawable.forum_collect_up);
                        }
                    }
                });

            }
        };
        listView.setAdapter(adapter);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void requestNetData() {
        LunTanDao.requestCheXingList(new BaseCallBack() {
            @Override
            public void success(Object data) {
                progressBar.setVisibility(View.GONE);
                List<CheXingInfo> tempList = (List<CheXingInfo>) data;
                if (tempList != null) {
                    list.addAll(tempList);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void failed(int errorCode, Object data) {

            }
        });
    }

    @Override
    protected int setLayoutId() {
        return R.layout.luntan_list_view_adapter;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String fid = list.get(position).getId();
        Intent intent = new Intent(getActivity(), LastReplyActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("fid", fid);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
