package com.example.activitytest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adapter.school_noticeAdapter;
import com.example.club.control.example.ClubManager;
import com.example.club.control.example.UserManager;
import com.example.club.control.example.User_ClubManager;
import com.example.club.model.Beanschoolnotice;
import com.example.club.model.Beanuser;
import com.example.club.util.BaseException;

import java.util.ArrayList;
import java.util.List;

public class login extends AppCompatActivity {
    Beanuser loginuser;
    private Handler handler=new Handler(){
        public void handleMessage(Message msg){
            Beanuser user=(Beanuser)msg.obj;
            if(msg.getData().getBoolean("bool")){
                Intent intent=new Intent();
                intent.setClass(login.this,MainActivity.class);
                startActivity(intent);
            }else {
                System.out.println("用户名或密码错误");
                AlertDialog alertDialog2 = new AlertDialog.Builder(login.this)
                        .setMessage("用户名或密码错误")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加"Yes"按钮
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        })
                        .create();
                alertDialog2.show();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        EditText e1=(EditText)findViewById(R.id.welcome_EditText1);
        EditText e2=(EditText)findViewById(R.id.welcome_EditText2);
        Button button1=(Button)findViewById(R.id.login_register);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
               Intent intent=new Intent(login.this,register.class);
               startActivity(intent);
            }
        });

        ImageView btn2=(ImageView)findViewById(R.id.login_enter);

        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                new Thread(){
                    @Override
                    public void run() {
                        UserManager user=new UserManager();
                        EditText edzh=(EditText)findViewById(R.id.welcome_EditText1);
                        EditText edpwd=(EditText)findViewById(R.id.welcome_EditText2);
                        loginuser=user.login(edzh.getText().toString(),edpwd.getText().toString());
                        Message message=new Message();
                        message.obj=loginuser;
                        Bundle bundle = new Bundle();
                        Beanuser.currentLoginUser=loginuser;
                        if(Beanuser.currentLoginUser==null)
                            bundle.putBoolean("bool",false );
                        else
                            bundle.putBoolean("bool",true );
                        message.setData(bundle);
                        handler.sendMessage(message);
                    }
                }.start();

            }
        });

    }
}
