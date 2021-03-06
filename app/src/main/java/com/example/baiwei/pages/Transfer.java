package com.example.baiwei.pages;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.baiwei.zkmt.R;

/**
 * Created by baiwei on 2017/7/6.
 */

public class Transfer extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transfer);

        setContentView(R.layout.charge);
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.ct_transfer);
        customTitleBar.setOnTitleClickListener(new CustomTitleBar.TitleOnClickListener() {
            @Override
            public void onLeftClick() {
                Toast.makeText(Transfer.this, "点击了左边的返回按钮", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightClick() {
                Toast.makeText(Transfer.this, "点击了右边的保存按钮", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
