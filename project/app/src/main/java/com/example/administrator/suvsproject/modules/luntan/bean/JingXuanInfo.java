package com.example.administrator.suvsproject.modules.luntan.bean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by Administrator on 2016/11/12 0012.
 */
@Table(name = "JingXuanInfo")
public class JingXuanInfo {
    @Column(name = "_id",isId = true)
    private String _id;
    @Column(name = "id")
    private String id;
    @Column(name = "tid")
    private String tid;
    @Column(name = "tile")
    private String title;
    @Column(name = "stitle")
    private String stitle;
    @Column(name = "photoUrl")
    private String photoUrl;
    @Column(name = "forumname")
    private String forumname;
    @Column(name = "comment")
    private String comment;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getStitle() {
        return stitle;
    }

    public void setStitle(String stitle) {
        this.stitle = stitle;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getForumname() {
        return forumname;
    }

    public void setForumname(String forumname) {
        this.forumname = forumname;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
