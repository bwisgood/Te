package com.example.baiwei.zkmt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by baiwei on 2017/7/8.
 */

public class MyAdapter extends BaseAdapter {

    private List<Map<String, Object>> data;
    private LayoutInflater layoutInflater;
    private Context context;

    public MyAdapter(Context context, List<Map<String, Object>> data) {
        this.context = context;
        this.data = data;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public final class Zujian {
        public TextView list_date;
        public TextView list_number;

    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Zujian zujian = null;
        if (view == null) {
            zujian = new Zujian();
            view = layoutInflater.inflate(R.layout.list, null);
            zujian.list_date = (TextView) view.findViewById(R.id.list_date);
            zujian.list_number = (TextView) view.findViewById(R.id.list_number);
            view.setTag(zujian);

        } else {
            zujian = (Zujian) view.getTag();
        }
        zujian.list_date.setText((String) data.get(i).get("list_date"));
        zujian.list_number.setText((String) data.get(i).get("list_number"));


        return view;
    }
}
