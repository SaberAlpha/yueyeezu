package com.example.administrator.suvsproject.modules.MyLogin.activity.Search;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.suvsproject.R;
import com.example.administrator.suvsproject.activity.BaseFragment;
import com.example.administrator.suvsproject.adapter.CommonAdapter;
import com.example.administrator.suvsproject.adapter.ViewHolder;
import com.example.administrator.suvsproject.i.BaseCallBack;
import com.example.administrator.suvsproject.modules.MyLogin.bean.Person.Myhome.MyhomeFirstbean;
import com.example.administrator.suvsproject.modules.MyLogin.dao.Person.Search.yonghuhdao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/13.
 */
public class yonghuFragment extends BaseFragment {
    private SwipeRefreshLayout swipeRefreshLayout;
    private int page;
    private ListView listView;
    private List<MyhomeFirstbean> datalist;
    private View footview;
    private CommonAdapter<MyhomeFirstbean> adapter;


    @Override
    protected void findViews(View view) {
        listView = (ListView) view.findViewById(R.id.mycollection_list_view);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.mycollect_swiperefreshLayout);
    }


    @Override
    protected void init() {
        datalist=new ArrayList<>();
        footview = getActivity().getLayoutInflater().inflate(R.layout.shipin_footloading_layout,listView, false);
        adapter = new CommonAdapter<MyhomeFirstbean>(getContext(),datalist,R.layout.collectionfragment_bankuai_item) {
            @Override
            public void convert(ViewHolder helper, int position, MyhomeFirstbean item) {
                helper.setText(R.id.collect_bankuai_name,item.getUsername());
                helper.setText(R.id.collect_bankuai_number,"所在地 ");

            }
        };
        listView.addFooterView(footview);
        listView.setAdapter(adapter);
        listView.removeFooterView(footview);


    }

    @Override
    protected void initEvent() {



        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page=1;
                swipeRefreshLayout.setColorSchemeColors(Color.BLUE,Color.BLACK);
                requestNetData();

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });


        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if(scrollState==SCROLL_STATE_IDLE){
                    int lastVisiblePosition = listView.getLastVisiblePosition();
                    if(lastVisiblePosition==datalist.size()){
                        View childAt = listView.getChildAt(listView.getChildCount() - 1);
                        if(childAt.getBottom()==listView.getBottom()){
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
    protected void requestNetData() {
        page=1;
        Bundle bundle = getArguments();
        String url= bundle.getString("url");;
        String text = bundle.getString("text");
        yonghuhdao.request(page, url ,text,new BaseCallBack() {
            @Override
            public void success(Object data) {
                swipeRefreshLayout.setRefreshing(false);
                final List<MyhomeFirstbean> list= (List<MyhomeFirstbean>) data;
                if(page==1){
                    datalist.clear();

                }
                if(listView.getFooterViewsCount()==0){
                    listView.addFooterView(footview);

                }
                if(list.size()<10){
                    listView.removeFooterView(footview);

                }
                if(list!=null){
                    datalist.addAll(list);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void failed(int errorCode, Object data) {
                swipeRefreshLayout.setRefreshing(false);

            }
        });
    }
    @Override
    protected void loadData() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_mycollection;
    }
}
