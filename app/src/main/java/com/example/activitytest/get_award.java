package com.example.activitytest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adapter.awardAdapter;
import com.example.club.control.example.AwardsManager;
import com.example.club.control.example.User_ClubManager;
import com.example.club.model.Beanawards;
import com.example.club.model.Beanuser;
import com.example.club.util.BaseException;

import java.util.ArrayList;
import java.util.List;

import static com.mysql.jdbc.AbandonedConnectionCleanupThread.shutdown;

public class get_award extends AppCompatActivity {
    int club_id;
    List<Beanawards> awards=new ArrayList<Beanawards>();
    public static void actionStart(Context context, int ID) {
        Intent intent = new Intent();
        intent.putExtra("id",ID);
        intent.setClass(context, awards.class);
        context.startActivity(intent);
    }
    private Handler handler=new Handler(){
        public void handleMessage(Message msg){
            try {
            awards=(ArrayList<Beanawards>)msg.obj;
            RecyclerView recyclerView=(RecyclerView)findViewById(R.id.get_award_recy);
            LinearLayoutManager layoutManager=new LinearLayoutManager(get_award.this);
            recyclerView.setLayoutManager(layoutManager);
            awardAdapter adapter=new awardAdapter(awards);
            recyclerView.setAdapter(adapter);
            Button acclno_release2=(Button)findViewById(R.id.Button_activity_get_award_release2);
            if(msg.getData().getBoolean("bool")){
                acclno_release2.setVisibility(View.VISIBLE);
            }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    private Handler mhandler=new Handler(){
        public void handleMessage(Message msg){
            String name=msg.getData().getString("name");
            if(name!=null){
                List<Beanawards> award1=new ArrayList<Beanawards>();
                Log.e("dfsd",name);
                for (int i=0;i<awards.size();i++){
                    if (awards.get(i).getAwards_name().matches(".*?"+name+".*?")){
                        award1.add(awards.get(i));
                    }
                }
                RecyclerView recyclerView=(RecyclerView)findViewById(R.id.get_award_recy);
                LinearLayoutManager layoutManager=new LinearLayoutManager(get_award.this);
                recyclerView.setLayoutManager(layoutManager);
                awardAdapter adapter=new awardAdapter(award1);
                recyclerView.setAdapter(adapter);
            }
        }
    };
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_award);
        Intent it=getIntent();
        club_id=it.getIntExtra("club_ID",0);
        new Thread(){
            @Override
            public void run() {
                try {
                    List<Beanawards> awards=new ArrayList<Beanawards>();
                    User_ClubManager user=new User_ClubManager();
                    AwardsManager uc=new AwardsManager();
                    awards=uc.loadAllclub(club_id);
                    Message message=new Message();
                    message.obj=awards;
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("bool",user.getuser_clubpro(Beanuser.currentLoginUser.getC_userID(),club_id));  //往Bundle中存放数据
                    message.setData(bundle);
                    handler.sendMessage(message);
                    shutdown();
                } catch (BaseException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        Button btnexit=(Button)findViewById(R.id.button_activity_get_award_exit);
        btnexit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
        Button acclno_release2=(Button)findViewById(R.id.Button_activity_get_award_release2);
        acclno_release2.setVisibility(View.GONE);
        Button btnadd=(Button)findViewById(R.id.Button_activity_get_award_release2);
        btnadd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("id",club_id);
                intent.setClass(get_award.this, apply_award.class);
                startActivity(intent);
            }
        });
        final EditText editText = (EditText) findViewById(R.id.activity_get_award_select);
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
