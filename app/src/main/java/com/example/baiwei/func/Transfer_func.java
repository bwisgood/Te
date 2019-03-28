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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baiwei.pages.CustomTitleBar;
import com.example.baiwei.thread.Transfer_func_thread;
import com.example.baiwei.zkmt.MainActivity;
import com.example.baiwei.zkmt.R;

/**
 * Created by baiwei on 2017/7/13.
 */

public class Transfer_func extends Activity {
    private EditText ed_username;
    private Spinner product;
    private Spinner method;
    private TextView test_user_id;
    private Button btn_active;
    private String product_data;
    private String method_data;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle b = msg.getData();
            if (b.getBoolean("state")){
                new AlertDialog.Builder(Transfer_func.this).setMessage("激活成功！")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent = new Intent(Transfer_func.this,MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }).show();
            }else {
                System.out.print(b.getString("error"));
                showToast(b.getString("error"));
            }
//            switch (msg.what){
//                case 1:
//                    new AlertDialog.Builder(Transfer_func.this).setMessage("激活成功！")
//                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialogInterface, int i) {
//                                    Intent intent = new Intent(Transfer_func.this,MainActivity.class);
//                                    startActivity(intent);
//                                    finish();
//                                }
//                            }).show();
//                    break;
//                case 0:
//                    showToast("失败"+a);
//                    break;
//
//
//            }
        }
    };

    private void showToast(String str) {
        Toast.makeText(Transfer_func.this,str,Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transfer_func);



        //获取user_id
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        int user_id_int =b.getInt("user_id");
        final String user_id = String.valueOf(user_id_int);
        //test_user_id.setText(user_id+"");
        init();

        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.ct_transfer_fun);
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



        btn_active.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                product_data = (String) product.getSelectedItem();
                method_data = (String) method.getSelectedItem();
                new Transfer_func_thread(ed_username.getText().toString(),product_data,method_data,"http://www.scolgo.cn/index.php/home/index/active",handler,user_id).start();
            }
        });

    }

    private void init(){
        product = (Spinner) findViewById(R.id.transfer_fun_spinner_product);
        method = (Spinner) findViewById(R.id.transfer_fun_spinner_payMethod);
        test_user_id = (TextView) findViewById(R.id.transfer_fun_tv_user_id);
        btn_active = (Button) findViewById(R.id.btn_active);
        ed_username = (EditText) findViewById(R.id.ed_username);
    }

}
