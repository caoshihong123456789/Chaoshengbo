package com.zuilot.chaoshengbo.javabean;

import com.zuilot.chaoshengbo.model.CarouselModel;
import com.zuilot.chaoshengbo.model.LiveModel;

import java.util.List;

/**
 * Created by Administrator on 2016/11/2.
 */

public class LiveActivityBean {

    List<LiveModel> lives;//全部直播列表
    List<CarouselModel> carousel;//轮播图---
    List<LiveActivityRecommendedBean.myUserInfoBean> recommondLives;//推荐直播列表

    public LiveActivityBean(List<LiveModel> lives,
                            List<CarouselModel> carousel,
                            List<LiveActivityRecommendedBean.myUserInfoBean> recommondLives) {
        this.lives = lives;
        this.carousel = carousel;
        this.recommondLives = recommondLives;
    }

    public List<LiveModel> getLives() {
        return lives;
    }

    public void setLives(List<LiveModel> lives) {
        this.lives = lives;
    }

    public List<CarouselModel> getCarousel() {
        return carousel;
    }

    public void setCarousel(List<CarouselModel> carousel) {
        this.carousel = carousel;
    }

    public List<LiveActivityRecommendedBean.myUserInfoBean> getRecommondLives() {
        return recommondLives;
    }

    public void setRecommondLives(List<LiveActivityRecommendedBean.myUserInfoBean> recommondLives) {
        this.recommondLives = recommondLives;
    }
}
