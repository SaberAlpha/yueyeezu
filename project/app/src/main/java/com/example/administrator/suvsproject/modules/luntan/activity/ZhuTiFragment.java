package com.example.administrator.suvsproject.modules.luntan.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.administrator.suvsproject.R;
import com.example.administrator.suvsproject.activity.BaseFragment;
import com.example.administrator.suvsproject.adapter.CommonAdapter;
import com.example.administrator.suvsproject.adapter.ViewHolder;
import com.example.administrator.suvsproject.i.BaseCallBack;
import com.example.administrator.suvsproject.modules.luntan.bean.ZhuTiInfo;
import com.example.administrator.suvsproject.modules.luntan.dao.LunTanDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/14 0014.
 */
public class ZhuTiFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private ListView listView;
    private ImageView shoucangImageView;
    private List<ZhuTiInfo> list;
    private CommonAdapter<ZhuTiInfo> adapter;
    private String tid;
    private String url;
    private int allPages;
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
        adapter = new CommonAdapter<ZhuTiInfo>(getContext(), list, R.layout.adpter_zhuti_info) {
            @Override
            public void convert(ViewHolder helper, int position, ZhuTiInfo item) {
                TextView positionView = helper.getView(R.id.position_tv);
                if (position < 3) {
                    positionView.setTextColor(Color.RED);
                }
                helper.setText(R.id.position_tv, position + 1 + "");
                helper.setText(R.id.title_tv, item.getTitle());

                //收藏
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
        LunTanDao.requestZhuTiList(new BaseCallBack() {
            @Override
            public void success(Object data) {
                progressBar.setVisibility(View.GONE);
                List<ZhuTiInfo> tempList = (List<ZhuTiInfo>) data;
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

    //点击item后获取tid,总页数和url传到CommonActivity
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        tid = list.get(position).getId();
        Log.e("tid", "onItemClick: "+tid );

        Intent intent = new Intent(getActivity(), CommonActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("tid", tid);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
