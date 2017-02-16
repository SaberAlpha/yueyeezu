package com.example.administrator.suvsproject.modules.MyLogin.util.Person;

import com.example.administrator.suvsproject.modules.MyLogin.bean.Person.Logininfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/12 0012.
 */
public class LoginParse {

        public static List<Logininfo> parseList(String data){
            List<Logininfo> list=new ArrayList<>();
            Logininfo info=null;
            try {
                JSONObject subobject=new JSONObject(data);
                info=new Logininfo();
                info.setErrcode(subobject.getString("errcode"));
                info.setBbsinfo(subobject.getString("bbsinfo"));
                info.setTelnum(subobject.getString("telnum"));
                info.setGroupid(subobject.getString("groupid"));
                info.setEmail(subobject.getString("email"));
                info.setUsername(subobject.getString("username"));
                list.add(info);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return  list;
        }
}
