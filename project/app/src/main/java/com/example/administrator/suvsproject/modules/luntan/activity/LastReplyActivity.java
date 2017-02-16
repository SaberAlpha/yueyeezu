package com.example.administrator.suvsproject.modules.luntan.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.administrator.suvsproject.R;
import com.example.administrator.suvsproject.activity.BaseActivity;
import com.example.administrator.suvsproject.adapter.CommonAdapter;
import com.example.administrator.suvsproject.adapter.ViewHolder;
import com.example.administrator.suvsproject.i.BaseCallBack;
import com.example.administrator.suvsproject.modules.luntan.bean.LastReplyInfo;
import com.example.administrator.suvsproject.modules.luntan.dao.LunTanDao;
import com.example.administrator.suvsproject.util.BitmapUtil;
import com.example.administrator.suvsproject.util.ThreadTask;
import com.example.administrator.suvsproject.util.XutilsManager;

import org.xutils.ex.DbException;

import java.util.ArrayList;
import java.util.List;

public class LastReplyActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    private static final String TAG = "info";
    private ImageView backImg;
    private CheckBox shoucangCb;
    private ImageView commentImg;
    private SwipeRefreshLayout refreshLayout;
    private ListView listView;
    private List<LastReplyInfo> list;
    private String fid;
    private int page = 1;
    private CommonAdapter<LastReplyInfo> adapter;
    private View footView;

    @Override
    protected void loadData() {
        ThreadTask.getInstance().executorDBThread(new Runnable() {
            @Override
            public void run() {
                try {
                    final List<LastReplyInfo> tempList = XutilsManager.getInstance().getDbManager().findAll(LastReplyInfo.class);
                    if (tempList != null) {
                        runOnUiThread(new Runnable() {
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
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        requestNetData();
                    }
                });
            }
        }, ThreadTask.ThreadPeriod.PERIOD_HIGHT);
    }

    @Override
    protected void init() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        fid = bundle.getString("fid");

        footView = getLayoutInflater().inflate(R.layout.load_layout, listView, false);

        list = new ArrayList<>();
        adapter = new CommonAdapter<LastReplyInfo>(this, list, R.layout.adapter_last_reply_info) {
            @Override
            public void convert(ViewHolder helper, int position, LastReplyInfo item) {
                int digest = item.getDigest();
                int displayorder = item.getDisplayorder();
                View homeIv = helper.getView(R.id.home_iv);
                if (displayorder == 0 && digest != 1) {
                    homeIv.setVisibility(View.GONE);
                } else {
                    homeIv.setVisibility(View.VISIBLE);
                }
                if (digest == 1) {
                    helper.setImageResource(R.id.home_iv, R.drawable.bbs_grade_jinhua);
                }
                if (displayorder == 1) {
                    helper.setImageResource(R.id.home_iv, R.drawable.bbs_grade_first);
                } else if (displayorder == 2) {
                    helper.setImageResource(R.id.home_iv, R.drawable.bbs_grade_second);
                } else if (displayorder == 3) {
                    helper.setImageResource(R.id.home_iv, R.drawable.bbs_grade_thirdly);
                }

                helper.setText(R.id.title_tv, item.getTitle());
                helper.setText(R.id.author_tv, item.getAuthor());
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
                Bitmap circleImage = BitmapUtil.createCircleImage(bitmap);
                helper.setImageBitmap(R.id.icon,circleImage);

                helper.setText(R.id.time_tv, item.getTime());

                helper.setText(R.id.talk_tv, item.getReplies() + "/" + item.getViews());

            }
        };
        listView.addFooterView(footView);
        listView.setAdapter(adapter);
        listView.removeFooterView(footView);
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
                if (scrollState == SCROLL_STATE_IDLE) {
                    int lastVisiblePosition = view.getLastVisiblePosition();
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

        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //收藏
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

        listView.setOnItemClickListener(this);
    }

    @Override
    protected void findViews() {
        backImg = (ImageView) findViewById(R.id.back_luntan);
        commentImg = (ImageView) findViewById(R.id.comment_iv);
        shoucangCb = (CheckBox) findViewById(R.id.shoucang_cb);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
        listView = (ListView) findViewById(R.id.list_view);
    }

    @Override
    protected int setLatoutId() {
        return R.layout.activity_last_reply;
    }

    @Override
    protected void requestNetData() {
        LunTanDao.requestLastReply(fid, page, new BaseCallBack() {

            @Override
            public void success(Object data) {
                refreshLayout.setRefreshing(false);
                final List<LastReplyInfo> tempList = (List<LastReplyInfo>) data;
                if (page == 1) {
                    ThreadTask.getInstance().executorDBThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                XutilsManager.getInstance().getDbManager().delete(LastReplyInfo.class);
                                XutilsManager.getInstance().getDbManager().save(tempList);
                            } catch (DbException e) {
                                e.printStackTrace();
                            }
                        }
                    }, ThreadTask.ThreadPeriod.PERIOD_HIGHT);

                    list.clear();
                }
                if (listView.getFooterViewsCount() == 0) {
                    listView.addFooterView(footView);
                }
                if (tempList.size() > 20) {
                    listView.removeFooterView(footView);
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String tid = list.get(position).getTid();

        Intent intent = new Intent(LastReplyActivity.this, CommonActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("tid", tid);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
