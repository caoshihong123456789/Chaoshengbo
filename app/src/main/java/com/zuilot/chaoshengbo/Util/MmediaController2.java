package com.zuilot.chaoshengbo.Util;

import android.app.Activity;
import android.widget.Toast;

import com.pili.pldroid.player.AVOptions;
import com.pili.pldroid.player.PLMediaPlayer;
import com.zuilot.chaoshengbo.module.PlaybackActivity;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by caoshihong on 2016/11/8.
 * <p>
 * 回放页面相关配置util
 * <p>
 * 在本页面实现监听视频进度 并且显示出来
 */

public class MmediaController2 implements
        PLMediaPlayer.OnPreparedListener,
        PLMediaPlayer.OnInfoListener,
        PLMediaPlayer.OnCompletionListener,
        PLMediaPlayer.OnVideoSizeChangedListener,
        PLMediaPlayer.OnErrorListener,
        PLMediaPlayer.OnBufferingUpdateListener {

    /*AVOptions	用于配置播放器参数，包括：超时时间、软硬件编解码*/
    private AVOptions avOptions;
    private PlaybackActivity context;
    private Toast mToast = null;
    private long currentPosition;
    private PLMediaPlayer mediaPlayer;
    private static Observable<PLMediaPlayer> currentPostionObservable;
    private boolean isSeekBarTouch=false;


    public void setSeekBarTouch(boolean seekBarTouch) {
        isSeekBarTouch = seekBarTouch;
    }

    public PLMediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public long getCurrentPosition() {
        return currentPosition;
    }

    public AVOptions getAVOptions(PlaybackActivity context) {
        this.context = context;
        if (avOptions == null) {
            avOptions = new AVOptions();
            //准备超时时间，包括创建资源、建立连接、请求码流等，单位是 ms
            avOptions.setInteger(AVOptions.KEY_PREPARE_TIMEOUT, 10 * 1000);
            //读取视频流超时时间，单位是 ms
            avOptions.setInteger(AVOptions.KEY_GET_AV_FRAME_TIMEOUT, 5 * 1000);

            // 当前播放的是否为在线直播，如果是，则底层会有一些播放优化
            // 默认值是：0  1--在线直播 0---回放
            avOptions.setInteger(AVOptions.KEY_LIVE_STREAMING, 0);

            // 是否开启"延时优化"，只在在线直播流中有效
            // 默认值是：0  1--在线直播 0---回放
            avOptions.setInteger(AVOptions.KEY_DELAY_OPTIMIZATION, 0);

            // 解码方式，codec＝1，硬解; codec=0, 软解
            // 默认值是：0
            avOptions.setInteger(AVOptions.KEY_MEDIACODEC, 0);

            // 是否自动启动播放，如果设置为 1，则在调用 `prepareAsync` 或者 `setVideoPath` 之后自动启动播放，无需调用 `start()`
            // 默认值是：1
            avOptions.setInteger(AVOptions.KEY_START_ON_PREPARED, 0);
        }
        return avOptions;
    }

    @Override
    public void onCompletion(PLMediaPlayer plMediaPlayer) {
        LogUtil.e("playbackUtil:" + plMediaPlayer.getCurrentPosition(), "onCompletion：" + plMediaPlayer.getDuration());
        if(plMediaPlayer.getCurrentPosition()+500 > plMediaPlayer.getDuration()){
            showToastTips("播放完成");
            onDestroy();
        }else{
            currentPosition = plMediaPlayer.getCurrentPosition();
            sendReconnectMessage();
        }

    }

    @Override
    public boolean onError(PLMediaPlayer plMediaPlayer, int errorCode) {
        boolean isNeedReconnect = false;
        LogUtil.e("---" + errorCode);
        switch (errorCode) {
            case PLMediaPlayer.ERROR_CODE_INVALID_URI:
                showToastTips("Invalid URL !");
                break;
            case PLMediaPlayer.ERROR_CODE_404_NOT_FOUND:
                showToastTips("404 resource not found !");
                break;
            case PLMediaPlayer.ERROR_CODE_CONNECTION_REFUSED:
                showToastTips("Connection refused !");
                break;
            case PLMediaPlayer.ERROR_CODE_CONNECTION_TIMEOUT:
                showToastTips("Connection timeout !");
                isNeedReconnect = true;
                break;
            case PLMediaPlayer.ERROR_CODE_EMPTY_PLAYLIST:
                showToastTips("Empty playlist !");
                break;
            case PLMediaPlayer.ERROR_CODE_STREAM_DISCONNECTED:
                showToastTips("Stream disconnected !");
                isNeedReconnect = true;
                break;
            case PLMediaPlayer.ERROR_CODE_IO_ERROR:
                showToastTips("Network IO Error !");
                isNeedReconnect = true;
                break;
            case PLMediaPlayer.ERROR_CODE_UNAUTHORIZED:
                showToastTips("Unauthorized Error !");
                break;
            case PLMediaPlayer.ERROR_CODE_PREPARE_TIMEOUT:
                showToastTips("Prepare timeout !");
                isNeedReconnect = true;
                break;
            case PLMediaPlayer.ERROR_CODE_READ_FRAME_TIMEOUT:
                showToastTips("Read frame timeout !");
                isNeedReconnect = true;
                break;
            case PLMediaPlayer.MEDIA_ERROR_UNKNOWN:
                break;
            default:
                showToastTips("unknown error !");
                break;
        }
        // Todo pls handle the error status here, reconnect or call finish()
        if (isNeedReconnect) {
            sendReconnectMessage();
        } else {
            ((Activity) context).finish();
        }
        // Return true means the error has been handled
        // If return false, then `onCompletion` will be called
        return true;
    }

    @Override
    public boolean onInfo(PLMediaPlayer plMediaPlayer, int i, int i1) {
        LogUtil.e(i1 + "onInfo:" + i);
        if (mediaPlayer == null) {
            this.mediaPlayer = plMediaPlayer;
        }
        if (i == 10003) {
            LogUtil.e("绑定订阅者，轮询改变ui，如果出错就重连");
            getCurrentObservable();

        }
        return false;
    }

    private Observable<PLMediaPlayer> getCurrentObservable() {
        if (currentPostionObservable == null) {
            currentPostionObservable = Observable.interval(1, TimeUnit.SECONDS)
                    .filter(new Func1<Long, Boolean>() {
                        @Override
                        public Boolean call(Long aLong) {
                            return !isSeekBarTouch && mediaPlayer !=null && mediaPlayer.isPlaying() ;
                        }
                    })
                    .map(new Func1<Long, PLMediaPlayer>() {
                        @Override
                        public PLMediaPlayer call(Long aLong) {
                            LogUtil.e("被观察者--" + mediaPlayer);
                            return mediaPlayer;
                        }
                    });
            currentPostionObservable.observeOn(AndroidSchedulers.mainThread())
                    .subscribe(context.getCurrentSubscriber());
        }
        return currentPostionObservable;
    }


    @Override
    public void onPrepared(PLMediaPlayer plMediaPlayer) {
        LogUtil.e("onPrepared:" + plMediaPlayer);
    }

    @Override
    public void onVideoSizeChanged(PLMediaPlayer plMediaPlayer, int i, int i1) {
        LogUtil.e(i1 + "onVideoSizeChanged:" + i);
    }

    @Override
    public void onBufferingUpdate(PLMediaPlayer plMediaPlayer, int i) {
        LogUtil.e("onBufferingUpdate:" + i);
    }

    private void showToastTips(final String tips) {
        Observable.just(tips)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        LogUtil.e("---showToastTips", "---" + s);
                        if (mToast != null) {
                            mToast.cancel();
                        }
                        mToast = Toast.makeText(context, tips, Toast.LENGTH_SHORT);
                        mToast.show();
                    }
                });
    }

    private void sendReconnectMessage() {
        showToastTips("正在重连...");
        if(mediaPlayer != null){
            currentPosition=mediaPlayer.getCurrentPosition();
            LogUtil.e("重连过程中输出当前进度："+currentPosition);
            mediaPlayer=null;
        }
        /*Observable.just("正在重连...").retryWhen(new Func1<Observable<? extends Throwable>, Observable<?>>() {
            @Override
            public Observable<?> call(Observable<? extends Throwable> observable) {

                return observable.flatMap(new Func1<Throwable, Observable<?>>() {
                    @Override
                    public Observable<?> call(Throwable throwable) {
                        if(throwable instanceof UnknownHostException){
                            Observable.error(throwable);
                        }
                        return Observable.just(throwable).zipWith(Observable.range(1, 5), new Func2<Throwable, Integer, Integer>() {
                            @Override
                            public Integer call(Throwable throwable, Integer integer) {
                                return integer;
                            }
                        }).flatMap(new Func1<Integer, Observable<?>>() {
                            @Override
                            public Observable<?> call(Integer integer) {
                               return Observable.timer((long) Math.pow(5, retryCount), TimeUnit.SECONDS);
                            }
                        });
                    }
                });
            }
        });*/
//        Observable.just("正在重连")
//                .timer(5, TimeUnit.SECONDS)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(context.getSubscriber());
    }

    public String generateTime(long position) {
        int totalSeconds = (int) (position / 1000);

        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours = totalSeconds / 3600;

        if (hours > 0) {
            return String.format(Locale.US, "%02d:%02d:%02d", hours, minutes,
                    seconds).toString();
        } else {
            return String.format(Locale.US, "%02d:%02d", minutes, seconds)
                    .toString();
        }
    }

    public void onPause() {
        context.getCurrentSubscriber().unsubscribe();
    }

    public void onResume() {

    }

    public void onDestroy() {
        context.getCurrentSubscriber().unsubscribe();
        currentPostionObservable=null;
    }

    public void seekTo(long currentPosition){
        if(mediaPlayer != null){
            mediaPlayer.seekTo(mediaPlayer.getDuration() * currentPosition / 1000);
        }
    }

}
