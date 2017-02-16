package com.example.administrator.suvsproject.modules.MyLogin.dao.Person.Search;

import com.example.administrator.suvsproject.i.BaseCallBack;
import com.example.administrator.suvsproject.modules.MyLogin.bean.Person.Myhome.MyhomeFirstbean;
import com.example.administrator.suvsproject.modules.MyLogin.util.Person.Myhome.MyhomefirstParse;
import com.example.administrator.suvsproject.net.HttpUtil;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/11/12 0012.
 */
public class yonghuhdao {
    public static void request(int page, String urlString ,String searchinfo, final BaseCallBack callBack){

        HashMap<String,String> params=new HashMap<>();
        params.put("page",page+"");
        params.put("username",searchinfo);
        HttpUtil.doHttpReqeust1("POST", " http://fb.fblife.com/openapi/index.php?mod=search&code=user&fromtype=7c383caa&fbtype=json&pagesize=10&page="+page+"&"+"username="+searchinfo, null, new BaseCallBack() {
            @Override
            public void success(Object data) {
               if(callBack!=null){
                 List<MyhomeFirstbean> list= MyhomefirstParse.parseList(data.toString(),"");
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
