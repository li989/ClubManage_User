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

import com.example.club.control.example.ClubManager;
import com.example.club.control.example.User_ClubManager;
import com.example.club.model.Beanclub;
import com.example.club.util.BaseException;

import any.BitmapToRound_Util;

public class club_main extends AppCompatActivity {
    Beanclub club=new Beanclub();
    private Handler handler=new Handler(){
        public void handleMessage(Message msg){
            try {
            TextView clubname = (TextView) findViewById(R.id.textView5);
            TextView clubtype = (TextView) findViewById(R.id.textView13);
            TextView clubremark=(TextView) findViewById(R.id.textView15);
            ImageView picture=(ImageView)findViewById(R.id.imageView);
            Beanclub a=(Beanclub)msg.obj;
            if(a.getClub_picture()!=null){
            byte[] datas = a.getClub_picture();
            Bitmap bitmap1 = BitmapFactory.decodeByteArray(datas, 0, datas.length);
                bitmap1=(new BitmapToRound_Util()).toRoundBitmap(bitmap1);
                picture.setImageBitmap(bitmap1);
            }
            else
                picture.setBackground(null);
            clubname.setText(a.getClub_name());
            clubremark.setText(a.getClub_remark());
            clubtype.setText(a.getClub_type());
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.club_main);
        Intent intent=getIntent();
        final int club_id=intent.getIntExtra("id",0);
        new Thread(){
            @Override
            public void run() {
                try {
                    Beanclub club1=new Beanclub();
                    ClubManager a=new ClubManager();
                    User_ClubManager user=new User_ClubManager();
                    club1=a.getClub(club_id);
                    Message message=new Message();
                    message.obj=club1;
                    club=club1;
                    handler.sendMessage(message);
                } catch (BaseException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        Button btnexit=(Button)findViewById(R.id.btnback);
        Button btncy=(Button)findViewById(R.id.btncy);
        Button btngg=(Button)findViewById(R.id.btngg);
        Button btnry=(Button)findViewById(R.id.btnry);
        Button btnpm=(Button)findViewById(R.id.btnpm);
        Button btnzm=(Button)findViewById(R.id.btnzm);
        Button btnhd=(Button)findViewById(R.id.btnhd);
        Button btnxq=(Button)findViewById(R.id.btnxq);
        Button btnsqlb=(Button)findViewById(R.id.btnsqlb);
        btnexit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
        btnhd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("club_ID",club_id);
                intent.setClass(club_main.this, club_act.class);
                startActivity(intent);
            }
        });
        btnhd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("club_ID",club_id);
                intent.setClass(club_main.this, club_act.class);
                startActivity(intent);
            }
        });
        btngg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("club_ID",club_id);
                intent.setClass(club_main.this, club_notice.class);
                startActivity(intent);
            }
        });
        btnry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("club_ID",club_id);
                intent.setClass(club_main.this, get_award.class);
                startActivity(intent);
            }
        });
        btncy.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("club_ID",club_id);
                intent.setClass(club_main.this, club_person.class);
                startActivity(intent);
            }
        });
        btnzm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("club_ID",club_id);
                intent.setClass(club_main.this, get_income.class);
                startActivity(intent);
            }
        });
        btnpm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("club_ID",club_id);
                intent.setClass(club_main.this, club_num.class);
                startActivity(intent);
            }
        });
        btnxq.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("club_ID",club_id);
                intent.setClass(club_main.this, club_introducein.class);
                startActivity(intent);
            }
        });
        btnsqlb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("club_ID",club_id);
                intent.setClass(club_main.this, club_joinlist.class);
                startActivity(intent);
            }
        });
    }

}
