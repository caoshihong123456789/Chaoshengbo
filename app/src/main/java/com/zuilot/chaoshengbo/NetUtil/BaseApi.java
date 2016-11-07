package com.zuilot.chaoshengbo.NetUtil;

import com.zuilot.chaoshengbo.javabean.BaseResponseBean;
import com.zuilot.chaoshengbo.javabean.FeatureDetailBean;
import com.zuilot.chaoshengbo.javabean.HomeActivityBean;
import com.zuilot.chaoshengbo.javabean.LiveActivityLivesBean;
import com.zuilot.chaoshengbo.javabean.LiveActivityRecommendedBean;
import com.zuilot.chaoshengbo.model.CarouselModel;
import com.zuilot.chaoshengbo.model.FeatureModel;
import com.zuilot.chaoshengbo.model.TopicDetailModel;
import com.zuilot.chaoshengbo.model.UserInfo;

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
