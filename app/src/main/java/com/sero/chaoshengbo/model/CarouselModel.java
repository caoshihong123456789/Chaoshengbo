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

    private LiveModel live; // live_meta		直播信息
    private PlayBackModel playtype; // playback_meta	回放信息
    private UserInfo anchor; //  user_meta	主播信息
    private FeatureModel feature; //  feature_meta	专题信息

    public LiveModel getLive() {
        return live;
    }

    public void setLive(LiveModel live) {
        this.live = live;
    }

    public PlayBackModel getPlaytype() {
        return playtype;
    }

    public void setPlaytype(PlayBackModel playtype) {
        this.playtype = playtype;
    }

    public UserInfo getAnchor() {
        return anchor;
    }

    public void setAnchor(UserInfo anchor) {
        this.anchor = anchor;
    }

    public FeatureModel getFeature() {
        return feature;
    }

    public void setFeature(FeatureModel feature) {
        this.feature = feature;
    }

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
}
