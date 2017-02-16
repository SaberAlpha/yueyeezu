package com.example.administrator.suvsproject.modules.MyLogin.dao.Person;

import com.example.administrator.suvsproject.i.BaseCallBack;
import com.example.administrator.suvsproject.modules.MyLogin.bean.Person.Logininfo;
import com.example.administrator.suvsproject.modules.MyLogin.util.Person.LoginParse;
import com.example.administrator.suvsproject.net.HttpUtil;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/11/12 0012.
 */
public class Logindao {
    public static void request(String  usernmae, String password, final BaseCallBack callBack){

        HashMap<String,String> params=new HashMap<>();
        params.put("username",usernmae+"");
        params.put("password",password+"");
        params.put("formattype=json","");

        HttpUtil.doHttpReqeust1("POST", "http://bbs.fblife.com/bbsapinew/login.php", params, new BaseCallBack() {
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
