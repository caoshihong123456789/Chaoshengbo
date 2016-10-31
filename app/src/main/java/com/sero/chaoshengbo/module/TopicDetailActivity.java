package com.sero.chaoshengbo.module;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.sero.chaoshengbo.NetUtil.BaseApi;
import com.sero.chaoshengbo.NetUtil.BaseSubscriber;
import com.sero.chaoshengbo.NetUtil.NetUtil;
import com.sero.chaoshengbo.R;
import com.sero.chaoshengbo.Util.BitmapBlurUtil;
import com.sero.chaoshengbo.activity.BaseActivity;
import com.sero.chaoshengbo.adapter.TopicDetailAdapter;
import com.sero.chaoshengbo.javabean.BaseResponseBean;
import com.sero.chaoshengbo.javabean.FeatureDetailBean;
import com.sero.chaoshengbo.model.TopicDetailModel;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TopicDetailActivity extends BaseActivity {


    @Bind(R.id.topicDetail_colltoobar_bg)
    ImageView topicDetailColltoobarBg;
    @Bind(R.id.topicDetail_colltoobar_title)
    TextView topicDetailColltoobarTitle;
    @Bind(R.id.topicDetail_colltoobar_menu)
    ImageView topicDetailColltoobarMenu;
    @Bind(R.id.topicDetail_colltoobar_layout)
    Toolbar topicDetailColltoobarLayout;
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


        //给页面设置工具栏
        setSupportActionBar(topicDetailColltoobarLayout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//返回键
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        topicDetailColltoobarMenu.setImageResource(R.mipmap.mores);

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
                .subscribe(new BaseSubscriber<BaseResponseBean<FeatureDetailBean<TopicDetailModel>>>(this) {
                    @Override
                    public void onNext(BaseResponseBean<FeatureDetailBean<TopicDetailModel>> topicDetailBeanBaseResponseBean) {
                        super.onNext(topicDetailBeanBaseResponseBean);
                        adapter.setList(topicDetailBeanBaseResponseBean.getData().getList());
                        topicDetailColltoobarTitle.setText(topicDetailBeanBaseResponseBean.getData().getName());

                        Glide.with(TopicDetailActivity.this).load(topicDetailBeanBaseResponseBean.getData().getImg())
                                .asBitmap().into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
                                BitmapBlurUtil.addTask(bitmap, new Handler() {
                                    @Override
                                    public void handleMessage(Message msg) {
                                        super.handleMessage(msg);
                                        topicDetailColltoobarBg.setImageResource(R.drawable.shape_mongolia);
                                        topicDetailColltoobarBg.setBackgroundDrawable((Drawable) msg.obj);
                                    }
                                });

                            }
                        });
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
