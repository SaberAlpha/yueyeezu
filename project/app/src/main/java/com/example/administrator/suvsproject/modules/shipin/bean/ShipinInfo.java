package com.example.administrator.suvsproject.modules.shipin.bean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by Administrator on 2016/11/12 0012.
 */
@Table(name="ShipinInfo")
public class ShipinInfo  {

    @Column(name="_id",isId = true)
    private int _id;
    @Column(name = "id")
    private String id;
    @Column(name = "title")
    private String title;
    @Column(name = "publishtime")
    private long publishtime;
    @Column(name = "videoduration")
    private long  videoduration;
    @Column(name = "addtime")
    private String addtime;
    @Column(name = "icon")
    private String icon;
    @Column(name = "videourl")
    private String videourl;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(long publishtime) {
        this.publishtime = publishtime;
    }

    public long getVideoduration() {
        return videoduration;
    }

    public void setVideoduration(long videoduration) {
        this.videoduration = videoduration;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getVideourl() {
        return videourl;
    }

    public void setVideourl(String videourl) {
        this.videourl = videourl;
    }
}
