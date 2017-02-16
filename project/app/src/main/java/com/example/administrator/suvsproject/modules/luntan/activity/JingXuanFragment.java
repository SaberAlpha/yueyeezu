package com.example.administrator.suvsproject.modules.luntan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.administrator.suvsproject.R;
import com.example.administrator.suvsproject.activity.BaseFragment;
import com.example.administrator.suvsproject.adapter.CommonAdapter;
import com.example.administrator.suvsproject.adapter.ViewHolder;
import com.example.administrator.suvsproject.i.BaseCallBack;
import com.example.administrator.suvsproject.modules.luntan.bean.JingXuanInfo;
import com.example.administrator.suvsproject.modules.luntan.dao.LunTanDao;
import com.example.administrator.suvsproject.util.ThreadTask;
import com.example.administrator.suvsproject.util.XutilsManager;

import org.xutils.ex.DbException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/12 0012.
 */
public class JingXuanFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener, AdapterView.OnItemClickListener {
    private static final String TAG = "print";
    private SwipeRefreshLayout refreshLayout;
    private RadioGroup radioGroup;
    private RadioButton shoucang;
    private RadioButton liulan;
    private Button paihang;
    private HorizontalScrollView horizontalScrollView;
    private LinearLayout textLayout;
    private ListView listView;
    private List<JingXuanInfo> list;
    private int page = 1;
    private CommonAdapter<JingXuanInfo> adapter;
    private View footView;
    private View headerView;
    private int lastIndex = -1;
    private Fragment[] fragments = new Fragment[2];
    private ProgressBar progressBar;

    @Override
    protected void findViews(View view) {
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
        progressBar = (ProgressBar)view.findViewById(R.id.progress_bar);
        radioGroup = (RadioGroup) view.findViewById(R.id.info_rg);
        shoucang = (RadioButton) view.findViewById(R.id.shoucang_rb);
        liulan = (RadioButton) view.findViewById(R.id.liulan_rb);
        paihang = (Button) view.findViewById(R.id.paihang_btn);
        horizontalScrollView = (HorizontalScrollView) view.findViewById(R.id.scroll_view);
        textLayout = (LinearLayout) view.findViewById(R.id.luntan_info_layout);
        listView = (ListView) view.findViewById(R.id.list_view);
    }

    @Override
    protected void initEvent() {
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                requestNetData();
            }
        });

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == SCROLL_STATE_IDLE){
                    int lastVisiblePosition = listView.getLastVisiblePosition();
                    if (lastVisiblePosition == list.size() + listView.getHeaderViewsCount()) {
                        //获取最后一个控件
                        View lastVisibleView = listView.getChildAt(listView.getChildCount() - 1);
                        if (lastVisibleView.getBottom() == listView.getBottom()) {
                            //加载下一页
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

        RadioGroup radioGroup = (RadioGroup) headerView.findViewById(R.id.info_rg);
        Button paihangButton = (Button) headerView.findViewById(R.id.paihang_btn);
        radioGroup.setOnCheckedChangeListener(this);
        paihangButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),PaihangActivity.class);
                startActivity(intent);
            }
        });

        listView.setOnItemClickListener(this);
    }

    @Override
    protected void init() {
        headerView = getActivity().getLayoutInflater().inflate(R.layout.luntan_header_layout,listView,false);
        footView = getActivity().getLayoutInflater().inflate(R.layout.load_layout, listView, false);
        list = new ArrayList<>();
        adapter = new CommonAdapter<JingXuanInfo>(getContext(), list, R.layout.adpter_jingxuan_info) {

            @Override
            public void convert(ViewHolder helper, int position, JingXuanInfo item) {
                helper.setImageByUrl(R.id.iv, item.getPhotoUrl());
                helper.setText(R.id.title, item.getTitle());
                helper.setText(R.id.stitle, item.getStitle());
                helper.setText(R.id.forumname, "论坛 - " + item.getForumname());
                helper.setText(R.id.comment, item.getComment());
            }
        };
        listView.addHeaderView(headerView);
        listView.setAdapter(adapter);

        addFragment(0);
    }

    @Override
    protected void loadData() {
        ThreadTask.getInstance().executorDBThread(new Runnable() {
            @Override
            public void run() {
                try {
                    final List<JingXuanInfo> tempList = XutilsManager.getInstance().getDbManager().findAll(JingXuanInfo.class);
                    if (tempList != null){
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                list.addAll(tempList);
                                adapter.notifyDataSetChanged();
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
        },ThreadTask.ThreadPeriod.PERIOD_HIGHT);
    }

    @Override
    protected void requestNetData() {
        LunTanDao.requestJingxuanList(page, new BaseCallBack() {
            @Override
            public void success(Object data) {
                progressBar.setVisibility(View.GONE);
                refreshLayout.setRefreshing(false);
                final List<JingXuanInfo> tempList = (List<JingXuanInfo>) data;

                if (page == 1) {
                    ThreadTask.getInstance().executorDBThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                XutilsManager.getInstance().getDbManager().delete(JingXuanFragment.class);
                                XutilsManager.getInstance().getDbManager().save(tempList);
                            } catch (DbException e) {
                                e.printStackTrace();
                            }
                        }
                    }, ThreadTask.ThreadPeriod.PERIOD_HIGHT);
                    list.clear();
                }

                if (listView.getFooterViewsCount() == 0){
                    listView.addFooterView(footView);
                }

                if (tempList.size() < 10) {
                    listView.removeView(footView);
                }

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
        return R.layout.fragment_luntan_jingxuan;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int position = 0;
        switch (checkedId) {
            case R.id.shoucang_rb:
                position = 0;
                break;
            case R.id.liulan_rb:
                position = 1;
                break;
        }
        addFragment(position);
    }

    private void addFragment(int position) {
        if (lastIndex == position) {
            return;
        }
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        if (lastIndex != -1) {
            transaction.hide(fragments[lastIndex]);
        }

        if (fragments[position] == null) {
            switch (position) {
                case 0:
                    fragments[0] = new ShouCangFragment();
                    break;
                case 1:
                    fragments[1] = new LiuLanFragment();
                    break;
            }
            transaction.add(R.id.info_frag_layout, fragments[position]);
        } else {
            transaction.show(fragments[position]);
        }
        lastIndex = position;
        transaction.commit();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String tid = list.get(position-1).getTid();
        Intent intent = new Intent(getActivity(), CommonActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("tid",tid);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
