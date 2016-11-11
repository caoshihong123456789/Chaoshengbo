package com.zuilot.chaoshengbo.javabean;

import com.zuilot.chaoshengbo.model.LiveModel;
import com.zuilot.chaoshengbo.model.UserInfo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/11/2.
 *
 * 带有直播对象参数的userinfo
 *
 */

public class LiveActivityRecommendedBean implements Serializable{

    private int count;
    private List<myUserInfoBean> list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<myUserInfoBean> getBean() {
        return list;
    }

    public void setBean(List<myUserInfoBean> bean) {
        this.list = bean;
    }

    public class myUserInfoBean extends UserInfo {
        private LiveModel live;

        public LiveModel getLive() {
            return live;
        }

        public void setLive(LiveModel live) {
            this.live = live;
        }
    }
}

