package com.example.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Huangcan on 2016/11/8.
 */
public class TimeAdapter extends BaseAdapter {
    private List<Time> list;
    private Context context;

    public TimeAdapter(List<Time> list, Context context) {
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
        TextView tv = null;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item,null,true);
            tv = (TextView) convertView.findViewById(R.id.tv_item);
            convertView.setTag(tv);
        }else{
            tv = (TextView) convertView.getTag();
        }
        tv.setText(list.get(position).getName());
        return convertView;
    }
}
