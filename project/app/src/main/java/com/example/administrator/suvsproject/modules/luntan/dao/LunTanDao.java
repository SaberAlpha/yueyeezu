package com.example.administrator.suvsproject.modules.luntan.dao;

import android.util.Log;

import com.example.administrator.suvsproject.i.BaseCallBack;
import com.example.administrator.suvsproject.modules.luntan.bean.BanKuaiInfo;
import com.example.administrator.suvsproject.modules.luntan.bean.CheXingInfo;
import com.example.administrator.suvsproject.modules.luntan.bean.DaDuiInfo;
import com.example.administrator.suvsproject.modules.luntan.bean.JingXuanInfo;
import com.example.administrator.suvsproject.modules.luntan.bean.LastReplyInfo;
import com.example.administrator.suvsproject.modules.luntan.bean.LunTanInfo;
import com.example.administrator.suvsproject.modules.luntan.bean.ShouCangSuccessInfo2;
import com.example.administrator.suvsproject.modules.luntan.bean.ZhuTiInfo;
import com.example.administrator.suvsproject.modules.luntan.util.LunTanParse;
import com.example.administrator.suvsproject.net.HttpUtil;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/11/12 0012.
 */
public class LunTanDao {
    private static final String TAG = "LunTanDao";

    public static void requestJingxuanList(int page, final BaseCallBack callBack) {

        HttpUtil.doHttpReqeust1("GET", "http://cmsweb.fblife.com/ajax.php?c=newstwo&a=getappindex&=10&datatype=3&type=json&pagesize=10&page=" + page, null, new BaseCallBack() {
            @Override
            public void success(Object data) {
                if (callBack != null) {
                    List<JingXuanInfo> tempList = LunTanParse.parseJingXuanList(data.toString());
                    callBack.success(tempList);
                }
            }

            @Override
            public void failed(int errorCode, Object data) {
                if (callBack != null) {
                    callBack.failed(errorCode, data);
                }
            }
        });
    }

    public static void requestZhuTiList(final BaseCallBack callBack) {
        HttpUtil.doHttpReqeust1("GET", "http://bbs.fblife.com/bbsapinew/cmsappgetranking.php?type=1&formattype=json&authcode=", null, new BaseCallBack() {
            @Override
            public void success(Object data) {
                if (callBack != null) {
                    List<ZhuTiInfo> tempList = LunTanParse.parseZhuTiList(data.toString());
                    callBack.success(tempList);
                }
            }

            @Override
            public void failed(int errorCode, Object data) {
                if (callBack != null) {
                    callBack.failed(errorCode, data);
                }
            }
        });
    }

    public static void requestCheXingList(final BaseCallBack callBack) {
        HttpUtil.doHttpReqeust1("GET", "http://bbs.fblife.com/bbsapinew/cmsappgetranking.php?type=2&formattype=json&authcode=", null, new BaseCallBack() {
            @Override
            public void success(Object data) {
                if (callBack != null) {
                    List<CheXingInfo> tempList = LunTanParse.parseCheXingList(data.toString());
                    callBack.success(tempList);
                }
            }

            @Override
            public void failed(int errorCode, Object data) {
                if (callBack != null) {
                    callBack.failed(errorCode, data);
                }
            }
        });
    }

    public static void requestDaDuiList(final BaseCallBack callBack) {
        HttpUtil.doHttpReqeust1("GET", "http://bbs.fblife.com/bbsapinew/cmsappgetranking.php?type=3&formattype=json&authcode=", null, new BaseCallBack() {
            @Override
            public void success(Object data) {
                if (callBack != null) {
                    List<DaDuiInfo> tempList = LunTanParse.parseDaDuiList(data.toString());
                    callBack.success(tempList);
                }
            }

            @Override
            public void failed(int errorCode, Object data) {
                if (callBack != null) {
                    callBack.failed(errorCode, data);
                }
            }
        });
    }

    public static void requestLunTanInfo(String tid, int page, final BaseCallBack callBack) {
        HttpUtil.doHttpReqeust1("GET", "http://bbs.fblife.com/bbsapinew/getThreadsNew_v2.php?authcode=&tid=" + tid + "&page=" + page + "&formattype=json", null, new BaseCallBack() {
            @Override
            public void success(Object data) {
                if (callBack != null) {
                    LunTanInfo detail = LunTanParse.parseLunTanDetail(data.toString());
                    callBack.success(detail);
                }
            }

            @Override
            public void failed(int errorCode, Object data) {
                if (callBack != null) {
                    callBack.failed(errorCode, data);
                }
            }
        });
    }

    public static void requestLunTanDetail(String urlString, final BaseCallBack callBack) {
        HttpUtil.doHttpReqeust1("GET", urlString, null, new BaseCallBack() {
            @Override
            public void success(Object data) {
                if (callBack != null) {
                    String content = String.valueOf(data);
                    Log.e(TAG, "success: " + content);
                    callBack.success(content);
                }
            }

            @Override
            public void failed(int errorCode, Object data) {
                if (callBack != null) {
                    callBack.failed(errorCode, data);
                }
            }
        });
    }

    public static void requestLastReply(String fid, int page, final BaseCallBack callBack) {
        HashMap<String, String> params = new HashMap<>();
        params.put("fid", fid);
        params.put("areaid", "0");
        params.put("orderby", "2");
        params.put("authcode", "");
        params.put("page", page + "");
        params.put("formattype", "json");
        params.put("typeid", "0");
        HttpUtil.doHttpReqeust1("POST", "http://bbs.fblife.com/bbsapinew/getforumthread.php", params, new BaseCallBack() {
            @Override
            public void success(Object data) {
                if (callBack != null) {
                    List<LastReplyInfo> tempList = LunTanParse.parseLastReply(data.toString());
                    callBack.success(tempList);
                }
            }

            @Override
            public void failed(int errorCode, Object data) {
                if (callBack != null) {
                    callBack.failed(errorCode, data);
                }
            }
        });
    }

    public static void requestBanKuaiInfo(final String categorytype, final BaseCallBack callBack) {
        HttpUtil.doHttpReqeust1("GET", "http://bbs.fblife.com/bbsapinew/getforumsbycategory.php?categorytype=" + categorytype + "&authcode=&formattype=json", null, new BaseCallBack() {
            @Override
            public void success(Object data) {
                if (callBack != null) {
                    List<BanKuaiInfo.BbsinfoBean> tempList = LunTanParse.parseBanKuaiInfo(data.toString());
                    callBack.success(tempList);
                }
            }

            @Override
            public void failed(int errorCode, Object data) {
                if (callBack != null) {
                    callBack.failed(errorCode, data);
                }
            }
        });
    }

    public static void requestShouCangSuccessInfo2(String url, final BaseCallBack callBack) {
        HttpUtil.doHttpReqeust1("GET",url , null, new BaseCallBack() {
            @Override
            public void success(Object data) {
                if (callBack != null) {
                    ShouCangSuccessInfo2 tempInfo = LunTanParse.parseShouCangSuccessInfo2(data.toString());
                    callBack.success(tempInfo);
                }
            }

            @Override
            public void failed(int errorCode, Object data) {
                if (callBack != null) {
                    callBack.failed(errorCode, data);
                }
            }
        });
    }

}
