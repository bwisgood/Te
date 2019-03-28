package com.example.baiwei.thread;

import android.app.Application;

/**
 * Created by baiwei on 2017/7/11.
 */

public class Data_app_user_id extends Application {
    private String user_id;

    public String get_user_id() {
        return this.user_id;
    }

    public void set_user_id(String c) {
        this.user_id = c;
    }

    @Override
    public void onCreate() {
        user_id = "";
        super.onCreate();
    }
}
