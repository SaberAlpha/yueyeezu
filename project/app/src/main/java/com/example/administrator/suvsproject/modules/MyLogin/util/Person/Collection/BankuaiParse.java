package com.example.administrator.suvsproject.modules.MyLogin.util.Person.Collection;

import com.example.administrator.suvsproject.modules.MyLogin.bean.Person.Collection.BankuaiInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/12 0012.
 */
public class BankuaiParse {

        public static List<BankuaiInfo> parseList(String data){
            List<BankuaiInfo> list=new ArrayList<>();
            BankuaiInfo info=null;
            try {
                JSONObject subobject=new JSONObject(data);
                JSONArray jsonArray=subobject.getJSONArray("bbsinfo");

                for(int i=0;i<jsonArray.length();i++){
                    try {
                        info=new BankuaiInfo();
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                       info.setName(jsonObject.getString("name"));
                        info.setFid(jsonObject.getString("fid"));
                        info.setPostcount(jsonObject.getString("postcount"));
                        info.setIssub(jsonObject.getString("issub"));
                        info.setIcon(jsonObject.getString("icon"));
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
