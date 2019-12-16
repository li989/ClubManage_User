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
import com.example.club.control.example.UserManager;
import com.example.club.model.Beanclub;
import com.example.club.util.BaseException;

import any.BitmapToRound_Util;


public class club_introducein extends AppCompatActivity {
    public static final int UPDate_TEXT=1;
    private TextView text;
    private Handler handler=new Handler(){
        public void handleMessage(Message msg){
            try {
            TextView clubname = (TextView) findViewById(R.id.textView_club_introduction_clubname2);
            TextView clubamount = (TextView) findViewById(R.id.textView_club_introduction_clubnum2);
            TextView clubpro = (TextView) findViewById(R.id.textView_club_introduction_clubpro2);
            TextView clubtime = (TextView) findViewById(R.id.textView_club_introduction_clubtime2);
            TextView clubgrade = (TextView) findViewById(R.id.textView_club_introduction_grade2);
            TextView clubcentent = (TextView) findViewById(R.id.textView_club_introduction_inf2);
            ImageView clubpic=( ImageView)findViewById(R.id.icon_club_introduction_head);
            String name=msg.getData().getString("name");
            Beanclub a=(Beanclub)msg.obj;
            clubname.setText(a.getClub_name());
            clubamount.setText(""+a.getClub_amount());
            clubpro.setText(name);
            clubtime.setText(a.getClub_createtime().toString());
            clubgrade.setText(""+a.getClub_grade());
            clubcentent.setText(a.getClub_remark());
            if(a.getClub_picture()!=null){
                Bitmap bitmap1 = BitmapFactory.decodeByteArray(a.getClub_picture(), 0, a.getClub_picture().length);
                bitmap1=(new BitmapToRound_Util()).toRoundBitmap(bitmap1);
                clubpic.setImageBitmap(bitmap1);
            }
            }catch (Exception e) {
                e.printStackTrace();
            }
            }
        };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.club_introductionin);
        Intent intent=getIntent();
        final int club_ID=intent.getIntExtra("club_ID",0);
        new Thread(){
            @Override
            public void run() {
                try {
                    Beanclub club=new Beanclub();
                    ClubManager a=new ClubManager();
                    club=a.getClub(club_ID);
                    Message message=new Message();
                    UserManager user=new UserManager();
                    message.obj=club;
                    Bundle bundle = new Bundle();
                    bundle.putString("name",user.loadselectpro(club.getClub_proID()));  //往Bundle中存放数据
                    message.setData(bundle);
                    handler.sendMessage(message);
                } catch (BaseException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        Button btnexit=(Button)findViewById(R.id.button_club_introduction_exit);
        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
