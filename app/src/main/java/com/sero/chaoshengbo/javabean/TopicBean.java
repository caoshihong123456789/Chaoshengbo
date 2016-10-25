package com.sero.chaoshengbo.javabean;

import com.sero.chaoshengbo.model.TopicModel;

import java.util.List;

/**
 * Created by caoshihong on 2016/10/21.
 *
 * 接口返回内容data里面的bean
 *
 *
 */

public class TopicBean {
    private int count;//总共有几条数据
    private List<TopicModel> list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<TopicModel> getList() {
        return list;
    }

    public void setList(List<TopicModel> list) {
        this.list = list;
    }
}
