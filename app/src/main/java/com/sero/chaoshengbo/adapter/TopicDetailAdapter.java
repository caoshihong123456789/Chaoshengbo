package com.sero.chaoshengbo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sero.chaoshengbo.R;
import com.sero.chaoshengbo.model.TopicDetailModel;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/10/24.
 */

public class TopicDetailAdapter extends RecyclerView.Adapter {

    private List<TopicDetailModel> list;

    public void setList(List<TopicDetailModel> list) {
        this.list = list;
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.topicdetail_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder mHolder=(ViewHolder)holder;
        Glide.with(holder.itemView.getContext()).load(list.get(position).getImg()).into(mHolder.topicdetailItemImage);
        mHolder.topicdetailItemTitle.setText(list.get(position).getTitle());
        mHolder.topicdetailItemTime.setText(list.get(position).getReleasedatetime());
        mHolder.topicdetailItemType.setText(list.get(position).getFrom());
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.topicdetail_item_image)
        ImageView topicdetailItemImage;
        @Bind(R.id.topicdetail_item_layout1)
        RelativeLayout topicdetailItemLayout1;
        @Bind(R.id.topicdetail_item_title)
        TextView topicdetailItemTitle;
        @Bind(R.id.topicdetail_item_type)
        TextView topicdetailItemType;
        @Bind(R.id.topicdetail_item_time)
        TextView topicdetailItemTime;
        @Bind(R.id.topicdetail_item_layout)
        RelativeLayout topicdetailItemLayout;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
