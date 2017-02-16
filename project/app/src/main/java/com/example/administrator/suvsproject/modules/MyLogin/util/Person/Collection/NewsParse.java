package com.example.administrator.suvsproject.modules.MyLogin.util.Person.Collection;

import com.example.administrator.suvsproject.modules.MyLogin.bean.Person.Collection.CollectionNewsInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/12 0012.
 */
public class NewsParse {

        public static List<CollectionNewsInfo> parseList(String data){
            List<CollectionNewsInfo> list=new ArrayList<>();
            CollectionNewsInfo info=null;
            try {
                JSONObject subobject=new JSONObject(data);
                JSONArray jsonArray=subobject.getJSONArray("list");

                for(int i=0;i<jsonArray.length();i++){
                    try {
                        info=new CollectionNewsInfo();
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                       info.setId(jsonObject.getString("id"));
                        info.setNid(jsonObject.getString("nid"));
                        info.setTitle(jsonObject.getString("title"));
                        info.setName(jsonObject.getString("channel_name"));
                        info.setDateline(jsonObject.getString("dateline"));
                        info.setType(jsonObject.getString("type"));
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
