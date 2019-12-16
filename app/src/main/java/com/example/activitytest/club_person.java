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

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adapter.personAdapter;
import com.example.club.control.example.User_ClubManager;
import com.example.club.model.Beanclub_person;
import com.example.club.util.BaseException;

import java.util.ArrayList;
import java.util.List;

import static com.mysql.jdbc.AbandonedConnectionCleanupThread.shutdown;

public class club_person extends AppCompatActivity {
    int club_id;
    List<Beanclub_person> user=new ArrayList<Beanclub_person>();
    public static void actionStart(Context context, Beanclub_person clper) {
        Intent intent = new Intent();
        intent.putExtra("user_id",clper.getC_userID());
        intent.putExtra("club_id",clper.getClub_ID());
        intent.setClass(context, club_perinf.class);
        context.startActivity(intent);
    }
    private Handler handler=new Handler(){
        public void handleMessage(Message msg){
            try {
            user=(ArrayList<Beanclub_person>)msg.obj;
            RecyclerView recyclerView=(RecyclerView)findViewById(R.id.get_award_recy);
            LinearLayoutManager layoutManager=new LinearLayoutManager(club_person.this);
            recyclerView.setLayoutManager(layoutManager);
            personAdapter adapter=new personAdapter(user);
            recyclerView.setAdapter(adapter);
            }catch (Exception e) {
                e.printStackTrace();
            }}

    };
    private Handler mhandler=new Handler(){
        public void handleMessage(Message msg){
            try {
    String name=msg.getData().getString("name");
            if(name!=null){
        List<Beanclub_person> user1=new ArrayList<Beanclub_person>();
        for (int i=0;i<user.size();i++){
            if (user.get(i).getC_name().matches(".*?"+name+".*?")){
                user1.add(user.get(i));
            }
        }
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.get_award_recy);
        LinearLayoutManager layoutManager=new LinearLayoutManager(club_person.this);
        recyclerView.setLayoutManager(layoutManager);
        personAdapter adapter=new personAdapter(user1);
        recyclerView.setAdapter(adapter);
    }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    protected void onCreate(Bundle savedInstanceState) {
        Intent it=getIntent();
        club_id=it.getIntExtra("club_ID",0);
        new Thread(){
            @Override
            public void run() {
                try {
                    List<Beanclub_person> user=new ArrayList<Beanclub_person>();
                    User_ClubManager uc=new User_ClubManager();
                    user=uc.loadalluser_club(club_id);
                    Message message=new Message();
                    message.obj=user;
                    handler.sendMessage(message);
                    shutdown();
                } catch (BaseException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.club_person);
        Button btnexit=(Button)findViewById(R.id.button_activity_club_person_exit);
        btnexit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
        final EditText editText = (EditText) findViewById(R.id.activity_club_person_select);
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                Message message=new Message();
                Bundle bundle = new Bundle();
                bundle.putString("name",editText.getText().toString());
                message.setData(bundle);
                mhandler.sendMessage(message);
            }
        });
    }
}
