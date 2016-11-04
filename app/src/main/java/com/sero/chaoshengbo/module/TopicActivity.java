package com.sero.chaoshengbo.module;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sero.chaoshengbo.NetUtil.BaseApi;
import com.sero.chaoshengbo.NetUtil.BaseSubscriber;
import com.sero.chaoshengbo.NetUtil.NetUtil;
import com.sero.chaoshengbo.R;
import com.sero.chaoshengbo.activity.BaseFragment;
import com.sero.chaoshengbo.adapter.TopicAdapter;
import com.sero.chaoshengbo.javabean.BaseResponseBean;
import com.sero.chaoshengbo.javabean.FeatureDetailBean;
import com.sero.chaoshengbo.model.FeatureModel;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TopicActivity extends BaseFragment {

    @Bind(R.id.activity_topic)
    LinearLayout activityTopic;
    @Bind(R.id.title_bar_title)
    TextView titleBarTitle;
    @Bind(R.id.topic_listview)
    RecyclerView topicListview;

    private TopicAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_topic, container, false);
        ButterKnife.bind(this, view);
        initView();
        initData();
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public static TopicActivity newInstance() {
        TopicActivity fragment = new TopicActivity();
        return fragment;
    }

    private void initView() {
        titleBarTitle.setText(getString(R.string.topic_title));

        topicListview.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new TopicAdapter();
        topicListview.setAdapter(adapter);
    }

    private void initData() {
        NetUtil.GetApi().TopIcGetData(BaseApi.user_id, 20, 0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<BaseResponseBean<FeatureDetailBean<FeatureModel>>>(getActivity()) {
                    @Override
                    public void onNext(BaseResponseBean<FeatureDetailBean<FeatureModel>> topicBeanBaseResponseBean) {
                        super.onNext(topicBeanBaseResponseBean);
                        adapter.setData(topicBeanBaseResponseBean.getData().getList());

                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }

                    @Override
                    public void onCompleted() {
                        super.onCompleted();

                    }

                    @Override
                    public void onStart() {
                        super.onStart();
                    }
                });
    }


}
