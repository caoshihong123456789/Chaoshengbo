package com.sero.chaoshengbo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sero.chaoshengbo.R;
import com.sero.chaoshengbo.activity.BaseActivity;
import com.sero.chaoshengbo.model.TopicModel;
import com.sero.chaoshengbo.module.TopDetailActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by caoshihong on 2016/10/20.
 */

public class TopicAdapter extends RecyclerView.Adapter {


    private List<TopicModel> list;
    private Context context;

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context=parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.topic_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder myholder = (ViewHolder) holder;
        Glide.with(context).load(list.get(position).getImg()).into(myholder.topicItemImg);
        myholder.topicItemTitle.setText(list.get(position).getIntro_short());
        myholder.topicItemDesc.setText(list.get(position).getIntro());
        myholder.tipicLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("跳转到固定界面", "---跳转到固定界面");
                Observable.just(list.get(position).getId()).map(new Func1<Integer, String>() {

                    @Override
                    public String call(Integer integer) {
                        return integer.toString();
                    }
                }).subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        BaseActivity.gotoActivity(context, TopDetailActivity.class, s);
                    }
                });
            }
        });
    }

    public void setData(List<TopicModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }



   /* @OnItemClick(R.id.tipic_layout)
     void onClick(int position) {
        Log.e("跳转到固定界面","---跳转到固定界面");
       *//* Observable.just(topicID).map(new Func1<Integer, String>() {

            @Override
            public String call(Integer integer) {
                return integer.toString();
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                BaseActivity.gotoActivity(context, TopDetailActivity.class,s);
            }
        });*//*

    }*/

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.topic_item_img)
        ImageView topicItemImg;
        @Bind(R.id.topic_item_title)
        TextView topicItemTitle;
        @Bind(R.id.topic_item_desc)
        TextView topicItemDesc;
        @Bind(R.id.tipic_layout)
        LinearLayout tipicLayout;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }
}
