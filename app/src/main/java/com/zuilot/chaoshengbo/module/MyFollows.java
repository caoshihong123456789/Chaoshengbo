package com.zuilot.chaoshengbo.module;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zuilot.chaoshengbo.NetUtil.BaseApi;
import com.zuilot.chaoshengbo.NetUtil.BaseSubscriber;
import com.zuilot.chaoshengbo.NetUtil.NetUtil;
import com.zuilot.chaoshengbo.R;
import com.zuilot.chaoshengbo.activity.BaseActivity;
import com.zuilot.chaoshengbo.javabean.BaseResponseBean;
import com.zuilot.chaoshengbo.model.UserInfo;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MyFollows extends BaseActivity {

    @Bind(R.id.title_bar_title)
    TextView titleBarTitle;
    @Bind(R.id.title_bar_layout)
    Toolbar titleBarLayout;
    @Bind(R.id.myfollows_recyclerView)
    RecyclerView myfollowsRecyclerView;
    @Bind(R.id.activity_my_follows)
    LinearLayout activityMyFollows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_follows);
        ButterKnife.bind(this);
    }

    private void initData(){
        NetUtil.GetApi().MyFollowsGetUserInfo(BaseApi.user_id,1,20).subscribe(new BaseSubscriber<BaseResponseBean<UserInfo>>(this) {
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
            public void onNext(BaseResponseBean<UserInfo> userInfoBaseResponseBean) {
                super.onNext(userInfoBaseResponseBean);

            }
        });
    }
}
