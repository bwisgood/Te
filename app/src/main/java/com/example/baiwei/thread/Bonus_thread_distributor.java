package com.example.baiwei.thread;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

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
import java.util.Objects;

/**
 * Created by baiwei on 2017/7/14.
 */

public class Bonus_thread_distributor extends Thread {


    public ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

    private String user_id;
    private Handler handler;
    public JSONObject object;
    private String url;
    private Context context;


    public Bonus_thread_distributor(String url, String user_id, Handler handler, ArrayList<Map<String, Object>> list, Context context) {
        this.url = url;
        this.user_id = user_id;
        this.handler = handler;
        this.list = list;
        this.context = context;

    }

    public void doGet() {

        url = url+"?user_id="+user_id;
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

                parseJson(stringBuffer.toString());
                Log.d("error_back-----",stringBuffer.toString());
                System.out.print("123------"+stringBuffer.toString());
            }



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseJson(String Json) {
        try {
            JSONObject j = new JSONObject(Json);
            JSONArray resultArray = j.getJSONArray("bonus");
            for (int i = 0; i < resultArray.length(); i++) {
                object = resultArray.getJSONObject(i);

                Map<String, Object> map = new HashMap<String, Object>();


                try {
                    //获取到json数据中的activity数组里的内容name
                    String title = object.getString("distributor");
                    //获取到json数据中的activity数组里的内容startTime
                    String add_time = object.getString("add_time");
                    //存入map
                    map.put("title", title);
                    map.put("add_time", add_time);
                    //ArrayList集合
                    if (!Objects.equals(title, "0.00")){

                        list.add(map);
                    }
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
