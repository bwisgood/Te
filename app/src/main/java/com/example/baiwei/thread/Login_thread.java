package com.example.baiwei.thread;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by baiwei on 2017/7/8.
 */

public class Login_thread extends Thread {

    private String url;
    private String account;
    private String password;
    private Handler handler;
    private EditText editText;
    private Context context;

    private Data_user_id1 user_id;

    private int uid;
    private int flag;

    private boolean f;

    public Login_thread(String url, String account, String password, Handler handler, EditText editText, Context context, Data_user_id1 user_id) {
        this.url = url;
        this.account = account;
        this.password = password;
        this.handler = handler;
        this.editText = editText;
        this.context = context;
        this.user_id = user_id;
    }

    private void doGet() {

        url = url + "?username="+account+"&password="+password;
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

            f = parseJson(stringBuffer.toString());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    //editText.setText("123");
                    if (f) {

                        user_id.setUser_id(uid);
                        handler.sendEmptyMessage(0);
                        Log.i("qwe", "qwe");

                    } else {
                        handler.sendEmptyMessage(1);
                        Log.i("123", "123");
                    }
                }
            });

            System.out.println("result:ehqwhje" + stringBuffer.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doPost() {
        //将数据封装成Json
        JSONObject data = new JSONObject();
        try {
            data.put("username", account);
            data.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        try {
            URL httpUrl = new URL(url);

            HttpURLConnection connection = (HttpURLConnection) httpUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Charsert", "UTF-8");
            connection.setReadTimeout(5000);
            connection.setUseCaches(false);
            connection.connect();
            OutputStream outputStream = connection.getOutputStream();


            String content = "username=" + URLEncoder.encode(account, "utf-8") + "&password=" + URLEncoder.encode(password, "utf-8");
            //outputStream.write(content.getBytes());

            String content1 = String.valueOf(data);
            Log.e("data", data.toString());
            outputStream.write(content1.getBytes());
            outputStream.flush();
            //outputStream.close();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();
            String str;

            try {
                while ((str = bufferedReader.readLine()) != null) {
                    stringBuffer.append(str);
                    Log.i("qwe", "qwe");
                }

                f = parseJson(stringBuffer.toString());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        editText.setText("");
                        if (f) {

                            user_id.setUser_id(uid);
                            handler.sendEmptyMessage(0);
                            Log.i("qwe", "qwe");

                        } else {
                            handler.sendEmptyMessage(1);
                        }
                    }
                });
            } finally {
                bufferedReader.close();
            }


//            handler.sendEmptyMessage(0);
//
//            //需要数据传递，用下面方法；
//            Message msg =new Message();
//            msg.obj = parseJson(str);//可以是基本类型，可以是对象，可以是List、map等；
//            handler.sendMessage(msg);


            Log.e("log", "result:" + stringBuffer.toString());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        doGet();
        // doPost();


        //Intent intent = new Intent(context, MainActivity.class);


    }


    private Boolean parseJson(String Json) {

        try {
            JSONObject jsonObject = new JSONObject(Json);


            JSONObject receive_data = jsonObject.getJSONObject("data");
            //receive_data.getInt("user_id");
            uid = receive_data.getInt("user_id");
            int result = 1;//jsonObject.getInt("result");
            if (result == 1) {
                //传回数组时使用JSONArray
//                JSONArray receive_data = jsonObject.getJSONArray("receive_data");
//                for (int i=0;i<receive_data.length();i++){
//                    String name = receive_data.getString(0);
//                }
                flag = jsonObject.getInt("state");
                Log.i("123", String.valueOf(flag));
            } else {
                //Toast.makeText(context,"result!=1",Toast.LENGTH_LONG).show();
                return false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (flag == 0) {

            return false;
        } else {
            return true;
        }
    }

}
