package com.example.activitytest;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.adapter.cluballAdapter;
import com.example.club.model.Beanclub;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class club_all extends AppCompatActivity  implements OnBannerListener{
    private FadingScrollView fadingScrollView;
    private View layout;
    private Banner mBanner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.club_all);
        initView();
        List<Beanclub> clubs=new ArrayList<Beanclub>();
        for (int i=0;i<100;i++){
            Beanclub club=new Beanclub();
            club.setClub_ID(i);
            club.setClub_name("足球社"+i);
            club.setClub_remark("啦啦啦"+i);
            clubs.add(club);
        }
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.club_all_rela);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        cluballAdapter adapter=new cluballAdapter(clubs);
        recyclerView.setAdapter(adapter);




        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        layout = findViewById(R.id.school_notice_liner);
        layout.setAlpha(0);

        fadingScrollView = (FadingScrollView)findViewById(R.id.nac_root);
        fadingScrollView.setFadingView(layout);
        fadingScrollView.setFadingHeightView(findViewById(R.id.mBanner3));
    }
    public void initView() {
        mBanner = findViewById(R.id.mBanner3);

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
                    Glide.with(club_all.this).load(path).into(imageView);
                }
            });
            //设置轮播的动画效果,里面有很多种特效,可以到GitHub上查看文档。
            mBanner.setBannerAnimation(Transformer.Accordion);
            mBanner.setImages(imgeList);//设置图片资源
            mBanner.setBannerStyle(BannerConfig.NOT_INDICATOR);//设置banner显示样式（带标题的样式）
            mBanner.setDelayTime(3000);//设置轮播时间3秒切换下一图
            mBanner.setOnBannerListener(this);//设置监听
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
        Toast.makeText(club_all.this, "你点击了第" + (position + 1) + "张轮播图", Toast.LENGTH_SHORT).show();
    }
}
