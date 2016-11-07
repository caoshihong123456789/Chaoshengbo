package com.zuilot.chaoshengbo.module;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.zuilot.chaoshengbo.NetUtil.BaseApi;
import com.zuilot.chaoshengbo.NetUtil.BaseSubscriber;
import com.zuilot.chaoshengbo.NetUtil.NetUtil;
import com.zuilot.chaoshengbo.R;
import com.zuilot.chaoshengbo.Util.BitmapBlurUtil;
import com.zuilot.chaoshengbo.Util.GlideCircleTransform;
import com.zuilot.chaoshengbo.activity.BaseFragment;
import com.zuilot.chaoshengbo.javabean.BaseResponseBean;
import com.zuilot.chaoshengbo.model.UserInfo;

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
    @Bind(R.id.user_avator_bg)
    ImageView userAvatorBg;

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

        Glide.with(this.getActivity()).load(info.getUser_avatar())
                .asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
                BitmapBlurUtil.addTask(bitmap, new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        userAvatorBg.setImageResource(R.drawable.shape_mongolia);
                        userAvatorBg.setBackgroundDrawable((Drawable) msg.obj);
                    }
                });
            }
        });
        userName.setText(info.getUser_name());
        userLocation.setText(info.getUser_location());
        userIntro.setText(info.getIntroduce());
        if (info.getUser_sex().equals("男")) {
            userName.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.mipmap.img_boy), null);
        } else {
            userName.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.mipmap.img_girl), null);
        }
        userAttentionText.setText(info.getConcern_count());
        userFollowText.setText(info.getFollower_count());
        userBuzzText.setText(info.getLikes());
        textMyEnergy.setText(info.getEnergy_balance());
        textCalorie.setText(info.getCalorie_balance());

    }
}
