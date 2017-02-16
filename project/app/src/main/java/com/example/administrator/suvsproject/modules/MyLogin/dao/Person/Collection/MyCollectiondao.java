package com.example.administrator.suvsproject.modules.MyLogin.dao.Person.Collection;

import com.example.administrator.suvsproject.i.BaseCallBack;
import com.example.administrator.suvsproject.modules.MyLogin.bean.Person.Collection.CollectiontieziInfo;
import com.example.administrator.suvsproject.modules.MyLogin.util.Person.Collection.tieziParse;
import com.example.administrator.suvsproject.net.HttpUtil;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/11/12 0012.
 */
public class MyCollectiondao {
    public static void request(int page, String urlString , final BaseCallBack callBack){

        HashMap<String,String> params=new HashMap<>();
        params.put("formattype=json","");
        params.put("page",page+"");
        params.put("pagesize=20","");
        HttpUtil.doHttpReqeust1("POST", urlString, params, new BaseCallBack() {
            @Override
            public void success(Object data) {
               if(callBack!=null){
                   List<CollectiontieziInfo> list= tieziParse.parseList(data.toString());
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
