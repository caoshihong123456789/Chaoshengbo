package com.zuilot.chaoshengbo.module;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zuilot.chaoshengbo.R;
import com.zuilot.chaoshengbo.activity.BaseActivity;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

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

    private UMShareAPI shareAPI=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        shareAPI=UMShareAPI.get(this);

    }

    public static void startLogin(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }


    @OnClick({R.id.login_back, R.id.login_weixin, R.id.login_weibo, R.id.login_checkbox, R.id.login_xieyi})
    public void onClick(View view) {
        SHARE_MEDIA platform=null;
        switch (view.getId()) {
            case R.id.login_back:
                this.finish();
                break;
            case R.id.login_weixin:
                platform=SHARE_MEDIA.WEIXIN;
                break;
            case R.id.login_weibo:
                platform=SHARE_MEDIA.SINA;
                break;
            case R.id.login_xieyi:
                break;
        }
        shareAPI.getPlatformInfo(LoginActivity.this, platform, umAuthListener);
    }

    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {

            if (data!=null){
                Toast.makeText(getApplicationContext(), data.toString(), Toast.LENGTH_SHORT).show();
                Log.e("error",data.toString());
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText( getApplicationContext(), "get fail"+t.getMessage(), Toast.LENGTH_SHORT).show();
            Log.e("error",t.getMessage());
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText( getApplicationContext(), "get cancel", Toast.LENGTH_SHORT).show();
            Log.e("error","get cancel");
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        shareAPI.onActivityResult(requestCode, resultCode, data);
    }
}
