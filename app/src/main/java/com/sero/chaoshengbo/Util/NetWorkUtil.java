package com.sero.chaoshengbo.Util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Administrator on 2016/10/21.
 */

public class NetWorkUtil {

    /**
     * 判断网络是否连接
     * @return
     */
    public static boolean isNetWorkAbailable(Context context){
        ConnectivityManager manager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(manager==null)return false;
        NetworkInfo networkInfo=manager.getActiveNetworkInfo();
        if(networkInfo==null || !networkInfo.isAvailable()){
            return false;
        }else{
            return true;
        }
    }
}
