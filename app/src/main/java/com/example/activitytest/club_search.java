package com.example.activitytest;

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

import com.example.adapter.cluballAdapter;
import com.example.club.control.example.ClubManager;
import com.example.club.control.example.SchoolNoticeManager;
import com.example.club.model.Beanclub;
import com.example.club.model.Beanclubnotice;
import com.example.club.model.Beanschoolnotice;
import com.example.club.util.BaseException;

import java.util.ArrayList;
import java.util.List;

public class club_search extends AppCompatActivity {
    private List<Beanclub> mclubs=new ArrayList<Beanclub>();
    private Handler handler=new Handler(){
        public void handleMessage(Message msg){
            List<Beanclub> clubs=(ArrayList<Beanclub>)msg.obj;

            RecyclerView recyclerView=(RecyclerView)findViewById(R.id.club_search_rela);
            LinearLayoutManager layoutManager=new LinearLayoutManager(club_search.this);
            recyclerView.setLayoutManager(layoutManager);
            cluballAdapter adapter=new cluballAdapter(clubs);
            recyclerView.setAdapter(adapter);

        }
    };
    private Handler handler1=new Handler(){
        public void handleMessage(Message msg){
            List<Beanclub> clubs=(ArrayList<Beanclub>)msg.obj;
            final TextView hot1=(TextView)findViewById(R.id.club_TextView_search_hot_name1);
            final TextView hot2=(TextView)findViewById(R.id.club_TextView_search_hot_name2);
            final TextView hot3=(TextView)findViewById(R.id.club_TextView_search_hot_name3);
            final TextView hot4=(TextView)findViewById(R.id.club_TextView_search_hot_name4);
            final TextView hot5=(TextView)findViewById(R.id.club_TextView_search_hot_name5);
            final TextView hot6=(TextView)findViewById(R.id.club_TextView_search_hot_name6);
            hot1.setText(clubs.get(0).getClub_name());
            hot2.setText(clubs.get(1).getClub_name());
            hot3.setText(clubs.get(2).getClub_name());
            hot4.setText(clubs.get(3).getClub_name());
            hot5.setText(clubs.get(4).getClub_name());
            hot6.setText(clubs.get(5).getClub_name());

        }
    };
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.club_search);
        Button btnexit=(Button)findViewById(R.id.club_search_back);
        btnexit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        final LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        RecyclerView rela=(RecyclerView)findViewById(R.id.club_search_rela);
        rela.setVisibility(View.GONE);
        final TextView hot1=(TextView)findViewById(R.id.club_TextView_search_hot_name1);
        final TextView hot2=(TextView)findViewById(R.id.club_TextView_search_hot_name2);
        final TextView hot3=(TextView)findViewById(R.id.club_TextView_search_hot_name3);
        final TextView hot4=(TextView)findViewById(R.id.club_TextView_search_hot_name4);
        final TextView hot5=(TextView)findViewById(R.id.club_TextView_search_hot_name5);
        final TextView hot6=(TextView)findViewById(R.id.club_TextView_search_hot_name6);
        final EditText e=(EditText)findViewById(R.id.club_editText5);
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


        final ImageView imgv=(ImageView)findViewById(R.id.club_TextView_school_notice_release3);
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
                RecyclerView recyclerView=(RecyclerView)findViewById(R.id.club_search_rela);
                recyclerView.setVisibility(View.VISIBLE);
                TextView hot=(TextView)findViewById(R.id.club_TextView_search_hot);
                final TextView hot1=(TextView)findViewById(R.id.club_TextView_search_hot_name1);
                final TextView hot2=(TextView)findViewById(R.id.club_TextView_search_hot_name2);
                final TextView hot3=(TextView)findViewById(R.id.club_TextView_search_hot_name3);
                final TextView hot4=(TextView)findViewById(R.id.club_TextView_search_hot_name4);
                final TextView hot5=(TextView)findViewById(R.id.club_TextView_search_hot_name5);
                final TextView hot6=(TextView)findViewById(R.id.club_TextView_search_hot_name6);
                RelativeLayout hot7=(RelativeLayout)findViewById(R.id.club_search_title);
                RelativeLayout hot8=(RelativeLayout)findViewById(R.id.club_search_label);
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
                            ClubManager a=new ClubManager();
                            mclubs=a.selectClub(e.getText().toString());
                            Message message=new Message();
                            message.obj=mclubs;
                            Bundle bundle = new Bundle();
                            bundle.putString("name","1");  //往Bundle中存放数据
                            message.setData(bundle);
                            handler.sendMessage(message);
                        } catch (BaseException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();

            }
        });

        new Thread(){
            @Override
            public void run() {
                try {
                    ClubManager a=new ClubManager();
                    mclubs=a.getsixhotClub();
                    Message message=new Message();
                    message.obj=mclubs;
                    Bundle bundle = new Bundle();
                    bundle.putInt("name",1);
                    message.setData(bundle);
                    handler1.sendMessage(message);
                } catch (BaseException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
