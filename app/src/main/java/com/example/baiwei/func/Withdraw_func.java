package com.example.baiwei.func;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.baiwei.pages.CustomTitleBar;
import com.example.baiwei.thread.Withdraw_func_thread;
import com.example.baiwei.zkmt.R;

/**
 * Created by baiwei on 2017/7/13.
 */

public class Withdraw_func extends Activity {

    String user_id;


    private EditText amount;
    private EditText account_number;
    private EditText account_bank;
    private EditText account_name;

    private Button btn_withdraw_apply;


    private Handler handler  = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    new AlertDialog.Builder(Withdraw_func.this).setMessage("申请成功 请耐心等待！")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
//                                    Intent intent = new Intent(Withdraw_func.this, MainActivity.class);
//                                    startActivity(intent);
                                    finish();
                                }
                            }).show();
                    break;
                case 1:
                    showToast("申请失败 请联系公司客服");
            }
        }
    };

    public void showToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.withdraw_func);
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.ct_withdraw_func);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        user_id = bundle.getString("user_id");
        init();

        btn_withdraw_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Withdraw_func_thread("http://www.scolgo.cn/index.php/home/index/withdraw",user_id,amount.getText().toString(),account_name.getText().toString(),account_number.getText().toString(),account_bank.getText().toString(),handler).start();
            }
        });




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

    private void init(){
        amount = (EditText) findViewById(R.id.withdraw_ed_amount);
        account_name = (EditText) findViewById(R.id.withdraw_ed_account_name);
        account_bank = (EditText) findViewById(R.id.withdraw_ed_account_bank);
        account_number = (EditText) findViewById(R.id.withdraw_ed_account_number);

        btn_withdraw_apply = (Button) findViewById(R.id.btn_withdraw_apply);
    }
}
