package com.example.administrator.suvsproject.modules.MyLogin.bean.Person;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/16 0016.
 */
public class Logininfo  implements Serializable{

    private String errcode;
    private String bbsinfo;
    private String telnum;
    private String groupid;
    private String email;
    private String uid;
    private String username;

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getBbsinfo() {
        return bbsinfo;
    }

    public void setBbsinfo(String bbsinfo) {
        this.bbsinfo = bbsinfo;
    }

    public String getTelnum() {
        return telnum;
    }

    public void setTelnum(String telnum) {
        this.telnum = telnum;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
