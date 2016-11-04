package com.sero.chaoshengbo.module;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sero.chaoshengbo.R;
import com.sero.chaoshengbo.activity.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {


    @Bind(R.id.login_back)
    Button loginBack;
    @Bind(R.id.login_weixin)
    ImageView loginWeixin;
    @Bind(R.id.login_weibo)
    ImageView loginWeibo;
    @Bind(R.id.login_checkbox)
    CheckBox loginCheckbox;
    @Bind(R.id.login_xieyi)
    TextView loginXieyi;
    @Bind(R.id.activity_login)
    RelativeLayout activityLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }

    public static void startLogin(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }


    @OnClick({R.id.login_back, R.id.login_weixin, R.id.login_weibo, R.id.login_checkbox, R.id.login_xieyi})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_back:
                this.finish();
                break;
            case R.id.login_weixin:
                break;
            case R.id.login_weibo:
                break;
            case R.id.login_xieyi:
                break;
        }
    }
}
