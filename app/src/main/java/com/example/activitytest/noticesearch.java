package com.example.activitytest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adapter.clubrankAdapter;
import com.example.adapter.school_noticeAdapter;
import com.example.club.control.example.ClubManager;
import com.example.club.control.example.SchoolNoticeManager;
import com.example.club.control.example.UserManager;
import com.example.club.model.Beanclub;
import com.example.club.model.Beanclubnotice;
import com.example.club.model.Beanschoolnotice;
import com.example.club.util.BaseException;

import java.util.ArrayList;
import java.util.List;

public class noticesearch extends AppCompatActivity {
    private List<Beanschoolnotice> mnotice=new ArrayList<Beanschoolnotice>();
    public static void actionStart(Context context, int id) {
        Intent intent = new Intent();
        intent.setClass(context, notice_digital.class);
        System.out.println(id);
        context.startActivity(intent);
    }

    private Handler handler=new Handler(){
        public void handleMessage(Message msg){
            List<Beanschoolnotice> notices=(ArrayList<Beanschoolnotice>)msg.obj;
            final TextView hot1=(TextView)findViewById(R.id.TextView_search_hot_name1);
            final TextView hot2=(TextView)findViewById(R.id.TextView_search_hot_name2);
            final TextView hot3=(TextView)findViewById(R.id.TextView_search_hot_name3);
            final TextView hot4=(TextView)findViewById(R.id.TextView_search_hot_name4);
            final TextView hot5=(TextView)findViewById(R.id.TextView_search_hot_name5);
            final TextView hot6=(TextView)findViewById(R.id.TextView_search_hot_name6);
            hot1.setText(notices.get(0).getSchoolnotice_title());
            hot2.setText(notices.get(1).getSchoolnotice_title());
            hot3.setText(notices.get(2).getSchoolnotice_title());
            hot4.setText(notices.get(3).getSchoolnotice_title());
            hot5.setText(notices.get(4).getSchoolnotice_title());
            hot6.setText(notices.get(5).getSchoolnotice_title());

        }
    };

    private Handler handler1=new Handler(){
        public void handleMessage(Message msg){
            List<Beanschoolnotice> notices=(ArrayList<Beanschoolnotice>)msg.obj;

            RecyclerView recyclerView=(RecyclerView)findViewById(R.id.notice_search_rela);
            LinearLayoutManager layoutManager=new LinearLayoutManager(noticesearch.this);
            recyclerView.setLayoutManager(layoutManager);
            school_noticeAdapter adapter=new school_noticeAdapter(notices);
            recyclerView.setAdapter(adapter);

        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notice_search);

        final EditText e=(EditText)findViewById(R.id.notice_search_edit);
        final TextView hot1=(TextView)findViewById(R.id.TextView_search_hot_name1);
        final TextView hot2=(TextView)findViewById(R.id.TextView_search_hot_name2);
        final TextView hot3=(TextView)findViewById(R.id.TextView_search_hot_name3);
        final TextView hot4=(TextView)findViewById(R.id.TextView_search_hot_name4);
        final TextView hot5=(TextView)findViewById(R.id.TextView_search_hot_name5);
        final TextView hot6=(TextView)findViewById(R.id.TextView_search_hot_name6);
        hot1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                e.setText(hot1.getText().toString());
            }
        });
        hot2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                e.setText(hot2.getText().toString());
            }
        });
        hot3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                e.setText(hot3.getText().toString());
            }
        });
        hot4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                e.setText(hot4.getText().toString());
            }
        });
        hot5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                e.setText(hot5.getText().toString());
            }
        });
        hot6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                e.setText(hot6.getText().toString());
            }
        });

        new Thread(){
            @Override
            public void run() {
                try {
                    SchoolNoticeManager a=new SchoolNoticeManager();
                    mnotice=a.getsixhot();
                    Message message=new Message();
                    message.obj=mnotice;
                    Bundle bundle = new Bundle();
                    bundle.putInt("name",1);
                    message.setData(bundle);
                    handler.sendMessage(message);
                } catch (BaseException e) {
                    e.printStackTrace();
                }
            }
        }.start();



        final LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        Button btnexit=(Button)findViewById(R.id.search_back);
        btnexit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        RecyclerView rela=(RecyclerView)findViewById(R.id.notice_search_rela);
        rela.setVisibility(View.GONE);

        final ImageView imgv=(ImageView)findViewById(R.id.TextView_school_notice_release3);

        e.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                imgv.callOnClick();
            }
        });


        imgv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RecyclerView recyclerView=(RecyclerView)findViewById(R.id.notice_search_rela);
                recyclerView.setVisibility(View.VISIBLE);
                TextView hot=(TextView)findViewById(R.id.TextView_search_hot);
                TextView hot1=(TextView)findViewById(R.id.TextView_search_hot_name1);
                TextView hot2=(TextView)findViewById(R.id.TextView_search_hot_name2);
                TextView hot3=(TextView)findViewById(R.id.TextView_search_hot_name3);
                TextView hot4=(TextView)findViewById(R.id.TextView_search_hot_name4);
                TextView hot5=(TextView)findViewById(R.id.TextView_search_hot_name5);
                TextView hot6=(TextView)findViewById(R.id.TextView_search_hot_name6);
                RelativeLayout hot7=(RelativeLayout)findViewById(R.id.search_label);
                RelativeLayout hot8=(RelativeLayout)findViewById(R.id.search_title);
                hot.setVisibility(View.GONE);
                hot1.setVisibility(View.GONE);
                hot2.setVisibility(View.GONE);
                hot3.setVisibility(View.GONE);
                hot4.setVisibility(View.GONE);
                hot5.setVisibility(View.GONE);
                hot6.setVisibility(View.GONE);
                hot7.setVisibility(View.GONE);
                hot8.setVisibility(View.GONE);
                new Thread(){
                    @Override
                    public void run() {
                        try {
                            SchoolNoticeManager a=new SchoolNoticeManager();
                            mnotice=a.selectNotice(e.getText().toString());
                            Message message=new Message();
                            message.obj=mnotice;
                            Bundle bundle = new Bundle();
                            bundle.putString("name","1");  //往Bundle中存放数据
                            message.setData(bundle);
                            handler1.sendMessage(message);
                        } catch (BaseException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();

            }
        });



    }
}
