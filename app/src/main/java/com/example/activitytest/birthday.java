package com.example.activitytest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.club.control.example.UserManager;
import com.example.club.model.Beanuser;
import com.example.club.util.BaseException;

import java.sql.Timestamp;
import java.util.Calendar;

public class birthday extends AppCompatActivity {
    private TextView btnbegin;
    private TextView btnover;
    private Calendar cal;
    private int year,month,day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.birthday);

            Button btnexit=(Button)findViewById(R.id.brithday_exit) ;
            btnexit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent();
                    intent.putExtra("default","exit");
                    setResult(0,intent);
                    finish();
                }
             });
            btnbegin=(TextView) findViewById(R.id.brithday_starttime1);
            Intent inte=getIntent();
            btnbegin.setText(inte.getStringExtra("default"));

            btnbegin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatePickerDialog.OnDateSetListener listener=new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker arg0, int year, int month, int day) {
                            String m="";
                            String d="";
                            m+=(++month);
                            d+=day;
                            if (month<10)
                                m="0"+m;
                            if (day<10)
                                d="0"+d;
                            btnbegin.setText(year+"-"+m+"-"+d);      //将选择的日期显示到TextView中,因为之前获取month直接使用，所以不需要+1，这个地方需要显示，所以+1
                        }
                    };
                    getbeginDate();
                    DatePickerDialog dialog=new DatePickerDialog(birthday.this, DatePickerDialog.THEME_HOLO_LIGHT,listener,year,month,day);//主题在这里！后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                    dialog.show();
                }
            });

        Button btnsave=(Button)findViewById(R.id.brithday_release2) ;
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog2 = new AlertDialog.Builder(birthday.this)
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
                                            Beanuser.currentLoginUser.setC_brithday(Timestamp.valueOf(btnbegin.getText().toString()+" 00:00:00"));
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
                                intent.putExtra("default","birthday");
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
        private void getbeginDate(){
            if (btnbegin.getText()==null) {
                cal = Calendar.getInstance();
                year = cal.get(Calendar.YEAR);       //获取年月日时分秒
                month = cal.get(Calendar.MONTH);   //获取到的月份是从0开始计数
                day = cal.get(Calendar.DAY_OF_MONTH);
            }else{
                cal = Calendar.getInstance();
                String s=btnbegin.getText().toString();
                year = Integer.parseInt(s.substring(0,s.indexOf("-")));     //获取年月日时分秒
                month = Integer.parseInt(s.substring(s.indexOf("-")+1,s.indexOf("-",s.indexOf("-")+1)))-1;  //获取到的月份是从0开始计数
                day = Integer.parseInt(s.substring(s.indexOf("-",s.indexOf("-")+1)+1));
            }

        }
        //获取当前日期  结束
}
