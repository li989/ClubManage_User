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

import com.example.club.control.example.UserManager;
import com.example.club.control.example.User_ClubManager;
import com.example.club.model.Beanuser;
import com.example.club.util.BaseException;

import any.BitmapToRound_Util;

import static com.mysql.jdbc.AbandonedConnectionCleanupThread.shutdown;

public class club_person_info extends AppCompatActivity {
    private Handler handler=new Handler(){
        public void handleMessage(Message msg){
            try {
            Beanuser user=(Beanuser) msg.obj;
            ImageView info_h_head=(ImageView)findViewById(R.id.info_h_head);
            ImageView info_h_back=(ImageView)findViewById(R.id.info_h_back);
            TextView per_t1=(TextView)findViewById(R.id.per_t1);
            TextView per_t2=(TextView)findViewById(R.id.per_t2);
            TextView per_t3=(TextView)findViewById(R.id.per_t3);
            TextView per_t4=(TextView)findViewById(R.id.per_t4);
            TextView per_t5=(TextView)findViewById(R.id.per_t5);
            TextView per_t6=(TextView)findViewById(R.id.per_t6);
            per_t1.setText(user.getC_name());
            per_t2.setText(user.getC_sno());
            per_t3.setText(user.getC_sex());
            per_t4.setText(user.getC_mobile());
            if(user.getC_brithday()!=null){
                per_t5.setText(""+new java.sql.Date(user.getC_brithday().getTime()));
            }
            per_t6.setText(user.getC_major());
            if(user.getC_picture()!=null){
                Bitmap bitmap1 = BitmapFactory.decodeByteArray(user.getC_picture(), 0, user.getC_picture().length);
                bitmap1=(new BitmapToRound_Util()).toRoundBitmap(bitmap1);
                info_h_head.setImageBitmap(bitmap1);
                info_h_back.setImageBitmap(bitmap1);
            }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.club_person_info);
        Intent it=getIntent();
        final int user_id=it.getIntExtra("user_id",0);
        final int club_id=it.getIntExtra("club_id",0);
        Button btnexit=(Button)findViewById(R.id.perinf_exit);
        btnexit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
        new Thread(){
            @Override
            public void run() {
                try {
                    Beanuser user=new Beanuser();
                    UserManager uc=new UserManager();
                    user=uc.selectuserbyid(user_id);
                    Message message=new Message();
                    message.obj=user;
                    handler.sendMessage(message);
                    shutdown();
                } catch (BaseException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        Button perinf_exit=(Button)findViewById(R.id.perinf_exit);
        perinf_exit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
        Button button1=(Button)findViewById(R.id.button4);
        Button button2=(Button)findViewById(R.id.button5);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            User_ClubManager user = new User_ClubManager();
                            user.modifyuserclub1(club_id,user_id,"已通过");
                            finish();
                            shutdown();
                        } catch (BaseException | InterruptedException e) {
                            e.printStackTrace();
                        }
                        finish();
                    }
                }.start();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            User_ClubManager user = new User_ClubManager();
                            user.deleteuser_club(user_id,club_id);
                            finish();
                            shutdown();
                        } catch (BaseException | InterruptedException e) {
                            e.printStackTrace();
                        }
                        finish();
                    }
                }.start();
            }
        });
    }
}
