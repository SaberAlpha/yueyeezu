package com.example.administrator.suvsproject.modules.MyLogin.util.Person.Collection;

import com.example.administrator.suvsproject.modules.MyLogin.bean.Person.Collection.CollectiontieziInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/12 0012.
 */
public class tieziParse {

        public static List<CollectiontieziInfo> parseList(String data){
            List<CollectiontieziInfo> list=new ArrayList<>();
            CollectiontieziInfo info=null;
            try {
                JSONObject subobject=new JSONObject(data);
                JSONArray jsonArray=subobject.getJSONArray("bbsinfo");

                for(int i=0;i<jsonArray.length();i++){
                    try {
                        info=new CollectiontieziInfo();
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        info.setTid(jsonObject.getString("tid"));
                        info.setSubject(jsonObject.getString("subject"));
                        info.setDateline(jsonObject.getString("dateline"));
                        info.setName(jsonObject.getString("forumname"));
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
