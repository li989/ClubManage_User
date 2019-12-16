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

import com.example.adapter.school_noticeAdapter;
import com.example.club.control.example.SchoolNoticeManager;
import com.example.club.model.Beanschoolnotice;
import com.example.club.model.Beanuser;
import com.example.club.util.BaseException;

import java.util.ArrayList;
import java.util.List;

import any.BitmapToRound_Util;

public class notice_digital extends AppCompatActivity {
    private Handler handler=new Handler(){
        public void handleMessage(Message msg){
            Beanschoolnotice notice=(Beanschoolnotice)msg.obj;
            TextView title=(TextView)findViewById(R.id.TextView_notice_digital_release);
            title.setText(notice.getSchoolnotice_title());
            TextView content=(TextView)findViewById(R.id.TextView_notice_digital_content);
            content.setText(notice.getSchoolnotice_content());
            TextView time=(TextView)findViewById(R.id.TextView_create_time2);
            time.setText(notice.getSchoolnotice_start_time().toString().substring(0,19));
            ImageView img=(ImageView)findViewById(R.id.image_notice_digital);
            Bitmap bitmap = BitmapFactory.decodeByteArray(notice.getSchoolnotice_picture(), 0, notice.getSchoolnotice_picture().length);
            img.setImageBitmap(bitmap);


        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent inte=getIntent();
        final int noticeid=inte.getIntExtra("id",-1);
        setContentView(R.layout.notice_digital);
        Button btnback=(Button)findViewById(R.id.button_notice_digital_exit);
        btnback.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
        new Thread(){
            @Override
            public void run() {
                SchoolNoticeManager a=new SchoolNoticeManager();
                Beanschoolnotice nt = a.selectNotice(noticeid);
                Message message=new Message();
                message.obj=nt;
                Bundle bundle = new Bundle();
                bundle.putString("name","1");  //往Bundle中存放数据
                message.setData(bundle);
                handler.sendMessage(message);
            }
        }.start();

    }
}
