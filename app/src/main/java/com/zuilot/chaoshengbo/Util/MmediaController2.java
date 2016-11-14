package com.zuilot.chaoshengbo.Util;

import com.pili.pldroid.player.PLMediaPlayer;

/**
 * 播放器控制器--在本页面实现监听视频进度 并且显示出来
 */

public class MmediaController2 {

    private PLMediaPlayer mediaPlayer;

    public void setMediaPlayer(PLMediaPlayer mediaPlayerControl) {
        this.mediaPlayer=mediaPlayerControl;
    }

    public PLMediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    /**
     * 当开始播放时，调用的方法
     */
    public void start(){
//        Observable.interval(0,1, TimeUnit.SECONDS)
    }

    /**
     * 根据播放器的生命周期指定的方法
     */
    public void stop(){

    }

}