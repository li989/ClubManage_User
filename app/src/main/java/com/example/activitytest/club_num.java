package com.example.activitytest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adapter.ClubnumAdapter;
import com.example.club.control.example.ClubManager;
import com.example.club.model.Beanclub;
import com.example.club.util.BaseException;

import java.util.ArrayList;
import java.util.List;

public class club_num extends AppCompatActivity {
    private List<Beanclub> mclubs=new ArrayList<Beanclub>();
    int club_id;
    private Handler handler=new Handler(){
        public void handleMessage(Message msg){
            List<Beanclub> clubs=(ArrayList<Beanclub>)msg.obj;
            RecyclerView recyclerView=(RecyclerView)findViewById(R.id.RecyclerView_activity_all);
            LinearLayoutManager layoutManager=new LinearLayoutManager(club_num.this);
            recyclerView.setLayoutManager(layoutManager);
            ClubnumAdapter adapter=new ClubnumAdapter(clubs);
            recyclerView.setAdapter(adapter);
            Beanclub club1=new Beanclub();
            int num=0;
            for(int i=0;i<clubs.size();i++){
                if(clubs.get(i).getClub_ID()==club_id){
                    club1=clubs.get(i);
                    num=i+1;
                }
            }
            TextView text1=(TextView)findViewById(R.id.TextView_club_num_min);
            TextView text2=(TextView)findViewById(R.id.TextView_club_num_name);
            TextView text3=(TextView)findViewById(R.id.TextView_club_num_grade);
            ImageView img=(ImageView)findViewById(R.id.Image_club_num);
            text1.setText(""+num);
            text2.setText(club1.getClub_name());
            text3.setText(""+club1.getClub_grade());
            Bitmap bitmap1 = BitmapFactory.decodeByteArray(club1.getClub_picture(), 0,club1.getClub_picture().length);
            img.setImageBitmap(bitmap1);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.club_num);
        Intent it=getIntent();
        club_id=it.getIntExtra("club_ID",0);
        new Thread(){
            @Override
            public void run() {
                try {
                    ClubManager a=new ClubManager();
                    mclubs=a.loadAllpx();
                    Message message=new Message();
                    message.obj=mclubs;
                    handler.sendMessage(message);
                } catch (BaseException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        Button btnexit=(Button)findViewById(R.id.button_activity_all_exit);
        btnexit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }
}
