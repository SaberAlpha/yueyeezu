package com.example.administrator.suvsproject.modules.MyLogin.util.Person.Myhome;

import com.example.administrator.suvsproject.modules.MyLogin.bean.Person.Myhome.MyhomeFirstbean;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2016/11/12 0012.
 */
public class MyhomefirstParse {

        public static List<MyhomeFirstbean> parseList(String data, String uid){
            List<MyhomeFirstbean> list=new ArrayList<>();
            MyhomeFirstbean info=null;
            try {
                JSONObject subobject=new JSONObject(data);
                JSONObject subJson = subobject.getJSONObject("data");
                    try {
                        Iterator<String> keys = subJson.keys();
                        while (keys.hasNext()){
                            JSONObject jsonObject=subJson.getJSONObject(keys.next());
                            info=new MyhomeFirstbean();
                            info.setFace_original(jsonObject.getString("face_small"));
                            info.setUsername(jsonObject.getString("username"));
                            info.setUid(jsonObject.getString("uid"));
                            info.setBlog_count(jsonObject.getString("blog_count"));
                            info.setAlbum_count(jsonObject.getString("album_count"));
                            info.setImage_count(jsonObject.getString("image_count"));
                            info.setTopic_count(jsonObject.getString("topic_count"));
                            info.setFollow_count(jsonObject.getString("follow_count"));
                            info.setFans_count(jsonObject.getString("fans_count"));
                            list.add(info);
                            keys.next();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return  list;
        }
}
