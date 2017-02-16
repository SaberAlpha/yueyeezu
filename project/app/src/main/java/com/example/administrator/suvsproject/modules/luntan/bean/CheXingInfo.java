package com.example.administrator.suvsproject.modules.luntan.bean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by Administrator on 2016/11/12 0012.
 */
@Table(name = "CheXingInfo")
public class CheXingInfo {
    @Column(name = "_id",isId = true)
    private String _id;
    @Column(name = "id")
    private String id;
    @Column(name = "tile")
    private String title;
    @Column(name = "icon")
    private String icon;
    @Column(name = "num")
    private int num;

    public String getId() {
        return id;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
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

}
