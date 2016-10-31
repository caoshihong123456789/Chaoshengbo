package com.sero.chaoshengbo.model;

/**
 * Created by caoshihong on 2016/10/27.
 *
 * 用户消息
 */

public class UserInfo {

    private String user_id; //
    private String user_sex; //
    private String user_name; //
    private String user_avatar; //用户头像
    private String user_location; //
    private String create_time; //创建时间
    private String introduce; //
    private String concern_count; //关注数
    private String follower_count; //粉丝数
    private String energy_balance; //能量余额
    private String calorie_balance; //卡路里余额
    private String stream_id; //七牛id
    private String live_id; //live表id，当前直播房间id
    private String likes; //点赞数，人气数
    private String energy_send_count; //历史送出能量总计
    private String calorie_receive_count; //历史收到卡路里总计
    private String user_type; //用户类型
    private String user_sig; //私匙签名
    private String userSigName; //用户签名
    private String is_follow; //此用户是否被当前用户关注
    private String liveimgurl; //直播列表高清图片
    private String enablelive; //能否开启直播

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(String user_sex) {
        this.user_sex = user_sex;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_avatar() {
        return user_avatar;
    }

    public void setUser_avatar(String user_avatar) {
        this.user_avatar = user_avatar;
    }

    public String getUser_location() {
        return user_location;
    }

    public void setUser_location(String user_location) {
        this.user_location = user_location;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getConcern_count() {
        return concern_count;
    }

    public void setConcern_count(String concern_count) {
        this.concern_count = concern_count;
    }

    public String getFollower_count() {
        return follower_count;
    }

    public void setFollower_count(String follower_count) {
        this.follower_count = follower_count;
    }

    public String getEnergy_balance() {
        return energy_balance;
    }

    public void setEnergy_balance(String energy_balance) {
        this.energy_balance = energy_balance;
    }

    public String getCalorie_balance() {
        return calorie_balance;
    }

    public void setCalorie_balance(String calorie_balance) {
        this.calorie_balance = calorie_balance;
    }

    public String getStream_id() {
        return stream_id;
    }

    public void setStream_id(String stream_id) {
        this.stream_id = stream_id;
    }

    public String getLive_id() {
        return live_id;
    }

    public void setLive_id(String live_id) {
        this.live_id = live_id;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getEnergy_send_count() {
        return energy_send_count;
    }

    public void setEnergy_send_count(String energy_send_count) {
        this.energy_send_count = energy_send_count;
    }

    public String getCalorie_receive_count() {
        return calorie_receive_count;
    }

    public void setCalorie_receive_count(String calorie_receive_count) {
        this.calorie_receive_count = calorie_receive_count;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getUser_sig() {
        return user_sig;
    }

    public void setUser_sig(String user_sig) {
        this.user_sig = user_sig;
    }

    public String getUserSigName() {
        return userSigName;
    }

    public void setUserSigName(String userSigName) {
        this.userSigName = userSigName;
    }

    public String getIs_follow() {
        return is_follow;
    }

    public void setIs_follow(String is_follow) {
        this.is_follow = is_follow;
    }

    public String getLiveimgurl() {
        return liveimgurl;
    }

    public void setLiveimgurl(String liveimgurl) {
        this.liveimgurl = liveimgurl;
    }

    public String getEnablelive() {
        return enablelive;
    }

    public void setEnablelive(String enablelive) {
        this.enablelive = enablelive;
    }
}
