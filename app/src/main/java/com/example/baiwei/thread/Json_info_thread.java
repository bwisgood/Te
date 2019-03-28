package com.example.baiwei.thread;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by baiwei on 2017/7/8.
 */

public class Json_info_thread extends Thread {

    private String url;

    private Context context;

    @Override
    public void run() {
        try {
            URL httpUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) httpUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5000);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();
            String str;

            while ((str = bufferedReader.readLine()) != null) {
                stringBuffer.append(str);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Boolean parseJson(String Json) {
        try {
            JSONObject jsonObject = new JSONObject(Json);

            int result = jsonObject.getInt("result");
            if (result == 1) {
                //传回数组时使用JSONArray
                JSONArray receive_data = jsonObject.getJSONArray("receive_data");
                for (int i = 0; i < receive_data.length(); i++) {
                    String name = receive_data.getString(0);
                }
                return true;

            } else {
                Toast.makeText(context, "result!=1", Toast.LENGTH_LONG).show();
                return false;

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }
}
