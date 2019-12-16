package com.example.activitytest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adapter.cluballAdapter;
import com.example.adapter.school_noticeAdapter;
import com.example.club.model.Beanclub;
import com.example.club.model.Beanschoolnotice;

import java.util.ArrayList;
import java.util.List;

public class school_notice extends AppCompatActivity {

    public static void actionStart(Context context,Beanschoolnotice notice) {
        Intent intent = new Intent();
        intent.setClass(context, notice_digital.class);
        intent.putExtra("id",notice.getSchoolnotice_ID());
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.school_notice);
        List<Beanschoolnotice> notices=new ArrayList<Beanschoolnotice>();

        for (int i=0;i<4;i++){
            Beanschoolnotice no=new Beanschoolnotice();
            no.setSchoolnotice_title("足球大赛"+i);
            no.setSchoolnotice_start_time(new java.sql.Timestamp(System.currentTimeMillis()));
            notices.add(no);
        }
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.school_notice_rela);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        school_noticeAdapter adapter=new school_noticeAdapter(notices);
        recyclerView.setAdapter(adapter);

        Button btnsearch=(Button)findViewById(R.id.school_notice_search);
        btnsearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(school_notice.this, noticesearch.class);
                startActivity(intent);
            }
        });
        Button btnsearch1=(Button)findViewById(R.id.TextView_school_notice_release2);
        btnsearch1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(school_notice.this, noticesearch.class);
                startActivity(intent);
            }
        });


    }
}
