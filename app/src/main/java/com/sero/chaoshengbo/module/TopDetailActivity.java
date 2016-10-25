package com.sero.chaoshengbo.module;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.sero.chaoshengbo.NetUtil.BaseApi;
import com.sero.chaoshengbo.NetUtil.BaseSubscriber;
import com.sero.chaoshengbo.NetUtil.NetUtil;
import com.sero.chaoshengbo.R;
import com.sero.chaoshengbo.activity.BaseActivity;
import com.sero.chaoshengbo.adapter.TopicDetailAdapter;
import com.sero.chaoshengbo.javabean.BaseResponseBean;
import com.sero.chaoshengbo.javabean.TopicDetailBean;
import com.sero.chaoshengbo.model.TopicDetailModel;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TopDetailActivity extends BaseActivity {


    @Bind(R.id.title_bar_title)
    TextView titleBarTitle;
    @Bind(R.id.title_bar_menu)
    ImageView titleBarMenu;
    @Bind(R.id.title_bar_layout)
    Toolbar titleBarLayout;
    @Bind(R.id.topicDetail_colltoobar)
    CollapsingToolbarLayout topicDetailColltoobar;
    @Bind(R.id.topicDetail_listview)
    RecyclerView topicDetailListview;
    @Bind(R.id.activity_top_detail)
    CoordinatorLayout activityTopDetail;
    private String topicDetailId;
    private TopicDetailAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_detail);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView() {
        titleBarTitle.setText("专题详情");


        //给页面设置工具栏
        setSupportActionBar(titleBarLayout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//返回键
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        titleBarMenu.setImageResource(R.mipmap.mores);

        //设置工具栏标题
//        topicDetailColltoobar.setTitle("cheeseName");
        topicDetailColltoobar.setTitleEnabled(false);


        topicDetailId = getIntent().getStringExtra("str0");
        topicDetailListview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TopicDetailAdapter();
        topicDetailListview.setAdapter(adapter);
    }


    private void initData() {

        NetUtil.GetApi().TopIcDetailGetData(BaseApi.user_id, topicDetailId, 10, 0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<BaseResponseBean<TopicDetailBean<TopicDetailModel>>>(this) {
                    @Override
                    public void onNext(BaseResponseBean<TopicDetailBean<TopicDetailModel>> topicDetailBeanBaseResponseBean) {
                        super.onNext(topicDetailBeanBaseResponseBean);
                        adapter.setList(topicDetailBeanBaseResponseBean.getData().getList());
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
