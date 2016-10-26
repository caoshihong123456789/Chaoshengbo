package com.sero.chaoshengbo.NetUtil;

import com.sero.chaoshengbo.javabean.BaseResponseBean;
import com.sero.chaoshengbo.javabean.TopicDetailBean;
import com.sero.chaoshengbo.model.TopicDetailModel;
import com.sero.chaoshengbo.model.TopicModel;

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
    Observable<BaseResponseBean<TopicDetailBean<TopicModel>>> TopIcGetData(
            @Query("user_id") String user_id,
            @Query("psize") int psize,
            @Query("pindex") int pindex);

    /**专题详情页获取数据的方法*/
    @GET(GetString.TOPIC_DETAIL_URL)
    Observable<BaseResponseBean<TopicDetailBean<TopicDetailModel>>> TopIcDetailGetData(
            @Query("user_id") String user_id,
            @Query("fid") String fid,
            @Query("psize") int psize,
            @Query("pindex") int pindex
    );


}
