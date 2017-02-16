package com.example.administrator.suvsproject.modules.shop.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.administrator.suvsproject.R;
import com.example.administrator.suvsproject.activity.BaseFragment;
import com.example.administrator.suvsproject.activity.LogActivity;

/**
 * Created by Administrator on 2016/11/11 0011.
 */
public class shopFragment extends BaseFragment {
    private View mine;
    private WebView webView;
    private View line;
    private int widthPixels;
    private View freshView;

    @Override
    protected void findViews(View view) {
        mine = view.findViewById(R.id.my_information_shop);
        webView = (WebView) view.findViewById(R.id.webview_shop);
        line = view.findViewById(R.id.line_view);
        widthPixels = getResources().getDisplayMetrics().widthPixels;
        freshView = view.findViewById(R.id.my_refresh_shop);
    }

    @Override
    protected void initEvent() {
        mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), LogActivity.class);
                startActivity(intent);
            }
        });

        freshView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl("http://m.fblife.com/mall/");
            }
        });
    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void init() {
        webView.getSettings().setJavaScriptEnabled(true);
       webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                setLineProgress(0);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });


        webView.setWebChromeClient(new WebChromeClient(){

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                setLineProgress(newProgress*1.0f/100);
            }
        });

        webView.loadUrl("http://m.fblife.com/mall/");
//        webView.loadUrl("http://183.60.151.216/238/39/75/bcloud/101596/ver_00_22-1071718122-avc-422021-aac-32013-134880-7804495-de0c3ae64db2d66509fc103ee4cae7ef-1478598281015.mp4?crypt=84aa7f2e519&b=462&nlh=4096&nlt=60&bf=90&p2p=1&video_type=mp4&termid=2&tss=no&platid=2&splatid=206&its=0&qos=5&fcheck=0&amltag=59888&mltag=59888&proxy=236324740,3070419576,1786224516&uid=977076102.rp&keyitem=GOw_33YJAAbXYE-cnQwpfLlv_b2zAkYctFVqe5bsXQpaGNn3T1-vhw..&ntm=1479105000&nkey=886eb4b8be598927e081ea17e7bf0d68&nkey2=7a13b96e0a8d8f203fac63beb9740503&geo=CN-19-248-1&mmsid=212372858&tm=1479086507372&key=d7928fecb606561108a44294534a4e3f&payff=0&cuid=1&vtype=13&dur=134&p1=3&p2=31&p3=310&cf=h5-android&p=101&playid=0&tag=mobile&sign=bcloud_101596&pay=0&ostype=android&hwtype=un&errc=0&gn=1080&vrtmcd=106&buss=59888&cips=58.60.255.134");
    }

    @Override
    public void onPause() {
        webView.reload();
        super.onPause();

    }

    private void setLineProgress(float progress) {
        ViewGroup.LayoutParams params = line.getLayoutParams();
        params.width= (int) (widthPixels*progress);
        line.setLayoutParams(params);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void requestNetData() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_shop;

    }
}
