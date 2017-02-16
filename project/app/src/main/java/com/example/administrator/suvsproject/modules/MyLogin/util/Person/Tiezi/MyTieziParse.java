package com.example.administrator.suvsproject.modules.MyLogin.util.Person.Tiezi;

import com.example.administrator.suvsproject.modules.MyLogin.bean.Person.Tiezi.TieziInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/12 0012.
 */
public class MyTieziParse {

        public static List<TieziInfo> parseList(String data){
            List<TieziInfo> list=new ArrayList<>();
            TieziInfo info=null;
            try {
                JSONObject subobject=new JSONObject(data);
                JSONArray jsonArray=subobject.getJSONArray("bbsinfo");

                for(int i=0;i<jsonArray.length();i++){
                    try {
                        info=new TieziInfo();
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
