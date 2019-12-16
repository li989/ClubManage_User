package com.example.activitytest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adapter.incomeAdapter;
import com.example.club.control.example.IncomeManager;
import com.example.club.control.example.User_ClubManager;
import com.example.club.model.Beanincome;
import com.example.club.model.Beanuser;
import com.example.club.util.BaseException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.mysql.jdbc.AbandonedConnectionCleanupThread.shutdown;

public class get_income extends AppCompatActivity {
    int club_id;
    int count=0;
    List<Beanincome> income=new ArrayList<Beanincome>();
    private Handler handler=new Handler(){
        public void handleMessage(Message msg){
            try {
            income=(ArrayList<Beanincome>)msg.obj;
            RecyclerView recyclerView=(RecyclerView)findViewById(R.id.school_notice_rela);
            LinearLayoutManager layoutManager=new LinearLayoutManager(get_income.this);
            recyclerView.setLayoutManager(layoutManager);
            incomeAdapter adapter=new incomeAdapter(income);
            recyclerView.setAdapter(adapter);
            Button acclno_release2=(Button)findViewById(R.id.Button_get_income_release2);
            TextView getin=(TextView)findViewById(R.id.get_income_output);
            TextView putin=(TextView)findViewById(R.id.get_income_input);
            getin.setText("支出:"+Math.abs(msg.getData().getDouble("getin")));
            putin.setText("收入:"+msg.getData().getDouble("putin"));
            if(msg.getData().getBoolean("bool")){
                acclno_release2.setVisibility(View.VISIBLE);
            }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_income);
        Intent it=getIntent();
        club_id=it.getIntExtra("club_ID",0);
        new Thread(){
            @Override
            public void run() {
                try {
                    List<Beanincome> income=new ArrayList<Beanincome>();
                    User_ClubManager user=new User_ClubManager();
                    IncomeManager uc=new IncomeManager();
                    income=uc.loadallincome(club_id);
                    double getin=uc.loadgetincome(club_id);
                    double putin=uc.loadputincome(club_id);
                    Message message=new Message();
                    message.obj=income;
                    Bundle bundle = new Bundle();
                    bundle.putDouble("getin",putin);
                    bundle.putDouble("putin",getin);
                    bundle.putBoolean("bool",user.getuser_clubpro(Beanuser.currentLoginUser.getC_userID(),club_id));  //往Bundle中存放数据
                    message.setData(bundle);
                    handler.sendMessage(message);
                    shutdown();
                } catch (BaseException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        Button acclno_release2=(Button)findViewById(R.id.Button_get_income_release2);
        acclno_release2.setVisibility(View.GONE);
        Button acclno_release3=(Button)findViewById(R.id.get_income_button2);
        acclno_release3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(count%2==0){
                List<Beanincome> income1=new ArrayList<Beanincome>();
                for (int i=0;i<income.size();i++){
                    if (isThisMonth(income.get(i).getIncome_time().getTime())){
                        income1.add(income.get(i));
                    }
                }
                Button acclno_release3=(Button)findViewById(R.id.get_income_button2);
                acclno_release3.setBackground(getResources().getDrawable(R.drawable.button_circle_shape1));
                RecyclerView recyclerView=(RecyclerView)findViewById(R.id.school_notice_rela);
                LinearLayoutManager layoutManager=new LinearLayoutManager(get_income.this);
                recyclerView.setLayoutManager(layoutManager);
                incomeAdapter adapter=new incomeAdapter(income1);
                recyclerView.setAdapter(adapter);
               }
                else{
                RecyclerView recyclerView=(RecyclerView)findViewById(R.id.school_notice_rela);
                LinearLayoutManager layoutManager=new LinearLayoutManager(get_income.this);
                recyclerView.setLayoutManager(layoutManager);
                incomeAdapter adapter=new incomeAdapter(income);
                recyclerView.setAdapter(adapter);
                Button acclno_release3=(Button)findViewById(R.id.get_income_button2);
                    acclno_release3.setBackground(getResources().getDrawable(R.drawable.button_circle_shape));
            }
                count++;
                }
        });

        Button btnexit=(Button)findViewById(R.id.button_get_income_exit);
        btnexit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
        Button btnadd=(Button)findViewById(R.id.Button_get_income_release2);
        btnadd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("id",club_id);
                intent.setClass(get_income.this, income_add.class);
                startActivity(intent);
            }
        });
    }

    public static boolean isThisMonth(long time)
    {
        return isThisTime(time,"yyyy-MM");
    }
    private static boolean isThisTime(long time,String pattern) {
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String param = sdf.format(date);//参数时间
        String now = sdf.format(new Date());//当前时间
        if(param.equals(now)){
            return true;
        }
        return false;
    }

}
