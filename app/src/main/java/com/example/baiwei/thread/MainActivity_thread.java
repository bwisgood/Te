package com.example.baiwei.thread;

import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by baiwei on 2017/7/11.
 */

public class MainActivity_thread extends Thread {

    private TextView textView_info;
    private TextView jifen1;
    private TextView jifen2;
    private TextView jifen3;
    private TextView jifen4;

    private String s1_info;
    private String s2_info;
    private String s3_info;
    private String s_jifen1 = "0";
    private String s_jifen2 = "0";
    private String s_jifen3 = "0";
    private String s_jifen4 = "0";


    private int user_id;
    private String url;
    private Handler handler;


    public MainActivity_thread(String url, int user_id,
                               TextView textView_info, TextView jifen1, TextView jifen2, TextView jifen3, TextView jifen4,
                               Handler handler) {
        this.user_id = user_id;
        this.url = url;
        this.textView_info = textView_info;
        this.jifen1 = jifen1;
        this.jifen2 = jifen2;
        this.jifen3 = jifen3;
        this.jifen4 = jifen4;
        this.handler = handler;

    }


    private void doGet() {
        url = url + "?user_id=" + user_id;
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

            //f  = parseJson(stringBuffer.toString());

            Log.d("stringbuffer", stringBuffer.toString());
            //String re_data = new String(stringBuffer.toString(),"utf-8");
            parseJson(stringBuffer.toString());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    textView_info.setText(s1_info + "\n" + s2_info + "\n" + s3_info);
                    Log.d("s_jifen", s_jifen1);
                    jifen1.setText(s_jifen1 + "\n中肽积分");
                    jifen2.setText(s_jifen2 + "\n注册积分");
                    jifen3.setText(s_jifen3 + "\n消费积分");
                    jifen4.setText(s_jifen4 + "\n注册资格");
                }
            });
            System.out.println("result:info----" + stringBuffer.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void parseJson(String Json) {
        try {
            JSONObject jsonObject = new JSONObject(Json);
            s1_info = URLDecoder.decode(jsonObject.getString("level"), "utf-8");
            if (s1_info==null){
                s1_info = "未激活";
            }
            s2_info = URLDecoder.decode(jsonObject.getString("realname"), "utf-8");
            s3_info = jsonObject.getString("invest_amount");
            s_jifen1 = jsonObject.getString("balance");
            s_jifen2 = jsonObject.getString("balance_registered");
            s_jifen3 = jsonObject.getString("balance_consumed");
            s_jifen4 = jsonObject.getString("register_qualification");

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
