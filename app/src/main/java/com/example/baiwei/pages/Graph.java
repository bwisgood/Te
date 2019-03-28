package com.example.baiwei.pages;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.baiwei.zkmt.R;

import org.json.JSONArray;
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
 * Created by baiwei on 2017/7/8.
 */

public class Graph extends Activity implements View.OnClickListener{

    private TableLayout table1;
    private TableLayout table2;
    private TableLayout table3;
    private TableLayout table4;
    private TableLayout table5;
    private TableLayout table6;
    private TableLayout table7;

    private TableLayout re_table1;
    private TableLayout re_table2;
    private TableLayout re_table3;
    private TableLayout re_table4;
    private TableLayout re_table5;
    private TableLayout re_table6;
    private TableLayout re_table7;

    private TextView username1;
    private TextView username2;
    private TextView username3;
    private TextView username4;
    private TextView username5;
    private TextView username6;
    private TextView username7;

    private TextView realname1;
    private TextView realname2;
    private TextView realname3;
    private TextView realname4;
    private TextView realname5;
    private TextView realname6;
    private TextView realname7;

    private TextView level1;
    private TextView level2;
    private TextView level3;
    private TextView level4;
    private TextView level5;
    private TextView level6;
    private TextView level7;

    private TextView node_amount1;
    private TextView node_amount2;
    private TextView node_amount3;
    private TextView node_amount4;
    private TextView node_amount5;
    private TextView node_amount6;
    private TextView node_amount7;

    private TextView node_amount_left1;
    private TextView node_amount_left2;
    private TextView node_amount_left3;
    private TextView node_amount_left4;
    private TextView node_amount_left5;
    private TextView node_amount_left6;
    private TextView node_amount_left7;

    private TextView node_amount_right1;
    private TextView node_amount_right2;
    private TextView node_amount_right3;
    private TextView node_amount_right4;
    private TextView node_amount_right5;
    private TextView node_amount_right6;
    private TextView node_amount_right7;


    private TextView node_id1;
    private TextView node_id2;
    private TextView node_id3;
    private TextView node_id4;
    private TextView node_id5;
    private TextView node_id6;
    private TextView node_id7;

    private TextView node_section1;
    private TextView node_section2;
    private TextView node_section3;
    private TextView node_section4;
    private TextView node_section5;
    private TextView node_section6;
    private TextView node_section7;

    private TextView user_id1;
    private TextView user_id2;
    private TextView user_id3;
    private TextView user_id4;
    private TextView user_id5;
    private TextView user_id6;
    private TextView user_id7;
    
    
    private String username_data1="";
    private String username_data2="";
    private String username_data3="";
    private String username_data4="";
    private String username_data5="";
    private String username_data6="";
    private String username_data7="";

    private String realname_data1="";
    private String realname_data2="";
    private String realname_data3="";
    private String realname_data4="";
    private String realname_data5="";
    private String realname_data6="";
    private String realname_data7="";

    private String level_data1="";
    private String level_data2="";
    private String level_data3="";
    private String level_data4="";
    private String level_data5="";
    private String level_data6="";
    private String level_data7="";

    private String node_amount_data1="";
    private String node_amount_data2="";
    private String node_amount_data3="";
    private String node_amount_data4="";
    private String node_amount_data5="";
    private String node_amount_data6="";
    private String node_amount_data7="";

    private String node_amount_data_left1="";
    private String node_amount_data_left2="";
    private String node_amount_data_left3="";
    private String node_amount_data_left4="";
    private String node_amount_data_left5="";
    private String node_amount_data_left6="";
    private String node_amount_data_left7="";

    private String node_amount_data_right1="";
    private String node_amount_data_right2="";
    private String node_amount_data_right3="";
    private String node_amount_data_right4="";
    private String node_amount_data_right5="";
    private String node_amount_data_right6="";
    private String node_amount_data_right7="";

    private String node_id_data1="";
    private String node_id_data2="";
    private String node_id_data3="";
    private String node_id_data4="";
    private String node_id_data5="";
    private String node_id_data6="";
    private String node_id_data7="";

    private String node_section_data1="";
    private String node_section_data2="";
    private String node_section_data3="";
    private String node_section_data4="";
    private String node_section_data5="";
    private String node_section_data6="";
    private String node_section_data7="";


    private String user_id_data1="";
    private String user_id_data2="";
    private String user_id_data3="";
    private String user_id_data4="";
    private String user_id_data5="";
    private String user_id_data6="";
    private String user_id_data7="";
    

    


    
    
    private Handler handler = new Handler();
    public String url="http://www.scolgo.cn/index.php/home/index/tu";
    
    private void init(){
        table1 = (TableLayout) findViewById(R.id.table1);
        table2 = (TableLayout) findViewById(R.id.table2);
        table3 = (TableLayout) findViewById(R.id.table3);
        table4 = (TableLayout) findViewById(R.id.table4);
        table5 = (TableLayout) findViewById(R.id.table5);
        table6 = (TableLayout) findViewById(R.id.table6);
        table7 = (TableLayout) findViewById(R.id.table7);

        re_table1 = (TableLayout) findViewById(R.id.re_table1);
        re_table2 = (TableLayout) findViewById(R.id.re_table2);
        re_table3 = (TableLayout) findViewById(R.id.re_table3);
        re_table4 = (TableLayout) findViewById(R.id.re_table4);
        re_table5 = (TableLayout) findViewById(R.id.re_table5);
        re_table6 = (TableLayout) findViewById(R.id.re_table6);
        re_table7 = (TableLayout) findViewById(R.id.re_table7);

        username1 = (TextView) findViewById(R.id.tv_username1);
        username2 = (TextView) findViewById(R.id.tv_username2);
        username3 = (TextView) findViewById(R.id.tv_username3);
        username4 = (TextView) findViewById(R.id.tv_username4);
        username5 = (TextView) findViewById(R.id.tv_username5);
        username6 = (TextView) findViewById(R.id.tv_username6);
        username7 = (TextView) findViewById(R.id.tv_username7);

        realname1 = (TextView) findViewById(R.id.tv_realname1);
        realname2 = (TextView) findViewById(R.id.tv_realname2);
        realname3 = (TextView) findViewById(R.id.tv_realname3);
        realname4 = (TextView) findViewById(R.id.tv_realname4);
        realname5 = (TextView) findViewById(R.id.tv_realname5);
        realname6 = (TextView) findViewById(R.id.tv_realname6);
        realname7 = (TextView) findViewById(R.id.tv_realname7);

        level1 = (TextView) findViewById(R.id.tv_level1);
        level2 = (TextView) findViewById(R.id.tv_level2);
        level3 = (TextView) findViewById(R.id.tv_level3);
        level4 = (TextView) findViewById(R.id.tv_level4);
        level5 = (TextView) findViewById(R.id.tv_level5);
        level6 = (TextView) findViewById(R.id.tv_level6);
        level7 = (TextView) findViewById(R.id.tv_level7);

        node_amount1 = (TextView) findViewById(R.id.node_amount1);
        node_amount2 = (TextView) findViewById(R.id.node_amount2);
        node_amount3 = (TextView) findViewById(R.id.node_amount3);
        node_amount4 = (TextView) findViewById(R.id.node_amount4);
        node_amount5 = (TextView) findViewById(R.id.node_amount5);
        node_amount6 = (TextView) findViewById(R.id.node_amount6);
        node_amount7 = (TextView) findViewById(R.id.node_amount7);

        node_amount_left1 = (TextView) findViewById(R.id.node_amount_left1);
        node_amount_left2 = (TextView) findViewById(R.id.node_amount_left2);
        node_amount_left3 = (TextView) findViewById(R.id.node_amount_left3);
        node_amount_left4 = (TextView) findViewById(R.id.node_amount_left4);
        node_amount_left5 = (TextView) findViewById(R.id.node_amount_left5);
        node_amount_left6 = (TextView) findViewById(R.id.node_amount_left6);
        node_amount_left7 = (TextView) findViewById(R.id.node_amount_left7);

        node_amount_right1 = (TextView) findViewById(R.id.node_amount_right1);
        node_amount_right2 = (TextView) findViewById(R.id.node_amount_right2);
        node_amount_right3 = (TextView) findViewById(R.id.node_amount_right3);
        node_amount_right4 = (TextView) findViewById(R.id.node_amount_right4);
        node_amount_right5 = (TextView) findViewById(R.id.node_amount_right5);
        node_amount_right6 = (TextView) findViewById(R.id.node_amount_right6);
        node_amount_right7 = (TextView) findViewById(R.id.node_amount_right7);

        node_id1 = (TextView) findViewById(R.id.node_id1);
        node_id2 = (TextView) findViewById(R.id.node_id2);
        node_id3 = (TextView) findViewById(R.id.node_id3);
        node_id4 = (TextView) findViewById(R.id.node_id4);
        node_id5 = (TextView) findViewById(R.id.node_id5);
        node_id6 = (TextView) findViewById(R.id.node_id6);
        node_id7 = (TextView) findViewById(R.id.node_id7);

        node_section1 = (TextView) findViewById(R.id.node_section1);
        node_section2 = (TextView) findViewById(R.id.node_section2);
        node_section3 = (TextView) findViewById(R.id.node_section3);
        node_section4 = (TextView) findViewById(R.id.node_section4);
        node_section5 = (TextView) findViewById(R.id.node_section5);
        node_section6 = (TextView) findViewById(R.id.node_section6);
        node_section7 = (TextView) findViewById(R.id.node_section7);

        user_id1 = (TextView) findViewById(R.id.user_id1);
        user_id2 = (TextView) findViewById(R.id.user_id2);
        user_id3 = (TextView) findViewById(R.id.user_id3);
        user_id4 = (TextView) findViewById(R.id.user_id4);
        user_id5 = (TextView) findViewById(R.id.user_id5);
        user_id6 = (TextView) findViewById(R.id.user_id6);
        user_id7 = (TextView) findViewById(R.id.user_id7);


    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph);

        init();
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        String user_id = b.getString("user_id");
        startThread(user_id);

        table1.setOnClickListener(this);
        table2.setOnClickListener(this);
        table3.setOnClickListener(this);
        table4.setOnClickListener(this);
        table5.setOnClickListener(this);
        table6.setOnClickListener(this);
        table7.setOnClickListener(this);

        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.ct_graph);
        customTitleBar.setOnTitleClickListener(new CustomTitleBar.TitleOnClickListener() {
            @Override
            public void onLeftClick() {
                //Toast.makeText(Charge.this, "点击了左边的返回按钮", Toast.LENGTH_SHORT).show();
                finish();
            }
            @Override
            public void onRightClick() {
                //Toast.makeText(Charge.this, "点击了右边的保存按钮", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void startThread(final String user_id){
        new Thread(new Runnable() {

            //url = url+"?user_id="+user_id;

            @Override
            public void run() {
                try {
                    System.out.print(url+":"+user_id);
                    URL requesturl = new URL(url+"?user_id="+user_id);
                    HttpURLConnection connection = (HttpURLConnection) requesturl.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setUseCaches(false);
                    connection.setReadTimeout(5000);
                    if (connection.getResponseCode()==200){
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        StringBuffer stringBuffer = new StringBuffer();
                        String str = null;
                        while ((str=bufferedReader.readLine())!=null){
                            stringBuffer.append(str);
                        }

                        System.out.println(stringBuffer.toString());
                        try {
                            JSONObject jsonObject = new JSONObject(stringBuffer.toString());
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            JSONObject object1 = jsonArray.getJSONObject(0);
                            JSONObject object2 = jsonArray.getJSONObject(1);
                            JSONObject object3 = jsonArray.getJSONObject(2);
                            JSONObject object4 = jsonArray.getJSONObject(3);
                            JSONObject object5 = jsonArray.getJSONObject(4);
                            JSONObject object6 = jsonArray.getJSONObject(5);
                            JSONObject object7 = jsonArray.getJSONObject(6);

                            username_data1 = object1.getString("username");
                            username_data2 = object2.getString("username");
                            username_data3 = object3.getString("username");
                            username_data4 = object4.getString("username");
                            username_data5 = object5.getString("username");
                            username_data6 = object6.getString("username");
                            username_data7 = object7.getString("username");

                            realname_data1 = object1.getString("realname");
                            realname_data2 = object2.getString("realname");
                            realname_data3 = object3.getString("realname");
                            realname_data4 = object4.getString("realname");
                            realname_data5 = object5.getString("realname");
                            realname_data6 = object6.getString("realname");
                            realname_data7 = object7.getString("realname");

                            level_data1 = object1.getString("level");
                            level_data2 = object2.getString("level");
                            level_data3 = object3.getString("level");
                            level_data4 = object4.getString("level");
                            level_data5 = object5.getString("level");
                            level_data6 = object6.getString("level");
                            level_data7 = object7.getString("level");

                            node_amount_data1 = object1.getString("node_amount");
                            node_amount_data2 = object2.getString("node_amount");
                            node_amount_data3 = object3.getString("node_amount");
                            node_amount_data4 = object4.getString("node_amount");
                            node_amount_data5 = object5.getString("node_amount");
                            node_amount_data6 = object6.getString("node_amount");
                            node_amount_data7 = object7.getString("node_amount");

                            node_amount_data_left1 = object1.getString("node_amount_left");
                            node_amount_data_left2 = object2.getString("node_amount_left");
                            node_amount_data_left3 = object3.getString("node_amount_left");
                            node_amount_data_left4 = object4.getString("node_amount_left");
                            node_amount_data_left5 = object5.getString("node_amount_left");
                            node_amount_data_left6 = object6.getString("node_amount_left");
                            node_amount_data_left7 = object7.getString("node_amount_left");

                            node_amount_data_right1 = object1.getString("node_amount_right");
                            node_amount_data_right2 = object2.getString("node_amount_right");
                            node_amount_data_right3 = object3.getString("node_amount_right");
                            node_amount_data_right4 = object4.getString("node_amount_right");
                            node_amount_data_right5 = object5.getString("node_amount_right");
                            node_amount_data_right6 = object6.getString("node_amount_right");
                            node_amount_data_right7 = object7.getString("node_amount_right");

                            node_id_data1 = object1.getString("node_id");
                            node_id_data2 = object2.getString("node_id");
                            node_id_data3 = object3.getString("node_id");
                            node_id_data4 = object4.getString("node_id");
                            node_id_data5 = object5.getString("node_id");
                            node_id_data6 = object6.getString("node_id");
                            node_id_data7 = object7.getString("node_id");

                            node_section_data1 = object1.getString("node_section");
                            node_section_data2 = object2.getString("node_section");
                            node_section_data3 = object3.getString("node_section");
                            node_section_data4 = object4.getString("node_section");
                            node_section_data5 = object5.getString("node_section");
                            node_section_data6 = object6.getString("node_section");
                            node_section_data7 = object7.getString("node_section");


                            user_id_data1 = object1.getString("user_id");
                            user_id_data2 = object2.getString("user_id");
                            user_id_data3 = object3.getString("user_id");
                            user_id_data4 = object4.getString("user_id");
                            user_id_data5 = object5.getString("user_id");
                            user_id_data6 = object6.getString("user_id");
                            user_id_data7 = object7.getString("user_id");

                            if (user_id_data7 == object7.getString("user_id")){


                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        username1.setText(username_data1);
                                        username2.setText(username_data2);
                                        username3.setText(username_data3);
                                        username4.setText(username_data4);
                                        username5.setText(username_data5);
                                        username6.setText(username_data6);
                                        username7.setText(username_data7);

                                        level1.setText(level_data1);
                                        level2.setText(level_data2);
                                        level3.setText(level_data3);
                                        level4.setText(level_data4);
                                        level5.setText(level_data5);
                                        level6.setText(level_data6);
                                        level7.setText(level_data7);

                                        realname1.setText(realname_data1);
                                        realname2.setText(realname_data2);
                                        realname3.setText(realname_data3);
                                        realname4.setText(realname_data4);
                                        realname5.setText(realname_data5);
                                        realname6.setText(realname_data6);
                                        realname7.setText(realname_data7);

                                        node_amount1.setText(node_amount_data1);
                                        node_amount2.setText(node_amount_data2);
                                        node_amount3.setText(node_amount_data3);
                                        node_amount4.setText(node_amount_data4);
                                        node_amount5.setText(node_amount_data5);
                                        node_amount6.setText(node_amount_data6);
                                        node_amount7.setText(node_amount_data7);

                                        node_amount_left1.setText(node_amount_data_left1);
                                        node_amount_left2.setText(node_amount_data_left2);
                                        node_amount_left3.setText(node_amount_data_left3);
                                        node_amount_left4.setText(node_amount_data_left4);
                                        node_amount_left5.setText(node_amount_data_left5);
                                        node_amount_left6.setText(node_amount_data_left6);
                                        node_amount_left7.setText(node_amount_data_left7);

                                        node_amount_right1.setText(node_amount_data_right1);
                                        node_amount_right2.setText(node_amount_data_right2);
                                        node_amount_right3.setText(node_amount_data_right3);
                                        node_amount_right4.setText(node_amount_data_right4);
                                        node_amount_right5.setText(node_amount_data_right5);
                                        node_amount_right6.setText(node_amount_data_right6);
                                        node_amount_right7.setText(node_amount_data_right7);

                                        node_id1.setText(node_id_data1);
                                        node_id2.setText(node_id_data2);
                                        node_id3.setText(node_id_data3);
                                        node_id4.setText(node_id_data4);
                                        node_id5.setText(node_id_data5);
                                        node_id6.setText(node_id_data6);
                                        node_id7.setText(node_id_data7);

                                        node_section1.setText(node_section_data1);
                                        node_section2.setText(node_section_data2);
                                        node_section3.setText(node_section_data3);
                                        node_section4.setText(node_section_data4);
                                        node_section5.setText(node_section_data5);
                                        node_section6.setText(node_section_data6);
                                        node_section7.setText(node_section_data7);

                                        user_id1.setText(user_id_data1);
                                        user_id2.setText(user_id_data2);
                                        user_id3.setText(user_id_data3);
                                        user_id4.setText(user_id_data4);
                                        user_id5.setText(user_id_data5);
                                        user_id6.setText(user_id_data6);
                                        user_id7.setText(user_id_data7);

                                    if (Objects.equals(user_id_data1, "")){
                                        table1.setVisibility(View.GONE);
                                        table2.setVisibility(View.GONE);
                                        table3.setVisibility(View.GONE);
                                        table4.setVisibility(View.GONE);
                                        table5.setVisibility(View.GONE);
                                        table6.setVisibility(View.GONE);
                                        table7.setVisibility(View.GONE);

                                        re_table1.setVisibility(View.VISIBLE);
                                    }
                                    if (Objects.equals(user_id_data2, "")){
                                        table2.setVisibility(View.GONE);
                                        table4.setVisibility(View.GONE);
                                        table5.setVisibility(View.GONE);
                                        re_table2.setVisibility(View.VISIBLE);
                                    }
                                    if (Objects.equals(user_id_data3, "")){
                                        table3.setVisibility(View.GONE);
                                        table6.setVisibility(View.GONE);
                                        table7.setVisibility(View.GONE);
                                        re_table3.setVisibility(View.VISIBLE);
                                    }
                                    if (Objects.equals(user_id_data4, "")){
                                        table4.setVisibility(View.GONE);
                                        re_table4.setVisibility(View.VISIBLE);
                                    }
                                    if (Objects.equals(user_id_data5, "")){
                                        table5.setVisibility(View.GONE);
                                        re_table5.setVisibility(View.VISIBLE);
                                    }
                                    if (Objects.equals(user_id_data6, "")){
                                        table6.setVisibility(View.GONE);
                                        re_table6.setVisibility(View.VISIBLE);
                                    }
                                    if (Objects.equals(user_id_data7, "")){
                                        table7.setVisibility(View.GONE);
                                        re_table7.setVisibility(View.VISIBLE);
                                    }
                                    }
                                });
                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.table1:
                startThread(user_id_data1);
                break;
            case R.id.table2:
                startThread(user_id_data2);
                break;
            case R.id.table3:
                startThread(user_id_data3);
                break;
            case R.id.table4:
                startThread(user_id_data4);
                break;
            case R.id.table5:
                startThread(user_id_data5);
                break;
            case R.id.table6:
                startThread(user_id_data6);
                break;
            case R.id.table7:
                startThread(user_id_data7);
                break;

        }
    }
}
