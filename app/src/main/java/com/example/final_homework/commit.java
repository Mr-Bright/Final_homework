package com.example.final_homework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class commit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commit);
        Intent intent = getIntent();
        int count = intent.getIntExtra("final_count",-1);
        int best = intent.getIntExtra("best_count",-1);

        TextView textView = (TextView)findViewById(R.id.final_count);
        TextView textView1 = (TextView)findViewById(R.id.bestcount_commit);
        TextView textView2 = (TextView)findViewById(R.id.minus_count);

        textView.setText("总步数："+count);
        textView1.setText("最佳步数："+best);
        textView2.setText("相差步数："+(count-best));
    }
}
