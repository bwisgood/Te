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
import com.example.baiwei.thread.Bonus_thread_ad;
import com.example.baiwei.zkmt.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by baiwei on 2017/7/13.
 */

public class Bonus_ad extends Activity {


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
                    Bonus_adapter list_item=new Bonus_adapter(Bonus_ad.this,list);
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

        setContentView(R.layout.bonus_ad);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        user_id = bundle.getString("user_id");
        lv = (ListView) findViewById(R.id.lv);

        new Bonus_thread_ad("http://www.scolgo.cn/index.php/home/index/bonus",user_id,handler,list,Bonus_ad.this).start();

        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.ct_bonus_ad);
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
