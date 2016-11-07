package com.zuilot.chaoshengbo.javabean;

import com.zuilot.chaoshengbo.model.LiveModel;

import java.util.List;

/**
 * Created by caoshihong on 2016/11/2.
 *
 *
 */

public class LiveActivityLivesBean {

    private List<LiveModel> list;
    private int count;

    public List<LiveModel> getList() {
        return list;
    }

    public void setList(List<LiveModel> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
