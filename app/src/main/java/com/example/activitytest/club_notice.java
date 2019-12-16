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

import com.example.adapter.clubnoticeAdapt;
import com.example.club.control.example.ClubNoticeManager;
import com.example.club.control.example.User_ClubManager;
import com.example.club.model.Beanclub;
import com.example.club.model.Beanclubnotice;
import com.example.club.model.Beanuser;
import com.example.club.util.BaseException;

import java.util.ArrayList;
import java.util.List;

import static com.mysql.jdbc.AbandonedConnectionCleanupThread.shutdown;

public class club_notice extends AppCompatActivity {
    public static void actionStart(Context context, int ID) {
        Intent intent = new Intent();
        intent.putExtra("id",ID);
        intent.setClass(context, club_notice_digital.class);
        context.startActivity(intent);
    }
    private Beanclub club= new Beanclub();
    private Handler handler=new Handler(){
        public void handleMessage(Message msg){
            List<Beanclubnotice>  a=(List<Beanclubnotice> )msg.obj;
            RecyclerView recyclerView=(RecyclerView)findViewById(R.id.acclno_recy);
            LinearLayoutManager layoutManager=new LinearLayoutManager(club_notice.this);
            Button acclno_release2=(Button)findViewById(R.id.acclno_release2);
            recyclerView.setLayoutManager(layoutManager);
            clubnoticeAdapt adapter=new clubnoticeAdapt(a);
            recyclerView.setAdapter(adapter);
            if(msg.getData().getBoolean("bool")){
                acclno_release2.setVisibility(View.VISIBLE);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.club_notice);
        Intent it = getIntent();
        final int ID = it.getIntExtra("club_ID",0);
        new Thread(){
            @Override
            public void run() {
                try {
                    User_ClubManager user=new User_ClubManager();
                    ClubNoticeManager notice=new ClubNoticeManager();
                    List<Beanclubnotice> clubnotice=new ArrayList<Beanclubnotice>();
                    clubnotice=notice.selectClubnotice(ID);
                    Message message=new Message();
                    message.obj=clubnotice;
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("bool",user.getuser_clubpro(Beanuser.currentLoginUser.getC_userID(),ID));  //往Bundle中存放数据
                    message.setData(bundle);
                    handler.sendMessage(message);
                    shutdown();
                } catch (BaseException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        Button btnadd=(Button)findViewById(R.id.acclno_release2);
        Button acclno_release2=(Button)findViewById(R.id.acclno_release2);
        acclno_release2.setVisibility(View.GONE);
        Button btnexit=(Button)findViewById(R.id.acclno_exit);
        btnexit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
        btnadd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("club_ID",ID);
                intent.setClass(club_notice.this, add_clubnotice.class);
                startActivity(intent);
            }
        });
    }
}
