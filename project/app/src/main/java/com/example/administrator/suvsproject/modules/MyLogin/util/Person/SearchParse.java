package com.example.administrator.suvsproject.modules.MyLogin.util.Person;

import android.util.Log;

import com.example.administrator.suvsproject.modules.MyLogin.bean.Person.search.MySerchInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/12 0012.
 */
public class SearchParse {

        public static List<MySerchInfo> parseList(String data){
            List<MySerchInfo> list=new ArrayList<>();
            MySerchInfo info=null;
            try {
                Log.e("print", "parseList: "+data );
                JSONObject subobject=new JSONObject(data);
                JSONArray jsonArray=subobject.getJSONArray("searchinfo");

                for(int i=0;i<jsonArray.length();i++){
                    try {
                        info=new MySerchInfo();
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                       info.setTid(jsonObject.getString("tid"));
                        info.setFid(jsonObject.getString("fid"));
                        info.setTitle(jsonObject.getString("title"));
                        info.setDateline(jsonObject.getLong("dateline"));
                        info.setContent(jsonObject.getString("content"));
                        info.setUrl(jsonObject.getString("url"));
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
