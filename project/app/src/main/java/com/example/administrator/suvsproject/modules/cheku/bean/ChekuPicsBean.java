package com.example.administrator.suvsproject.modules.cheku.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/16 0016.
 */
public class ChekuPicsBean implements Serializable  {

    /**
     * name : 沃尔沃XC60
     * photourl : http://carport.fblife.com/attachments/photo/10/133974298655_m.jpg
     * type : 1
     */

    private String name;
    private String photourl;
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotourl() {
        return photourl;
    }

    public void setPhotourl(String photourl) {
        this.photourl = photourl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ChekuPicsBean{" +
                "name='" + name + '\'' +
                ", photourl='" + photourl + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
