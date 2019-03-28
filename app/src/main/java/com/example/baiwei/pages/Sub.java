package com.example.baiwei.pages;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.widget.ListView;
import android.widget.Toast;

import com.example.baiwei.adapter.Bonus_adapter;
import com.example.baiwei.adapter.Sub_adapter;
import com.example.baiwei.func.Bonus_ad;
import com.example.baiwei.thread.Bonus_thread_ad;
import com.example.baiwei.thread.Sub_thread;
import com.example.baiwei.zkmt.R;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by baiwei on 2017/7/14.
 */

public class Sub extends Activity {

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
                    Sub_adapter list_item=new Sub_adapter(Sub.this,list);
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

        setContentView(R.layout.subordinate);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        user_id = String.valueOf(bundle.getInt("user_id"));
        lv = (ListView) findViewById(R.id.lv_sub);

        new Sub_thread("http://www.scolgo.cn/index.php/home/index/zhitui",user_id,handler,list,Sub.this).start();


        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.ct_sub);
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
