package com.example.administrator.suvsproject.modules.MyLogin.activity.Myhome;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.suvsproject.R;
import com.example.administrator.suvsproject.activity.BaseActivity;
import com.example.administrator.suvsproject.adapter.CommonAdapter;
import com.example.administrator.suvsproject.adapter.ViewHolder;
import com.example.administrator.suvsproject.i.BaseCallBack;
import com.example.administrator.suvsproject.modules.MyLogin.bean.Person.Myhome.Myhomebean;
import com.example.administrator.suvsproject.modules.MyLogin.dao.Person.Myhome.Myhomeitemdao;
import com.qrcode.ErweiMaActivity;

import java.util.ArrayList;
import java.util.List;

public class MyhomeActivity extends BaseActivity {
    private SwipeRefreshLayout swipeRefreshLayout;
    private int page;
    private ListView listView;
    private List<Myhomebean> datalist;
    private View footview;
    private CommonAdapter<Myhomebean> adapter;
    @Override
    protected void findViews() {
        listView = (ListView) findViewById(R.id.Myhome_list_view);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.myhome_swipfresh);
    }
    @Override
    protected void init() {
//        Myhomefirstdao.request(page, new BaseCallBack() {
//            @Override
//            public void success(Object data) {
//                MyhomeFirstbean myhomeFirstbean= (MyhomeFirstbean) data;
//                ((TextView)findViewById(R.id.myhome_attionnum)).setText(myhomeFirstbean.getFollow_count());
//                ((TextView)findViewById(R.id.myhome_fannum)).setText(myhomeFirstbean.getFans_count());
//                ((TextView)findViewById(R.id.myhome_writenum)).setText(myhomeFirstbean.getAlbum_count());
//                ((TextView)findViewById(R.id.myhome_paintnum)).setText(myhomeFirstbean.getImage_count());
//                ((TextView)findViewById(R.id.myhome_quannum)).setText(myhomeFirstbean.getBlog_count());
//            }
//
//            @Override
//            public void failed(int errorCode, Object data) {
//
//            }
//        });

        datalist=new ArrayList<>();
        footview = getLayoutInflater().inflate(R.layout.shipin_footloading_layout,listView, false);
        adapter = new CommonAdapter<Myhomebean>(this,datalist,R.layout.myhome_item) {
            @Override
            public void convert(ViewHolder helper, int position, Myhomebean item) {

                helper.setText(R.id.myhome_time,item.getDateline());
                helper.setText(R.id.myhome_title,item.getUsername());
                helper.setText(R.id.myhome_item_content,item.getExtension());
                helper.setText(R.id.myhome_item_title,item.getContent());
            }
        };
        listView.addFooterView(footview);
        listView.setAdapter(adapter);
        listView.removeFooterView(footview);
    }
    @Override
    protected void initEvent() {
        findViewById(R.id.myhome_imformation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyhomeActivity.this,MyinformationActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.Myhome_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.myhome_erweima).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyhomeActivity.this, ErweiMaActivity.class);
                startActivity(intent);
            }
        });

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
    protected void loadData() {

    }

    @Override
    protected void requestNetData() {
        page=1;
        Myhomeitemdao.request(page,new BaseCallBack() {
            @Override
            public void success(Object data) {
                swipeRefreshLayout.setRefreshing(false);
                final List<Myhomebean> list= (List<Myhomebean>) data;
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
  protected int setLatoutId() {
        return R.layout.activity_myhome;
    }


}
