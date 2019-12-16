package com.example.activitytest;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.club.control.example.UserManager;
import com.example.club.model.Beanuser;
import com.example.club.util.BaseException;

public class Changemobile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_mobile);
        Button btnexit=(Button)findViewById(R.id.button_change_mobile_exit) ;
        final EditText e=(EditText)findViewById(R.id.EditText_change_mobile_theme);
        Intent inte=getIntent();
        e.setText(inte.getStringExtra("default"));
        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("default","exit");
                setResult(0,intent);
                finish();
            }
        });

        Button btnsave=(Button)findViewById(R.id.TextView_change_mobile_button) ;
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog2 = new AlertDialog.Builder(Changemobile.this)
                        .setTitle("确认修改")
                        .setMessage("是否保存修改信息")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加"Yes"按钮
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                new Thread(){
                                    @Override
                                    public void run() {
                                        try {
                                            UserManager a=new UserManager();
                                            Beanuser.currentLoginUser.setC_mobile(e.getText().toString());
                                            Beanuser.currentLoginUser=a.modifyuser(Beanuser.currentLoginUser);
                                            if (Beanuser.currentLoginUser==null){
                                                Intent intent=new Intent();
                                                intent.putExtra("default","error");
                                                setResult(0,intent);
                                                finish();
                                            }

                                        } catch (BaseException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }.start();
                                Intent intent=new Intent();
                                intent.putExtra("default","mobile");
                                setResult(0,intent);
                                finish();
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
    }
}
