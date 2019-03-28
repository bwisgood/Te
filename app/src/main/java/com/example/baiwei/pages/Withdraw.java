package com.example.baiwei.pages;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.baiwei.adapter.Bonus_adapter;
import com.example.baiwei.adapter.Withdraw_adapter;
import com.example.baiwei.func.Bonus_ad;
import com.example.baiwei.func.Withdraw_func;
import com.example.baiwei.thread.Bonus_thread_ad;
import com.example.baiwei.thread.Withdraw_thread;
import com.example.baiwei.zkmt.R;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by baiwei on 2017/7/6.
 */

public class Withdraw extends Activity {


    private Button withdraw;

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
                    Withdraw_adapter list_item=new Withdraw_adapter(Withdraw.this,list);
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
        setContentView(R.layout.withdraw);

        System.out.print("withdraw.class");

        withdraw = (Button) findViewById(R.id.btn_withdraw);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        user_id = bundle.getInt("user_id") +"";
        lv = (ListView) findViewById(R.id.lv_withdraw);


        new Withdraw_thread("http://www.scolgo.cn/index.php/home/index/withdrawRecord",user_id,handler,list,Withdraw.this).start();


        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.ct_withdraw);
        customTitleBar.setOnTitleClickListener(new CustomTitleBar.TitleOnClickListener() {
            @Override
            public void onLeftClick() {
                //Toast.makeText(Withdraw.this, "点击了左边的返回按钮", Toast.LENGTH_SHORT).show();
                finish();
            }


            @Override
            public void onRightClick() {
                //Toast.makeText(Withdraw.this, "点击了右边的保存按钮", Toast.LENGTH_SHORT).show();
            }
        });

        withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_withdraw_fun = new Intent(Withdraw.this, Withdraw_func.class);
                intent_withdraw_fun.putExtra("user_id",user_id);
                startActivity(intent_withdraw_fun);
            }
        });


    }
}
