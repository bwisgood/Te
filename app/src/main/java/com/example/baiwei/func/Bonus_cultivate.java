package com.example.baiwei.func;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.widget.ListView;
import android.widget.Toast;

import com.example.baiwei.adapter.Bonus_adapter;
import com.example.baiwei.pages.CustomTitleBar;
import com.example.baiwei.thread.Bonus_thread_cultivate;
import com.example.baiwei.thread.Bonus_thread_perfomance;
import com.example.baiwei.zkmt.R;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by baiwei on 2017/7/14.
 */

public class Bonus_cultivate extends Activity{

    private String user_id;
    private ListView lv;
    public ArrayList<Map<String, Object>> list=new ArrayList<Map<String,Object>>();

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    //showToast("123");
                    Bonus_adapter list_item=new Bonus_adapter(Bonus_cultivate.this,list);
                    lv.setAdapter(list_item);
                    break;
            }
        }
    };

    private void showToast(String s){
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.bonus_cultivate);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        user_id = bundle.getString("user_id");
        lv = (ListView) findViewById(R.id.lv2);

        new Bonus_thread_cultivate("http://www.scolgo.cn/index.php/home/index/bonus",user_id,handler,list,Bonus_cultivate.this).start();

        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.ct_bonus_cultivate);
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
