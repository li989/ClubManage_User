package com.example.activitytest;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.club.control.example.UserManager;
import com.example.club.model.Beanuser;
import com.example.club.util.BaseException;

public class ChangePwd extends AppCompatActivity {
    private Handler handler=new Handler(){
        public void handleMessage(Message msg){
            String s=(String)msg.obj;
            AlertDialog alertDialog2 = new AlertDialog.Builder(ChangePwd.this)
                    .setMessage(s)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加"Yes"按钮
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    })
                    .create();
            alertDialog2.show();

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_pwd);
        Button btnexit=(Button)findViewById(R.id.button_change_pwd_exit) ;
        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        final EditText edt1=(EditText)findViewById(R.id.EditText_change_pwd_theme1) ;
        final EditText edt2=(EditText)findViewById(R.id.EditText_change_pwd_theme2) ;
        final EditText edt3=(EditText)findViewById(R.id.EditText_change_pwd_theme3) ;
        Button btnsave=(Button)findViewById(R.id.TextView_change_pwd_button) ;
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog2 = new AlertDialog.Builder(ChangePwd.this)
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
                                            Message msg=new Message();
                                            if (a.login(Beanuser.currentLoginUser.getC_number(),edt1.getText().toString()) != null){
                                                if(edt2.getText().toString().equals(edt3.getText().toString())){
                                                    a.changepwd(Beanuser.currentLoginUser,edt2.getText().toString());
                                                    finish();
                                                }else
                                                    msg.obj="输入密码不一致";
                                            }else
                                                msg.obj="原密码不正确";
                                            Bundle bundle = new Bundle();
                                            bundle.putString("name","1");  //往Bundle中存放数据
                                            msg.setData(bundle);
                                            handler.sendMessage(msg);
                                        } catch (Exception e) {
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



    }
}
