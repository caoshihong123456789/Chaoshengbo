package com.sero.chaoshengbo.model;

/**
 * Created by caoshihong on 2016/10/27.
 *
 * 首页--咨询model
 */

public class InfoModel {

    private String id;      //	int	资讯id
    private String title;   //	varchar	标题
    private String title2;  //	varchar	副标题
    private String url;     //		varchar	链接地址
    private String img;     //		varchar	图片地址
    private String infotype;//	tinyint	资讯类型0webview
    private String click;   //	int	阅读量

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

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getInfotype() {
        return infotype;
    }

    public void setInfotype(String infotype) {
        this.infotype = infotype;
    }

    public String getClick() {
        return click;
    }

    public void setClick(String click) {
        this.click = click;
    }
}
