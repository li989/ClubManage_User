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

import com.example.club.control.example.ClubNoticeManager;
import com.example.club.model.Beanclubnotice;
import com.example.club.util.BaseException;

import static com.mysql.jdbc.AbandonedConnectionCleanupThread.shutdown;

public class club_notice_digital extends AppCompatActivity {
    private Handler handler=new Handler(){
        public void handleMessage(Message msg){
            try {
            Beanclubnotice clubnotice=(Beanclubnotice) msg.obj;
            ImageView info_h_head=(ImageView)findViewById(R.id.image_notice_digital);
            TextView per_t1=(TextView)findViewById(R.id.textView19);
            TextView per_t2=(TextView)findViewById(R.id.TextView_notice_digital_content);
            TextView per_t3=(TextView)findViewById(R.id.TextView_create_time2);
            per_t1.setText(clubnotice.getClubnotice_title());
            per_t2.setText(clubnotice.getClubnotice_content());
            if(clubnotice.getClubnotice_start_time()!=null){
                per_t3.setText(""+new java.sql.Date(clubnotice.getClubnotice_start_time().getTime()));
            }
            if(clubnotice.getClubnotice_picture()!=null){
                Bitmap bitmap1 = BitmapFactory.decodeByteArray(clubnotice.getClubnotice_picture(), 0, clubnotice.getClubnotice_picture().length);
                info_h_head.setImageBitmap(bitmap1);
                info_h_head.setBackground(null);
            }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.club_notice_digital);
        Button btnexit=(Button)findViewById(R.id.button_notice_digital_exit);
        Intent it = getIntent();
        final int ID = it.getIntExtra("id",0);
        btnexit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
        new Thread(){
            @Override
            public void run() {
                try {
                    Beanclubnotice notice=new Beanclubnotice();
                    ClubNoticeManager uc=new ClubNoticeManager();
                    uc .updateClubnoticen(ID);
                    notice=uc.select_aClubnotice(ID);
                    Message message=new Message();
                    message.obj=notice;
                    handler.sendMessage(message);
                    shutdown();
                } catch (BaseException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
