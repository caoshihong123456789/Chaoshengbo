package com.zuilot.chaoshengbo.activity;


import android.app.Fragment;

import com.umeng.analytics.MobclickAgent;

/**
 * Created by Administrator on 2016/10/19.
 */

public class BaseFragment extends Fragment {


    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(generateTag(getCallerStackTraceElement())); //统计页面(仅有Activity的应用中SDK自动调用，不需要单独写。"SplashScreen"为页面名称，可自定义)
        MobclickAgent.onResume(getActivity());          //统计时长
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(generateTag(getCallerStackTraceElement())); // （仅有Activity的应用中SDK自动调用，不需要单独写）保证 onPageEnd 在onPause 之前调用,因为 onPause 中会保存信息。"SplashScreen"为页面名称，可自定义
        MobclickAgent.onPause(getActivity());
    }

    private static StackTraceElement getCallerStackTraceElement() {
        return Thread.currentThread().getStackTrace()[5];
    }
    private static String generateTag(StackTraceElement caller) {
        String tag = "%s.%s(Line:%d)"; // 占位符
        String callerClazzName = caller.getClassName(); // 获取到类名
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        tag = String.format(tag, callerClazzName, caller.getMethodName(), caller.getLineNumber()); // 替换
        return tag;
    }
}
