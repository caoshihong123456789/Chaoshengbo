package com.sero.chaoshengbo.Util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by caoshihong on 2016/10/21.
 *
 * toast 弹框工具类
 */

public class ToastUtil {

    /**
     * 若是已经有toast了，只要更改文字，不需要再调用maktext
     *
     */
    public static Toast toast;
    public static void disPlayOnlyMesShort(Context context, String mes){
        if(toast==null){
            toast=Toast.makeText(context,mes,Toast.LENGTH_SHORT);
        }else{
            toast.setText(mes.toString());
        }
        toast.show();
    }
}
