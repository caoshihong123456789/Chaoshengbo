package com.zuilot.chaoshengbo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zuilot.chaoshengbo.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WebViewActivity extends BaseActivity {

    @Bind(R.id.title_bar_title)
    TextView titleBarTitle;
    @Bind(R.id.title_bar_layout)
    Toolbar titleBarLayout;
    @Bind(R.id.webView)
    WebView webView;
    @Bind(R.id.activity_web_view)
    LinearLayout activityWebView;

    private static  String OPTION_URL="option_url";
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);
        url=getIntent().getStringExtra(OPTION_URL);
        initView();
    }

    private void initView(){
        webView.loadUrl(url);


    }

    public static void intoWebView(Context context, String url){
        Intent intent=new Intent(context,WebViewActivity.class);
        intent.putExtra(OPTION_URL,url);
        context.startActivity(intent);

    }

}
