package com.example.baiwei.thread;

import android.os.Handler;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

/**
 * Created by baiwei on 2017/7/14.
 */

public class Withdraw_func_thread extends Thread {

    private String url;
    private String user_id;
    private String amount;
    private String account_name;
    private String account_number;
    private String account_bank;
    private Handler handler;


    public Withdraw_func_thread(String url,String user_id,String amount,String account_name,
                                String account_number,String account_bank,
                                Handler handler){
        this.url = url;
        this.user_id = user_id;
        this.amount = amount;
        this.account_name = account_name;
        this.account_number = account_number;
        this.account_bank = account_bank;
        this.handler = handler;
    }


    private void doGet() {
        url = url + "?user_id="+user_id+"&amount="+amount+"&account_name="+account_name
        +"&account_number="+account_number+"&account_bank="+account_bank;
        // + URLEncoder.encode(account,"utf-8")+
        try {
            URL httpURL = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) httpURL.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5000);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String str;
            StringBuffer stringBuffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                stringBuffer.append(str);
            }

            Log.d("stringbuffer", stringBuffer.toString());
System.out.print(stringBuffer.toString());
            boolean f = parseJson(stringBuffer.toString());

            if (f){
                handler.sendEmptyMessage(0);
            }else{
                handler.sendEmptyMessage(1);
            }

            System.out.println("result:info----" + stringBuffer.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean parseJson(String Json){
        try {
            JSONObject jsonObject = new JSONObject(Json);
            String f = jsonObject.getString("state");
            if (Objects.equals(f, "1")){
                return true;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void run() {
        doGet();
    }
}
