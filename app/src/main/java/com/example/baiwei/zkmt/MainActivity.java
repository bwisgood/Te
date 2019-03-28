package com.example.baiwei.zkmt;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.baiwei.func.Transfer_func;
import com.example.baiwei.pages.Achievement;
import com.example.baiwei.pages.Bonus;
import com.example.baiwei.pages.Charge;
import com.example.baiwei.pages.Graph;
import com.example.baiwei.pages.Notice;
import com.example.baiwei.pages.Sub;
import com.example.baiwei.pages.Transaction;
import com.example.baiwei.pages.Withdraw;
import com.example.baiwei.thread.MainActivity_thread;

public class MainActivity extends AppCompatActivity {
    //------------------控件定义区
    private TextView tv_info;
    private TextView tv_balance;
    private TextView tv_balance_consumed;
    private TextView tv_balance_registered;
    private TextView tv_balance_qualification;

    private ImageView graph;
    private ImageView transcation;
    private ImageView registe;
    private ImageView sub;
    private ImageView achievement;
    private ImageView withdraw;
    private ImageView bonus;
    private ImageView charge;//充值
    private ImageView notice;
    private ImageView transfer;//激活
    private Button register;//注册

    //---------------------------------------------------」
    private void init() {
        tv_info = (TextView) findViewById(R.id.main_tv_info);
        tv_balance = (TextView) findViewById(R.id.main_tv_balance);
        tv_balance_consumed = (TextView) findViewById(R.id.main_tv_balance_consumed);
        tv_balance_registered = (TextView) findViewById(R.id.main_tv_balance_register);
        tv_balance_qualification = (TextView) findViewById(R.id.main_tv_balance_qualification);

        graph = (ImageView) findViewById(R.id.main_graph);
        transcation = (ImageView) findViewById(R.id.main_transcation);
        registe = (ImageView) findViewById(R.id.main_registe);
        sub = (ImageView) findViewById(R.id.main_sub);
        achievement = (ImageView) findViewById(R.id.main_ach);
        withdraw = (ImageView) findViewById(R.id.main_withdraw);
        bonus = (ImageView) findViewById(R.id.main_bonus);
        charge = (ImageView) findViewById(R.id.main_charge);
        notice = (ImageView) findViewById(R.id.main_notice);
        transfer = (ImageView) findViewById(R.id.main_transfer);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final int user_id = bundle.getInt("user_id");
        //Toast.makeText(MainActivity.this, user_id + "", Toast.LENGTH_LONG).show();
        init();

        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);


            }
        };
        new MainActivity_thread("http://scolgo.cn/index.php/home/index/info", user_id, tv_info, tv_balance, tv_balance_consumed, tv_balance_registered, tv_balance_qualification, handler).start();


        //--------------------各图标点击事件
        //公告
        notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Notice.class);
                startActivity(intent);
            }
        });

        //充值
        charge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Charge.class);
                startActivity(i);
            }
        });
        //这里的激活多了一张Transfer 真正的在Transfer_func
        //transfer 激活
        transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_transfer = new Intent(MainActivity.this, Transfer_func.class);
                intent_transfer.putExtra("user_id", user_id);
                startActivity(intent_transfer);
            }
        });

        //奖金
        bonus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_bonus = new Intent(MainActivity.this, Bonus.class);
                intent_bonus.putExtra("user_id",user_id);
                startActivity(intent_bonus);
            }
        });

        //提现
        withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_withdraw = new Intent(MainActivity.this, Withdraw.class);
                intent_withdraw.putExtra("user_id",user_id);
                startActivity(intent_withdraw);
            }
        });

        //业绩
        achievement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_ach = new Intent(MainActivity.this, Achievement.class);
                intent_ach.putExtra("user_id",user_id);
                startActivity(intent_ach);
            }
        });

        //直推下属
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_sub = new Intent(MainActivity.this, Sub.class);
                intent_sub.putExtra("user_id",user_id);
                startActivity(intent_sub);
            }
        });

        //注册新会员
        registe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_registe = new Intent(MainActivity.this, Register.class);
                //intent_registe.putExtra("user_id",user_id);
                startActivity(intent_registe);
            }
        });
        //交易流水
        transcation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_transcation = new Intent(MainActivity.this, Transaction.class);
                intent_transcation.putExtra("user_id",String.valueOf(user_id));
                startActivity(intent_transcation);
            }
        });

        //安置网络tu
        graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_graph = new Intent(MainActivity.this, Graph.class);
                intent_graph.putExtra("user_id",String.valueOf(user_id));
                startActivity(intent_graph);
            }
        });

    }


}
