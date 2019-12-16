package com.example.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.activitytest.MyClub;
import com.example.activitytest.R;
import com.example.activitytest.school_notice;
import com.example.club.model.Beanmycl;
import com.example.club.model.Beanschoolnotice;

import java.util.List;

import any.BitmapToRound_Util;

public class myclAdapter  extends RecyclerView.Adapter<myclAdapter.ViewHolder>{
    private List<Beanmycl> mBeanmycl;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView myclname;
        ImageView myclImage;
        TextView myclgrade;
        TextView mycltime;
        public ViewHolder(View view){
            super(view);
            myclname=(TextView)view.findViewById(R.id.mcl_name);
            myclImage=(ImageView) view.findViewById(R.id.mcl_imageView1);
            myclgrade=(TextView)view.findViewById(R.id.mcl_grade);
            mycltime=(TextView)view.findViewById(R.id.mcl_time);
        }
    }
    public myclAdapter(List<Beanmycl> userList){
        mBeanmycl=userList;
    }
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.my_club_list,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Beanmycl mycl=mBeanmycl.get(position);
        holder.myclname.setText(mycl.getClub_name());
        try {


            if(mycl.getClub_picture()!=null) {
                Bitmap bitmap1 = BitmapFactory.decodeByteArray(mycl.getClub_picture(), 0, mycl.getClub_picture().length);
                bitmap1=(new BitmapToRound_Util()).toRoundBitmap(bitmap1);
                holder.myclImage.setImageBitmap(bitmap1);
            }else
                holder.myclImage.setImageResource(R.drawable.head);
            holder.myclgrade.setText(mycl.getUser_grade());
            holder.mycltime.setText(mycl.getJoin_time().toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Beanmycl mycl=mBeanmycl.get(position);
                MyClub.actionStart(holder.itemView.getContext(),mycl);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBeanmycl.size();
    }
}
