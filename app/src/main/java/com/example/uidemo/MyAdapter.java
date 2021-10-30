package com.example.uidemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyAdapter extends BaseAdapter {

    Context context;
    List<Map<String,String>> dataList;

    public MyAdapter(Context context, List<Map<String,String>> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        if (dataList == null){
            dataList = new ArrayList<>();
        }
        return dataList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyHolder holder;
        if (view == null){
            holder = new MyHolder();
            view = LayoutInflater.from(context).inflate(R.layout.listview_item_layout,null);
            holder.img = view.findViewById(R.id.item_img);
            holder.text1 = view.findViewById(R.id.item_text1);
            holder.text2 = view.findViewById(R.id.item_text2);
            view.setTag(holder);
        }else {
            holder = (MyHolder) view.getTag();
        }

        if (dataList != null){
            Map<String,String> map = dataList.get(i);
            holder.img.setImageResource(Integer.parseInt(map.get("img")));
            holder.text1.setText(map.get("text1"));
            holder.text2.setText(map.get("text2"));
        }

        return view;
    }

    class MyHolder{
        ImageView img;
        TextView text1,text2;
    }
}
