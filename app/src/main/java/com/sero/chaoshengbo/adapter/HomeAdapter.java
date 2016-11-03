package com.sero.chaoshengbo.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sero.chaoshengbo.R;
import com.sero.chaoshengbo.Util.GlideCircleTransform;
import com.sero.chaoshengbo.javabean.HomeActivityBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/10/31.
 */

public class HomeAdapter extends RecyclerView.Adapter {


    private HomeActivityBean bean;

    public void setData(HomeActivityBean bean) {
        this.bean = bean;
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position < bean.getTop().size()) {
            return 0;
        } else if (position < bean.getTop().size() + bean.getFeature().size()) {
            return 1;
        } else if (position < bean.getTop().size() + bean.getFeature().size() + 1) {
            return 2;
        } else if (position < bean.getTop().size() + bean.getFeature().size() + 1 + 1 +bean.getInfo().size()) {
            return 3;
        }
        return -1;
    }

    private int getCountWithType(int type,int position){
        switch (type){
            case 0:
            default:
                return position ;
            case 1:
                return position - bean.getTop().size();
            case 2:
                return position - (bean.getTop().size() + bean.getFeature().size());
            case 3:
                Log.e("输出当前position",(position - (bean.getTop().size() + bean.getFeature().size() + 1 ))+"--"+position);
                return position - (bean.getTop().size() + bean.getFeature().size() + 1 );
        }
    }

    /**
     * 根据position 得出当前view的类型
     *
     * @return 0-置顶 1-专题 2-直播 3-咨询
     */
    @Override
    public int getItemCount() {
        if(bean == null){
            return  0;
        }else{
            if (bean.getLives()==null || bean.getLives().size() == 0) {
                return getCount(bean.getTop()) + getCount(bean.getFeature()) + getCount(bean.getInfo());
            } else {
                return getCount(bean.getTop()) + getCount(bean.getFeature()) + getCount(bean.getInfo()) + 1;
            }
        }
    }

    int getCount(List<? extends Object> list){
        return list==null?0:list.size();
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case 0:
                mViewHolder holder0 = (mViewHolder) holder;
                Glide.with(holder.itemView.getContext()).load(bean.getTop().get(position).getImg()).into(holder0.homeImage);
                holder0.homeTitle.setText(bean.getTop().get(position).getTitle());
                holder0.homeTitle2.setText(bean.getTop().get(position).getTitle2());
                holder0.homeFeature.setImageResource(R.mipmap.item_top);
                holder0.homeFeature.setVisibility(View.VISIBLE);
                holder0.readCount.setVisibility(View.GONE);
                break;
            case 1:
                mViewHolder holder1 = (mViewHolder) holder;
                Glide.with(holder.itemView.getContext()).load(bean.getFeature().get(getCountWithType(1,position)).getImg()).into(holder1.homeImage);
                holder1.homeTitle.setText(bean.getFeature().get(getCountWithType(1,position)).getName());
                holder1.homeTitle2.setText(bean.getFeature().get(getCountWithType(1,position)).getIntro_short());
                holder1.homeFeature.setImageResource(R.mipmap.item_topic);
                holder1.homeFeature.setVisibility(View.VISIBLE);
                holder1.readCount.setVisibility(View.GONE);
                break;
            case 3:
                mViewHolder holder3 = (mViewHolder) holder;
                Glide.with(holder.itemView.getContext()).load(bean.getInfo().get(getCountWithType(3,position)).getImg()).into(holder3.homeImage);
                holder3.homeTitle.setText(bean.getInfo().get(getCountWithType(3,position)).getTitle());
                holder3.homeTitle2.setText(bean.getInfo().get(getCountWithType(3,position)).getTitle2());
                holder3.readCount.setText(bean.getInfo().get(getCountWithType(3,position)).getClick() + "阅读");
                holder3.homeFeature.setVisibility(View.GONE);
                holder3.readCount.setVisibility(View.VISIBLE);
                break;
            default:

            case 2:
                mViewHolder1 holder2 = (mViewHolder1) holder;
                Glide.with(holder.itemView.getContext()).load(bean.getLives().get(0).getUser().getLiveimgurl()).into(holder2.imageLiveShow1);
                Glide.with(holder.itemView.getContext()).load(bean.getLives().get(0).getUser().getUser_avatar()).
                        transform(new GlideCircleTransform(holder.itemView.getContext())).into(holder2.live_show_portrait1);
                holder2.live_info1.setText(bean.getLives().get(0).getUser().getUser_name());
                if(bean.getLives().get(0).getType().equals("1")){
                    holder2.live_state_btn1.setText("直播");
                }else{
                    holder2.live_state_btn1.setText("");
                }

                Glide.with(holder.itemView.getContext()).load(bean.getLives().get(1).getUser().getLiveimgurl()).into(holder2.imageLiveShow2);
                Glide.with(holder.itemView.getContext()).load(bean.getLives().get(1).getUser().getUser_avatar())
                        .transform(new GlideCircleTransform(holder.itemView.getContext())).into(holder2.live_show_portrait2);
                holder2.live_info2.setText(bean.getLives().get(1).getUser().getUser_name());
                if(bean.getLives().get(0).getType().equals("1")){
                    holder2.live_state_btn2.setText("直播");
                }else{
                    holder2.live_state_btn2.setText("");
                }
                break;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
            case 1:
            case 3:
            default:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item, null);
                return new mViewHolder(view);
            case 2:
                View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_live, null);
                mViewHolder1 holder1=new mViewHolder1(view1);
                holder1.live_show_portrait1=(ImageView)holder1.liveShow1.findViewById(R.id.live_show_portrait);
                holder1.live_state_btn1=(TextView)holder1.liveShow1.findViewById(R.id.live_state_btn);
                holder1.live_info1=(TextView)holder1.liveShow1.findViewById(R.id.live_info);

                holder1.live_show_portrait2=(ImageView)holder1.liveShow2.findViewById(R.id.live_show_portrait);
                holder1.live_state_btn2=(TextView)holder1.liveShow2.findViewById(R.id.live_state_btn);
                holder1.live_info2=(TextView)holder1.liveShow2.findViewById(R.id.live_info);
                return holder1;
        }

    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    static class mViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.home_image)
        ImageView homeImage;
        @Bind(R.id.home_title)
        TextView homeTitle;
        @Bind(R.id.home_title_2)
        TextView homeTitle2;
        @Bind(R.id.home_read_count)
        TextView readCount;
        @Bind(R.id.home_feature)
        ImageView homeFeature;

        mViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class mViewHolder1 extends RecyclerView.ViewHolder {
        @Bind(R.id.more)
        TextView more;
        @Bind(R.id.image_live_show_1)
        ImageView imageLiveShow1;
        @Bind(R.id.live_show_1)
        RelativeLayout liveShow1;

        ImageView live_show_portrait1;
        TextView live_state_btn1,live_info1;

        @Bind(R.id.image_live_show_2)
        ImageView imageLiveShow2;
        @Bind(R.id.live_show_2)
        RelativeLayout liveShow2;

        ImageView live_show_portrait2;
        TextView live_state_btn2,live_info2;

        mViewHolder1(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
