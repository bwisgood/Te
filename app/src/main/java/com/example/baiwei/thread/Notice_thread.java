package com.example.baiwei.thread;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by baiwei on 2017/7/12.
 */

public class Notice_thread extends Thread {


    public ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();


    private Handler handler;
    public JSONObject object;

    //private Context context;

    private String url;
    //private int user_id;


    public Notice_thread(String url, Handler handler, ArrayList<Map<String, Object>> list) {

        this.url = url;
        this.handler = handler;
        this.list = list;
    }


    private void doGet() {

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

            System.out.println("result:info----" + stringBuffer.toString());

            parseJson(stringBuffer.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseJson(String Json) {
        try {
            JSONObject j = new JSONObject(Json);
            JSONArray resultArray = j.getJSONArray("arts");
            for (int i = 0; i < resultArray.length(); i++) {
                object = resultArray.getJSONObject(i);

                Map<String, Object> map = new HashMap<String, Object>();


                try {
                    //获取到json数据中的activity数组里的内容name
                    String title = object.getString("title");
                    //获取到json数据中的activity数组里的内容startTime
                    String add_time = object.getString("add_time");
                    //存入map
                    map.put("title", title);
                    map.put("add_time", add_time);
                    //ArrayList集合
                    list.add(map);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);


            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        doGet();
    }
}
