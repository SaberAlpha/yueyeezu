package com.example.administrator.suvsproject.modules.cheku.bean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.io.Serializable;

@Table(name = "t_CarBean")
public class CarBean implements Serializable {
    /**
     * id : 358
     * name : 奥迪
     * pid : 0
     * groupid : 0
     * priority : 358
     * type :
     * carfrom :
     * fwords : A
     * words : audi
     * nwords :
     * series_price_min : 0
     * series_price_max : 0
     * size :
     * price_range :
     * price : 0
     * carnum : 0
     * picnum : 0
     * content :
     * area : 德国
     * fid : 158
     * photo : http://carport.fblife.com/attachments/brand/audi.jpg
     * url :
     * recommend : 0
     */

    @Column(name = "_id", isId = true)
    private int _id;

    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;  //车名

    @Column(name = "pid")
    private String pid;

    @Column(name = "groupid")
    private String groupid;

    @Column(name = "priority")
    private String priority;

    @Column(name = "type")
    private String type;

    @Column(name = "carfrom")
    private String carfrom;

    @Column(name = "fwords")
    private String fwords; ////车名的首字母

    @Column(name = "words")
    private String words;

    @Column(name = "nwords")
    private String nwords;

    @Column(name = "series_price_min")
    private String series_price_min;

    @Column(name = "series_price_max")
    private String series_price_max;

    @Column(name = "size")
    private String size;

    @Column(name = "price_range")
    private String price_range;

    @Column(name = "price")
    private String price;

    @Column(name = "carnum")
    private String carnum;

    @Column(name = "picnum")
    private String picnum;

    @Column(name = "content")
    private String content;

    @Column(name = "area")
    private String area;

    @Column(name = "fid")
    private String fid;

    @Column(name = "photo")
    private String photo;

    @Column(name = "url")
    private String url;

    @Column(name = "recommend")
    private String recommend;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCarfrom() {
        return carfrom;
    }

    public void setCarfrom(String carfrom) {
        this.carfrom = carfrom;
    }

    public String getFwords() {
        return fwords;
    }

    public void setFwords(String fwords) {
        this.fwords = fwords;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public String getNwords() {
        return nwords;
    }

    public void setNwords(String nwords) {
        this.nwords = nwords;
    }

    public String getSeries_price_min() {
        return series_price_min;
    }

    public void setSeries_price_min(String series_price_min) {
        this.series_price_min = series_price_min;
    }

    public String getSeries_price_max() {
        return series_price_max;
    }

    public void setSeries_price_max(String series_price_max) {
        this.series_price_max = series_price_max;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPrice_range() {
        return price_range;
    }

    public void setPrice_range(String price_range) {
        this.price_range = price_range;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCarnum() {
        return carnum;
    }

    public void setCarnum(String carnum) {
        this.carnum = carnum;
    }

    public String getPicnum() {
        return picnum;
    }

    public void setPicnum(String picnum) {
        this.picnum = picnum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    @Override
    public String toString() {
        return "CarBean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", pid='" + pid + '\'' +
                ", groupid='" + groupid + '\'' +
                ", priority='" + priority + '\'' +
                ", type='" + type + '\'' +
                ", carfrom='" + carfrom + '\'' +
                ", fwords='" + fwords + '\'' +
                ", words='" + words + '\'' +
                ", nwords='" + nwords + '\'' +
                ", series_price_min='" + series_price_min + '\'' +
                ", series_price_max='" + series_price_max + '\'' +
                ", size='" + size + '\'' +
                ", price_range='" + price_range + '\'' +
                ", price='" + price + '\'' +
                ", carnum='" + carnum + '\'' +
                ", picnum='" + picnum + '\'' +
                ", content='" + content + '\'' +
                ", area='" + area + '\'' +
                ", fid='" + fid + '\'' +
                ", photo='" + photo + '\'' +
                ", url='" + url + '\'' +
                ", recommend='" + recommend + '\'' +
                '}';
    }
}
