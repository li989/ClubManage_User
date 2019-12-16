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
import com.example.adapter.myclAdapter;
import com.example.club.control.example.ClubManager;
import com.example.club.control.example.UserManager;
import com.example.club.model.Beanclub;
import com.example.club.model.Beanmycl;
import com.example.club.model.Beanuser;
import com.example.club.util.BaseException;

import java.util.ArrayList;
import java.util.List;

public class MyClub extends AppCompatActivity {
    List<Beanmycl> mycl=new ArrayList<Beanmycl>();
    private Handler handler=new Handler(){
        public void handleMessage(Message msg){
            List<Beanmycl> mycls=(ArrayList<Beanmycl>)msg.obj;
            RecyclerView recyclerView=(RecyclerView)findViewById(R.id.myclub_recyc);
            LinearLayoutManager layoutManager=new LinearLayoutManager(MyClub.this);
            recyclerView.setLayoutManager(layoutManager);
            myclAdapter adapter=new myclAdapter(mycls);
            recyclerView.setAdapter(adapter);

        }
    };
    public static void actionStart(final Context context, final Beanmycl mycl) {
        new Thread(){
            private Handler handler1=new Handler(){
                public void handleMessage(Message msg){
                    try {
                        String s=msg.getData().getString("name");
                        Intent intent = new Intent();
                        intent.putExtra("id",mycl.getClub_id());
                        if (s==null){
                            intent.setClass(context, club_introduceout.class);
                            context.startActivity(intent);
                        } else if(s.equals("社长")){
                            intent.setClass(context, club_main.class);
                            context.startActivity(intent);
                        }else if(s.equals("社员")){
                            intent.setClass(context, club_main1.class);
                            context.startActivity(intent);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            };
            public void run() {
                ClubManager a=new ClubManager();
                String s=a.inclub(Beanuser.currentLoginUser.getC_userID(),mycl.getClub_id());
                Message message=new Message();
                Bundle bundle = new Bundle();
                bundle.putString("name",s);  //往Bundle中存放数据
                message.setData(bundle);
                handler1.sendMessage(message);

            }
        }.start();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_club);

        Button btnexit=(Button)findViewById(R.id.myclub_exit) ;
        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        new Thread(){
            @Override
            public void run() {
                UserManager a=new UserManager();
                mycl=a.getmyclub(Beanuser.currentLoginUser.getC_userID());
                Message message=new Message();
                message.obj=mycl;
                Bundle bundle = new Bundle();
                bundle.putString("name","1");  //往Bundle中存放数据
                message.setData(bundle);
                handler.sendMessage(message);

            }
        }.start();
//        Beanmycl user1=new Beanmycl();
//        user1.setClub_name("篮球社");
//        user1.setUser_grade("社员");
//        user1.setJoin_time(new java.sql.Timestamp(System.currentTimeMillis()));
//        for(int i=0;i<100;i++){
//            user.add(user1);
//        }
//        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.myclub_recyc);
//        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//        myclAdapter adapter=new myclAdapter(user);
//        recyclerView.setAdapter(adapter);
    }
}
