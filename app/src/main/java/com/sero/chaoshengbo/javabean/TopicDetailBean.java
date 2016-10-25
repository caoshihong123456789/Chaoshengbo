package com.sero.chaoshengbo.javabean;

import java.util.List;

/**
 * Created by caoshihong on 2016/10/24.
 *
 * 返回值中data 中的数据
 */

public class TopicDetailBean<T> {

    private int count;
//    private List<TopicDetailModel> topicDetailModelList;
    private List<T> list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
