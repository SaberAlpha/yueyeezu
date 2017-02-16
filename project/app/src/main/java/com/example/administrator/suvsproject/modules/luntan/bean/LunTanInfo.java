package com.example.administrator.suvsproject.modules.luntan.bean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by Administrator on 2016/11/12 0012.
 */
@Table(name = "LunTanInfo")
public class LunTanInfo {
    @Column(name = "_id",isId = true)
    private String _id;
    @Column(name = "tid")
    private String tid;
    @Column(name = "tile")
    private String title;
    @Column(name = "allPages")
    private int allPages;
     @Column(name = "url")
    private String url;
    @Column(name = "bbsinfo")
    private String bbsinfo;

    public String getBbsinfo() {
        return bbsinfo;
    }

    public void setBbsinfo(String bbsinfo) {
        this.bbsinfo = bbsinfo;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public int getAllPages() {
        return allPages;
    }

    public void setAllPages(int allPages) {
        this.allPages = allPages;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
