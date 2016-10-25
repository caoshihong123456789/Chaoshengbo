package com.sero.chaoshengbo.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.RelativeLayout;
import com.sero.chaoshengbo.R;
import com.sero.chaoshengbo.adapter.MainTabAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;

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
        initView();

    }

    private void initView() {
        MainTabAdapter adapter = new MainTabAdapter(getFragmentManager()).setContext(this);
        maintabViewpager.setAdapter(adapter);
        tabs.setupWithViewPager(maintabViewpager);
        for(int i=0;i<adapter.getCount();i++){
            tabs.getTabAt(i).setCustomView(adapter.getTabView(i));
        }
    }

}
