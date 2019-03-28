package com.example.baiwei.pages;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.example.baiwei.func.Bonus_ad;
import com.example.baiwei.func.Bonus_cultivate;
import com.example.baiwei.func.Bonus_distributor;
import com.example.baiwei.func.Bonus_performance;
import com.example.baiwei.zkmt.R;

/**
 * Created by baiwei on 2017/7/8.
 */

public class Bonus extends Activity {
    private Button btn_ad;
    private Button btn_performance;
    private Button btn_cultivate;
    private Button btn_distributor;
    private String user_id;

    private void init(){
        btn_ad = (Button) findViewById(R.id.bonus_btn_ad);
        btn_performance = (Button) findViewById(R.id.bonus_btn_performance);
        btn_cultivate = (Button) findViewById(R.id.bonus_btn_cultivate);
        btn_distributor = (Button) findViewById(R.id.bonus_btn_distributor);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bonus);

        //左右上角按钮
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.ct_bonus);
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


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        user_id = bundle.getInt("user_id") +"";
        init();

        //广告津贴
        btn_ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_bonus_ad = new Intent(Bonus.this, Bonus_ad.class);
                intent_bonus_ad.putExtra("user_id",user_id);
                startActivity(intent_bonus_ad);
            }
        });
        //效绩津贴
        btn_performance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_bonus_performance = new Intent(Bonus.this, Bonus_performance.class);
                intent_bonus_performance.putExtra("user_id",user_id);
                startActivity(intent_bonus_performance);
            }
        });

        //培育津贴
        btn_cultivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_bonus_cultivate = new Intent(Bonus.this, Bonus_cultivate.class);
                intent_bonus_cultivate.putExtra("user_id",user_id);
                startActivity(intent_bonus_cultivate);
            }
        });
//        分销奖励
        btn_distributor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_bonus_distributor = new Intent(Bonus.this, Bonus_distributor.class);
                intent_bonus_distributor.putExtra("user_id",user_id);
                startActivity(intent_bonus_distributor);
            }
        });


    }
}
