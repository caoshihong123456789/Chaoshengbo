package com.sero.chaoshengbo.Util;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;

/**
 * Created by caoshihong on 2016/10/20.
 */

public class AnimationUtil {

    private static  float scalex[]={1.0F, 0.8F, 1.2F, 1.0F};
    public static void animationScale(final View v, final int time, final int index){
        final AnimationSet animationSet = new AnimationSet(true);
        ScaleAnimation scaleAnimation = new ScaleAnimation(scalex[index],scalex[index+1],
                scalex[index],scalex[index+1],
                Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scaleAnimation.setDuration(time);
        animationSet.addAnimation(scaleAnimation);
        //启动动画
        v.startAnimation(animationSet);
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(index < scalex.length-2){
                    animationScale(v,time,index+1);
                }
            }
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
