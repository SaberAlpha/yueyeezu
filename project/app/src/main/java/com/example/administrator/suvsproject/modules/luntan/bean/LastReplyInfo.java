package com.example.administrator.suvsproject.modules.luntan.bean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by Administrator on 2016/11/12 0012.
 */
@Table(name = "LastReplyInfo")
public class LastReplyInfo {
    @Column(name = "_id", isId = true)
    private String _id;
    @Column(name = "tid")
    private String tid;
    @Column(name = "tile")
    private String title;
    @Column(name = "author")
    private String author;
    @Column(name = "digest")
    private int digest;
    @Column(name = "authorid")
    private String authorid;
    @Column(name = "replies")
    private int replies;
    @Column(name = "views")
    private int views;
    @Column(name = "displayorder")
    private int displayorder;
    @Column(name = "time")
    private String time;

    public int getDigest() {
        return digest;
    }

    public void setDigest(int digest) {
        this.digest = digest;
    }

    public int getDisplayorder() {
        return displayorder;
    }

    public void setDisplayorder(int displayorder) {
        this.displayorder = displayorder;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorid() {
        return authorid;
    }

    public void setAuthorid(String authorid) {
        this.authorid = authorid;
    }


    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getReplies() {
        return replies;
    }

    public void setReplies(int replies) {
        this.replies = replies;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
