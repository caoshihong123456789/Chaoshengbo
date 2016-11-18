package com.zuilot.chaoshengbo.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.RelativeLayout;

import com.zuilot.chaoshengbo.NetUtil.BaseApi;
import com.zuilot.chaoshengbo.NetUtil.BaseSubscriber;
import com.zuilot.chaoshengbo.NetUtil.NetUtil;
import com.zuilot.chaoshengbo.R;
import com.zuilot.chaoshengbo.adapter.MainTabAdapter;
import com.zuilot.chaoshengbo.javabean.BaseResponseBean;
import com.zuilot.chaoshengbo.model.UserInfo;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainTabActivity extends BaseActivity {


    @Bind(android.R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.maintab_tablayout)
    AppBarLayout maintabTablayout;
    @Bind(R.id.maintab_viewpager)
    ViewPager maintabViewpager;
    @Bind(R.id.activity_main_tab)
    RelativeLayout activityMainTab;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initView() {
        MainTabAdapter adapter = new MainTabAdapter(getFragmentManager()).setContext(this);
        maintabViewpager.setAdapter(adapter);
        tabs.setupWithViewPager(maintabViewpager);
        for(int i=0;i<adapter.getCount();i++){
            tabs.getTabAt(i).setCustomView(adapter.getTabView(i));
        }
        maintabViewpager.setOffscreenPageLimit(4);//使viewpager 预先加载四页 是所有页面不被销毁

    }

    private void initData(){
        NetUtil.GetApi().UserCenterActivityLives(BaseApi.user_id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new BaseSubscriber<BaseResponseBean<UserInfo>>(this) {
                    @Override
                    public void onNext(BaseResponseBean<UserInfo> userInfoBaseResponseBean) {
                        super.onNext(userInfoBaseResponseBean);
                      MApplication.userInfo=userInfoBaseResponseBean.getData();
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
