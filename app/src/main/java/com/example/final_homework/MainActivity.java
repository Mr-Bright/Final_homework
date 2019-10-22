package com.example.final_homework;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    protected void click(View v){
        int id = v.getId();
        switch (id){
            case R.id.new_game:
                intent = new Intent(this,game.class);
                intent.putExtra("flag",0);
                startActivityForResult(intent,1);
                break;

            case R.id.re_game:
                SharedPreferences record = getSharedPreferences("record", Activity.MODE_PRIVATE);
                int his = record.getInt("step_count",-1);
                if(his==-1){
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Warning!")
                            .setMessage("没有游戏记录！")
                            .setPositiveButton("yes", null);
                    builder.create().show();
                    break;
                }
                else {
                    intent = new Intent(this, game.class);
                    intent.putExtra("flag", 1);
                    startActivityForResult(intent, 2);
                    break;
                }

            case R.id.leaderbroad:
                intent = new Intent(this,leaderbroad.class);
                startActivityForResult(intent,3);
                break;

            default:
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
    }
}
