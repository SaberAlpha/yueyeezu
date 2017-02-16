package com.example.administrator.suvsproject.modules.cheku.bean;

/**
 * Created by Administrator on 2016/11/14 0014.
 */
public class CarSortBean {

    private String name;
    private String url;

    public CarSortBean(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
