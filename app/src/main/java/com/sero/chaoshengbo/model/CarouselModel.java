package com.sero.chaoshengbo.model;

/**
 * Created by caoshihong on 2016/10/27.
 *
 * 首页轮播  carousel_meta
 */

public class CarouselModel {

    private String id; //
    private String title; //
    private String url; //
    private String imgurl; //
    private String androidout; //
    private String relid; //关联id
    private String carousel_type; //轮播类型,0链接,1直播live,2回放,3主播,4专题
    private String live; // live_meta		直播信息
    private String playtype; // playback_meta	回放信息
    private String anchor; //  user_meta	主播信息
    private String feature; //  feature_meta	专题信息

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getAndroidout() {
        return androidout;
    }

    public void setAndroidout(String androidout) {
        this.androidout = androidout;
    }

    public String getRelid() {
        return relid;
    }

    public void setRelid(String relid) {
        this.relid = relid;
    }

    public String getCarousel_type() {
        return carousel_type;
    }

    public void setCarousel_type(String carousel_type) {
        this.carousel_type = carousel_type;
    }

    public String getLive() {
        return live;
    }

    public void setLive(String live) {
        this.live = live;
    }

    public String getPlaytype() {
        return playtype;
    }

    public void setPlaytype(String playtype) {
        this.playtype = playtype;
    }

    public String getAnchor() {
        return anchor;
    }

    public void setAnchor(String anchor) {
        this.anchor = anchor;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }
}
