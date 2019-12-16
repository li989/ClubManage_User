package com.example.activitytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                //实现跳转
                startActivity(new Intent(welcome.this, login.class));
                finish(); //销毁此活动
                return false;
            }
        }).sendEmptyMessageDelayed(0,3000);
    }
}
