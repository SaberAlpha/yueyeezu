package com.example.administrator.suvsproject.modules.MyLogin.dao.Person.Myhome;

import com.example.administrator.suvsproject.i.BaseCallBack;
import com.example.administrator.suvsproject.modules.MyLogin.bean.Person.Logininfo;
import com.example.administrator.suvsproject.modules.MyLogin.bean.Person.Myhome.MyhomeFirstbean;
import com.example.administrator.suvsproject.modules.MyLogin.util.Person.Myhome.MyhomefirstParse;
import com.example.administrator.suvsproject.net.HttpUtil;
import com.se7en.utils.SystemUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/11/12 0012.
 */
public class Myhomefirstdao {
    public static void request( int page, final BaseCallBack callBack){
        Logininfo logininfo = null;
        try {
            logininfo= (Logininfo) SystemUtil.getSharedSerializable("loginfo");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String bbsinfo = logininfo.getBbsinfo();
        final String uid = logininfo.getUid();
        HashMap<String,String> params=new HashMap<>();
        params.put("fbtype=json","");
        params.put("uid=",uid);
        params.put("fbtype=json","");
        HttpUtil.doHttpReqeust1("POST", "http://fb.fblife.com/openapi/index.php?mod=getuser&code=base&fromtype=7c383caa&authkey=", params, new BaseCallBack() {
            @Override
            public void success(Object data) {
               if(callBack!=null){
                   List<MyhomeFirstbean> myhomeFirstbean=MyhomefirstParse.parseList(data.toString(),uid);
                   callBack.success(myhomeFirstbean);
               }
            }

            @Override
            public void failed(int errorCode, Object data) {
                callBack.failed(errorCode,data);
            }
        });
    }
}
