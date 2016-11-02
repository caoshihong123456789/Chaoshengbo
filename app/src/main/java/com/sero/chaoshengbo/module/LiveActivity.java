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
import com.sero.chaoshengbo.adapter.LiveAdapter;
import com.sero.chaoshengbo.javabean.BaseResponseBean;
import com.sero.chaoshengbo.javabean.LiveActivityBean;
import com.sero.chaoshengbo.javabean.LiveActivityLivesBean;
import com.sero.chaoshengbo.javabean.LiveActivityRecommendedBean;
import com.sero.chaoshengbo.model.CarouselModel;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
    @Bind(R.id.live_button)
    Button liveButton;
    private LiveAdapter adapter;

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

    @OnClick(R.id.live_button)
    public void onClick() {
        initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void initView(){
        liveRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter=new LiveAdapter();
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
                        Log.e("liveActivity--zip","--"+listLives+listCarousel+listRecommend);
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
                Log.e("输出bean：" ,"--"+bean.toString());
            }
        });
    }


}
