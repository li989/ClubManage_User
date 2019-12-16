package com.example.activitytest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.adapter.cluballAdapter;
import com.example.club.control.example.ClubManager;
import com.example.club.model.Beanclub;
import com.example.club.util.BaseException;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class club_more extends AppCompatActivity {
    private FadingScrollView fadingScrollView;
    private View layout;
    private Banner mBanner;
    private List<Beanclub> mclubs=new ArrayList<Beanclub>();
    private Handler handler=new Handler(){
        public void handleMessage(Message msg){
            List<Beanclub> clubs=(ArrayList<Beanclub>)msg.obj;

            RecyclerView recyclerView=(RecyclerView)findViewById(R.id.club_more_rela);
            LinearLayoutManager layoutManager=new LinearLayoutManager(club_more.this);
            recyclerView.setLayoutManager(layoutManager);
            cluballAdapter adapter=new cluballAdapter(clubs);
            recyclerView.setAdapter(adapter);

        }
    };
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.club_more);
        final Intent inte=getIntent();
        TextView title=(TextView)findViewById(R.id.club_more_title);
        title.setText(inte.getStringExtra("more"));
        new Thread(){
            @Override
            public void run() {
                try {
                    ClubManager a=new ClubManager();
                    mclubs=a.selecthClub(inte.getStringExtra("more"));
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

        List<Beanclub> clubs=new ArrayList<Beanclub>();
        for (int i=0;i<20;i++){
            Beanclub club=new Beanclub();
            club.setClub_ID(i);
            club.setClub_name("足球社"+i);
            club.setClub_remark("啦啦啦"+i);
            clubs.add(club);
        }
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.club_more_rela);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        cluballAdapter adapter=new cluballAdapter(clubs);
        recyclerView.setAdapter(adapter);
        Button btnexit=(Button)findViewById(R.id.club_more_exit);
        btnexit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

    }

}
