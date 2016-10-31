package com.sero.chaoshengbo.NetUtil;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by caoshihong on 2016/10/21.
 * <p>
 * 网络请求类--主要功能是获得retrofitAPI
 */

public class NetUtil {

    private static Retrofit retrofit = null;
    private static BaseApi baseApi = null;
    private static OkHttpClient okHttpClient=new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build();
    private static Converter.Factory gsonFactory= GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaAdapterFactory= RxJavaCallAdapterFactory.create();


    public static Retrofit getInstance() {
        if (retrofit == null) {
            retrofit=new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(GetString.getYbxcUrl())
                    .addCallAdapterFactory(rxJavaAdapterFactory)
                    .addConverterFactory(gsonFactory)
                    .build();
        }
        return retrofit;
    }

    public static BaseApi GetApi(){
        if(baseApi == null){
            baseApi=getInstance().create(BaseApi.class);
        }
        return baseApi;
    }
}
