package com.example.administrator.suvsproject.modules.MyLogin.dao.Person.Collection;

import com.example.administrator.suvsproject.i.BaseCallBack;
import com.example.administrator.suvsproject.modules.MyLogin.bean.Person.Collection.BankuaiInfo;
import com.example.administrator.suvsproject.modules.MyLogin.util.Person.Collection.BankuaiParse;
import com.example.administrator.suvsproject.net.HttpUtil;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/11/12 0012.
 */
public class Bankuaidao {
    public static void request(int page, String urlString , final BaseCallBack callBack){

        HashMap<String,String> params=new HashMap<>();
        params.put("formattype=json","");
        params.put("page",page+"");
        params.put("pagesize=10","");
        params.put("action=query","");
        HttpUtil.doHttpReqeust1("GET", urlString, params, new BaseCallBack() {
            @Override
            public void success(Object data) {
               if(callBack!=null){
                   List<BankuaiInfo> list= BankuaiParse.parseList(data.toString());
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
