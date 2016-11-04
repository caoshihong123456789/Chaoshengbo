package com.sero.chaoshengbo.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v13.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sero.chaoshengbo.R;
import com.sero.chaoshengbo.Util.AnimationUtil;
import com.sero.chaoshengbo.module.HomeActivity;
import com.sero.chaoshengbo.module.LiveActivity;
import com.sero.chaoshengbo.module.LoginActivity;
import com.sero.chaoshengbo.module.TopicActivity;
import com.sero.chaoshengbo.module.UserCenterActivity;

/**
 * Created by Administrator on 2016/10/20.
 */

public class MainTabAdapter extends FragmentPagerAdapter{

    private Context context;
    private String[] title = {"首页", "现场", "专题", "我的"};
    private int[] image = {R.drawable.tab_nav_icon_home_selector, R.drawable.tab_nav_icon_live_selector
            , R.drawable.tab_nav_icon_topic_selector, R.drawable.tab_nav_icon_person_selector};

    public MainTabAdapter(FragmentManager fm) {
        super(fm);
    }

    public  MainTabAdapter setContext(Context context) {
        this.context=context;
        return this;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return HomeActivity.newInstance("222", "222e");
            case 1:
                return LiveActivity.newInstance();
            case 2:
                return TopicActivity.newInstance();
            case 3:
            default:
                return UserCenterActivity.newInstance();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return null;
    }


    public View getTabView(final int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.maintab_item_layout, null);
        TextView textView = (TextView) view.findViewById(R.id.tvTitle);
        final ImageView imageView = (ImageView) view.findViewById(R.id.ivIcon);
        textView.setText(title[position]);
        imageView.setImageResource(image[position]);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        AnimationUtil.animationScale(imageView,150,0);
                        break;
                    case MotionEvent.ACTION_UP:
                        if(position == 3){
                            LoginActivity.startLogin(context);
                        }
                        break;
                }
                return true;
            }
        });
        return view;
    }


}
