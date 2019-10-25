package com.example.final_homework;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class commit extends AppCompatActivity {
    int count;
    int best;
    int minus;
    Handler handler;
    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commit);
        Intent intent = getIntent();
        count = intent.getIntExtra("final_count",-1);
        best = intent.getIntExtra("best_count",-1);
        minus = count-best;

        TextView textView = (TextView)findViewById(R.id.final_count);
        TextView textView1 = (TextView)findViewById(R.id.bestcount_commit);
        TextView textView2 = (TextView)findViewById(R.id.minus_count);

        textView.setText("总步数："+count);
        textView1.setText("最佳步数："+best);
        textView2.setText("相差步数："+(count-best));

        final Intent intent1 = new Intent(this,leaderbroad.class);

        handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                if(msg.what==5) {
                    String flag = (String)msg.obj;
                    if (flag.equals("yes")){
                        startActivity(intent1);
                        finish();
                    }
                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(commit.this);
                        builder.setTitle("Error!")
                                .setMessage("上传失败！")
                                .setPositiveButton("yes", null);
                        builder.create().show();
                    }

                }
                super.handleMessage(msg);
            }
        };

    }
    public void click(View v){
        EditText editText = (EditText)findViewById(R.id.name);
        final String name = editText.getText().toString();
        if (name.equals("")){
            editText.setHint("请输入用户名");
        }
        else {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    String path = "http://10.0.2.2:8080/Text_mining/Leaderbroad?username=" + name + "&step=" + count + "&best=" + best+"&flag=commit";
                    try {
                        URL url = new URL(path);
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        connection.setRequestMethod("GET");
                        connection.setReadTimeout(8000);//设置读取超时的毫秒数
                        connection.setConnectTimeout(8000);//设置连接超时的毫秒数
                        InputStream in = connection.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                        String result = reader.readLine();//读取服务器进行逻辑处理后页面显示的数据
                        if (result.equals("upload successfully!")) {
                            Message msg = handler.obtainMessage(5);
                            msg.obj = "yes";
                            handler.sendMessage(msg);
                        } else {
                            Message msg = handler.obtainMessage(5);
                            msg.obj = "no";
                            handler.sendMessage(msg);
                        }

                    } catch (MalformedURLException e) {
                        Message msg = handler.obtainMessage(5);
                        msg.obj = "no";
                        handler.sendMessage(msg);
                        e.printStackTrace();
                    } catch (IOException e) {
                        Message msg = handler.obtainMessage(5);
                        msg.obj = "no";
                        handler.sendMessage(msg);
                        e.printStackTrace();
                    }
                }
            }).start();
        }


    }
}
