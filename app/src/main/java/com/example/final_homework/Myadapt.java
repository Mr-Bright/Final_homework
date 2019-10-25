package com.example.final_homework;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

public class Myadapt extends ArrayAdapter {
    public Myadapt(Context context, int resource, ArrayList<Map<String, String>> list) {
        super(context, resource, list);
    }
    public View getView(int position, View converView, ViewGroup parent){
        View itemView = converView;
        if(itemView==null){
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        Map<String, String> map = (Map<String, String>) getItem(position);
        TextView username = (TextView)itemView.findViewById(R.id.username);
        TextView minus = (TextView)itemView.findViewById(R.id.minus_score);
        TextView best = (TextView)itemView.findViewById(R.id.best_score);

        username.setText(map.get("username"));
        minus.setText("相差步数: "+map.get("minus_count"));
        best.setText("最佳步数: "+map.get("best_count"));

        return itemView;

    }
}
