package com.example.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.activitytest.R;
import com.example.activitytest.club_notice;
import com.example.club.model.Beanclubnotice;

import java.util.List;

public class clubnoticeAdapt extends RecyclerView.Adapter<clubnoticeAdapt.ViewHolder> {
    private List<Beanclubnotice> mBeanclubnotice;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView noticename;
        ImageView noticeImage;
        TextView noticetime;
        TextView noticenumber;
        public ViewHolder(View view){
            super(view);
            noticename=(TextView)view.findViewById(R.id.cnl_name);
            noticeImage=(ImageView) view.findViewById(R.id.cnl_imageView1);
            noticetime=(TextView)view.findViewById(R.id.cnl_time);
            noticenumber=(TextView)view.findViewById(R.id.cnl_number);
        }
    }
    public clubnoticeAdapt(List<Beanclubnotice> noticeList){
        mBeanclubnotice=noticeList;
    }
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.club_notice_list,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Beanclubnotice notice=mBeanclubnotice.get(position);
        try {
            holder.noticename.setText(notice.getClubnotice_title());
            if (notice.getClubnotice_picture() != null) {
                Bitmap bitmap1 = BitmapFactory.decodeByteArray(notice.getClubnotice_picture(), 0, notice.getClubnotice_picture().length);
                holder.noticeImage.setImageBitmap(bitmap1);
            }
            holder.noticetime.setText(notice.getClubnotice_start_time().toString());
            holder.noticenumber.setText("" + notice.getClubnotice_number());
        }catch (Exception e){
            e.printStackTrace();
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Beanclubnotice notice=mBeanclubnotice.get(position);
                club_notice.actionStart(holder.itemView.getContext(),notice.getClubnotice_ID());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBeanclubnotice.size();
    }
}
