package com.example.administrator.suvsproject.modules.MyLogin.dao.Person.Myhome;

import com.example.administrator.suvsproject.i.BaseCallBack;
import com.example.administrator.suvsproject.modules.MyLogin.bean.Person.Logininfo;
import com.example.administrator.suvsproject.modules.MyLogin.bean.Person.Myhome.Myhomebean;
import com.example.administrator.suvsproject.modules.MyLogin.util.Person.Myhome.MyhomeitemParse;
import com.example.administrator.suvsproject.net.HttpUtil;
import com.se7en.utils.SystemUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/11/12 0012.
 */
public class Myhomeitemdao {
    public static void request(int page , final BaseCallBack callBack){
        Logininfo logininfo = null;
        try {
          logininfo= (Logininfo) SystemUtil.getSharedSerializable("loginfo");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String bbsinfo = logininfo.getBbsinfo();
        String uid = logininfo.getUid();
        HashMap<String,String> params=new HashMap<>();
        params.put("page",page+"");
        params.put("fbtype=json","");
        params.put("uid=",uid);
        HttpUtil.doHttpReqeust1("POST","http://fb.fblife.com/openapi/index.phpcode=mylist&fromtype=7c383caa&mod=getweibo&authkey="+bbsinfo, params, new BaseCallBack() {
            @Override
            public void success(Object data) {
               if(callBack!=null){
                   List<Myhomebean> list= MyhomeitemParse.parseList(data.toString());
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
