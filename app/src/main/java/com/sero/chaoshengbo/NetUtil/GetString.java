package com.sero.chaoshengbo.NetUtil;

import com.sero.chaoshengbo.BuildConfig;

/**
 * Created by caoshihong on 2016/10/21.
 *
 * 专门写uri的类
 */

public class GetString {
    private static String YBXC_URL="";

    static{
        switch (BuildConfig.buildType){
            case 1://debug版本 现在还没有测试版本，不需要
            case 0://正式版本

                YBXC_URL="http://www.yingboxuncai.com/";


                break;
        }
    }

    public static String getYbxcUrl() {
        return YBXC_URL;
    }


    public static final String TOPIC_URL="/chat/index/features";//专题url
    public static final String TOPIC_DETAIL_URL="/chat/index/featurecontents";//专题详情url


}
