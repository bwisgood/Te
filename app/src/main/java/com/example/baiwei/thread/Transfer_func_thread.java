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
import java.util.Objects;

/**
 * Created by baiwei on 2017/7/13.
 */

public class Transfer_func_thread extends Thread {

    private String username;
    private String level;
    private String method;
    //private String password;

    private String user_id;
    private String url;
    private Handler handler;


    public Transfer_func_thread(String username, String level,
                                String method, String url, Handler handler,String user_id) {

        this.user_id = user_id;
        this.username = "zt85124739";
        this.level = level;
        this.method = method;
        this.url = url;
        this.handler = handler;
    }

    private void doGet() {
        url = url + "?username=" + username + "&product=" + level + "&method=" + method+"&user_id="+user_id ;
        try {
            URL requestUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5000);

            if (connection.getResponseCode() == 200) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuffer stringBuffer = new StringBuffer();
                String str;
                while ((str = bufferedReader.readLine()) != null) {
                    stringBuffer.append(str);
                }

                System.out.println("123"+stringBuffer.toString());
                parseJson(stringBuffer.toString());

            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void parseJson(String Json) {
        try {
            JSONObject jsonObject = new JSONObject(Json);
            String des = URLDecoder.decode(jsonObject.getString("des"), "utf-8");
            boolean f = false;
            if (Objects.equals(jsonObject.getString("state"), String.valueOf(1))) {
                f = true;

            }else {
                //handler.sendMessage(msg);

                f = false;
            }
            //Message msg = handler.obtainMessage();
            Message message = handler.obtainMessage();
            Bundle b = new Bundle();
            b.putString("error",des);
            b.putBoolean("state",f);
            message.setData(b);
            message.sendToTarget();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        doGet();
    }
}
