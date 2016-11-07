package com.zuilot.chaoshengbo.module;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zuilot.chaoshengbo.NetUtil.BaseApi;
import com.zuilot.chaoshengbo.NetUtil.BaseSubscriber;
import com.zuilot.chaoshengbo.NetUtil.NetUtil;
import com.zuilot.chaoshengbo.R;
import com.zuilot.chaoshengbo.activity.BaseFragment;
import com.zuilot.chaoshengbo.adapter.CarouselAdapter;
import com.zuilot.chaoshengbo.adapter.LiveAdapter;
import com.zuilot.chaoshengbo.javabean.BaseResponseBean;
import com.zuilot.chaoshengbo.javabean.LiveActivityBean;
import com.zuilot.chaoshengbo.javabean.LiveActivityLivesBean;
import com.zuilot.chaoshengbo.javabean.LiveActivityRecommendedBean;
import com.zuilot.chaoshengbo.model.CarouselModel;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func3;
import rx.schedulers.Schedulers;

/**
 *
 */
public class LiveActivity extends BaseFragment {

    @Bind(R.id.live_recyclerview)
    RecyclerView liveRecyclerview;
    @Bind(R.id.carousel_colltoobar_viewpager)
    ViewPager carouselColltoobarViewpager;
    @Bind(R.id.carousel_colltoobar_title)
    TextView carouselColltoobarTitle;
    @Bind(R.id.carousel_colltoobar_menu)
    ImageView carouselColltoobarMenu;
    @Bind(R.id.carousel_colltoobar_layout)
    Toolbar carouselColltoobarLayout;
    @Bind(R.id.carousel_colltoobar)
    CollapsingToolbarLayout carouselColltoobar;
    private LiveAdapter adapter;
    private CarouselAdapter carcouselAdapter;

    public LiveActivity() {
        // Required empty public constructor
    }

    public static LiveActivity newInstance() {
        LiveActivity fragment = new LiveActivity();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_live, container, false);
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

    private void initView() {
        //给页面设置工具栏
        ((AppCompatActivity) getActivity()).setSupportActionBar(carouselColltoobarLayout);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);//返回键
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        carouselColltoobarTitle.setText("现场");

        //设置工具栏标题
        carouselColltoobar.setTitleEnabled(false);

        carcouselAdapter = new CarouselAdapter();
        carouselColltoobarViewpager.setAdapter(carcouselAdapter);

        liveRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new LiveAdapter();
        liveRecyclerview.setAdapter(adapter);
    }

    private void initData() {
        Observable.zip(NetUtil.GetApi().LiveActivityGetLives(BaseApi.user_id, 20, 1),
                NetUtil.GetApi().LiveActivityGetCarousel(BaseApi.user_id),
                NetUtil.GetApi().LiveActivityGetRecommendLives(BaseApi.user_id),
                new Func3<BaseResponseBean<LiveActivityLivesBean>,
                        BaseResponseBean<List<CarouselModel>>,
                        BaseResponseBean<LiveActivityRecommendedBean>, LiveActivityBean>() {
                    @Override
                    public LiveActivityBean call(BaseResponseBean<LiveActivityLivesBean> listLives,
                                                 BaseResponseBean<List<CarouselModel>> listCarousel,
                                                 BaseResponseBean<LiveActivityRecommendedBean> listRecommend) {
                        Log.e("liveActivity--zip", "--" + listLives + listCarousel + listRecommend);
                        return new LiveActivityBean(listLives.getData().getList(),
                                listCarousel.getData(),
                                listRecommend.getData().getBean());
                    }
                }
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<LiveActivityBean>(this.getActivity()) {
                    @Override
                    public void onStart() {
                        super.onStart();
                    }

                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }

                    @Override
                    public void onNext(LiveActivityBean bean) {
                        super.onNext(bean);
                        adapter.setList(bean);
                        carcouselAdapter.setList(bean.getCarousel(), getActivity());
                        Log.e("输出bean：", "--" + bean.toString());
                    }
                });
    }


}
