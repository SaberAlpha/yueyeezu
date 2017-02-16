package com.example.administrator.suvsproject.modules.cheku.util;

import com.example.administrator.suvsproject.modules.cheku.bean.CarBean;
import com.example.administrator.suvsproject.modules.cheku.bean.ChekuCarZongshuBean;
import com.example.administrator.suvsproject.modules.cheku.bean.ChekuInformationBean;
import com.example.administrator.suvsproject.modules.cheku.bean.ChekuPicsBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 2016/11/15 0015.
 */
public class JsonParseUtil {

    public static List<CarBean> getCarBeenListofDeault(String sb) throws JSONException {
        JSONObject object = new JSONObject(sb);
        JSONArray array = object.getJSONArray("carlist");

        TypeToken<List<CarBean>> typeToken = new TypeToken<List<CarBean>>() {
        };
        return new Gson().fromJson(array.toString(), typeToken.getType());
    }

    public static List<CarBean> getCarBeenListofSortDetail(String sb) throws JSONException {
        JSONObject object = new JSONObject(sb);
        JSONArray array = object.getJSONArray("listinfo");

        TypeToken<List<CarBean>> typeToken = new TypeToken<List<CarBean>>() {
        };
        return new Gson().fromJson(array.toString(), typeToken.getType());
    }

    public static List<ChekuCarZongshuBean> getChekuZongshuBean(String sb) throws JSONException {
        JSONObject object = new JSONObject(sb);
        JSONArray array = object.getJSONArray("model");

        TypeToken<List<ChekuCarZongshuBean>> typeToken = new TypeToken<List<ChekuCarZongshuBean>>() {
        };
        return new Gson().fromJson(array.toString(), typeToken.getType());
    }

    public static List<ChekuPicsBean> getChekuPicsBean(String sb) throws JSONException {
        JSONObject object = new JSONObject(sb);
        JSONArray array = object.getJSONArray("photo");

        TypeToken<List<ChekuPicsBean>> typeToken = new TypeToken<List<ChekuPicsBean>>() {
        };
        return new Gson().fromJson(array.toString(), typeToken.getType());
    }


    public static List<ChekuInformationBean> getChekuInfoBean(String sb) throws JSONException {
        JSONObject object = new JSONObject(sb);
        JSONArray array = object.getJSONArray("news");

        TypeToken<List<ChekuInformationBean>> typeToken = new TypeToken<List<ChekuInformationBean>>() {
        };
        return new Gson().fromJson(array.toString(), typeToken.getType());
    }


}
