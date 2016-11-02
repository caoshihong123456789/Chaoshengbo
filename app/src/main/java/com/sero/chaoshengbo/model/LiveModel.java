package com.sero.chaoshengbo.model;

/**
 * Created by caoshihong on 2016/10/27.
 * <p>
 * 直播 live_meta
 */

public class LiveModel extends PlayBackModel{

    /** 继承回放model， 以下属性回放中有 不在定义
     * private String id;//live表id、live_id，房间id
    private String title;//标题
    private String user_id;//主播用户id
    private String snapshot;//截图
    private String horizontal;//是否横屏(0手机，1PC，2webview)
    private String status;//是否连接状态
    private String playback_url;//回放地址
    private String share_url;//分享地址
    private String time;//时长
    private String type;//直播类型(直播，回放)
    private String androidout;//安卓是否跳出*/

    private String show; //是否显示
    private String rtmp_publish_url; //rtmp推流地址
    private String rtmp_play_url; //rtmp播放地址
    private String hls_play_url; //hls播放地址
    private String socket_url; //socket地址
    private String webview_url; //webview播放地址
    private String date_create; //创建时间
    private String live_user_count; //当前在线人数
    private UserInfo user;
    private String stream_json;//转为直播使用


    public String getStream_json() {
        return stream_json;
    }

    public void setStream_json(String stream_json) {
        this.stream_json = stream_json;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }

    public String getRtmp_publish_url() {
        return rtmp_publish_url;
    }

    public void setRtmp_publish_url(String rtmp_publish_url) {
        this.rtmp_publish_url = rtmp_publish_url;
    }

    public String getRtmp_play_url() {
        return rtmp_play_url;
    }

    public void setRtmp_play_url(String rtmp_play_url) {
        this.rtmp_play_url = rtmp_play_url;
    }

    public String getHls_play_url() {
        return hls_play_url;
    }

    public void setHls_play_url(String hls_play_url) {
        this.hls_play_url = hls_play_url;
    }

    public String getSocket_url() {
        return socket_url;
    }

    public void setSocket_url(String socket_url) {
        this.socket_url = socket_url;
    }

    public String getWebview_url() {
        return webview_url;
    }

    public void setWebview_url(String webview_url) {
        this.webview_url = webview_url;
    }

    public String getDate_create() {
        return date_create;
    }

    public void setDate_create(String date_create) {
        this.date_create = date_create;
    }

    public String getLive_user_count() {
        return live_user_count;
    }

    public void setLive_user_count(String live_user_count) {
        this.live_user_count = live_user_count;
    }
}
