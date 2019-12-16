package com.example.activitytest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.adapter.cluballAdapter;
import com.example.club.control.example.SchoolNoticeManager;
import com.example.club.control.example.UserManager;
import com.example.club.model.Beanclub;
import com.example.club.model.Beanuser;
import com.example.club.util.BaseException;

import java.util.ArrayList;
import java.util.List;

public class register extends AppCompatActivity {
    private Handler handler=new Handler(){
        public void handleMessage(Message msg){
            final String s=(String)msg.obj;
            AlertDialog alertDialog4 = new AlertDialog.Builder(register.this)
                    .setMessage(s)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加"Yes"按钮
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (s.equals("注册成功")){
                                finish();
                            }
                        }
                    })
                    .create();
            alertDialog4.show();

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        Button button1=(Button)findViewById(R.id.login_register);
        Button btnexit=(Button)findViewById(R.id.register_exit);
        Button btnre=(Button)findViewById(R.id.register_release2);
        btnexit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();

            }
        });
        final EditText editText1=(EditText)findViewById(R.id.register_mima2);
        final EditText editText2=(EditText)findViewById(R.id.register_yuanmima2);
        final EditText edtmobile=(EditText)findViewById(R.id.register_photo2);
        final TextView error=(TextView)findViewById(R.id.register_error);
        final EditText edtzh=(EditText)findViewById(R.id.register_zhang2);
        btnre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText1.getText().toString().equals(editText2.getText().toString()))
                    new Thread(){
                        @Override
                        public void run() {
                            try {
                                UserManager a=new UserManager();
                                Beanuser user=new Beanuser();
                                Message msg=new Message();
                                user.setC_mobile(edtmobile.getText().toString());
                                user.setC_pwd(editText1.getText().toString());
                                user.setC_number(edtzh.getText().toString());
                                msg.obj=a.createuser(user);
                                Bundle bundle = new Bundle();
                                bundle.putString("name","1");  //往Bundle中存放数据
                                msg.setData(bundle);
                                handler.sendMessage(msg);
                            } catch (BaseException e) {
                                e.printStackTrace();

                            }
                        }
                    }.start();
                else{
                    AlertDialog alertDialog4 = new AlertDialog.Builder(register.this)
                            .setMessage("输入密码不一致")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加"Yes"按钮
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                }
                            })
                            .create();
                    alertDialog4.show();
                }



            }
        });


        editText2.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!editText1.getText().toString().equals(editable.toString())){
                    error.setText("输入密码不一致");
                }else {
                    error.setText("");
                }
            }
        });

    }
}
