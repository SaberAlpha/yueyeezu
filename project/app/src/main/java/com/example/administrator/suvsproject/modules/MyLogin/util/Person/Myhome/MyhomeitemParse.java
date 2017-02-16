package com.example.administrator.suvsproject.modules.MyLogin.util.Person.Myhome;

import com.example.administrator.suvsproject.modules.MyLogin.bean.Person.Myhome.Myhomebean;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2016/11/12 0012.
 */
public class MyhomeitemParse {

        public static List<Myhomebean> parseList(String data){
            List<Myhomebean> list=new ArrayList<>();
            Myhomebean info=null;
            try {
                JSONObject subobject=new JSONObject(data);
                JSONObject jsonObject = subobject.getJSONObject("weiboinfo");
                Iterator<String> keys = jsonObject.keys();
                while (keys.hasNext()){
                    info=new Myhomebean();
                    JSONObject weiboinfo = jsonObject.getJSONObject(keys.next());
                    try {
                        info.setExtension(weiboinfo.getString("extension"));
                        info.setContent(weiboinfo.getString("content"));
                        info.setFace_original(weiboinfo.getString("image_origina"));
                        info.setDateline(weiboinfo.getString("dateline"));
                        info.setFrom(weiboinfo.getString("from"));
                        info.setUsername(weiboinfo.getString("username"));
                        list.add(info);;
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return  list;
        }
}
