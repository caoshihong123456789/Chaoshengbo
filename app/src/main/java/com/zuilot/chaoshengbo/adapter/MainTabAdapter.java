package com.zuilot.chaoshengbo.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v13.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zuilot.chaoshengbo.R;
import com.zuilot.chaoshengbo.Util.AnimationUtil;
import com.zuilot.chaoshengbo.module.HomeActivity;
import com.zuilot.chaoshengbo.module.LiveActivity;
import com.zuilot.chaoshengbo.module.LoginActivity;
import com.zuilot.chaoshengbo.module.TopicActivity;
import com.zuilot.chaoshengbo.module.UserCenterActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/10/20.
 */

public class MainTabAdapter extends FragmentPagerAdapter {

    @Bind(R.id.ivIcon)
    ImageView ivIcon;
    @Bind(R.id.tvTitle)
    TextView tvTitle;
    private Context context;
    private String[] title = {"首页", "现场", "专题", "我的"};
    private int[] image = {R.drawable.tab_nav_icon_home_selector, R.drawable.tab_nav_icon_live_selector
            , R.drawable.tab_nav_icon_topic_selector, R.drawable.tab_nav_icon_person_selector};

    public MainTabAdapter(FragmentManager fm) {
        super(fm);
    }

    public MainTabAdapter setContext(Context context) {
        this.context = context;
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
        final ViewHolder viewholder = new ViewHolder(view);

        viewholder.tvTitle.setText(title[position]);
        viewholder.ivIcon.setImageResource(image[position]);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        AnimationUtil.animationScale(viewholder.ivIcon, 150, 0);
                        if (position == 3 && false) {
                            LoginActivity.startLogin(context);
                            return true;
                        }
                        break;

                }
                return false;
            }
        });
        return view;
    }




    static class ViewHolder {
        @Bind(R.id.ivIcon)
        ImageView ivIcon;
        @Bind(R.id.tvTitle)
        TextView tvTitle;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

    }
}
