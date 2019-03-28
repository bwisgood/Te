package com.example.baiwei.zkmt;

import android.app.Activity;
import android.content.Context;
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
import com.example.baiwei.thread.Data_user_id1;
import com.example.baiwei.thread.Login_thread;

import java.io.IOException;
import java.util.Objects;

/**
 * Created by baiwei on 2017/7/6.
 */

public class Login extends Activity {
    private EditText check_number;
    private EditText account;//用户名
    private EditText password;
    private Button btn_login;
    private TextView login_register;
    private ImageView check;
    private boolean check_result;
    private String checkString = null;


    private Data_user_id1 user_id = new Data_user_id1();
    private Context context = Login.this;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                Intent intent = new Intent(Login.this, MainActivity.class);
                intent.putExtra("user_id", user_id.getUser_id());
                new IOException("user_id:" + user_id + "");
                startActivity(intent);
                finish();
            } else if (msg.what == 1) {
                //Toast.makeText(this,"",Toast.LENGTH_LONG).show();
                showToast("用户名或密码错误");
            }
        }
    };

    private Handler handler1 = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            checkString = (String) msg.obj;
            switch (msg.what){
                case 0:
                    check_result = true;
                    break;
                case 1:
                    check_result = false;

            }
        }
    };


    public void showToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    public void init(){
        check_number = (EditText) findViewById(R.id.login_ed_check);
        check  = (ImageView) findViewById(R.id.image_check);
        password = (EditText) findViewById(R.id.login_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        account = (EditText) findViewById(R.id.login_account);
        login_register = (TextView) findViewById(R.id.login_register);

    }

    public void refresh()
    {
        check_number.setText("");
        password.setText("");
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        init();
        //还不是会员？
        login_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this, Register.class);
                startActivity(i);
            }
        });

        //生成验证码
        new Check_thread(check,handler1,checkString).start();

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Check_thread(check,handler1,checkString).start();
            }
        });
        //添加到登陆按钮里的事件
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.e("check",checkString);

                if (Objects.equals(check_number.getText().toString(), checkString)){
                    new Login_thread("http://scolgo.cn/index.php/home/index/user", account.getText().toString(), password.getText().toString(), handler, account, context, user_id).start();
                }else{
                    showToast("验证码错误");
                    refresh();
                    new Check_thread(check,handler1,checkString).start();
                }

                //验证用户名密码
                //scolgo.cn/index.php/home/index/user
//                handler = new Handler(){
//                    @Override
//                    public void handleMessage(Message msg) {
//                        super.handleMessage(msg);
//                        switch (msg.what){
//                            case 0:
//                                //完成主界面更新,拿到数据
//                                Boolean data = (Boolean) msg.obj;
//                                //完成后的操作
//                                if (data) {
//
//                                    Bundle bundle = new Bundle();
//                                    bundle.putString("account", account.getText().toString());
//                                    Intent intent = new Intent(Login.this, MainActivity.class);
//                                    startActivity(intent);
//                                    break;
//                                }else {
//                                    Toast.makeText(Login.this,"账号或用户名错误",Toast.LENGTH_LONG).show();
//                                }
//                            default:
//                                break;
//                        }
//                    }
//                };

//                if (check_result){
//                    new Login_thread("http://scolgo.cn/index.php/home/index/user", account.getText().toString(), password.getText().toString(), handler, account, context, user_id).start();
//                }

                //验证验证码


                //传递数据
                //用户名

            }

            ;
        });


    }

    //生成验证码
    public void checkNumber() {


    }


}
