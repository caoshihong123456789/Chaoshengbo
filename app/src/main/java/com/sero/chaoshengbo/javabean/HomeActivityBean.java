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
    private List<CarouselModel> carouselModelList;
    private List<LiveModel> liveModelList;
    private List<FeatureModel> featureModelList;
    private List<InfoModel> infoModelList;
    private List<InfoModel> topInfoModelList;
    private int count;

    public List<CarouselModel> getCarouselModelList() {
        return carouselModelList;
    }

    public void setCarouselModelList(List<CarouselModel> carouselModelList) {
        this.carouselModelList = carouselModelList;
    }

    public List<LiveModel> getLiveModelList() {
        return liveModelList;
    }

    public void setLiveModelList(List<LiveModel> liveModelList) {
        this.liveModelList = liveModelList;
    }

    public List<FeatureModel> getFeatureModelList() {
        return featureModelList;
    }

    public void setFeatureModelList(List<FeatureModel> featureModelList) {
        this.featureModelList = featureModelList;
    }

    public List<InfoModel> getInfoModelList() {
        return infoModelList;
    }

    public void setInfoModelList(List<InfoModel> infoModelList) {
        this.infoModelList = infoModelList;
    }

    public List<InfoModel> getTopInfoModelList() {
        return topInfoModelList;
    }

    public void setTopInfoModelList(List<InfoModel> topInfoModelList) {
        this.topInfoModelList = topInfoModelList;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
