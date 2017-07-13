package com.example.baiwei.te;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    public static final MediaType JSON=MediaType.parse("application/json; charset=utf-8");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textView = (TextView) findViewById(R.id.tv);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        JSONObject jsonObject = new JSONObject();
//                        try {
//                            jsonObject.put("user","123");
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//
//                        try {
//                            URL url = new URL("http://scolgo.cn/test.php");
//                            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                            connection.setRequestMethod("POST");
//                            connection.setRequestProperty("Charset","utf-8");
//                            connection.setRequestProperty("Content-type","application/json");
//                            connection.setUseCaches(false);
//                            connection.connect();
//
//                            OutputStream outputStream = connection.getOutputStream();
//                            outputStream.write(jsonObject.toString().getBytes());
//
//                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                            StringBuffer stringBuffer = new StringBuffer();
//                            String str;
//
//                            while ((str=bufferedReader.readLine())!=null){
//                                stringBuffer.append(str);
//                            }
//
//                            Log.e("log",stringBuffer.toString());
//
//                        } catch (MalformedURLException e) {
//                            e.printStackTrace();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }).start();

                textView.setText("123567");
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        JSONObject jsonObject = new JSONObject();
                        //String callstr = OKHttpTool
                        try {
                            jsonObject.put("username","123456");
                            jsonObject.put("password","123456");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        String cal = jsonObject.toString();
                        Log.e(TAG,cal);
                        OkHttpClient okHttpClient = new OkHttpClient();
                        RequestBody requestBody = RequestBody.create(JSON,cal);
                        Request request = new Request.Builder()
                                .url("http://scolgo.cn/test.php")
                                .post(requestBody)
                                .build();
                        try {
                            Response response=okHttpClient.newCall(request).execute();

                            if(response.isSuccessful()){
                                //打印服务端返回结果
                                Log.i(TAG,response.body().string());
                                //System.out.println(response.body().string());
                                throw new IOException("Unexpected code1: " + response);
                            }else{
                                throw new IOException("Unexpected code " + response);
                            }


                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this,"",Toast.LENGTH_LONG).show();
                            }
                        });

                    }
                }).start();



            }
        });
    }
}
