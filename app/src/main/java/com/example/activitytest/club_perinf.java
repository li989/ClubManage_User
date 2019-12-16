package com.example.activitytest;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

public class club_perinf extends AppCompatActivity {
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
            Button button1=(Button)findViewById(R.id.button7);
            if(msg.getData().getBoolean("bool")){
                button1.setVisibility(View.VISIBLE);
            }
            if (user.getC_userID()==Beanuser.currentLoginUser.getC_userID())
                button1.setVisibility(View.INVISIBLE);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.club_perinf);
        Intent it=getIntent();
        final int user_id=it.getIntExtra("user_id",0);
        final int club_id=it.getIntExtra("club_id",0);
        new Thread(){
            @Override
            public void run() {
                try {
                    Beanuser user=new Beanuser();
                    UserManager uc=new UserManager();
                    User_ClubManager user1=new User_ClubManager();
                    user=uc.selectuserbyid(user_id);
                    Message message=new Message();
                    message.obj=user;
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("bool",user1.getuser_clubpro(Beanuser.currentLoginUser.getC_userID(),club_id));  //往Bundle中存放数据
                    message.setData(bundle);
                    handler.sendMessage(message);
                    shutdown();
                } catch (BaseException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        Button button1=(Button)findViewById(R.id.button7);
        button1.setVisibility(View.INVISIBLE);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                    AlertDialog alertDialog2 = new AlertDialog.Builder(club_perinf.this)
                            .setTitle("转让")
                            .setMessage("确定转让社长")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加"Yes"按钮
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    new Thread(){
                                        @Override
                                        public void run() {
                                            try {
                                                User_ClubManager a=new User_ClubManager();
                                                a.zhuanuser_club(user_id, Beanuser.currentLoginUser.getC_userID(),club_id);
                                                finish();
                                            } catch (BaseException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }.start();
                                }
                            })

                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {//添加取消
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                }
                            })
                            .create();
                    alertDialog2.show();
                }
        });
        Button perinf_exit=(Button)findViewById(R.id.perinf_exit);
        perinf_exit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }
}
