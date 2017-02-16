package com.example.administrator.suvsproject.modules.cheku.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/15 0015.
 */
public class ChekuCarZongshuBean {

    private List<String> name;
    private List<String> cid;
    private List<String> c_cszdj;
    private List<String> c_fdj;
    private List<String> c_bsx;

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

    public List<String> getCid() {
        return cid;
    }

    public void setCid(List<String> cid) {
        this.cid = cid;
    }

    public List<String> getC_cszdj() {
        return c_cszdj;
    }

    public void setC_cszdj(List<String> c_cszdj) {
        this.c_cszdj = c_cszdj;
    }

    public List<String> getC_fdj() {
        return c_fdj;
    }

    public void setC_fdj(List<String> c_fdj) {
        this.c_fdj = c_fdj;
    }

    public List<String> getC_bsx() {
        return c_bsx;
    }

    public void setC_bsx(List<String> c_bsx) {
        this.c_bsx = c_bsx;
    }


    @Override
    public String toString() {
        return "ChekuCarZongshuBean{" +
                "name=" + name +
                ", cid=" + cid +
                ", c_cszdj=" + c_cszdj +
                ", c_fdj=" + c_fdj +
                ", c_bsx=" + c_bsx +
                '}';
    }
}
