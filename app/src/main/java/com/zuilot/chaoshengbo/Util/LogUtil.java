package com.zuilot.chaoshengbo.Util;

/**
 * Created by Administrator on 2016/11/14.
 */

import android.text.TextUtils;
import android.util.Log;
import com.zuilot.chaoshengbo.BuildConfig;


public class LogUtil {

    public static String customTagPrefix = "";  // 自定义Tag的前缀，可以是作者名
    public static boolean DEBUG = BuildConfig.Logable;
    public static String TAG="sero";


    /**
     * @param logContent
     */
    public static void e(String logContent){
        if(DEBUG)e("", logContent);
    }
    /**
     *
     */
    public static void e(String tag, String msg){
        if(DEBUG) {
            if(TextUtils.isEmpty(tag)){
                StackTraceElement caller = getCallerStackTraceElement();
                String tagClass = generateTag(caller);

                Log.e(tagClass,msg);
            }else{
                Log.e(tag,msg);
            }
        }
    }

    /**
     * @param logContent
     */
    public static void d(String logContent){
        if(DEBUG)d("", logContent);
    }
    /**
     *
     */
    public static void d(String tag, String msg){
        if(DEBUG) {
            if(TextUtils.isEmpty(tag)){
                StackTraceElement caller = getCallerStackTraceElement();
                String tagClass = generateTag(caller);

                Log.d(tagClass,msg);
            }else{
                Log.d(tag,msg);
            }
        }
    }

    /**
     * @param logContent
     */
    public static void i(String logContent){
        if(DEBUG)i("", logContent);
    }
    /**
     *
     */
    public static void i(String tag, String msg){
        if(DEBUG) {
            if(TextUtils.isEmpty(tag)){
                StackTraceElement caller = getCallerStackTraceElement();
                String tagClass = generateTag(caller);

                Log.i(tagClass,msg);
            }else{
                Log.i(tag,msg);
            }
        }
    }


    private static StackTraceElement getCallerStackTraceElement() {
        return Thread.currentThread().getStackTrace()[5];
    }
    private static String generateTag(StackTraceElement caller) {
        String tag = "%s.%s(Line:%d)"; // 占位符
        String callerClazzName = caller.getClassName(); // 获取到类名
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        tag = String.format(tag, callerClazzName, caller.getMethodName(), caller.getLineNumber()); // 替换
        tag = TextUtils.isEmpty(customTagPrefix) ? tag : customTagPrefix + ":" + tag;

        return tag;
    }
}
