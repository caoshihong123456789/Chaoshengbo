package com.zuilot.chaoshengbo.model;

/**
 * Created by caoshihong on 2016/10/27.
 *
 * 回放 model
 */

public class PlayBackModel {
    private String id;//live表id、live_id，房间id
    private String title;//标题
    private String user_id;//主播用户id
    private String snapshot;//截图
    private String horizontal;//是否横屏(0手机，1PC，2webview)
    private String status;//是否连接状态
    private String playback_url;//回放地址
    private String share_url;//分享地址
    private String time;//时长
    private String type;//直播类型(直播，回放)
    private String androidout;//安卓是否跳出

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getSnapshot() {
        return snapshot;
    }

    public void setSnapshot(String snapshot) {
        this.snapshot = snapshot;
    }

    public String getHorizontal() {
        return horizontal;
    }

    public void setHorizontal(String horizontal) {
        this.horizontal = horizontal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPlayback_url() {
        return playback_url;
    }

    public void setPlayback_url(String playback_url) {
        this.playback_url = playback_url;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAndroidout() {
        return androidout;
    }

    public void setAndroidout(String androidout) {
        this.androidout = androidout;
    }
}
