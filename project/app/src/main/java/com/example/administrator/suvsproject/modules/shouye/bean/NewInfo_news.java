package com.example.administrator.suvsproject.modules.shouye.bean;

/**
 * Created by DQH on 2016/11/15.
 */
public class NewInfo_news extends BaseInfo{

    private String scancount;  //浏览数
    private String publishtime;
    private String photo;
    private String tid;
    private String channelname;
    private String avatar;
    private String author;

    public String getChannelname() {
        return channelname;
    }

    public void setChannelname(String channelname) {
        this.channelname = channelname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getScancount() {
        return scancount;
    }

    public void setScancount(String scancount) {
        this.scancount = scancount;
    }

    public String getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(String publishtime) {
        this.publishtime = publishtime;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

}
