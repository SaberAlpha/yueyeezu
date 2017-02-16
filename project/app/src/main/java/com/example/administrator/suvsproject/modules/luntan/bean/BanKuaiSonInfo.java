package com.example.administrator.suvsproject.modules.luntan.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/16 0016.
 */
public class BanKuaiSonInfo extends BanKuaiInfo{

    /**
     * fid : 10
     * name : 粤
     * sub : [{"fid":116,"name":"深圳中队","isfavorite":0}]
     * isfavorite : 0
     */

    private String fid;
    private String name;
    private List<BanKuaiGrandSonInfo> gList;
    private int befroeGid;

    public int getBefroeGid() {
        return befroeGid;
    }

    public void setBefroeGid(int befroeGid) {
        this.befroeGid = befroeGid;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BanKuaiGrandSonInfo> getgList() {
        return gList;
    }

    public void setgList(List<BanKuaiGrandSonInfo> gList) {
        this.gList = gList;
    }

}
