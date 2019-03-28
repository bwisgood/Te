package com.example.baiwei.pages;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.baiwei.zkmt.R;

/**
 * Created by baiwei on 2017/7/7.
 */

public class Registe extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_registe);

        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.ct_main_registe);
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
}
