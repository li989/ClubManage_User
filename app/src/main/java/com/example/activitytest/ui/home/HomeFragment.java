package com.example.activitytest.ui.home;

import android.content.Context;
import android.content.Intent;

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
import com.example.activitytest.notice_digital;
import com.example.activitytest.noticesearch;
import com.example.adapter.school_noticeAdapter;
import com.example.club.control.example.ClubManager;
import com.example.club.control.example.ClubNoticeManager;
import com.example.club.control.example.SchoolNoticeManager;
import com.example.club.control.example.UserManager;
import com.example.club.control.example.User_ClubManager;
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



public class HomeFragment extends Fragment implements OnBannerListener {
    private Banner mBanner;
    private HomeViewModel homeViewModel;
    private List<Beanschoolnotice> nt=new ArrayList<Beanschoolnotice>();
    public static void actionStart(Context context,int id) {
        Intent intent = new Intent();
        intent.setClass(context, notice_digital.class);
        intent.putExtra("id",id);
        context.startActivity(intent);
    }
    private Handler handler=new Handler(){
        public void handleMessage(Message msg){

            try {
                View root=getView();
                List<Beanschoolnotice> notices=(ArrayList<Beanschoolnotice>)msg.obj;
                RecyclerView recyclerView=(RecyclerView)root.findViewById(R.id.home_rela);
                LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutManager);
                school_noticeAdapter adapter=new school_noticeAdapter(notices);
                recyclerView.setAdapter(adapter);
            }catch (Exception e){
                e.printStackTrace();
            }



        }
    };
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_home, container, false);
        initView(root);

            new Thread(){
                @Override
                public void run() {
                    try {
                        SchoolNoticeManager a=new SchoolNoticeManager();
                        nt=a.getallnotice();
                        Message message=new Message();
                        message.obj=nt;
                        Bundle bundle = new Bundle();
                        bundle.putString("name","1");  //往Bundle中存放数据
                        message.setData(bundle);

                        handler.sendMessage(message);
                    } catch (BaseException e) {
                        e.printStackTrace();
                    }
                }
            }.start();


        Button btnsearch=(Button)root.findViewById(R.id.home_search1);
        btnsearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), noticesearch.class);
                startActivity(intent);
            }
        });
        Button btnsearch1=(Button)root.findViewById(R.id.home_search2);
        btnsearch1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), noticesearch.class);
                startActivity(intent);
            }
        });

        return root;
    }
    public void initView(View root) {
        mBanner = root.findViewById(R.id.mBanner1);
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
        HomeFragment.actionStart(getContext(),position+1);
        Toast.makeText(getActivity(), "你点击了第" + (position + 1) + "张轮播图", Toast.LENGTH_SHORT).show();
    }
}