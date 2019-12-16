package com.example.activitytest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adapter.clubactAdapter;
import com.example.club.control.example.ActivityManager;
import com.example.club.control.example.User_ClubManager;
import com.example.club.model.Beanactivity;
import com.example.club.model.Beanuser;
import com.example.club.util.BaseException;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import static com.mysql.jdbc.AbandonedConnectionCleanupThread.shutdown;

public class club_act extends AppCompatActivity  {
    private FadingScrollView fadingScrollView;
    private View layout;
    private Banner mBanner;
    int club_id;
    public static void actionStart(Context context, Beanactivity act) {
        Intent intent = new Intent();
        Log.e("ip",""+act.getActivity_ID());
        intent.putExtra("id",act.getActivity_ID());
        intent.setClass(context, club_actinf.class);
        context.startActivity(intent);
    }
    private Handler handler=new Handler(){
        public void handleMessage(Message msg){
            try {
            List<Beanactivity>  activitys=(ArrayList<Beanactivity>)msg.obj;
            RecyclerView recyclerView=(RecyclerView)findViewById(R.id.club_act_rela);
            LinearLayoutManager layoutManager=new LinearLayoutManager(club_act.this);
            recyclerView.setLayoutManager(layoutManager);
            clubactAdapter adapter=new clubactAdapter(activitys);
            recyclerView.setAdapter(adapter);
            Button acclno_release2=(Button)findViewById(R.id.club_act_title1);
            if(msg.getData().getBoolean("bool")){
                acclno_release2.setVisibility(View.VISIBLE);
            }}catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.club_activity);
        Intent it=getIntent();
        club_id=it.getIntExtra("club_ID",0);
        Button btnexit=(Button)findViewById(R.id.club_act_exit);
        btnexit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        Button btn1=(Button)findViewById(R.id.club_act_title1);
        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("id",club_id);
                intent.setClass(club_act.this, apply_activity.class);
                startActivity(intent);
            }
        });
        new Thread(){
            @Override
            public void run() {
                try {
                    List<Beanactivity> activity=new ArrayList<Beanactivity>();
                    User_ClubManager user=new User_ClubManager();
                    ActivityManager uc=new ActivityManager();
                    activity=uc.loadAllclub(club_id);
                    Message message=new Message();
                    message.obj=activity;
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("bool",user.getuser_clubpro(Beanuser.currentLoginUser.getC_userID(),club_id));  //往Bundle中存放数据
                    message.setData(bundle);
                    handler.sendMessage(message);
                    shutdown();
                } catch (BaseException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        Button acclno_release2=(Button)findViewById(R.id.club_act_title1);
        acclno_release2.setVisibility(View.GONE);
    }
}
