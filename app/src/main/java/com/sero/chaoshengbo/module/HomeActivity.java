package com.sero.chaoshengbo.module;

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

import com.sero.chaoshengbo.NetUtil.BaseApi;
import com.sero.chaoshengbo.NetUtil.BaseSubscriber;
import com.sero.chaoshengbo.NetUtil.NetUtil;
import com.sero.chaoshengbo.R;
import com.sero.chaoshengbo.adapter.CarouselAdapter;
import com.sero.chaoshengbo.adapter.HomeAdapter;
import com.sero.chaoshengbo.javabean.BaseResponseBean;
import com.sero.chaoshengbo.javabean.HomeActivityBean;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 首页--tab也第一页
 */
public class HomeActivity extends BaseFragment {

    @Bind(R.id.home_recyclerview)
    RecyclerView homeRecyclerview;
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

    private HomeAdapter myAdapter;
    private  CarouselAdapter carcouselAdapter;

    public static HomeActivity newInstance(String param1, String param2) {
        HomeActivity fragment = new HomeActivity();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_activity, container, false);
        ButterKnife.bind(this, view);
        initView();
        initData();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    private void initView() {
        //给页面设置工具栏
        ((AppCompatActivity)getActivity()).setSupportActionBar(carouselColltoobarLayout);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);//返回键
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        carouselColltoobarTitle.setText("首页");

        //设置工具栏标题
//        carouselColltoobar.setTitle("首页");
        carouselColltoobar.setTitleEnabled(false);

        carcouselAdapter =new CarouselAdapter();
        carouselColltoobarViewpager.setAdapter(carcouselAdapter);


        homeRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myAdapter = new HomeAdapter();
        homeRecyclerview.setAdapter(myAdapter);
    }

    /**
     * 数据初始化
     */
    private void initData() {
        NetUtil.GetApi().HomeActivityGetData(BaseApi.user_id, 20, 1).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<BaseResponseBean<HomeActivityBean>>(getActivity()) {
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
                    public void onNext(BaseResponseBean<HomeActivityBean> homeActivityBeanBaseResponseBean) {
                        super.onNext(homeActivityBeanBaseResponseBean);
                        Log.e("输出首页数据", "--" + homeActivityBeanBaseResponseBean.getData().toString());
                        myAdapter.setData(homeActivityBeanBaseResponseBean.getData());
                        carcouselAdapter.setList(homeActivityBeanBaseResponseBean.getData().getCarousel(),getActivity());

                    }
                });
    }


}
