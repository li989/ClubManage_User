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
import com.example.club.model.Beanclub;

import java.util.List;

public class ClubnumAdapter extends RecyclerView.Adapter<ClubnumAdapter.ViewHolder> {
    private List<Beanclub> mBeanclub;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView clubmin;
        ImageView clubImage;
        TextView clubname;
        TextView clubgrade;
        public ViewHolder(View view){
            super(view);
            clubmin=(TextView)view.findViewById(R.id.TextView_club_num_list_min);
            clubImage=(ImageView) view.findViewById(R.id.Image_club_num_list);
            clubname=(TextView)view.findViewById(R.id.TextView_club_num_list_name);
            clubgrade=(TextView)view.findViewById(R.id.TextView_club_num_list_grade);
        }
    }
    public ClubnumAdapter(List<Beanclub> clubList){
        mBeanclub=clubList;
    }
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.club_num_list,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
         Beanclub club=mBeanclub.get(position);
        try {
             holder.clubgrade.setText(""+club.getClub_grade());
             Bitmap bitmap1 = BitmapFactory.decodeByteArray(club.getClub_picture(), 0, club.getClub_picture().length);
             holder.clubImage.setImageBitmap(bitmap1);
             holder.clubmin.setText(""+(position+1));
             holder.clubname.setText(club.getClub_name());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mBeanclub.size();
    }
}
