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
import com.example.club.control.example.User_ClubManager;
import com.example.club.model.Beanclub;
import com.example.club.model.Beanuser;
import com.example.club.util.BaseException;

import any.BitmapToRound_Util;

import static com.mysql.jdbc.AbandonedConnectionCleanupThread.shutdown;


public class club_introduceout extends AppCompatActivity {
    public static final int UPDate_TEXT=1;
    private Beanclub club=new Beanclub();
    private Handler handler=new Handler(){
        public void handleMessage(Message msg){
            TextView clubname = (TextView) findViewById(R.id.textView_club_introduction_clubname2);
            TextView clubamount = (TextView) findViewById(R.id.textView_club_introduction_clubnum2);
            TextView clubpro = (TextView) findViewById(R.id.textView_club_introduction_clubpro2);
            TextView clubtime = (TextView) findViewById(R.id.textView_club_introduction_clubtime2);
            TextView clubgrade = (TextView) findViewById(R.id.textView_club_introduction_grade2);
            TextView clubremark=(TextView) findViewById(R.id.textView_club_introduction_inf2);
            ImageView clubpic=( ImageView)findViewById(R.id.icon_club_introduction_head);
            Button btnjoin=(Button)findViewById(R.id.button);
            String name=msg.getData().getString("name");
            if(msg.getData().getBoolean("bool")){
                btnjoin.setVisibility(View.GONE);
            }
            Beanclub a=(Beanclub)msg.obj;
            clubname.setText(a.getClub_name());
            clubamount.setText(""+a.getClub_amount());
            clubpro.setText(name);
            clubtime.setText(a.getClub_createtime().toString());
            clubgrade.setText(""+a.getClub_grade());
            clubremark.setText(a.getClub_remark());
            if(a.getClub_picture()!=null){
                Bitmap bitmap1 = BitmapFactory.decodeByteArray(a.getClub_picture(), 0, a.getClub_picture().length);
                bitmap1=(new BitmapToRound_Util()).toRoundBitmap(bitmap1);
                clubpic.setImageBitmap(bitmap1);
            }

        }
    };
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.club_introductionout);
        final Button btnjoin=(Button)findViewById(R.id.button);
        new Thread(){
            @Override
            public void run() {
                try {

                    ClubManager a=new ClubManager();
                    Intent intent=getIntent();
                    club=a.getClub(intent.getIntExtra("id",0));
                    UserManager user=new UserManager();
                    Message message=new Message();
                    message.obj=club;
                    Bundle bundle = new Bundle();
                    bundle.putString("name",user.loadselectpro(club.getClub_proID()));  //往Bundle中存放数据
                    User_ClubManager uscl=new User_ClubManager();
                    bundle.putBoolean("bool", uscl.getuser_club(Beanuser.currentLoginUser.getC_userID(),club.getClub_ID()));
                    message.setData(bundle);
                    handler.sendMessage(message);
                    shutdown();
                } catch (BaseException | InterruptedException e) {
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
        btnjoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    @Override
                    public void run() {
                        try {
                            User_ClubManager a=new User_ClubManager();
                            a.adduser_club(Beanuser.currentLoginUser,club);
                        } catch (BaseException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
                btnjoin.setVisibility(View.GONE);
            }
        });

    }
}