package com.sero.chaoshengbo.javabean;

import com.sero.chaoshengbo.model.CarouselModel;
import com.sero.chaoshengbo.model.FeatureModel;
import com.sero.chaoshengbo.model.InfoModel;
import com.sero.chaoshengbo.model.LiveModel;

import java.util.List;

/**
 * Created by caoshihong on 2016/10/27.
 *
 * 首页 返回值data中的数据
 */

public class HomeActivityBean {
    private List<CarouselModel> carousel;
    private List<LiveModel> lives;
    private List<FeatureModel> feature;
    private List<InfoModel> info;
    private List<InfoModel> top;
    private int count;

    public List<CarouselModel> getCarousel() {
        return carousel;
    }

    public void setCarousel(List<CarouselModel> carousel) {
        this.carousel = carousel;
    }

    public List<LiveModel> getLives() {
        return lives;
    }

    public void setLives(List<LiveModel> lives) {
        this.lives = lives;
    }

    public List<FeatureModel> getFeature() {
        return feature;
    }

    public void setFeature(List<FeatureModel> feature) {
        this.feature = feature;
    }

    public List<InfoModel> getInfo() {
        return info;
    }

    public void setInfo(List<InfoModel> info) {
        this.info = info;
    }

    public List<InfoModel> getTop() {
        return top;
    }

    public void setTop(List<InfoModel> top) {
        this.top = top;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
