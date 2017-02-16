package com.example.administrator.suvsproject.modules.shouye.dao;

import com.example.administrator.suvsproject.modules.shouye.bean.BaseInfo;
import com.example.administrator.suvsproject.modules.shouye.bean.NewInfo_ad;
import com.example.administrator.suvsproject.modules.shouye.bean.NewInfo_news;
import com.example.administrator.suvsproject.modules.shouye.bean.NewInfo_photo;
import com.example.administrator.suvsproject.modules.shouye.bean.ProductsInfo;
import com.example.administrator.suvsproject.modules.shouye.bean.ShipinInfo;
import com.example.administrator.suvsproject.modules.shouye.bean.SlidesInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DQH on 2016/11/17.
 */
public class JsonParse {

    public List<BaseInfo> Parse(String json){
        List<BaseInfo> list = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject rspData = jsonObject.getJSONObject("rspData");
            if(rspData.has("slides")){
                JSONArray array_slides = rspData.getJSONArray("slides");
                for (int i = 0; i < array_slides.length(); i++) {
                    try {
                        SlidesInfo slidesInfo = new SlidesInfo();
                        JSONObject subJson = array_slides.getJSONObject(i);
                        slidesInfo.setTitle(subJson.getString("title"));
                        slidesInfo.setPhoto(subJson.getString("photo"));
                        slidesInfo.setLink(subJson.getString("link"));
                        list.add(slidesInfo);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
            if(rspData.has("products")){
                JSONArray array_products = rspData.getJSONArray("products");
                for (int i = 0; i < array_products.length(); i++) {
                    try {
                        ProductsInfo productsInfo = new ProductsInfo();
                        JSONObject subJson = array_products.getJSONObject(i);
                        productsInfo.setTitle(subJson.getString("title"));
                        productsInfo.setPhoto(subJson.getString("photo"));
                        productsInfo.setTarget(subJson.getString("target"));
                        list.add(productsInfo);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            if(rspData.has("news")){
                JSONArray array_news = rspData.getJSONArray("news");
                try {
                    for (int i = 0; i < array_news.length(); i++) {
                        JSONObject subJson = array_news.getJSONObject(i);
                        String itemType = subJson.getString("itemtype");
                        if(itemType.equals("photo")){
                            NewInfo_photo newInfo_photo = new NewInfo_photo();
                            newInfo_photo.setItemtype(itemType);
                            newInfo_photo.setTitle(subJson.getString("title"));
                            newInfo_photo.setTid(subJson.getString("tid"));
                            JSONArray photoes = subJson.getJSONArray("photoes");
                            String[] str = new String[3];
                            for (int j = 0; j < photoes.length(); j++) {
                                str[j]=photoes.getString(j);
                            }
                            newInfo_photo.setPhotoes(str);
                            list.add(newInfo_photo);
                        }else if(itemType.equals("ad")){
                            NewInfo_ad newInfo = new NewInfo_ad();
                            newInfo.setItemtype(itemType);
                            JSONObject ad = subJson.getJSONObject("ad");
                            newInfo.setAdlink(ad.getString("adlink"));
                            newInfo.setAdphoto(ad.getString("adphoto"));
                            newInfo.setAdtype(ad.getInt("adtype"));
                            list.add(newInfo);
                        }else if(itemType.equals("video")){
                            ShipinInfo shipinInfo = new ShipinInfo();
                            shipinInfo.setItemtype(itemType);
                            shipinInfo.setTitle(subJson.getString("title"));
                            shipinInfo.setPhoto(subJson.getString("photo"));
                            list.add(shipinInfo);
                        }else if (itemType.equals("bbs")||itemType.equals("news")){
                            NewInfo_news newInfo_news = new NewInfo_news();
                            newInfo_news.setAvatar(subJson.getString("avatar"));
                            newInfo_news.setChannelname(subJson.getString("channelname"));
                            newInfo_news.setItemtype(itemType);
                            if(subJson.has("scancount")){
                            newInfo_news.setScancount(subJson.getString("scancount"));}
                            newInfo_news.setAuthor(subJson.getString("author"));
                            newInfo_news.setTitle(subJson.getString("title"));
                            newInfo_news.setPhoto(subJson.getString("photo"));
                            newInfo_news.setPublishtime(subJson.getString("publishtime"));
                            newInfo_news.setTid(subJson.getString("tid"));
                            list.add(newInfo_news);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
