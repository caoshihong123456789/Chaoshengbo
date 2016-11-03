package com.sero.chaoshengbo.module;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sero.chaoshengbo.NetUtil.BaseApi;
import com.sero.chaoshengbo.NetUtil.BaseSubscriber;
import com.sero.chaoshengbo.NetUtil.NetUtil;
import com.sero.chaoshengbo.R;
import com.sero.chaoshengbo.Util.GlideCircleTransform;
import com.sero.chaoshengbo.javabean.BaseResponseBean;
import com.sero.chaoshengbo.model.UserInfo;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by caoshihong on 2016/11/3.
 * <p>
 * 我的 页面
 */

public class UserCenterActivity extends BaseFragment {


    @Bind(R.id.user_avator)
    ImageView userAvator;
    @Bind(R.id.user_name)
    TextView userName;
    @Bind(R.id.user_location)
    TextView userLocation;
    @Bind(R.id.user_intro)
    TextView userIntro;
    @Bind(R.id.user_follow)
    TextView userFollow;
    @Bind(R.id.user_attention_layout)
    LinearLayout userAttentionLayout;
    @Bind(R.id.user_contribution)
    LinearLayout userContribution;
    @Bind(R.id.user_myVideo)
    LinearLayout userMyVideo;
    @Bind(R.id.user_myNews)
    LinearLayout userMyNews;
    @Bind(R.id.text_myEnergy)
    TextView textMyEnergy;
    @Bind(R.id.user_myEnergy)
    LinearLayout userMyEnergy;
    @Bind(R.id.text_calorie)
    TextView textCalorie;
    @Bind(R.id.user_mycalorie)
    LinearLayout userMycalorie;
    @Bind(R.id.user_myBill)
    LinearLayout userMyBill;
    @Bind(R.id.user_myQuestion)
    LinearLayout userMyQuestion;
    @Bind(R.id.user_caishen)
    LinearLayout userCaishen;
    @Bind(R.id.user_setting)
    LinearLayout userSetting;
    @Bind(R.id.user_attention_text)
    TextView userAttentionText;
    @Bind(R.id.user_follow_text)
    TextView userFollowText;
    @Bind(R.id.user_buzz_text)
    TextView userBuzzText;

    public static Fragment newInstance() {
        UserCenterActivity fragment = new UserCenterActivity();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_usercenter, null);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void initData() {
        NetUtil.GetApi().UserCenterActivityLives(BaseApi.user_id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new BaseSubscriber<BaseResponseBean<UserInfo>>(getActivity()) {
                    @Override
                    public void onNext(BaseResponseBean<UserInfo> userInfoBaseResponseBean) {
                        super.onNext(userInfoBaseResponseBean);
                        initView(userInfoBaseResponseBean.getData());
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

    private void initView(UserInfo info) {
        Glide.with(this.getActivity()).load(info.getUser_avatar())
                .transform(new GlideCircleTransform(getActivity()))
                .into(userAvator);
        userName.setText(info.getUser_name());
        userLocation.setText(info.getUser_location());
        userIntro.setText(info.getIntroduce());
        if (info.getUser_sex().equals("男")) {
            userName.setCompoundDrawables(null, null, getResources().getDrawable(R.mipmap.img_boy), null);
        } else {
            userName.setCompoundDrawables(null, null, getResources().getDrawable(R.mipmap.img_girl), null);
        }
        userAttentionText.setText(info.getConcern_count());
        userFollowText.setText(info.getFollower_count());
        userBuzzText.setText(info.getLikes());

    }
}
