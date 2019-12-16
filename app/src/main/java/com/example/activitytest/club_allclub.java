package com.example.activitytest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adapter.cluballAdapter;
import com.example.adapter.school_noticeAdapter;
import com.example.club.control.example.ClubManager;
import com.example.club.control.example.SchoolNoticeManager;
import com.example.club.model.Beanclub;
import com.example.club.model.Beanschoolnotice;
import com.example.club.model.Beanuser;
import com.example.club.util.BaseException;

import java.util.ArrayList;
import java.util.List;

public class club_allclub extends AppCompatActivity {
    private List<Beanclub> mclubs=new ArrayList<Beanclub>();
    private Handler handler=new Handler(){
        public void handleMessage(Message msg){
            List<Beanclub> clubs=(ArrayList<Beanclub>)msg.obj;

            RecyclerView recyclerView=(RecyclerView)findViewById(R.id.club_allclub_rela);
            LinearLayoutManager layoutManager=new LinearLayoutManager(club_allclub.this);
            recyclerView.setLayoutManager(layoutManager);
            cluballAdapter adapter=new cluballAdapter(clubs);
            recyclerView.setAdapter(adapter);

        }
    };
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.club_allclub);
        Button btnexit=(Button)findViewById(R.id.club_allclub_exit);
        btnexit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        new Thread(){
            @Override
            public void run() {
                try {
                    ClubManager a=new ClubManager();
                    mclubs=a.loadAll();
                    Message message=new Message();
                    message.obj=mclubs;
                    Bundle bundle = new Bundle();
                    bundle.putString("name","1");  //往Bundle中存放数据
                    message.setData(bundle);
                    handler.sendMessage(message);
                } catch (BaseException e) {
                    e.printStackTrace();
                }
            }
        }.start();


        Button btntop=(Button)findViewById(R.id.btn_top);
        final ScrollView sc=(ScrollView)findViewById(R.id.nac_root);
        btntop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sc.fullScroll(ScrollView.FOCUS_UP);
            }
        });
    }

}
