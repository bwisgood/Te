package com.example.baiwei.zkmt;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baiwei.thread.Check_thread;
import com.example.baiwei.thread.Register_thread;

import java.util.Objects;

/**
 * Created by baiwei on 2017/7/6.
 */

public class Register extends Activity {

    private Button btn_Register_registe;
    private TextView textView_login;
    private ImageView check_image;

    private String checkString;

    private EditText ed_check;
    private EditText tuijianren;
    private EditText username;
    private EditText password;
    private EditText realname;
    private EditText idcard;
    private EditText phone;
    private EditText confirm_password;


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            String f = bundle.getString("f");
            String des = bundle.getString("des");

            if (Objects.equals(f,"1")){
                new AlertDialog.Builder(Register.this).setMessage("注册成功！")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(Register.this, Login.class);
                                startActivity(intent);
                                finish();
                            }
                        }).show();
            }else {
                showToast(des);
            }


//            switch (msg.what) {
//                case 0:
//                    new AlertDialog.Builder(Register.this).setMessage("注册成功！")
//                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialogInterface, int i) {
//                                    Intent intent = new Intent(Register.this, Login.class);
//                                    startActivity(intent);
//                                    finish();
//                                }
//                            }).show();
//                    break;
//                case 1:
//                    showToast("注册失败");
//
//            }
        }
    };

    private Handler handler1 = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            checkString = (String) msg.obj;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rigister);
        init();


//        生成验证码
        new Check_thread(check_image,handler1,checkString).start();




        textView_login = (TextView) findViewById(R.id.textView_login);
        btn_Register_registe = (Button) findViewById(R.id.btn_Register_registe);

        textView_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });

        check_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Check_thread(check_image,handler1,checkString).start();
            }
        });

        btn_Register_registe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if (Objects.equals(checkString, ed_check.getText().toString())){
                    if (Objects.equals(password.getText().toString(), confirm_password.getText().toString())){
                        new Register_thread(tuijianren.getText().toString(),
                                username.getText().toString(),
                                password.getText().toString(),
                                realname.getText().toString(),
                                idcard.getText().toString(),
                                phone.getText().toString(),
                                "http://www.scolgo.cn/index.php/home/index/regist", handler).start();
                    }else{
                        showToast("两次密码输入不一致");
                        password.setText("");
                        confirm_password.setText("");
                        ed_check.setText("");
                    }
                }else{
                    showToast("验证码输入错误");
                    new Check_thread(check_image,handler1,checkString).start();
                    password.setText("");
                    confirm_password.setText("");
                    ed_check.setText("");
                }
            }
        });
    }


    public void showToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    public void init() {
        tuijianren = (EditText) findViewById(R.id.register_ed_tuijianren);
        username = (EditText) findViewById(R.id.register_ed_username);
        password = (EditText) findViewById(R.id.register_ed_password);
        realname = (EditText) findViewById(R.id.register_ed_realname);
        idcard = (EditText) findViewById(R.id.register_ed_idcard);
        phone = (EditText) findViewById(R.id.register_ed_phone);
        confirm_password = (EditText) findViewById(R.id.register_ed_confirm_password);
        ed_check = (EditText) findViewById(R.id.register_ed_check);

        check_image = (ImageView) findViewById(R.id.check_image);
    }
}
