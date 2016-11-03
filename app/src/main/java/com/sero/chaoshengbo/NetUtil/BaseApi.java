package com.sero.chaoshengbo.NetUtil;

import com.sero.chaoshengbo.javabean.BaseResponseBean;
import com.sero.chaoshengbo.javabean.FeatureDetailBean;
import com.sero.chaoshengbo.javabean.HomeActivityBean;
import com.sero.chaoshengbo.javabean.LiveActivityLivesBean;
import com.sero.chaoshengbo.javabean.LiveActivityRecommendedBean;
import com.sero.chaoshengbo.model.CarouselModel;
import com.sero.chaoshengbo.model.FeatureModel;
import com.sero.chaoshengbo.model.TopicDetailModel;
import com.sero.chaoshengbo.model.UserInfo;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/10/21.
 *
 * 各种网络请求的api
 *
 */

public interface BaseApi {

    String user_id="100530340";//暂时先用固定值，还没有写获得个人信息的接口

    /**获取专题页数据的方法*/
    @GET(GetString.TOPIC_URL)
    Observable<BaseResponseBean<FeatureDetailBean<FeatureModel>>> TopIcGetData(
            @Query("user_id") String user_id,
            @Query("psize") int psize,
            @Query("pindex") int pindex);

    /**专题详情页获取数据的方法*/
    @GET(GetString.TOPIC_DETAIL_URL)
    Observable<BaseResponseBean<FeatureDetailBean<TopicDetailModel>>> TopIcDetailGetData(
            @Query("user_id") String user_id,
            @Query("fid") String fid,
            @Query("psize") int psize,
            @Query("pindex") int pindex
    );

    /**获得首页咨询与推荐*/
    @GET(GetString.HomeActivity_URL)
    Observable<BaseResponseBean<HomeActivityBean>> HomeActivityGetData(
            @Query("user_id") String user_id,
            @Query("psize") int psize,
            @Query("pindex") int pindex
    );

    /**获得现场页所有直播列表*/
    @GET(GetString.LiveActivity_GETLives_URL)
    Observable<BaseResponseBean<LiveActivityLivesBean>> LiveActivityGetLives(
            @Query("user_id") String user_id,
            @Query("psize") int psize,
            @Query("pindex") int pindex
    );


    /**获得现场页轮播图列表*/
    @GET(GetString.LiveActivity_GETCarousel_URL)
    Observable<BaseResponseBean<List<CarouselModel>>> LiveActivityGetCarousel(
            @Query("user_id") String user_id
    );

    /**获得现场页推荐主播列表*/
    @GET(GetString.LiveActivity_GETRecommend_URL)
    Observable<BaseResponseBean<LiveActivityRecommendedBean>> LiveActivityGetRecommendLives(
            @Query("user_id") String user_id
    );

    /**获得现场页推荐主播列表*/
    @GET(GetString.UserCenterActivity_getUserInfo_URL)
    Observable<BaseResponseBean<UserInfo>> UserCenterActivityLives(
            @Query("user_id") String user_id
    );


}
