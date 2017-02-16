package com.example.administrator.suvsproject.modules.shipin.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.example.administrator.suvsproject.R;
import com.example.administrator.suvsproject.activity.BaseFragment;
import com.example.administrator.suvsproject.activity.LogActivity;
import com.example.administrator.suvsproject.i.BaseCallBack;
import com.example.administrator.suvsproject.modules.shipin.adapter.VideoListAdapter;
import com.example.administrator.suvsproject.modules.shipin.bean.ShipinInfo;
import com.example.administrator.suvsproject.modules.shipin.dao.Shipindao;
import com.example.administrator.suvsproject.util.ThreadTask;
import com.example.administrator.suvsproject.util.XutilsManager;

import org.xutils.ex.DbException;

import java.util.ArrayList;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * Created by Administrator on 2016/11/20.
 */
public class Shipinvideo extends BaseFragment {
    ListView listView;
    VideoListAdapter adapterVideoList;
    private SwipeRefreshLayout swipeRefreshLayout;
    private int page;
    private List<ShipinInfo> datalist;
    private View footview;
    private View mine;

    SensorManager sensorManager;
    JCVideoPlayer.JCAutoFullscreenListener sensorEventListener;
    @Override
    protected void findViews(View view) {
        listView = (ListView) view.findViewById(R.id.listview);
        mine = view.findViewById(R.id.my_information_shipin);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.shipinvideoswipe_layout);

        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        sensorEventListener = new JCVideoPlayer.JCAutoFullscreenListener();
    }
    @Override
    protected void initEvent() {
        mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LogActivity.class);
                startActivity(intent);
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                swipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.BLACK);
                requestNetData();

            }
        });
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == SCROLL_STATE_IDLE) {
                    int lastVisiblePosition = listView.getLastVisiblePosition();
                    if (lastVisiblePosition == datalist.size()) {
                        View childAt = listView.getChildAt(listView.getChildCount() - 1);
                        if (childAt.getBottom() == listView.getBottom()) {
                            page++;
                            requestNetData();
                        }
                    }
                }
            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }
        });
    }
    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorEventListener);
        JCVideoPlayer.releaseAllVideos();
    }
    @Override
    public void onResume() {
        super.onResume();
        Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(sensorEventListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    protected void init() {
        datalist = new ArrayList<>();
        footview = getActivity().getLayoutInflater().inflate(R.layout.shipin_footloading_layout, listView, false);
        listView.addFooterView(footview);
        adapterVideoList = new VideoListAdapter(getContext(),datalist);
        listView.setAdapter(adapterVideoList);
        listView.removeFooterView(footview);
    }
    @Override
    protected void loadData() {
        ThreadTask.getInstance().executorDBThread(new Runnable() {
            @Override
            public void run() {
                try {
                    List<ShipinInfo> list = XutilsManager.getInstance().getDbManager().findAll(ShipinInfo.class);
                    if (list != null) {
                        datalist.addAll(list);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapterVideoList.notifyDataSetChanged();
                            }
                        });

                    }
                } catch (DbException e) {
                    e.printStackTrace();
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        requestNetData();
                    }
                });
            }
        }, ThreadTask.ThreadPeriod.PERIOD_HIGHT);
    }
    @Override
    protected void requestNetData() {
        Shipindao.request(page, new BaseCallBack() {
            @Override
            public void success(Object data) {
                swipeRefreshLayout.setRefreshing(false);
                final List<ShipinInfo> list = (List<ShipinInfo>) data;
                if (page == 1) {
                    ThreadTask.getInstance().executorDBThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                XutilsManager.getInstance().getDbManager().delete(ShipinInfo.class);
                                XutilsManager.getInstance().getDbManager().save(list);
                            } catch (DbException e) {
                                e.printStackTrace();
                            }
                        }
                    }, ThreadTask.ThreadPeriod.PERIOD_HIGHT);
                    datalist.clear();

                }
                if (listView.getFooterViewsCount() == 0) {
                    listView.addFooterView(footview);

                }
                if (list.size() < 20) {
                    listView.removeFooterView(footview);
                }
                if (list != null) {
                    datalist.addAll(list);
                    adapterVideoList.notifyDataSetChanged();
                }
            }

            @Override
            public void failed(int errorCode, Object data) {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
    @Override
    protected int setLayoutId() {
        return R.layout.activity_listview_content;
    }
}
