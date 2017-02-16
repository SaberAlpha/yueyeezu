package com.example.administrator.suvsproject.modules.MyLogin.dao.Person;

import android.text.TextUtils;

import com.example.administrator.suvsproject.i.BaseCallBack;
import com.example.administrator.suvsproject.modules.MyLogin.bean.Person.Logininfo;
import com.example.administrator.suvsproject.modules.MyLogin.util.Person.LoginParse;
import com.example.administrator.suvsproject.net.HttpUtil;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/11/12 0012.
 */
public class Registerdao {
    public static void request(int step,String telphone,String telcode, String password,
                               String username, String email, final BaseCallBack callBack){

        HashMap<String,String> params=new HashMap<>();
        params.put("step",step+"");
        if(!TextUtils.isEmpty(telphone)){
            params.put("telphone",telphone);
        }
        if(!TextUtils.isEmpty(telcode)){
            params.put("telcode",telcode);
        }
        if(!TextUtils.isEmpty(username)){
            params.put("username",username);
        }
        if(!TextUtils.isEmpty(email)){
            params.put("email",email);
        }
        if(!TextUtils.isEmpty(email)){
            params.put("email",email);
        }
        if(!TextUtils.isEmpty(password)){
            params.put("password",password);
        }
        params.put("&keycode=e2e3420683&datatype=json","");

        HttpUtil.doHttpReqeust1("GET", "http://bbs.fblife.com/bbsapinew/register.php?type=phone&step=", params, new BaseCallBack() {
            @Override
            public void success(Object data) {
               if(callBack!=null){
                   List<Logininfo> list= LoginParse.parseList(data.toString());
                   callBack.success(list);
               }
            }

            @Override
            public void failed(int errorCode, Object data) {
                callBack.failed(errorCode,data);
            }
        });
    }
}
