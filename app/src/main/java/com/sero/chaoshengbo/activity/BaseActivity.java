package com.sero.chaoshengbo.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by caoshihong on 2016/10/19.
 * 根activity
 */

public class BaseActivity extends AppCompatActivity {
    public static void gotoActivity(Context context, Class className,String...strings){
        Intent intent=new Intent(context,className);
        for(int i=0;i<strings.length;i++){
            intent.putExtra("str"+i,strings[i]);
        }
        context.startActivity(intent);

    }
}
