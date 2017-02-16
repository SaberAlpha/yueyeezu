package com.example.administrator.suvsproject.modules.shipin.util;

import android.util.Log;

import com.example.administrator.suvsproject.modules.shipin.bean.ShipinInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/12 0012.
 */
public class ShipinParse  {

        public static List<ShipinInfo> parseList(String data){
            List<ShipinInfo> list=new ArrayList<>();
            ShipinInfo info=null;
            try {
                JSONObject subobject=new JSONObject(data);
                JSONArray jsonArray = subobject.getJSONArray("datainfo");
                for(int i=0;i<jsonArray.length();i++){
                    try {
                        info=new ShipinInfo();
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        info.setId(jsonObject.getString("user_id"));
                        info.setTitle(jsonObject.getString("title"));
                        info.setIcon(jsonObject.getString("qrurl"));
                        JSONObject videoUrl = jsonObject.getJSONObject("video_url");
                        Log.e("print", "parseList: >>>>>>>>>>"+videoUrl.getString("main_url") );
                        info.setVideourl(videoUrl.getString("main_url"));
                        info.setAddtime(jsonObject.getString("add_time"));
                        info.setPublishtime(jsonObject.getLong("publishtime"));
                        info.setVideoduration(jsonObject.getLong("video_duration"));
                        list.add(info);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return  list;
        }
}
