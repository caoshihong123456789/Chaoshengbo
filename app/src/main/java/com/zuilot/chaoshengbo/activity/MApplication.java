package com.zuilot.chaoshengbo.activity;

import android.app.Application;

import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

/**
 * Created by Administrator on 2016/11/4.
 */

public class MApplication extends Application {

    {
        PlatformConfig.setWeixin("wxdf83cf251b5fa145", "fc1bb28f5d374ee529b82c2ad32bba35");
        PlatformConfig.setSinaWeibo("3049200700", "c271225593229b9f203e92f6c59419f5");
        PlatformConfig.setQQZone("1104744458", "Lnn2lH2OIaSN1J5n");
        Config.REDIRECT_URL="http://www.yingboxuncai.com";
    }

    @Override
    public void onCreate() {
        super.onCreate();

        /*第三方登陆和分享*/

        UMShareAPI.get(this);
        /*第三方登陆和分享*/
    }


}
