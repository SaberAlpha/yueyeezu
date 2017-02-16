package com.example.administrator.suvsproject.modules.luntan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.suvsproject.R;
import com.example.administrator.suvsproject.activity.BaseActivity;
import com.example.administrator.suvsproject.i.BaseCallBack;
import com.example.administrator.suvsproject.modules.MyLogin.activity.LoginCheckActivity;
import com.example.administrator.suvsproject.modules.MyLogin.bean.Person.Logininfo;
import com.example.administrator.suvsproject.modules.luntan.bean.LunTanInfo;
import com.example.administrator.suvsproject.modules.luntan.dao.LunTanDao;
import com.se7en.utils.SystemUtil;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

public class CommonActivity extends BaseActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private static final String TAG = "CommonActivity";
    private ImageView backImg;
    private CheckBox shoucangCb;
    private CheckBox louzhuCb;
    private ImageView shareImg;
    private ImageView beforeImg;
    private ImageView nextImg;
    private ImageView commentImg;
    private TextView pageTv;
    private int page = 1;
    private WebView webView;
    private int allPages;
    private String tid;
    String errcode = "1";
    String authcode;
    private String url;
    private String name;
    private SwipeRefreshLayout refreshLayout;
    private ProgressBar progressBar;

    @Override
    protected void loadData() {

    }

    @Override
    protected void init() {
        webView.getSettings().setJavaScriptEnabled(true);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        tid = bundle.getString("tid");
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

        backImg.setOnClickListener(this);
        shareImg.setOnClickListener(this);
        commentImg.setOnClickListener(this);

        louzhuCb.setOnCheckedChangeListener(this);
    }

    @Override
    protected void findViews() {
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
        progressBar = (ProgressBar)findViewById(R.id.progress_bar);
        backImg = (ImageView) findViewById(R.id.back_luntan);
        shoucangCb = (CheckBox) findViewById(R.id.shoucang_cb);
        shareImg = (ImageView) findViewById(R.id.share_iv);
        louzhuCb = (CheckBox) findViewById(R.id.louzhu_cb);
        beforeImg = (ImageView) findViewById(R.id.before_iv);
        nextImg = (ImageView) findViewById(R.id.next_iv);
        commentImg = (ImageView) findViewById(R.id.comment_iv);
        pageTv = (TextView) findViewById(R.id.page_tv);
        webView = (WebView) findViewById(R.id.web_view);
    }

    @Override
    protected int setLatoutId() {
        return R.layout.activity_common;
    }

    @Override
    protected void requestNetData() {
        LunTanDao.requestLunTanInfo(tid, page, new BaseCallBack() {
            @Override
            public void success(Object data) {
                refreshLayout.setRefreshing(false);
                progressBar.setVisibility(View.GONE);
                LunTanInfo detail = (LunTanInfo) data;
                allPages = detail.getAllPages();
                String bbsinfo = detail.getBbsinfo();
                webView.loadDataWithBaseURL(null, bbsinfo, "text/html", "utf-8", null);
                pageTv.setText(page + "/" + allPages);
            }

            @Override
            public void failed(int errorCode, Object data) {

            }
        });

        beforeImg.setOnClickListener(CommonActivity.this);
        nextImg.setOnClickListener(CommonActivity.this);
        shoucangCb.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //返回
            case R.id.back_luntan:
                finish();
                break;
            //分享
            case R.id.share_iv:
                break;
            //上一页
            case R.id.before_iv:
                if (page > 1) {
                    page--;
                    requestNetData();
                }
                break;
            //下一页
            case R.id.next_iv:
                if (page < allPages) {
                    page++;
                    requestNetData();
                }
                break;
            //评论
            case R.id.comment_iv:
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.shoucang_cb:
                isChecked(buttonView, isChecked, R.drawable.forum_collect_up, R.drawable.forum_collect_down);
                break;
            case R.id.louzhu_cb:
                isChecked(buttonView, isChecked, R.drawable.louzhu_up, R.drawable.louzhu_down);
                break;
        }
    }

    private void isChecked(CompoundButton buttonView, boolean isChecked, int unCheckedImgId, int checkedImgId) {
        try {
            Logininfo loginfo = (Logininfo) SystemUtil.getSharedSerializable("loginfo");
            authcode = loginfo.getBbsinfo();
            url = "http://bbs.fblife.com/bbsapinew/addfavoritesthread.php?authcode="+authcode+"&tid="+tid+"&formattype=json";
            if (SystemUtil.getSharedBoolean("firstLogin", false)) {
                errcode = loginfo.getErrcode();
                //已登录
                if (errcode.equals("0")) {
                    if (isChecked) {    //收藏
                        buttonView.setBackgroundResource(checkedImgId);
                        Log.e(TAG, "isChecked: url"+url );
                        Toast.makeText(CommonActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();

                        LunTanDao.requestLunTanInfo(tid, 1, new BaseCallBack() {
                            @Override
                            public void success(Object data) {
                                LunTanInfo info = (LunTanInfo) data;
                                String title = info.getTitle();

                                Log.e(TAG, "success: "+title );
                                EventBus.getDefault().post(title);
                            }

                            @Override
                            public void failed(int errorCode, Object data) {

                            }
                        });
//                        LunTanDao.requestShouCangSuccessInfo2(url, new BaseCallBack() {
//                            @Override
//                            public void success(Object data) {
//                                ShouCangSuccessInfo2 shouCangInfo = (ShouCangSuccessInfo2) data;
//                                String tid = shouCangInfo.getTid();
//
//
//                            }
//
//                            @Override
//                            public void failed(int errorCode, Object data) {
//
//                            }
//                        });
                    } else {    //取消收藏
                        buttonView.setBackgroundResource(unCheckedImgId);
                        url = "http://bbs.fblife.com/bbsapinew/delfavoritesthread.php?action=del&authcode="+authcode+"&delid="+tid+"&format";
                        LunTanDao.requestLunTanInfo(tid, 1, new BaseCallBack() {
                            @Override
                            public void success(Object data) {
                                LunTanInfo info = (LunTanInfo) data;
                                String title = "1";
                                EventBus.getDefault().post("1");
                            }

                            @Override
                            public void failed(int errorCode, Object data) {

                            }
                        });
                    }
                }
            } else{    //未登录
                Intent intent = new Intent(CommonActivity.this, LoginCheckActivity.class);
                startActivity(intent);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
