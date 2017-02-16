package com.example.administrator.suvsproject.modules.luntan.util;

import android.util.Log;

import com.example.administrator.suvsproject.modules.luntan.bean.BanKuaiGrandSonInfo;
import com.example.administrator.suvsproject.modules.luntan.bean.BanKuaiInfo;
import com.example.administrator.suvsproject.modules.luntan.bean.BanKuaiSonInfo;
import com.example.administrator.suvsproject.modules.luntan.bean.CheXingInfo;
import com.example.administrator.suvsproject.modules.luntan.bean.DaDuiInfo;
import com.example.administrator.suvsproject.modules.luntan.bean.JingXuanInfo;
import com.example.administrator.suvsproject.modules.luntan.bean.LastReplyInfo;
import com.example.administrator.suvsproject.modules.luntan.bean.LunTanInfo;
import com.example.administrator.suvsproject.modules.luntan.bean.ShouCangSuccessInfo2;
import com.example.administrator.suvsproject.modules.luntan.bean.ZhuTiInfo;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/12 0012.
 */
public class LunTanParse {

    private static final String TAG = "LunTanParse";

    public static List<JingXuanInfo> parseJingXuanList(String json) {
        List<JingXuanInfo> list = new ArrayList<>();
        JingXuanInfo info = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("app");

            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    info = new JingXuanInfo();
                    JSONObject subJson = jsonArray.getJSONObject(i);
                    info.setId(subJson.getString("id"));
                    info.setTid(subJson.getString("tid"));
                    info.setTitle(subJson.getString("title"));
                    info.setStitle(subJson.getString("stitle"));
                    info.setForumname(subJson.getString("forumname"));
                    info.setComment(subJson.getString("comment"));

                    JSONArray photo = subJson.getJSONArray("photo");
                    String photoUrl = (String) photo.get(0);
                    info.setPhotoUrl(photoUrl);

                    list.add(info);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<ZhuTiInfo> parseZhuTiList(String json) {
        List<ZhuTiInfo> list = new ArrayList<>();
        ZhuTiInfo info = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("bbsinfo");

            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    info = new ZhuTiInfo();
                    JSONObject subJson = jsonArray.getJSONObject(i);
                    info.setId(subJson.getString("id"));
                    info.setTitle(subJson.getString("title"));

                    list.add(info);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<CheXingInfo> parseCheXingList(String json) {
        List<CheXingInfo> list = new ArrayList<>();
        CheXingInfo info = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("bbsinfo");

            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    info = new CheXingInfo();
                    JSONObject subJson = jsonArray.getJSONObject(i);
                    info.setId(subJson.getString("id"));
                    info.setTitle(subJson.getString("title"));
                    info.setIcon(subJson.getString("icon"));
                    info.setNum(subJson.getInt("num"));

                    list.add(info);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<DaDuiInfo> parseDaDuiList(String json) {
        List<DaDuiInfo> list = new ArrayList<>();
        DaDuiInfo info = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("bbsinfo");

            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    info = new DaDuiInfo();
                    JSONObject subJson = jsonArray.getJSONObject(i);
                    info.setId(subJson.getString("id"));
                    info.setTitle(subJson.getString("title"));
                    info.setIcon(subJson.getString("icon"));
                    info.setNum(subJson.getInt("num"));

                    list.add(info);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static LunTanInfo parseLunTanDetail(String json) {
        LunTanInfo info = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            info = new LunTanInfo();
            info.setTid(jsonObject.getString("tid"));
            info.setAllPages(jsonObject.getInt("all_pages"));
            info.setTitle(jsonObject.getString("title"));
            info.setUrl(jsonObject.getString("url"));
            info.setBbsinfo(jsonObject.getString("bbsinfo"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return info;
    }

    public static List<LastReplyInfo> parseLastReply(String json) {
        List<LastReplyInfo> list = new ArrayList<>();
        LastReplyInfo info = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject bbsinfo = jsonObject.getJSONObject("bbsinfo");
            JSONObject forumthread = bbsinfo.getJSONObject("forumthread");
            JSONArray jsonArray = forumthread.getJSONArray("item");
            Log.e(TAG, "parseCheXingLastReply:jsonArray " + jsonArray.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    info = new LastReplyInfo();
                    JSONObject subJson = jsonArray.getJSONObject(i);

                    info.setTid(subJson.getString("tid"));
                    info.setTitle(subJson.getString("title"));
                    info.setAuthor(subJson.getString("author"));
                    info.setDigest(subJson.getInt("digest"));
                    info.setAuthorid(subJson.getString("authorid"));
                    info.setReplies(subJson.getInt("replies"));
                    info.setViews(subJson.getInt("views"));
                    info.setDisplayorder(subJson.getInt("displayorder"));

                    long time = Long.parseLong(subJson.getString("time"));
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd");
                    String formatTime = simpleDateFormat.format(time);
                    info.setTime(formatTime);

                    list.add(info);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<BanKuaiInfo.BbsinfoBean> parseBanKuaiInfo(String json) {
        List<BanKuaiInfo.BbsinfoBean> list = new ArrayList<>();
        List<BanKuaiSonInfo> sList = new ArrayList<>();
        List<BanKuaiGrandSonInfo> gList = new ArrayList<>();

        BanKuaiInfo.BbsinfoBean info = null;
        BanKuaiSonInfo sinfo = null;
        BanKuaiGrandSonInfo ginfo = null;
        int befroeGid = 0;
        int lastpostion = -1;
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("bbsinfo");
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    info = new BanKuaiInfo.BbsinfoBean();
                    JSONObject parentJson = jsonArray.getJSONObject(i);
                    info.setGid(parentJson.getInt("gid"));

                    if (lastpostion == -1) {

                    } else {
                        if (lastpostion == parentJson.getInt("gid")) {

                        } else {
                            befroeGid = befroeGid + 1;
                        }
                    }
                    lastpostion = parentJson.getInt("gid");

                    info.setName(parentJson.getString("name"));
                    list.add(info);
                    if (parentJson.has("sub")) {
                        JSONArray sub = parentJson.getJSONArray("sub");
                        for (int j = 0; j < sub.length(); j++) {
                            try {
                                sinfo = new BanKuaiSonInfo();
                                JSONObject sonJson = sub.getJSONObject(j);
                                sinfo.setFid(sonJson.getString("fid"));
                                sinfo.setName(sonJson.getString("name"));
                                sinfo.setBefroeGid(befroeGid);
                                sList.add(sinfo);

                                EventBus.getDefault().post(sonJson);
                                if (sonJson.has("sub")) {
                                    JSONArray sub1 = sonJson.getJSONArray("sub");
                                    for (int k = 0; k < sub1.length(); k++) {
                                        try {
                                            ginfo = new BanKuaiGrandSonInfo();
                                            JSONObject grandSonJson = sub1.getJSONObject(k);
                                            ginfo.setFid(grandSonJson.getInt("fid"));
                                            ginfo.setName(grandSonJson.getString("name"));
                                            gList.add(ginfo);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    sList.get(j).setgList(gList);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    list.get(i).setsList(sList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ShouCangSuccessInfo2 parseShouCangSuccessInfo2(String json) {
        ShouCangSuccessInfo2 shouCangSuccessInfo2 = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject bbsinfo = jsonObject.getJSONObject("bbsinfo");
            Log.e(TAG, "parseShouCangSuccessInfo2: "+bbsinfo.getString("tid") );
            shouCangSuccessInfo2.setTid(bbsinfo.getString("tid"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return shouCangSuccessInfo2;
    }
}
