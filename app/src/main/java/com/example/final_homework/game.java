package com.example.final_homework;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class game extends AppCompatActivity {
    int count;
    int best;
    DoubleCount doubleCount;
    Stack stack;
    ImageButton[] pos;
    Drawable[] icon;
    List history;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override

    //接受flag并初始化游戏
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        history = new ArrayList<String>();

        pos = new ImageButton[9];
        icon = new Drawable[9];

        pos[0] = (ImageButton)findViewById(R.id.b1);
        pos[1] = (ImageButton)findViewById(R.id.b2);
        pos[2] = (ImageButton)findViewById(R.id.b3);
        pos[3] = (ImageButton)findViewById(R.id.b4);
        pos[4] = (ImageButton)findViewById(R.id.b5);
        pos[5] = (ImageButton)findViewById(R.id.b6);
        pos[6] = (ImageButton)findViewById(R.id.b7);
        pos[7] = (ImageButton)findViewById(R.id.b8);
        pos[8] = (ImageButton)findViewById(R.id.b9);

        icon[0] = getDrawable(R.drawable.p0);
        icon[1] = getDrawable(R.drawable.p1);
        icon[2] = getDrawable(R.drawable.p2);
        icon[3] = getDrawable(R.drawable.p3);
        icon[4] = getDrawable(R.drawable.p4);
        icon[5] = getDrawable(R.drawable.p5);
        icon[6] = getDrawable(R.drawable.p6);
        icon[7] = getDrawable(R.drawable.p7);
        icon[8] = getDrawable(R.drawable.p8);

        Intent intent = getIntent();
        int flag = intent.getIntExtra("flag",-1);
        if (flag==0){
            setnumber();
        }
        else if(flag==1){
            SharedPreferences record = getSharedPreferences("record", Activity.MODE_PRIVATE);
            count = record.getInt("step_count",-1);
            best = record.getInt("best_count",-1);
            String strJson = record.getString("history",null);
            Gson gson = new Gson();
            history = gson.fromJson(strJson, new TypeToken<List<String>>() {}.getType());

            TextView step_count = (TextView)findViewById(R.id.count);
            TextView best_count = (TextView)findViewById(R.id.bestcount);
            step_count.setText("当前步数："+count);
            best_count.setText("最佳步数："+best);
            draw_broad((String)history.get(count));
        }
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    //菜单处理
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.before){
            if(count>0) {
                draw_broad((String) history.get(count - 1));
                history.remove(count - 1);
                count--;
                TextView step_count = (TextView) findViewById(R.id.count);
                step_count.setText("当前步数：" + count);
            }
            else{
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Warning!")
                        .setMessage("没有历史记录！")
                        .setPositiveButton("yes", null);
                builder.create().show();
            }
        }
        else if(item.getItemId()==R.id.restart){
            setnumber();
        }

        return super.onOptionsItemSelected(item);
    }

    //绘制单步棋盘
    public void draw_broad(String str){
        for (int i = 0; i < 9; i++) {
            if (str.charAt(i) != '9') {
                pos[i].setContentDescription(str.charAt(i)+"");
                pos[i].setImageDrawable(icon[Integer.parseInt(str.charAt(i)+"")-1]);
            } else {
                pos[i].setContentDescription("X");
                pos[i].setImageDrawable(icon[8]);
            }
        }
    }

    //行为逻辑函数
    public void gaming(View v){
        if (v.equals(pos[0])){
            if (pos[1].getContentDescription().equals("X")){
                pos[1].setContentDescription(pos[0].getContentDescription());
                pos[0].setContentDescription("X");
                pos[1].setImageDrawable(pos[0].getDrawable());
                pos[0].setImageDrawable(icon[8]);
                count++;
                check();
            }
            else if(pos[3].getContentDescription().equals("X")){
                pos[3].setContentDescription(pos[0].getContentDescription());
                pos[0].setContentDescription("X");
                pos[3].setImageDrawable(pos[0].getDrawable());
                pos[0].setImageDrawable(icon[8]);
                count++;
                check();
            }
        }
        else if(v.equals(pos[1])){
            if(pos[0].getContentDescription().equals("X")){
                pos[0].setContentDescription(pos[1].getContentDescription());
                pos[1].setContentDescription("X");
                pos[0].setImageDrawable(pos[1].getDrawable());
                pos[1].setImageDrawable(icon[8]);
                count++;
                check();
            }
            else if(pos[2].getContentDescription().equals("X")){
                pos[2].setContentDescription(pos[1].getContentDescription());
                pos[1].setContentDescription("X");
                pos[2].setImageDrawable(pos[1].getDrawable());
                pos[1].setImageDrawable(icon[8]);
                count++;
                check();
            }
            else if(pos[4].getContentDescription().equals("X")){
                pos[4].setContentDescription(pos[1].getContentDescription());
                pos[1].setContentDescription("X");
                pos[4].setImageDrawable(pos[1].getDrawable());
                pos[1].setImageDrawable(icon[8]);
                count++;
                check();
            }
        }
        else if (v.equals(pos[2])){
            if(pos[1].getContentDescription().equals("X")){
                pos[1].setContentDescription(pos[2].getContentDescription());
                pos[2].setContentDescription("X");
                pos[1].setImageDrawable(pos[2].getDrawable());
                pos[2].setImageDrawable(icon[8]);
                count++;
                check();
            }
            else if(pos[5].getContentDescription().equals("X")){
                pos[5].setContentDescription(pos[2].getContentDescription());
                pos[2].setContentDescription("X");
                pos[5].setImageDrawable(pos[2].getDrawable());
                pos[2].setImageDrawable(icon[8]);
                count++;
                check();
            }
        }
        else if(v.equals(pos[3])){
            if(pos[0].getContentDescription().equals("X")){
                pos[0].setContentDescription(pos[3].getContentDescription());
                pos[3].setContentDescription("X");
                pos[0].setImageDrawable(pos[3].getDrawable());
                pos[3].setImageDrawable(icon[8]);
                count++;
                check();
            }
            else if(pos[4].getContentDescription().equals("X")){
                pos[4].setContentDescription(pos[3].getContentDescription());
                pos[3].setContentDescription("X");
                pos[4].setImageDrawable(pos[3].getDrawable());
                pos[3].setImageDrawable(icon[8]);
                count++;
                check();
            }
            else if(pos[6].getContentDescription().equals("X")){
                pos[6].setContentDescription(pos[3].getContentDescription());
                pos[3].setContentDescription("X");
                pos[6].setImageDrawable(pos[3].getDrawable());
                pos[3].setImageDrawable(icon[8]);
                count++;
                check();
            }
        }
        else if(v.equals(pos[4])){
            if (pos[1].getContentDescription().equals("X")){
                pos[1].setContentDescription(pos[4].getContentDescription());
                pos[4].setContentDescription("X");
                pos[1].setImageDrawable(pos[4].getDrawable());
                pos[4].setImageDrawable(icon[8]);
                count++;
                check();
            }
            else if(pos[3].getContentDescription().equals("X")){
                pos[3].setContentDescription(pos[4].getContentDescription());
                pos[4].setContentDescription("X");
                pos[3].setImageDrawable(pos[4].getDrawable());
                pos[4].setImageDrawable(icon[8]);
                count++;
                check();
            }
            else if(pos[5].getContentDescription().equals("X")){
                pos[5].setContentDescription(pos[4].getContentDescription());
                pos[4].setContentDescription("X");
                pos[5].setImageDrawable(pos[4].getDrawable());
                pos[4].setImageDrawable(icon[8]);
                count++;
                check();
            }
            else if(pos[7].getContentDescription().equals("X")){
                pos[7].setContentDescription(pos[4].getContentDescription());
                pos[4].setContentDescription("X");
                pos[7].setImageDrawable(pos[4].getDrawable());
                pos[4].setImageDrawable(icon[8]);
                count++;
                check();
            }
        }
        else if(v.equals(pos[5])){
            if(pos[2].getContentDescription().equals("X")){
                pos[2].setContentDescription(pos[5].getContentDescription());
                pos[5].setContentDescription("X");
                pos[2].setImageDrawable(pos[5].getDrawable());
                pos[5].setImageDrawable(icon[8]);
                count++;
                check();
            }
            else if(pos[4].getContentDescription().equals("X")){
                pos[4].setContentDescription(pos[5].getContentDescription());
                pos[5].setContentDescription("X");
                pos[4].setImageDrawable(pos[5].getDrawable());
                pos[5].setImageDrawable(icon[8]);
                count++;
                check();
            }
            else if(pos[8].getContentDescription().equals("X")){
                pos[8].setContentDescription(pos[5].getContentDescription());
                pos[5].setContentDescription("X");
                pos[8].setImageDrawable(pos[5].getDrawable());
                pos[5].setImageDrawable(icon[8]);
                count++;
                check();
            }
        }
        else if(v.equals(pos[6])){
            if(pos[3].getContentDescription().equals("X")){
                pos[3].setContentDescription(pos[6].getContentDescription());
                pos[6].setContentDescription("X");
                pos[3].setImageDrawable(pos[6].getDrawable());
                pos[6].setImageDrawable(icon[8]);
                count++;
                check();
            }
            else if(pos[7].getContentDescription().equals("X")){
                pos[7].setContentDescription(pos[6].getContentDescription());
                pos[6].setContentDescription("X");
                pos[7].setImageDrawable(pos[6].getDrawable());
                pos[6].setImageDrawable(icon[8]);
                count++;
                check();
            }
        }
        else if(v.equals(pos[7])){
            if(pos[6].getContentDescription().equals("X")){
                pos[6].setContentDescription(pos[7].getContentDescription());
                pos[7].setContentDescription("X");
                pos[6].setImageDrawable(pos[7].getDrawable());
                pos[7].setImageDrawable(icon[8]);
                count++;
                check();
            }
            else if(pos[4].getContentDescription().equals("X")){
                pos[4].setContentDescription(pos[7].getContentDescription());
                pos[7].setContentDescription("X");
                pos[4].setImageDrawable(pos[7].getDrawable());
                pos[7].setImageDrawable(icon[8]);
                count++;
                check();
            }
            else if(pos[8].getContentDescription().equals("X")){
                pos[8].setContentDescription(pos[7].getContentDescription());
                pos[7].setContentDescription("X");
                pos[8].setImageDrawable(pos[7].getDrawable());
                pos[7].setImageDrawable(icon[8]);
                count++;
                check();
            }
        }
        else if(v.equals(pos[8])){
            if(pos[5].getContentDescription().equals("X")){
                pos[5].setContentDescription(pos[8].getContentDescription());
                pos[8].setContentDescription("X");
                pos[5].setImageDrawable(pos[8].getDrawable());
                pos[8].setImageDrawable(icon[8]);
                count++;
                check();
            }
            else if(pos[7].getContentDescription().equals("X")){
                pos[7].setContentDescription(pos[8].getContentDescription());
                pos[8].setContentDescription("X");
                pos[7].setImageDrawable(pos[8].getDrawable());
                pos[8].setImageDrawable(icon[8]);
                count++;
                check();
            }
        }

    }

    //每一步后检查棋盘状态并存入历史记录
    public void check(){
        TextView step_count = (TextView)findViewById(R.id.count);
        step_count.setText("当前步数："+count);
        final Intent intent = new Intent(this,MainActivity.class);
        final Intent commit = new Intent(this, commit.class);
        String str = "";
        for (int i = 0; i < 9; i++) {
            if (!pos[i].getContentDescription().equals("X")) {
                str = str + pos[i].getContentDescription();
            }
            else{
                str = str+"9";
            }
        }
        history.add(str);
        if (str.equals("123456789")){
            SharedPreferences record = getSharedPreferences("record", Activity.MODE_PRIVATE);
            record.edit().clear().commit();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("notice")
                    .setMessage("Congratulation!\n是否上传成绩？")
                    .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        commit.putExtra("final_count",count);
                        commit.putExtra("best_count",best);
                        startActivity(commit);
                        finish();
                        }
                    }).setNegativeButton("no",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    setResult(1,intent);
                    finish();
                }
            });
            builder.create().show();
        }
    }

    //重置棋盘并计算最佳步数
    public void setnumber(){
        count = 0;
        history.clear();
        int[] a = new int[9];
        while (true) {
            int[] rand = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
            for (int i = 0; i < 9; i++) {
                int m = (int) (Math.random() * rand.length);
                while (rand[m] == 0) {
                    m = (int) (Math.random() * rand.length);
                }
                a[i] = rand[m];
                rand[m] = 0;
            }
            int max = 0;
            for (int i = 0; i < 9; i++) {
                if (a[i] != 9) {
                    for (int j = i + 1; j < 9; j++) {
                        if (a[i] > a[j] && a[j] != 9) {
                            max++;
                        }
                    }
                }
            }
            if (max % 2 == 0) {
                break;
            }
        }

        a[0] = 1;a[1] = 2;a[2] = 3;a[3] = 4;a[4] = 5;a[5] = 6;a[6] = 7;a[7] = 9;a[8] = 8;

        String str = "";
        for(int i = 0;i<9;i++){
            str = str+String.valueOf(a[i]);
        }
        history.add(str);

        doubleCount = new DoubleCount(str);
        best = doubleCount.getBest();

        TextView step_count = (TextView)findViewById(R.id.count);
        TextView best_count = (TextView)findViewById(R.id.bestcount);
        step_count.setText("当前步数："+count);
        best_count.setText("最佳步数："+best);

        for (int i = 0; i < 9; i++) {
            if (a[i] != 9) {
                pos[i].setContentDescription(String.valueOf(a[i]));
                pos[i].setImageDrawable(icon[a[i]-1]);
            } else {
                pos[i].setContentDescription("X");
                pos[i].setImageDrawable(icon[8]);
            }
        }
    }

    //设置返回时自动存档
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0){
            SharedPreferences record = getSharedPreferences("record", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = record.edit();
            editor.putInt("step_count",count);
            editor.putInt("best_count",best);
            Gson gson = new Gson();
            String strJson = gson.toJson(history);
            editor.putString("history",strJson);
            editor.apply();

            Intent intent = new Intent(this,MainActivity.class);
            setResult(1,intent);
            finish();
        }
        return false;
    }

}
