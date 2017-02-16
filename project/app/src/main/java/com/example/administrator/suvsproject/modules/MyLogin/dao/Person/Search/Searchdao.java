package com.example.administrator.suvsproject.modules.MyLogin.dao.Person.Search;

import android.util.Log;

import com.example.administrator.suvsproject.i.BaseCallBack;
import com.example.administrator.suvsproject.modules.MyLogin.bean.Person.search.MySerchInfo;
import com.example.administrator.suvsproject.modules.MyLogin.util.Person.SearchParse;
import com.example.administrator.suvsproject.net.HttpUtil;

import java.util.List;

/**
 * Created by Administrator on 2016/11/12 0012.
 */
public class Searchdao {
    public static void request(int page, String urlString ,String searchinfo, final BaseCallBack callBack){

//        HashMap<String,String> params=new HashMap<>();
//
//        params.put("pagesize=10","");
//        params.put("formattype=json","");
//        params.put("charset=utf8","");
//        params.put("page",page+"");
//        params.put("query",searchinfo);
//        params.put("","");
        Log.e("pirnt", "request:>>>>>>>>> "+ urlString+"&pagesize=10&formattype=json&charset=utf8&page="+page+"&query="+searchinfo);
        HttpUtil.doHttpReqeust1("POST", urlString+"&pagesize=10&formattype=json&charset=utf8&page="+page+"query="+searchinfo, null, new BaseCallBack() {
            @Override
            public void success(Object data) {
               if(callBack!=null){
                   List<MySerchInfo> list= SearchParse.parseList(data.toString());
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
