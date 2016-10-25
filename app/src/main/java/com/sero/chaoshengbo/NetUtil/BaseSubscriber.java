package com.sero.chaoshengbo.NetUtil;

import android.content.Context;

import com.sero.chaoshengbo.Util.NetWorkUtil;
import com.sero.chaoshengbo.Util.ToastUtil;

import rx.Subscriber;

/**
 * Created by caoshihong on 2016/10/21.
 *
 * 接口监听者，在这里统一做网络请求失败，开始的提示操作
 */

public abstract class BaseSubscriber<T>  extends Subscriber<T> {

    private Context context;
    protected BaseSubscriber(Context context) {
        super();
        this.context=context;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(!NetWorkUtil.isNetWorkAbailable(context)){
            ToastUtil.disPlayOnlyMesShort(context,"网络未连接，请检查网络");
        }
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(T t) {

    }


}
