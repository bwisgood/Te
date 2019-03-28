package com.example.baiwei.thread;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.baiwei.zkmt.Check_Number;

import java.util.Objects;

/**
 * Created by baiwei on 2017/7/14.
 */

public class Check_thread extends Thread {
    private ImageView imageView;
    private String ed_check_number;
    private Bitmap bitmap;
    private Handler handler;
    public Check_thread(ImageView imageView,Handler handler,String ed_check_number){
        this.handler = handler;
        this.imageView = imageView;
        this.ed_check_number = ed_check_number;

    }

    @Override
    public void run() {
        final Check_Number check_number = new Check_Number();
        bitmap = check_number.createBitmap();

        Message msg1 = handler.obtainMessage();
        msg1.obj = check_number.getCode();
        handler.sendMessage(msg1);
        handler.post(new Runnable() {
            @Override
            public void run() {
                imageView.setImageBitmap(bitmap);

            }
        });

    }
}
