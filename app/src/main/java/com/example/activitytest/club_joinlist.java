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

import com.example.adapter.userAdapter;
import com.example.club.control.example.User_ClubManager;
import com.example.club.model.Beanclub_person;
import com.example.club.util.BaseException;

import java.util.ArrayList;
import java.util.List;

import static com.mysql.jdbc.AbandonedConnectionCleanupThread.shutdown;

public class club_joinlist extends AppCompatActivity {
    public static void actionStart(Context context, int user_ID,int club_ID) {
        Intent intent = new Intent();
        intent.putExtra("club_id",club_ID);
        intent.putExtra("user_id",user_ID);
        intent.setClass(context, club_person_info.class);
        context.startActivity(intent);
    }
    List<Beanclub_person> clper=new ArrayList<Beanclub_person>();
    int club_id;
    private Handler handler=new Handler(){
        public void handleMessage(Message msg){
            List<Beanclub_person> uscl=new ArrayList<Beanclub_person>();
            uscl=(ArrayList<Beanclub_person>)msg.obj;
            RecyclerView recyclerView=(RecyclerView)findViewById(R.id.club_joinlist_rela);
            LinearLayoutManager layoutManager=new LinearLayoutManager(club_joinlist.this);
            recyclerView.setLayoutManager(layoutManager);
            userAdapter adapter=new userAdapter(uscl);
            recyclerView.setAdapter(adapter);
        }
    };
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.club_joinlist);
        Button btnback=(Button)findViewById(R.id.club_sqlbback);
        btnback.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
        Intent it=getIntent();
        club_id=it.getIntExtra("club_ID",0);
        new Thread(){
            @Override
            public void run() {
                try {
                    User_ClubManager user=new User_ClubManager();
                    clper=user.loadalluserclub(club_id);
                    Message message=new Message();
                    message.obj=clper;
                    handler.sendMessage(message);
                    shutdown();
                } catch (BaseException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}