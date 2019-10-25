package com.example.final_homework;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class leaderbroad extends AppCompatActivity {
    ArrayList listItems;
    Myadapt myadpater;
    List<Record_bean> record;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderbroad);
        record = new ArrayList<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                String path = "http://10.0.2.2:8080/Text_mining/Leaderbroad?flag=leaderbroad";
                URL url = null;
                try {
                    url = new URL(path);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(200);//设置读取超时的毫秒数
                    connection.setConnectTimeout(200);//设置连接超时的毫秒数
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    String result = reader.readLine();//读取服务器进行逻辑处理后页面显示的数据
                    Log.i("responsefrom",result);

                    Gson gson = new Gson();
                    record = gson.fromJson(result, new TypeToken<List<Record_bean>>() {}.getType());

                    Message msg = handler.obtainMessage(4);
                    msg.obj = record;
                    handler.sendMessage(msg);
                } catch (MalformedURLException e) {
                    Message msg = handler.obtainMessage(3);
                    msg.obj = "no";
                    handler.sendMessage(msg);
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    Message msg = handler.obtainMessage(3);
                    msg.obj = "no";
                    handler.sendMessage(msg);
                    e.printStackTrace();
                } catch (IOException e) {
                    Message msg = handler.obtainMessage(3);
                    msg.obj = "no";
                    handler.sendMessage(msg);
                    e.printStackTrace();
                }


            }
        }).start();

        listItems = new ArrayList<HashMap<String,String>>();

        handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                if(msg.what==4) {
                        List<Record_bean> temp = (List<Record_bean>)msg.obj;
                        for (Record_bean bean:temp){
                            HashMap<String,String > map = new HashMap<>();
                            map.put("username",bean.username);
                            map.put("minus_count",""+(bean.step-bean.best));
                            map.put("best_count",""+bean.best);
                            listItems.add(map);
                        }
                        myadpater = new Myadapt(leaderbroad.this, R.layout.list_item,listItems);
                        ListView listView = (ListView)findViewById(R.id.list);
                        listView.setAdapter(myadpater);
                        listView.setEmptyView(findViewById(R.id.empty));

                }
                else if(msg.what==3){
                    AlertDialog.Builder builder = new AlertDialog.Builder(leaderbroad.this);
                    builder.setTitle("Error!")
                            .setMessage("服务器链接失败！")
                            .setPositiveButton("退出", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finish();
                                }
                            });
                    builder.create().show();
                }
                super.handleMessage(msg);
            }
        };

    }
}
