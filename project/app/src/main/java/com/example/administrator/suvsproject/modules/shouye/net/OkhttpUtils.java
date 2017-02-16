package com.example.administrator.suvsproject.modules.shouye.net;

import android.os.Handler;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by DQH on 2016/11/17.
 */
public class OkhttpUtils {

    private final static OkHttpClient client = new OkHttpClient();
    private static Handler mHandler;

    static {
        mHandler = new Handler();
    }

    public static void get(final String url, final Callback callBack) throws IOException {
        Request request = new Request.Builder().url(url).build();
        execute(request,callBack);
    }


    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static void post(final String url, final String json,final Callback callBack) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        execute(request,callBack);

    }

    private static void execute(final Request requestCall, final Callback callBack){

        Call call = client.newCall(requestCall);
        call.enqueue(callBack);
    }

}
