package com.example.activitytest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import com.example.adapter.cluballAdapter;
import com.example.adapter.myclAdapter;
import com.example.club.control.example.ClubManager;
import com.example.club.model.Beanclub;
import com.example.club.model.Beanmycl;
import com.example.club.util.BaseException;

import java.util.ArrayList;
import java.util.List;

public class club_classify extends AppCompatActivity{
    public static int actid=0;

    String cla[] = {"社会实践","文学艺术","学术科技","爱心公益","体育健身","综合"};
    private List<Beanclub> mclubs=new ArrayList<Beanclub>();
    private Handler handler=new Handler(){
        public void handleMessage(Message msg){
            List<Beanclub> clubs=(ArrayList<Beanclub>)msg.obj;
            RecyclerView recyclerView=(RecyclerView)findViewById(R.id.club_classify_rela);
            LinearLayoutManager layoutManager=new LinearLayoutManager(club_classify.this);
            recyclerView.setLayoutManager(layoutManager);
            cluballAdapter adapter=new cluballAdapter(clubs);
            recyclerView.setAdapter(adapter);

        }
    };
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.club_classify);
        setrela();
        final TextView activity1=findViewById(R.id.tv_event_log_activity1);
        final TextView activity2=findViewById(R.id.tv_event_log_activity2);
        final TextView activity3=findViewById(R.id.tv_event_log_activity3);
        final TextView activity4=findViewById(R.id.tv_event_log_activity4);
        final TextView activity5=findViewById(R.id.tv_event_log_activity5);
        final TextView activity6=findViewById(R.id.tv_event_log_activity6);
        final HorizontalScrollView mHorizontalScrollView=(HorizontalScrollView)findViewById(R.id.horizontalScrollView);
        color(activity1,activity2,activity3,activity4,activity5,activity6);
        activity1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                color(activity1,activity2,activity3,activity4,activity5,activity6);
                actid=0;
                setrela();
                WindowManager wm1 = club_classify.this.getWindowManager();
                int screenWidth = wm1.getDefaultDisplay().getWidth();
                int rb_px = (int) activity1.getX() + activity1.getWidth() / 2;
                mHorizontalScrollView.scrollTo(rb_px - screenWidth / 2, 0);
            }
        });
        activity2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0){
                color(activity2,activity1,activity3,activity4,activity5,activity6);
                actid=1;
                setrela();
                WindowManager wm1 = club_classify.this.getWindowManager();
                int screenWidth = wm1.getDefaultDisplay().getWidth();
                int rb_px = (int) activity2.getX() + activity2.getWidth() / 2;
                mHorizontalScrollView.scrollTo(rb_px - screenWidth / 2, 0);

            }
        });
        activity3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0){
                color(activity3,activity2,activity1,activity4,activity5,activity6);
                actid=2;
                setrela();
                WindowManager wm1 = club_classify.this.getWindowManager();
                int screenWidth = wm1.getDefaultDisplay().getWidth();
                int rb_px = (int) activity3.getX() + activity3.getWidth() / 2;
                mHorizontalScrollView.scrollTo(rb_px - screenWidth / 2, 0);
            }
        });
        activity4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0){
                color(activity4,activity2,activity3,activity1,activity5,activity6);
                actid=3;
                setrela();
                WindowManager wm1 = club_classify.this.getWindowManager();
                int screenWidth = wm1.getDefaultDisplay().getWidth();
                int rb_px = (int) activity4.getX() + activity4.getWidth() / 2;
                mHorizontalScrollView.scrollTo(rb_px - screenWidth / 2, 0);
            }
        });
        activity5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0){
                color(activity5,activity2,activity3,activity4,activity1,activity6);
                actid=4;
                setrela();
                WindowManager wm1 = club_classify.this.getWindowManager();
                int screenWidth = wm1.getDefaultDisplay().getWidth();
                int rb_px = (int) activity5.getX() + activity5.getWidth() / 2;
                mHorizontalScrollView.scrollTo(rb_px - screenWidth / 2, 0);
            }
        });
        activity6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0){
                color(activity6,activity2,activity3,activity4,activity5,activity1);
                actid=5;
                setrela();
                WindowManager wm1 = club_classify.this.getWindowManager();
                int screenWidth = wm1.getDefaultDisplay().getWidth();
                int rb_px = (int) activity6.getX() + activity6.getWidth() / 2;
                mHorizontalScrollView.scrollTo(rb_px - screenWidth / 2, 0);
            }
        });


        Button btnexit=(Button)findViewById(R.id.club_classify_exit);
        btnexit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

    }
public void color(TextView v1,TextView v2,TextView v3,TextView v4,TextView v5,TextView v6){
    v1.setTextColor(Color.parseColor("#2196F3"));
    v2.setTextColor(Color.parseColor("#8F8585"));
    v3.setTextColor(Color.parseColor("#8F8585"));
    v4.setTextColor(Color.parseColor("#8F8585"));
    v5.setTextColor(Color.parseColor("#8F8585"));
    v6.setTextColor(Color.parseColor("#8F8585"));
}
public void setrela(){

    new Thread(){
        @Override
        public void run() {
            try {
                ClubManager a=new ClubManager();
                mclubs=a.selectclaClub(cla[actid]);
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


}


}
