package com.example.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.activitytest.MyClub;
import com.example.activitytest.R;
import com.example.activitytest.ui.dashboard.DashboardFragment;
import com.example.club.model.Beanclub;
import com.example.club.model.Beanmycl;


import java.security.spec.ECField;
import java.util.List;

import any.BitmapToRound_Util;

public class cluballAdapter extends RecyclerView.Adapter<cluballAdapter.ViewHolder> {
    private List<Beanclub> mBeanclub;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView clubname;
        ImageView clubImage;
        TextView clubremark;
        public ViewHolder(View view){
            super(view);
            clubname=(TextView)view.findViewById(R.id.club_all_name);
            clubImage=(ImageView) view.findViewById(R.id.club_all_img);
            clubremark=(TextView)view.findViewById(R.id.club_all_remark);
        }
    }
    public cluballAdapter(List<Beanclub> clubList){
        mBeanclub=clubList;
    }
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.club_all_list,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Beanclub club=mBeanclub.get(position);
        try {
            holder.clubname.setText(club.getClub_name());
            if (club.getClub_picture() != null) {
                Bitmap bitmap1 = BitmapFactory.decodeByteArray(club.getClub_picture(), 0, club.getClub_picture().length);
                bitmap1 = (new BitmapToRound_Util()).toRoundBitmap(bitmap1);
                holder.clubImage.setImageBitmap(bitmap1);
            } else
                holder.clubImage.setImageResource(R.drawable.head);
            holder.clubremark.setText(club.getClub_remark());
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
