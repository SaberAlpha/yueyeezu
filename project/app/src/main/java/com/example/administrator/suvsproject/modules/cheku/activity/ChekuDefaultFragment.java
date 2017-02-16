package com.example.administrator.suvsproject.modules.cheku.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.example.administrator.suvsproject.R;
import com.example.administrator.suvsproject.modules.cheku.adapter.CarAdapter;
import com.example.administrator.suvsproject.modules.cheku.bean.CarBean;
import com.example.administrator.suvsproject.modules.cheku.net.MyTask;
import com.example.administrator.suvsproject.modules.cheku.util.CharacterParser;
import com.example.administrator.suvsproject.modules.cheku.util.PinyinComparator;
import com.example.administrator.suvsproject.modules.cheku.widget.SideBar;
import com.example.administrator.suvsproject.util.ThreadTask;
import com.example.administrator.suvsproject.util.XutilsManager;

import org.xutils.ex.DbException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ChekuDefaultFragment extends Fragment implements SectionIndexer {


    View view;
    //控件
    private ListView sortListView;
    private SideBar sideBar;
    private LinearLayout floating_layout;
    private TextView floating_catalog;
    ProgressBar cheku_default_pb;

    //适配器
    private CarAdapter adapter;
    /**
     * 上次第一个可见元素，用于滚动时记录标识。
     */
    private int lastFirstVisibleItem = -1;
    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser characterParser;
    private List<CarBean> SourceDateList;

    /**
     * 根据拼音来排列ListView里面的数据类
     */
    private PinyinComparator pinyinComparator;

    String url = "http://carport.fblife.com/carapi/getcaralllist.php?datatype=json&groupid=0";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cheku_default, null);

        //  实例化汉字转拼音类
        characterParser = CharacterParser.getInstance();
        pinyinComparator = new PinyinComparator();
        SourceDateList = new ArrayList<>();
        initViews();
        initData();
        return view;
    }


    private void initViews() {
        floating_layout = (LinearLayout) view.findViewById(R.id.floating_layout);
        floating_catalog = (TextView) view.findViewById(R.id.floating_catalog);
        sideBar = (SideBar) view.findViewById(R.id.sidrbar);
        sortListView = (ListView) view.findViewById(R.id.car_lv);
        cheku_default_pb = (ProgressBar) view.findViewById(R.id.cheku_default_pb);
    }


    private void initData() {
        ThreadTask.getInstance().executorDBThread(new Runnable() {
            @Override
            public void run() {
                try {
                    List<CarBean> list = XutilsManager.getInstance().getDbManager().findAll(CarBean.class);
                    if (list != null) {
                        SourceDateList.addAll(list);
                        runOnUiThread();

                    } else {
                        requestNetData();
                    }
                } catch (DbException e) {
                    e.printStackTrace();
                }
            }

        }, ThreadTask.ThreadPeriod.PERIOD_HIGHT);
    }

    private void requestNetData() {
        MyTask task = new MyTask(new MyTask.CallBack() {
            @Override
            public void getCarList(List<CarBean> data) {
                SourceDateList.addAll(data);
                ThreadTask.getInstance().executorDBThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            XutilsManager.getInstance().getDbManager().save(SourceDateList);
                        } catch (DbException e) {
                            e.printStackTrace();
                        }
                    }
                }, ThreadTask.ThreadPeriod.PERIOD_HIGHT);
                runOnUiThread();

            }
        });
        task.execute(url);
    }

    public void runOnUiThread() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                cheku_default_pb.setVisibility(View.GONE);
                init();
            }
        });
    }


    private void init() {
        // 根据a-z进行排序源数据
        Collections.sort(SourceDateList, pinyinComparator);
        adapter = new CarAdapter(getContext(), SourceDateList);
        sortListView.setAdapter(adapter);
        initEvent();
    }

    private void initEvent() {
        // 设置右侧触摸监听
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                // // 该字母首次出现的位置
                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    sortListView.setSelection(position);
                }
            }
        });

        //listview的每一项点击事件
        sortListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //这里要利用adapter.getItem(position)来获取当前position所对应的对象

            }
        });


        sortListView.setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                int section = getSectionForPosition(firstVisibleItem);
                int nextSection = getSectionForPosition(firstVisibleItem + 1);
                int nextSecPosition = getPositionForSection(+nextSection);
                if (firstVisibleItem != lastFirstVisibleItem) {
                    MarginLayoutParams params = (MarginLayoutParams) floating_layout
                            .getLayoutParams();
                    params.topMargin = 0;
                    floating_layout.setLayoutParams(params);
                    floating_catalog.setText(SourceDateList.get(
                            getPositionForSection(section)).getFwords());
                }
                if (nextSecPosition == firstVisibleItem + 1) {
                    View childView = view.getChildAt(0);
                    if (childView != null) {
                        int titleHeight = floating_layout.getHeight();
                        int bottom = childView.getBottom();
                        MarginLayoutParams params = (MarginLayoutParams) floating_layout
                                .getLayoutParams();
                        if (bottom < titleHeight) {
                            float pushedDistance = bottom - titleHeight;
                            params.topMargin = (int) pushedDistance;
                            floating_layout.setLayoutParams(params);
                        } else {
                            if (params.topMargin != 0) {
                                params.topMargin = 0;
                                floating_layout.setLayoutParams(params);
                            }
                        }
                    }
                }
                lastFirstVisibleItem = firstVisibleItem;
            }
        });

    }


    @Override
    public Object[] getSections() {
        return null;
    }

    /**
     * 根据ListView的当前位置获取分类的首字母的Char ascii值
     */
    public int getSectionForPosition(int position) {
        return SourceDateList.get(position).getFwords().charAt(0);
    }

    /**
     * 根据分类的首
     * 字母的Char ascii值获取其第一次出现该首字母的位置
     */
    public int getPositionForSection(int section) {
        for (int i = 0; i < SourceDateList.size(); i++) {
            String sortStr = SourceDateList.get(i).getFwords();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }
}
