package com.zuilot.chaoshengbo.adapter;


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zuilot.chaoshengbo.R;
import com.zuilot.chaoshengbo.model.CarouselModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/3.
 */

public class CarouselAdapter extends PagerAdapter{

    private  List<CarouselModel> list;
    private Context context;
    private List<View> listimage;
    public CarouselAdapter() {
        listimage=new ArrayList<View>();
    }

    public void setList(List<CarouselModel>  list,Context context) {
        this.list=list;
        this.context=context;
        this.notifyDataSetChanged();
    }

    public void setLiveList(List<CarouselModel>  list,Context context){

    }

    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(listimage.get(position));

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
       View view= LayoutInflater.from(context).inflate(R.layout.imageview,null);
        ImageView image=(ImageView) view.findViewById(R.id.imageview);
        Glide.with(context).load(list.get(position).getImgurl()).into(image);
        listimage.add(view);
        container.addView(view);
        return view;
    }


}
