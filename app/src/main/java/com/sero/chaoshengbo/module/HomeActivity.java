package com.sero.chaoshengbo.module;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sero.chaoshengbo.NetUtil.BaseApi;
import com.sero.chaoshengbo.NetUtil.BaseSubscriber;
import com.sero.chaoshengbo.NetUtil.NetUtil;
import com.sero.chaoshengbo.R;
import com.sero.chaoshengbo.adapter.HomeAdapter;
import com.sero.chaoshengbo.javabean.BaseResponseBean;
import com.sero.chaoshengbo.javabean.HomeActivityBean;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 首页--tab也第一页
 */
public class HomeActivity extends BaseFragment {

    @Bind(R.id.fragment1_button)
    Button fragment1Button;
    @Bind(R.id.home_recyclerview)
    RecyclerView homeRecyclerview;

    private HomeAdapter myAdapter;

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

    @OnClick(R.id.fragment1_button)
    public void onClick() {
        initData();
    }

    private void initView() {

        homeRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myAdapter=new HomeAdapter();
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

                    }
                });
    }


}
