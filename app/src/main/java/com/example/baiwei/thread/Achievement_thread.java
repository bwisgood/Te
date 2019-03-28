package com.example.baiwei.thread;

import android.os.Handler;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by baiwei on 2017/7/14.
 */

public class Achievement_thread extends Thread {

    private TextView tv_amount;
    private TextView tv_amount_left;
    private TextView tv_amount_right;

    private String url;
    private String user_id;
    private Handler handler;

    public Achievement_thread(String url, String user_id,TextView tv_amount,
                              TextView tv_amount_left,TextView tv_amount_right,Handler handler){
        this.tv_amount = tv_amount;
        this.tv_amount_left = tv_amount_left;
        this.tv_amount_right = tv_amount_right;

        this.url = url;
        this.user_id = user_id;
        this.handler = handler;
    }

    public void doGet(){
        url = url+"?user_id="+user_id;
        try {
            URL requestUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5000);
            if (connection.getResponseCode()==200){
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuffer sb = new StringBuffer();
                String str = null;
                while ((str=bufferedReader.readLine())!=null){
                    sb.append(str);
                }


                parseJson(sb.toString());

                System.out.println(sb.toString());
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    private void parseJson(String Json){
        try {
            JSONObject jsonObject = new JSONObject(Json);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            JSONObject object = jsonArray.getJSONObject(0);

            final String node_amount = object.getString("node_amount");
            final String node_amount_left = object.getString("node_amount_left");
            final String node_amount_right = object.getString("node_amount_right");

            handler.post(new Runnable() {
                @Override
                public void run() {
                    tv_amount.setText(node_amount);
                    tv_amount_left.setText(node_amount_left);
                    tv_amount_right.setText(node_amount_right);
                }
            });



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        doGet();
    }
}
