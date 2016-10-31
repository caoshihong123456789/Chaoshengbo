package com.sero.chaoshengbo.model;

import java.io.Serializable;

/**
 * Created by caoshihong on 2016/5/25.
 *
 * 专题列表页的bean  feature_meta
 */
public class FeatureModel implements Serializable {
    //专题id
    private int id;
    //专题名
    private String name;
    //专题详细介绍
    private String intro;
    //专题简介
    private String intro_short;
    //专题页的封面
    private String img;
    //专题列表页的头像
    private String img_small;
    //专题分享地址
    private String share ;
    //观众数
    private int views;
    //点赞数
    private  int likes;
    //关注数
    private  int follows;
    //关注状态
    private String follow_state;

    //专题分享图片
    private String share_img;

    //专题分享标题
    private String share_title;

    //专题分享内容
    private String share_content;

    private String share_thumb_img;

    public String getShare_thumb_img() {
        return share_thumb_img;
    }

    public void setShare_thumb_img(String share_thumb_img) {
        this.share_thumb_img = share_thumb_img;
    }

    public String getShare_content() {
        return share_content;
    }

    public void setShare_content(String share_content) {
        this.share_content = share_content;
    }

    public String getShare_title() {
        return share_title;
    }

    public void setShare_title(String share_title) {
        this.share_title = share_title;
    }

    public String getShare_img() {
        return share_img;
    }

    public void setShare_img(String share_img) {
        this.share_img = share_img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getIntro_short() {
        return intro_short;
    }

    public void setIntro_short(String intro_short) {
        this.intro_short = intro_short;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImg_small() {
        return img_small;
    }

    public void setImg_small(String img_small) {
        this.img_small = img_small;
    }

    public String getShare() {
        return share;
    }

    public void setShare(String share) {
        this.share = share;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getFollows() {
        return follows;
    }

    public void setFollows(int follows) {
        this.follows = follows;
    }

    public String getFollow_state() {
        return follow_state;
    }

    public void setFollow_state(String follow_state) {
        this.follow_state = follow_state;
    }
}
