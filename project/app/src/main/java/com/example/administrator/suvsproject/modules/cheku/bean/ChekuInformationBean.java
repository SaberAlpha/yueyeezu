package com.example.administrator.suvsproject.modules.cheku.bean;

/**
 * Created by Administrator on 2016/11/16 0016.
 */
public class ChekuInformationBean {
    /**
     * id : 160011
     * title : “硬派”旅行 e族试驾2015款沃尔沃V60 T5
     * stitle : 试驾沃尔沃V60 T5
     * photo : http://img10.fblife.com/attachments/20150523/1432310503.jpg.180x120.jpg?1
     * newslink : http://drive.fblife.com/html/20150522/160011.html
     * publishtime : 1432303828
     * description : 很多汽车编辑都是旅行车的粉丝，这并不是什么秘密，沃尔沃V60给了我们更加充分的理由继续热爱旅行车。它有英俊的外表，实用性很好而且还有不错的驾驶乐趣。在这个小众而细分的市场中，V60光彩照人。
     */

    private String id;
    private String title;
    private String stitle;
    private String photo;
    private String newslink;
    private String publishtime;
    private String description;

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

    public String getStitle() {
        return stitle;
    }

    public void setStitle(String stitle) {
        this.stitle = stitle;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getNewslink() {
        return newslink;
    }

    public void setNewslink(String newslink) {
        this.newslink = newslink;
    }

    public String getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(String publishtime) {
        this.publishtime = publishtime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
