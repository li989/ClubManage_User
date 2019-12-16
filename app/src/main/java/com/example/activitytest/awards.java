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

import com.example.club.control.example.AwardsManager;
import com.example.club.model.Beanawards;
import com.example.club.util.BaseException;

import static com.mysql.jdbc.AbandonedConnectionCleanupThread.shutdown;

public class awards extends AppCompatActivity {
    private Handler handler=new Handler(){
        public void handleMessage(Message msg){
            Beanawards awards=(Beanawards) msg.obj;
            ImageView info_h_head=(ImageView)findViewById(R.id.image_notice_digital);
            TextView per_t1=(TextView)findViewById(R.id.textView19);
            TextView per_t3=(TextView)findViewById(R.id.TextView_create_time2);
            per_t1.setText(awards.getAwards_name());
            if(awards.getAwards_time()!=null){
                per_t3.setText(""+new java.sql.Date(awards.getAwards_time().getTime()));
            }
            if(awards.getAwards_picture()!=null){
                Bitmap bitmap1 = BitmapFactory.decodeByteArray(awards.getAwards_picture(), 0, awards.getAwards_picture().length);
                info_h_head.setBackground(null);
                info_h_head.setImageBitmap(bitmap1);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.awards);
        Button btnexit=(Button)findViewById(R.id.button_notice_digital_exit);

        Intent it = getIntent();
        final int ID = it.getIntExtra("id",0);
        new Thread(){
            @Override
            public void run() {
                try {
                    Beanawards awards=new Beanawards();
                    AwardsManager uc=new AwardsManager();
                    awards=uc.loadawards(ID);
                    Message message=new Message();
                    message.obj=awards;
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
