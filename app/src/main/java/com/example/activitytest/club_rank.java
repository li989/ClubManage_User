package com.example.activitytest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adapter.cluballAdapter;
import com.example.adapter.clubrankAdapter;
import com.example.club.control.example.ClubManager;
import com.example.club.control.example.UserManager;
import com.example.club.model.Beanclub;
import com.example.club.util.BaseException;

import java.util.ArrayList;
import java.util.List;

public class club_rank extends AppCompatActivity {
    private List<Beanclub> mclubs=new ArrayList<Beanclub>();
    private Handler handler=new Handler(){
        public void handleMessage(Message msg){
            List<Beanclub> clubs=(ArrayList<Beanclub>)msg.obj;

            RecyclerView recyclerView=(RecyclerView)findViewById(R.id.club_rank_rela);
            LinearLayoutManager layoutManager=new LinearLayoutManager(club_rank.this);
            recyclerView.setLayoutManager(layoutManager);
            clubrankAdapter adapter=new clubrankAdapter(clubs,msg.getData().getStringArrayList("name"));
            recyclerView.setAdapter(adapter);

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.club_rank);

        new Thread(){
            @Override
            public void run() {
                try {
                    ClubManager a=new ClubManager();
                    mclubs=a.loadAllpx();
                    ArrayList<String> mname=new ArrayList<String>();
                    Message message=new Message();
                    mname=(new UserManager()).selectszpm();
                    message.obj=mclubs;
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("name",mname);  //往Bundle中存放数据
                    message.setData(bundle);
                    handler.sendMessage(message);
                } catch (BaseException e) {
                    e.printStackTrace();
                }
            }
        }.start();


        Button btnexit=(Button)findViewById(R.id.club_rank_exit);
        btnexit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }
}
