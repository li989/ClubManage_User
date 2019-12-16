package com.example.activitytest.ui.dashboard;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.aware.DiscoverySession;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.activitytest.R;
import com.example.activitytest.*;
import com.example.activitytest.ui.home.HomeFragment;
import com.example.adapter.cluballAdapter;
import com.example.adapter.school_noticeAdapter;
import com.example.club.control.example.ClubManager;
import com.example.club.control.example.SchoolNoticeManager;
import com.example.club.model.Beanclub;
import com.example.club.model.Beanschoolnotice;
import com.example.club.model.Beanuser;
import com.example.club.util.BaseException;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment implements OnBannerListener{
    private View root=getView();
    private DashboardViewModel dashboardViewModel;
    private Banner mBanner;
    private List<Beanclub> mclubs=new ArrayList<Beanclub>();

    public static void actionStart(final Context context, final Beanclub club) {
        new Thread(){
            private Handler handler1=new Handler(){
                public void handleMessage(Message msg){
                    try {
                        String s=msg.getData().getString("name");
                        Intent intent = new Intent();
                        intent.putExtra("id",club.getClub_ID());
                        if (s==null){
                            intent.setClass(context, club_introduceout.class);
                            context.startActivity(intent);
                        } else if(s.equals("社长")){
                            intent.setClass(context, club_main.class);
                            context.startActivity(intent);
                        }else if(s.equals("社员")){
                            intent.setClass(context, club_main1.class);
                            context.startActivity(intent);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            };
            public void run() {
                ClubManager a=new ClubManager();
                String s=a.inclub(Beanuser.currentLoginUser.getC_userID(),club.getClub_ID());
                Message message=new Message();
                Bundle bundle = new Bundle();
                bundle.putString("name",s);  //往Bundle中存放数据
                message.setData(bundle);
                handler1.sendMessage(message);

            }
        }.start();

    }
    private Handler handler=new Handler(){
        public void handleMessage(Message msg){
            try {
                View root=getView();
                List<Beanclub> clubs=(ArrayList<Beanclub>)msg.obj;

                List<Beanclub> clubs1=new ArrayList<Beanclub>();
                List<Beanclub> clubs2=new ArrayList<Beanclub>();
                int i;
                for( i=0;i<5;i++){
                    clubs1.add(clubs.get(i));
                }
                for(;i<10;i++){
                    clubs2.add(clubs.get(i));
                }
                RecyclerView recyclerView=(RecyclerView)root.findViewById(R.id.club_all_rela);
                LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutManager);
                cluballAdapter adapter=new cluballAdapter(clubs1);
                recyclerView.setAdapter(adapter);

                RecyclerView recyclerView1=(RecyclerView)root.findViewById(R.id.club_all_rela1);
                LinearLayoutManager layoutManager1=new LinearLayoutManager(getActivity());
                recyclerView1.setLayoutManager(layoutManager1);
                cluballAdapter adapter1=new cluballAdapter(clubs2);
                recyclerView1.setAdapter(adapter1);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    };

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        new Thread(){
            @Override
            public void run() {
                try {
                    ClubManager a=new ClubManager();
                    mclubs=a.selecthnClub();
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
        initView(root);
        Button btn1=(Button)root.findViewById(R.id.TextView_school_notice_release2);
        Button btn2=(Button)root.findViewById(R.id.school_notice_search);
        Button btn3=(Button)root.findViewById(R.id.club_all_btn_cla);
        Button btn4=(Button)root.findViewById(R.id.botton_more);
        Button btn5=(Button)root.findViewById(R.id.botton_more1);
        Button btn6=(Button)root.findViewById(R.id.btn_right);
        Button btn7=(Button)root.findViewById(R.id.btn_right1);
        Button btn8=(Button)root.findViewById(R.id.club_all_btn_rank);
        Button btn9=(Button)root.findViewById(R.id.club_all_btn_all);
        btn9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(),club_allclub.class);
                startActivity(intent);
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(),club_rank.class);
                startActivity(intent);
            }
        });


        btn4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(),club_more.class);
                intent.putExtra("more","热门社团");
                startActivity(intent);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(),club_more.class);
                intent.putExtra("more","推荐社团");
                startActivity(intent);
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(),club_more.class);
                startActivity(intent);
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(),club_more.class);
                startActivity(intent);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(),club_classify.class);
                startActivity(intent);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(),club_search.class);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(),club_search.class);
                startActivity(intent);
            }
        });

        return root;
    }
    public void initView(View root) {
        mBanner = root.findViewById(R.id.mBanner3);
        //图片资源
        int[] imageResourceID = new int[]{R.drawable.systemback, R.drawable.listback, R.drawable.my, R.drawable.myclub};
        List<Integer> imgeList = new ArrayList<>();
        //轮播标题
        String[] mtitle = new String[]{"图片1", "图片2", "图片3", "图片4"};
        List<String> titleList = new ArrayList<>();

        for (int i = 0; i < imageResourceID.length; i++) {
            imgeList.add(imageResourceID[i]);//把图片资源循环放入list里面
            titleList.add(mtitle[i]);//把标题循环设置进列表里面
            //设置图片加载器，通过Glide加载图片
            mBanner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(getActivity()).load(path).into(imageView);
                }
            });
            //设置轮播的动画效果,里面有很多种特效,可以到GitHub上查看文档。
            mBanner.setBannerAnimation(Transformer.Accordion);
            mBanner.setImages(imgeList);//设置图片资源
            mBanner.setBannerStyle(BannerConfig.NOT_INDICATOR);//设置banner显示样式（带标题的样式）
            mBanner.setDelayTime(3000);//设置轮播时间3秒切换下一图
            mBanner.setOnBannerListener(this);//设置监听
            mBanner.setFocusable(true);//获取焦点
            mBanner.setFocusableInTouchMode(true);//触摸是否能获取到焦点
            mBanner.requestFocus();//用于指定屏幕中的焦点View
            mBanner.start();//开始进行banner渲染
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mBanner.startAutoPlay();//开始轮播
    }

    @Override
    public void onStop() {
        super.onStop();
        mBanner.stopAutoPlay();//结束轮播
    }

    //对轮播图设置点击监听事件
    @Override
    public void OnBannerClick(int position) {
        Beanclub c=new Beanclub();
        c.setClub_ID(position+1);
        DashboardFragment.actionStart(getContext(),c);
        Toast.makeText(getActivity(), "你点击了第" + (position + 1) + "张轮播图", Toast.LENGTH_SHORT).show();
    }


}