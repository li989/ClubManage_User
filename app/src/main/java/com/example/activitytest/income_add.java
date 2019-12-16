package com.example.activitytest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.club.control.example.IncomeManager;
import com.example.club.model.Beanincome;
import com.example.club.util.BaseException;

import any.CustomDialog;

import static com.mysql.jdbc.AbandonedConnectionCleanupThread.shutdown;

public class income_add extends AppCompatActivity {
    private Handler mhandler=new Handler(){
        public void handleMessage(Message msg){
            CustomDialog customDialog = new CustomDialog(income_add.this);
            customDialog.setTitle("提醒");
            if(msg.what==1){
                customDialog.setMessage("收支数额未填写");}
            if(msg.what==2){
                customDialog.setMessage("收支详情未填写");}
            customDialog.setConfirm("confirm", new CustomDialog.IOnConfirmListener(){
                @Override
                public void onConfirm(CustomDialog dialog) {
                }
            });
            customDialog.show();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.income_add);
        Intent it = getIntent();
        final int ID = it.getIntExtra("id",0);
        Button btnrel2=(Button)findViewById(R.id.TextView_income_add_release2);
        final EditText editText1=(EditText)findViewById(R.id.EditText_club_notice_theme);
        final EditText editText2=(EditText)findViewById(R.id.EditText_club_notice_page);
        btnrel2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new Thread(){
                    @Override
                    public void run() {
                        try {
                            if(editText1.getText().toString().equals("")){
                                Message message=new Message();
                                message.what=1;
                                mhandler.sendMessage(message);
                            }
                            else if(editText2.getText().toString().equals("")){
                                Message message=new Message();
                                message.what=2;
                                mhandler.sendMessage(message);
                            }
                            else{
                            String number=editText1.getText().toString();
                            Double nu=Double.valueOf(number.toString());
                            IncomeManager income=new IncomeManager();
                            Beanincome beanincome=new Beanincome();
                            income.addincome(ID,nu,editText2.getText().toString());
                            finish();}
                            shutdown();
                        } catch (BaseException | InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });



        Button btnexit=(Button)findViewById(R.id.button_income_add_exit);
        btnexit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }

}

