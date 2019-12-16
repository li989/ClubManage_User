package com.example.activitytest;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.club.control.example.UserManager;
import com.example.club.model.Beanuser;
import com.example.club.util.BaseException;

import java.util.List;

public class ChangeSex extends AppCompatActivity {
    private Spinner spinner;
    private List<String> data_list;
    private ArrayAdapter<String> arr_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_sex);

        Intent inte=getIntent();
        Button btnexit=(Button)findViewById(R.id.button_change_sex_exit) ;
        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("default","exit");
                setResult(0,intent);
                finish();
            }
        });

        final RadioGroup radgroup = (RadioGroup) findViewById(R.id.radioGroup);
        RadioButton man=(RadioButton)findViewById(R.id.btnMan);
        RadioButton woman=(RadioButton)findViewById(R.id.btnWoman);

        Button btnsave=(Button)findViewById(R.id.TextView_change_sex_button) ;
        for (int i = 0; i < radgroup.getChildCount(); i++) {
            RadioButton rd = (RadioButton) radgroup.getChildAt(i);
            if (rd.getText().toString().equals(inte.getStringExtra("default"))) {
                rd.setChecked(true);
                break;
            }
        }
        btnsave.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String s="";
                for (int i = 0; i < radgroup.getChildCount(); i++) {
                    RadioButton rd = (RadioButton) radgroup.getChildAt(i);
                    if (rd.isChecked()) {
                        s+=rd.getText().toString();
                        break;
                    }
                }
                final String sexs=s;

                AlertDialog alertDialog2 = new AlertDialog.Builder(ChangeSex.this)
                        .setTitle("确认修改")
                        .setMessage("是否保存修改信息")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加"Yes"按钮
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                new Thread(){
                                    public void run() {
                                        try {
                                            UserManager a=new UserManager();
                                            Beanuser.currentLoginUser.setC_sex(sexs);
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
                                intent.putExtra("default","sex");
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




