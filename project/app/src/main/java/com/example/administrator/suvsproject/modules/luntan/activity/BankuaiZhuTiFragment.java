package com.example.administrator.suvsproject.modules.luntan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.administrator.suvsproject.R;
import com.example.administrator.suvsproject.activity.BaseFragment;
import com.example.administrator.suvsproject.adapter.CommonAdapter;
import com.example.administrator.suvsproject.adapter.ViewHolder;
import com.example.administrator.suvsproject.i.BaseCallBack;
import com.example.administrator.suvsproject.modules.luntan.bean.BanKuaiGrandSonInfo;
import com.example.administrator.suvsproject.modules.luntan.bean.BanKuaiInfo;
import com.example.administrator.suvsproject.modules.luntan.bean.BanKuaiSonInfo;
import com.example.administrator.suvsproject.modules.luntan.dao.LunTanDao;
import com.example.administrator.suvsproject.modules.luntan.widget.MyListView;
import com.se7en.utils.DeviceUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yu on 2016/11/15.
 */
public class BankuaiZhuTiFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private static final String TAG = "BankuaiCheXingFragment";
    private ListView parentListView;
    private String categorytype = "zhuti";
    private List<BanKuaiInfo.BbsinfoBean> list;
    private CommonAdapter<BanKuaiInfo.BbsinfoBean> adapter;
    private CommonAdapter<BanKuaiSonInfo> sAdapter;
    private CommonAdapter<BanKuaiGrandSonInfo> gAdapter;
    private MyListView childListView;
    private int eachHeight;
    private List<BanKuaiSonInfo> sList;
    private List<BanKuaiGrandSonInfo> gList;
    private RelativeLayout itemLayout;
    private ProgressBar progressBar;

    @Override
    protected void findViews(View view) {
        parentListView = (ListView) view.findViewById(R.id.list_view);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
    }

    @Override
    protected void initEvent() {
//        parentListView.setOnItemClickListener(this);
    }

    @Override
    protected void init() {
        eachHeight = DeviceUtils.dip2px(48);

        list = new ArrayList<>();
        adapter = new CommonAdapter<BanKuaiInfo.BbsinfoBean>(getContext(), list, R.layout.quanbu_recycler_view_item) {
            @Override
            public void convert(ViewHolder helper, int position, final BanKuaiInfo.BbsinfoBean item) {
                helper.setText(R.id.gname, item.getName());

                childListView = helper.getView(R.id.child_list_layout);
                itemLayout = helper.getView(R.id.item_layout);

                final int gid = item.getGid();
                sList = item.getsList();

                sAdapter = new CommonAdapter<BanKuaiSonInfo>(getContext(), sList, R.layout.quanbu_recycler_view_item_item) {

                    @Override
                    public void convert(ViewHolder helper, int position, BanKuaiSonInfo item) {
                        Log.e(TAG, "convert:sList " + item.getName());

                        helper.setText(R.id.sname, item.getName());


//                        switch (gid) {
//                            case 195:
//
//                                break;
//                            case 624:
//                                helper.setText(R.id.sname, item.getName());
//                                break;
//                            case 413:
//                                helper.setText(R.id.sname, item.getName());
//                                break;
//                            case 583:
//                                helper.setText(R.id.sname, item.getName());
//                                break;
//                            case 15:
//                                helper.setText(R.id.sname, item.getName());
//                                break;
//                        }

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

                        //加号
                        CheckBox addCb = helper.getView(R.id.add_cb);
                    }
                };
                childListView.setAdapter(sAdapter);

//                setViewHeigth(childListView, 0);
                childListView.setTag(false);
                itemLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e(TAG, "onClick: ");

                        //TODO 当隐藏控件是显示，就隐藏该控件，当隐藏控件是隐藏的，就显示出来(true的时候就是显示的)
                        if ((Boolean) childListView.getTag()) {
                            childListView.setVisibility(View.GONE);
//                            setViewHeigth(itemLayout, 0);
                            childListView.setTag(false);
                        } else {
                            childListView.setVisibility(View.VISIBLE);
//                            setViewHeigth(itemLayout, eachHeight * sList.size());
                            childListView.setTag(true);
                        }
                    }
                });

                childListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String fid = sList.get(position).getFid();
                        Intent intent = new Intent(getActivity(), LastReplyActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("fid", fid);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
            }
        };
        parentListView.setAdapter(adapter);


    }

    private void setViewHeigth(View view, int heigth) {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
        params.height = heigth;
        view.setLayoutParams(params);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void requestNetData() {
        LunTanDao.requestBanKuaiInfo(categorytype, new BaseCallBack() {
            @Override
            public void success(Object data) {
                progressBar.setVisibility(View.GONE);
                List<BanKuaiInfo.BbsinfoBean> tempList = (List<BanKuaiInfo.BbsinfoBean>) data;

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
        Log.e(TAG, "onItemClick: ");

        //TODO 当隐藏控件是显示，就隐藏该控件，当隐藏控件是隐藏的，就显示出来(true的时候就是显示的)
        if ((Boolean) childListView.getTag()) {
//            childListView.setVisibility(View.GONE);
            setViewHeigth(childListView, 0);
            childListView.setTag(false);
        } else {
//            childListView.setVisibility(View.VISIBLE);
            Log.e(TAG, "onItemClick: " + eachHeight * sList.size());
            setViewHeigth(childListView, eachHeight * sList.size());
            childListView.setTag(true);
        }
    }

}
