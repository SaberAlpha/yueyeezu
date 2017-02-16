package com.example.administrator.suvsproject.modules.shouye.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.suvsproject.R;
import com.example.administrator.suvsproject.SUVsApplication;
import com.example.administrator.suvsproject.modules.shouye.adapter.MyAdapter;
import com.example.administrator.suvsproject.modules.shouye.adapter.MyPagerAdapter;
import com.example.administrator.suvsproject.modules.shouye.bean.BaseInfo;
import com.example.administrator.suvsproject.modules.shouye.bean.ProductsInfo;
import com.example.administrator.suvsproject.modules.shouye.bean.SlidesInfo;
import com.example.administrator.suvsproject.modules.shouye.dao.JsonParse;
import com.example.administrator.suvsproject.modules.shouye.net.OkhttpUtils;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by DQH on 2016/11/17.
 */
public class MyListFragment extends Fragment {
    private String path;
    private View view;
    private ListView listview;
    private SwipeRefreshLayout refresh;
    private String url = "http://api.cms.fblife.com/fbapp/portal";
    private int page = 1;
    private List<BaseInfo> parse;
    private List<BaseInfo> baseInfos;
    private View footer;
    private View head;
    private ArrayList<ProductsInfo> products;
    private ArrayList<SlidesInfo> slides;
    private ViewPager viewpage;
    private TextView picture_title;
    private LinearLayout dotContainer;
    private MyAdapter myAdapter;
    private MyPagerAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        path = getArguments().getString("path");
        view = inflater.inflate(R.layout.fragment_tab_item, container, false);
        listview = (ListView) view.findViewById(R.id.shouye_tab_item);
        refresh = (SwipeRefreshLayout) view.findViewById(R.id.swipeLayout);
        initListener();
        init();
        loadData();
        return view;
    }

    private void loadData() {
        String urlbody = "{\"classname\":\""+path+"\",\"page\":\""+page+"\",\"pagesize\":\"20\"}";
        try {
            OkhttpUtils.post(url, urlbody, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {

                    String result = response.body().string();

                    if(result!=null){
                        parse = new JsonParse().Parse(result);
                    }
                    SUVsApplication.getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            if(parse != null){
                                footer = getActivity().getLayoutInflater().inflate(R.layout.layout_loading, listview, false);
                                refresh.setRefreshing(false);// 将下拉刷新控件隐藏
                                List<BaseInfo> tempList = parse;
                                // 只有当page等于初始化值的时候才需要清除list的数据

                                if (page == 1)
                                {
                                    baseInfos.clear();
                                }

                                if (tempList != null)
                                {
                                    baseInfos.addAll(tempList);
                                    if(page == 1) {
                                        if("".equals(path)&& (page==1)){
                                            head = getActivity().getLayoutInflater().inflate(R.layout.item_shouye_head, listview, false);
                                            slides = new ArrayList<>();
                                            products = new ArrayList<>();
                                            List<BaseInfo> baseInfoOne = new ArrayList<>();
                                            for(int i = 0; i < baseInfos.size(); i++){
                                                if (baseInfos.get(i) instanceof SlidesInfo){
                                                    slides.add((SlidesInfo)baseInfos.get(i));
                                                }else if(baseInfos.get(i) instanceof ProductsInfo){
                                                    products.add((ProductsInfo) baseInfos.get(i));
                                                }else{
                                                    baseInfoOne.add(baseInfos.get(i));
                                                }
                                            }
                                            baseInfos.clear();
                                            baseInfos.addAll(baseInfoOne);
                                            //设置数据适配器,添加小圆点等
                                            setUi();
                                            setproducts();
                                            listview.addHeaderView(head);
                                        }
                                        myAdapter = new MyAdapter(getContext(), baseInfos,path);
                                        listview.addFooterView(footer);// 兼容4.0以前的版本
                                        listview.setAdapter(myAdapter);
                                        listview.removeFooterView(footer);
                                    }else{
                                        myAdapter.notifyDataSetChanged();
                                    }

                                }
                                // 仅当listview中不存在footer的时候才可以添加footer
                                if (listview.getFooterViewsCount() == 0)
                                {
                                    listview.addFooterView(footer);
                                }
                                // 当当前页的数据返回小于10时，就代表已经是最后一页
                                if (tempList.size() < 20)
                                {
                                    Toast.makeText(getActivity(),"数据加载完毕！",Toast.LENGTH_SHORT).show();
                                    listview.removeFooterView(footer);
                                }

                            }

                        }
                    });
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init() {

        //设置刷新控件圆圈的颜色
        refresh.setColorSchemeColors(Color.YELLOW,Color.RED);
        baseInfos = new ArrayList<>();
    }

    private void setproducts() {
        ImageView picture_one = (ImageView) head.findViewById(R.id.houye_item_picture_one);
        TextView text_one = (TextView) head.findViewById(R.id.houye_item_text_one);
        ImageView picture_two = (ImageView) head.findViewById(R.id.houye_item_picture_two);
        TextView text_two = (TextView) head.findViewById(R.id.houye_item_text_two);
        ImageView picture_three = (ImageView) head.findViewById(R.id.houye_item_picture_three);
        TextView text_three = (TextView) head.findViewById(R.id.houye_item_text_three);
        ImageView picture_four= (ImageView) head.findViewById(R.id.houye_item_picture_four);
        TextView text_four = (TextView) head.findViewById(R.id.houye_item_text_four);
        Picasso.with(getContext()).load( products.get(0).getPhoto()).into(picture_one);
        text_one.setText(products.get(0).getTitle());
        Picasso.with(getContext()).load( products.get(1).getPhoto()).into(picture_two);
        text_two.setText(products.get(1).getTitle());
        Picasso.with(getContext()).load( products.get(2).getPhoto()).into(picture_three);
        text_three.setText(products.get(2).getTitle());
        Picasso.with(getContext()).load( products.get(3).getPhoto()).into(picture_four);
        text_four.setText(products.get(3).getTitle());
    }

    private void setProducts(TextView text_four) {
        text_four.setText(products.get(3).getTitle());
    }

    private void setUi() {
        viewpage = (ViewPager) head.findViewById(R.id.shouye_item_home_picture_pager);
        picture_title = (TextView) head.findViewById(R.id.houye_item_picture_title);
        dotContainer = (LinearLayout) head.findViewById
                (R.id.houye_item_picture_container_indicator);
        //设置小圆点
        initDots();
        adapter = new MyPagerAdapter(getActivity(), slides);
        picture_title.setText(slides.get(0).getTitle());
        viewpage.setAdapter(adapter);
        //设置监听器：页面改变的时候重新设置圆点和标题
        viewpage.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                position = position % slides.size();
                picture_title.setText(slides.get(position).getTitle());
                for (int i = 0; i < dotContainer.getChildCount(); i++) {
                    ImageView dotView = (ImageView) dotContainer.getChildAt(i);
                    dotView.setImageResource(R.mipmap.banner_dian_blur);
                    if (i == position)
                        dotView.setImageResource(R.mipmap.banner_dian_focus);
                }
            }
        });
        // 设置curItem为count/2
        int diff = Integer.MAX_VALUE / 2 % slides.size();
        int index = Integer.MAX_VALUE / 2;
        viewpage.setCurrentItem(index - diff);
        // 自动轮播
        final AutoScrollTask autoScrollTask = new AutoScrollTask();
        autoScrollTask.start();
        // 用户触摸的时候移除自动轮播的任务
        viewpage.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        autoScrollTask.stop();
                        break;
                    case MotionEvent.ACTION_MOVE:

                        break;
                    case MotionEvent.ACTION_UP:
                        autoScrollTask.start();
                        break;

                    default:
                        break;
                }
                return false;
            }
        });
    }
    Handler handler = new Handler();
    class AutoScrollTask implements Runnable {

        /**开始轮播*/
        public void start() {
            handler.postDelayed(this, 2000);
        }

        public void stop() {
            handler.removeCallbacks(this);
        }

        /**结束轮播*/
        @Override
        public void run() {
            int item = viewpage.getCurrentItem();
            item++;
            viewpage.setCurrentItem(item);
            // 结束-->再次开始
            start();
        }
    }



    private void initDots() {
        for (int i = 0; i < slides.size(); i++) {
            ImageView dotView = new ImageView(getContext());
            dotView.setPadding(10, 0, 10, 0);
            dotView.setImageResource(R.mipmap.banner_dian_blur);
            if (i == 0) {
                dotView.setImageResource(R.mipmap.banner_dian_focus);
            }
            dotContainer.addView(dotView);
        }
    }


    private void initListener() {
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //执行下拉刷新的业务
                page = 1;
                loadData();
            }
        });
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        listview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // 当listview滑动完全停止后开始判断是否加载下一页
                if(scrollState == SCROLL_STATE_IDLE){
                    // 获取当前最后一个可见的item的位置
                    int lastVisiblePosition = listview.getLastVisiblePosition();
                    //最后一个item显示出来
                    if(lastVisiblePosition == baseInfos.size()+ listview.getHeaderViewsCount()){
                        //获取最后一个item控件的对象
                        View childView = listview.getChildAt(listview.getChildCount() - 1);
                        // 如果最后一item的底部和listview的底部重合就加载下一页
                        if(childView.getBottom() == listview.getBottom()){
                            //加载下一页
                            page++;
                            loadData();
                        }
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }
}
