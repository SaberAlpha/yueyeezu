package com.example.administrator.suvsproject.modules.shouye.bean;

public class ShipinInfo extends BaseInfo {


    private int _id;

    private String id;

    private String title;

    private long publishtime;

    private long  videoduration;

    private String addtime;

    private String photo;

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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getVideourl() {
        return videourl;
    }

    public void setVideourl(String videourl) {
        this.videourl = videourl;
    }
}
