package com.zuilot.chaoshengbo.javabean;

import com.zuilot.chaoshengbo.model.FeatureModel;

import java.util.List;

/**
 * Created by caoshihong on 2016/10/24.
 *
 * 返回值中data 中的数据
 */

public class FeatureDetailBean<T> extends FeatureModel {

    private int count;
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
