package com.example.baiwei.thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Objects;

/**
 * Created by baiwei on 2017/7/13.
 */

public class Register_thread extends Thread {
    private String tuijianren;
    private String username;
    private String password;
    private String realname;
    private String idcard;
    private String phone;

    String s;
    private String url;
    private Handler handler;

    public Register_thread(String tuijianren, String username,
                           String password, String realname,
                           String idcard, String phone, String url,
                           Handler handler) {
        this.tuijianren = tuijianren;
        this.username = username;
        this.password = password;
        this.realname = realname;
        this.idcard = idcard;
        this.phone = phone;
        this.url = url;
        this.handler = handler;
    }

    private void parseJson(String Json) {
        try {
            JSONObject jsonObject = new JSONObject(Json);
            String flag = jsonObject.getString("state");
            s = URLDecoder.decode(jsonObject.getString("des"),"utf-8");
            Message message = handler.obtainMessage();
            Bundle b = new Bundle();
            b.putString("des",s);
            b.putString("f",flag);
            message.setData(b);
            message.sendToTarget();
//            if (Objects.equals(flag, String.valueOf(1))) {
//
//            }

            //String s = jsonObject.getString("des");
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void doGet() {

        url = url + "?referrer_name="+tuijianren+ "&username=" + username +
                "&password="+password + "&realname="+realname+ "&idcard=" +idcard+
                 "&phone="+phone;

        try {
            URL requestUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5000);

            if (connection.getResponseCode() == 200) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuffer stringBuffer = new StringBuffer();
                String str = null;
                while ((str = bufferedReader.readLine()) != null) {
                    stringBuffer.append(str);
                }

                System.out.println(stringBuffer.toString());
                parseJson(stringBuffer.toString());
//                if (parseJson(stringBuffer.toString())) {
//                    handler.sendEmptyMessage(0);
//                } else {
//                    Log.d("error_back", stringBuffer.toString());
//                    Log.d("error_back", connection.getResponseMessage());
//                    handler.sendEmptyMessage(1);
//
//                }

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        doGet();
    }
}
