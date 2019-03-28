package com.example.baiwei.pages;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baiwei.thread.Achievement_thread;
import com.example.baiwei.zkmt.R;

/**
 * Created by baiwei on 2017/7/7.
 */

public class Achievement extends Activity {

    private String user_id;

    private TextView tv_amount;
    private TextView tv_amount_left;
    private TextView tv_amount_right;
    private Handler handler  = new Handler();

    private void init(){
        tv_amount = (TextView) findViewById(R.id.ach_amount);
        tv_amount_left = (TextView) findViewById(R.id.ach_amount_left);
        tv_amount_right = (TextView) findViewById(R.id.ach_amount_right);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.achievement);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        user_id = bundle.getInt("user_id") +"";
        init();

        new Achievement_thread("http://www.scolgo.cn/index.php/home/index/achievement",user_id,tv_amount,tv_amount_left,tv_amount_right,handler).start();


        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.ct_achievement);
        customTitleBar.setOnTitleClickListener(new CustomTitleBar.TitleOnClickListener() {
            @Override
            public void onLeftClick() {
                //Toast.makeText(Charge.this, "点击了左边的返回按钮", Toast.LENGTH_SHORT).show();
                finish();
            }
            @Override
            public void onRightClick() {
                //Toast.makeText(Charge.this, "点击了右边的保存按钮", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
