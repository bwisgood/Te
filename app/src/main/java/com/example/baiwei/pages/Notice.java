package com.example.baiwei.pages;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.widget.ListView;
import android.widget.Toast;

import com.example.baiwei.adapter.Bonus_adapter;
import com.example.baiwei.adapter.Notice_adapter;
import com.example.baiwei.func.Bonus_ad;
import com.example.baiwei.thread.Notice_thread;
import com.example.baiwei.zkmt.MyAdapter;
import com.example.baiwei.zkmt.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by baiwei on 2017/7/8.
 */

public class Notice extends Activity {

    private ListView lv;
    public ArrayList<Map<String, Object>> list=new ArrayList<Map<String,Object>>();

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    //showToast("123");
                    Notice_adapter list_item=new Notice_adapter(Notice.this,list);
                    lv.setAdapter(list_item);
                    break;
            }
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notice);
        lv = (ListView) findViewById(R.id.lv_notice);
        new Notice_thread("http://scolgo.cn/index.php/home/index/art",handler,list).start();


        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.ct_notice);
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
