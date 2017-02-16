package com.example.administrator.suvsproject.modules.shipin.dao;

import com.example.administrator.suvsproject.i.BaseCallBack;
import com.example.administrator.suvsproject.modules.shipin.bean.ShipinInfo;
import com.example.administrator.suvsproject.modules.shipin.util.ShipinParse;
import com.example.administrator.suvsproject.net.HttpUtil;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/11/12 0012.
 */
public class Shipindao  {
    public static void request(int page, final BaseCallBack callBack){

        HashMap<String,String> params=new HashMap<>();
        params.put("page",page+"");
        HttpUtil.doHttpReqeust("GET", "http://cmsweb.fblife.com/ajax.php?c=news&a=getvideolist&pagesize=20&page=", params, new BaseCallBack() {
            @Override
            public void success(Object data) {
               if(callBack!=null){
                   List<ShipinInfo> list= ShipinParse.parseList(data.toString());
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
