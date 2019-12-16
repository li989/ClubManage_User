package com.example.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.activitytest.R;
import com.example.activitytest.notice_digital;
import com.example.activitytest.school_notice;
import com.example.activitytest.ui.home.HomeFragment;
import com.example.club.model.Beanschoolnotice;

import java.util.List;

import any.BitmapToRound_Util;

public class school_noticeAdapter extends RecyclerView.Adapter<school_noticeAdapter.ViewHolder> {
    private List<Beanschoolnotice> mBeanschoolnotice;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView school_notice_title;
        ImageView school_notice_imgtx;
        TextView school_notice_time1;
        TextView school_notice_time2;
        public ViewHolder(View view){
            super(view);
            school_notice_title=(TextView)view.findViewById(R.id.school_notice_title);
            school_notice_imgtx=(ImageView) view.findViewById(R.id.school_notice_imgtx);
            school_notice_time1=(TextView)view.findViewById(R.id.school_notice_time1);
            school_notice_time2=(TextView)view.findViewById(R.id.school_notice_time2);
        }
    }
    public school_noticeAdapter(List<Beanschoolnotice> school_notice){
        mBeanschoolnotice=school_notice;
    }
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.school_notice_list,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Beanschoolnotice notice=mBeanschoolnotice.get(position);
        holder.school_notice_title.setText(notice.getSchoolnotice_title());
        try {
            if(notice.getSchoolnotice_picture()!=null) {
                Bitmap bitmap1 = BitmapFactory.decodeByteArray(notice.getSchoolnotice_picture(), 0, notice.getSchoolnotice_picture().length);
                bitmap1=(new BitmapToRound_Util()).toRoundBitmap(bitmap1);
                holder.school_notice_imgtx.setImageBitmap(bitmap1);
            }else
                holder.school_notice_imgtx.setImageResource(R.drawable.head);
            holder.school_notice_time1.setText("发布时间");
            holder.school_notice_time2.setText(notice.getSchoolnotice_start_time().toString().substring(0, 10));
        }catch (Exception e){
            e.printStackTrace();
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Beanschoolnotice notice=mBeanschoolnotice.get(position);
                HomeFragment.actionStart(holder.itemView.getContext(),notice.getSchoolnotice_ID());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBeanschoolnotice.size();
    }
}
