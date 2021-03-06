package com.example.baiwei.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.baiwei.zkmt.R;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by baiwei on 2017/7/14.
 */

public class Withdraw_adapter extends BaseAdapter {
    public ArrayList<Map<String, Object>> list=new ArrayList<Map<String,Object>>();

    public Context context;

    public Withdraw_adapter(Context context,ArrayList<Map<String, Object>> list){
        this.list = list;
        this.context = context;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();

            convertView = LayoutInflater.from(context).inflate(R.layout.listview_notice, null);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.tv);
            viewHolder.shijian = (TextView) convertView.findViewById(R.id.shijian);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textView.setText(list.get(position).get("title").toString());
        viewHolder.shijian.setText(list.get(position).get("add_time").toString());
        viewHolder.textView.setTextColor(Color.rgb(100,149,237));
        return convertView;
    }
}
