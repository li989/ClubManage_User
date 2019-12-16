package com.example.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewAnimator;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.activitytest.R;
import com.example.activitytest.ui.dashboard.DashboardFragment;
import com.example.club.control.example.UserManager;
import com.example.club.model.Beanclub;
import com.example.club.model.Beanuser;
import com.example.club.util.BusinessException;

import java.util.List;

import any.BitmapToRound_Util;
import any.CircleImage;

public class clubrankAdapter extends RecyclerView.Adapter<clubrankAdapter.ViewHolder> {
    private List<Beanclub> mBeanclub;
    private List<String> mszname;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView clubmin;
        ImageView clubImage;
        TextView clubname;
        TextView clubsz;
        TextView clubszname;
        TextView clubgrade;
        public ViewHolder(View view){
            super(view);
            clubmin=(TextView)view.findViewById(R.id.list_rank);
            clubImage=(ImageView) view.findViewById(R.id.club_rank_list_img);
            clubname=(TextView)view.findViewById(R.id.club_rank_list_t1);
            clubsz=(TextView)view.findViewById(R.id.club_rank_list_t2);
            clubszname=(TextView)view.findViewById(R.id.club_rank_list_t3);
            clubgrade=(TextView)view.findViewById(R.id.list_grade);
        }
    }
    public clubrankAdapter(List<Beanclub> clubList,List<String> namelist){
        mBeanclub=clubList;
        mszname=namelist;
    }

    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.club_rank_list,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Beanclub club=mBeanclub.get(position);
        String szname=mszname.get(position);
        try{
            holder.clubgrade.setText(""+club.getClub_grade());
            if(club.getClub_picture()!=null) {
                Bitmap bitmap1 = BitmapFactory.decodeByteArray(club.getClub_picture(), 0, club.getClub_picture().length);
                bitmap1=(new BitmapToRound_Util()).toRoundBitmap(bitmap1);
                holder.clubImage.setImageBitmap(bitmap1);
            }else
                holder.clubImage.setImageResource(R.drawable.head);
            holder.clubmin.setText(""+(position+1));
            holder.clubname.setText(club.getClub_name());
            holder.clubszname.setText(szname);
            holder.clubsz.setText("社长");
        }catch (Exception e){
            e.printStackTrace();
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Beanclub club=mBeanclub.get(position);
                DashboardFragment.actionStart(holder.itemView.getContext(),club);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mBeanclub.size();
    }
}
