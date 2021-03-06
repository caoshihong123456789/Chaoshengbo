package com.zuilot.chaoshengbo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zuilot.chaoshengbo.R;
import com.zuilot.chaoshengbo.Util.GlideCircleTransform;
import com.zuilot.chaoshengbo.javabean.LiveActivityRecommendedBean;
import com.zuilot.chaoshengbo.module.PlaybackActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/3.
 */

public class LiveRecommendAdapter extends RecyclerView.Adapter {


    private Context context;
    private List<LiveActivityRecommendedBean.myUserInfoBean> list;

    public  LiveRecommendAdapter(Context context, List<LiveActivityRecommendedBean.myUserInfoBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setList(Context context, List<LiveActivityRecommendedBean.myUserInfoBean> list) {
        this.context = context;
        this.list = list;
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder=(ViewHolder)holder;
        Glide.with(context).load(list.get(position).getUser_avatar())
                .transform(new GlideCircleTransform(context))
                .into(viewHolder.liveRecommendImg);
        viewHolder.liveRecommendText.setText(list.get(position).getUser_name());
        viewHolder.liveRecommendLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlaybackActivity.intoPlayBack(context,list.get(position));
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.live_recommend_item, null);
        return new ViewHolder(view);
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.live_recommend_img)
        ImageView liveRecommendImg;
        @Bind(R.id.live_recommend_text)
        TextView liveRecommendText;
        @Bind(R.id.live_recommend_layout)
        LinearLayout liveRecommendLayout;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }
}
