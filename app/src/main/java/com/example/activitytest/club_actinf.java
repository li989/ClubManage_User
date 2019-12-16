package com.example.activitytest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.club.control.example.ActivityManager;
import com.example.club.control.example.ClubManager;
import com.example.club.control.example.PlaceManager;
import com.example.club.model.Beanactivity;
import com.example.club.util.BaseException;

import static com.mysql.jdbc.AbandonedConnectionCleanupThread.shutdown;

public class club_actinf extends AppCompatActivity {
    private Handler handler=new Handler(){
        public void handleMessage(Message msg){
            try {
            Beanactivity activity=(Beanactivity) msg.obj;
            String clubname=msg.getData().getString("clubname");
            String placename=msg.getData().getString("placename");
            ImageView info_h_head=(ImageView)findViewById(R.id.image_notice_digital);
            TextView per_t1=(TextView)findViewById(R.id.textView19);
            TextView per_t2=(TextView)findViewById(R.id.textView23);
            TextView per_t3=(TextView)findViewById(R.id.textView21);
            TextView per_t4=(TextView)findViewById(R.id.textView25);
            TextView per_t5=(TextView)findViewById(R.id.textView27);
            TextView per_t6=(TextView)findViewById(R.id.TextView_notice_digital_content);
            per_t1.setText(activity.getActivity_name());
            per_t2.setText(clubname);
            if(activity.getActivity_start_time()!=null){
                per_t3.setText(""+new java.sql.Date(activity.getActivity_start_time().getTime()));
            }
            if(activity.getActivity_finish_time()!=null){
                per_t4.setText(""+new java.sql.Date(activity.getActivity_finish_time().getTime()));
            }
            per_t5.setText(placename);
            per_t6.setText(activity.getActivity_centent());
            if(activity.getActivity_picture()!=null){
                Bitmap bitmap1 = BitmapFactory.decodeByteArray(activity.getActivity_picture(), 0,activity.getActivity_picture().length);
                info_h_head.setBackground(null);
                info_h_head.setImageBitmap(bitmap1);
            }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.club_actinf);
        Button btnexit=(Button)findViewById(R.id.button_notice_digital_exit);
        Intent it = getIntent();
        final int ID = it.getIntExtra("id",0);
        Log.e("op",""+ID);
        new Thread(){
            @Override
            public void run() {
                try {
                    Beanactivity activity=new Beanactivity();
                    ClubManager club=new ClubManager();
                    PlaceManager place=new PlaceManager();
                    ActivityManager uc=new ActivityManager();
                    activity=uc.loadactivitya(ID);
                    Bundle bundle = new Bundle();
                    bundle.putString("clubname",club.getClub(activity.getClub_ID()).getClub_name());
                    bundle.putString("placename",place.getPlacea(activity.getPlace_ID()).getPlace_name());
                    Message message=new Message();
                    message.obj=activity;
                    message.setData(bundle);
                    handler.sendMessage(message);
                    shutdown();
                } catch (BaseException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        btnexit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }
}
