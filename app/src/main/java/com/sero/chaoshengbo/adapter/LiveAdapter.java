package com.sero.chaoshengbo.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sero.chaoshengbo.R;
import com.sero.chaoshengbo.Util.GlideCircleTransform;
import com.sero.chaoshengbo.javabean.LiveActivityBean;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/2.
 */

public class LiveAdapter extends RecyclerView.Adapter {

    private LiveActivityBean bean;
    private Context context;

    public void setList(LiveActivityBean bean) {
        this.bean = bean;
        this.notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        switch (viewType) {
            case 0:
                View view0 = LayoutInflater.from(context).inflate(R.layout.liveitem_recommend_item, parent, false);
                return new ViewHolder0(view0);
            case 1:
            default:
                View view = LayoutInflater.from(context).inflate(R.layout.live_item, parent, false);
                return new ViewHolder(view);
        }

    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return 0;
            default:
                return 1;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ViewHolder0){
            ViewHolder0 holder1 = (ViewHolder0) holder;
            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            holder1.recomendRecyclerview.setLayoutManager(linearLayoutManager);
            LiveRecommendAdapter adapter=new LiveRecommendAdapter(context,bean.getRecommondLives());
            holder1.recomendRecyclerview.setAdapter(adapter);

        }else{
            ViewHolder holder1 = (ViewHolder) holder;

            Glide.with(context).load(bean.getLives().get(position).getUser().getUser_avatar()).transform(new GlideCircleTransform(context)).into(holder1.liveAvatar);
            Glide.with(context).load(bean.getLives().get(position).getUser().getLiveimgurl()).into(holder1.liveImage);
            holder1.liveLocation.setText(bean.getLives().get(position).getUser().getUser_location());
            holder1.liveName.setText(bean.getLives().get(position).getUser().getUser_name());
            holder1.liveNumber.setText(bean.getLives().get(position).getLive_user_count());
            holder1.liveTitle.setText(bean.getLives().get(position).getTitle());
            switch (bean.getLives().get(position).getUser().getUser_sex()) {
                case "男":
                    holder1.liveName.setCompoundDrawables(null, null, context.getResources().getDrawable(R.mipmap.img_boy), null);
                    break;
                case "女":
                    holder1.liveName.setCompoundDrawables(null, null, context.getResources().getDrawable(R.mipmap.img_girl), null);
                    break;
            }

            switch (bean.getLives().get(position).getType()) {
                case "0":
                    holder1.liveType.setText(bean.getLives().get(position).getHorizontal().equals("0") ? "手机直播" : "PC直播");
                    break;
                case "1":
                    holder1.liveType.setText(bean.getLives().get(position).getHorizontal().equals("0") ? "手机回放" : "PC回放");
                    break;
            }
        }


    }

    @Override
    public int getItemCount() {
        return bean == null ? 0 : bean.getLives().size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.live_avatar)
        ImageView liveAvatar;
        @Bind(R.id.live_name)
        TextView liveName;
        @Bind(R.id.live_location)
        TextView liveLocation;
        @Bind(R.id.live_number)
        TextView liveNumber;
        @Bind(R.id.live_type)
        TextView liveType;
        @Bind(R.id.live_relative1)
        RelativeLayout liveRelative1;
        @Bind(R.id.live_title)
        TextView liveTitle;
        @Bind(R.id.live_image)
        ImageView liveImage;
        @Bind(R.id.live_status)
        ImageView liveStatus;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class ViewHolder0 extends RecyclerView.ViewHolder {
        @Bind(R.id.recomend_recyclerview)
        RecyclerView recomendRecyclerview;

        ViewHolder0(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
