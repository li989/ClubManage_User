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
import com.example.activitytest.club_person;
import com.example.club.model.Beanclub_person;

import java.util.List;

public class personAdapter extends RecyclerView.Adapter<personAdapter.ViewHolder>{
    private List<Beanclub_person> mBeanuser;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView personname;
        ImageView personImage;
        TextView persongrade;
        public ViewHolder(View view){
            super(view);
            personname=(TextView)view.findViewById(R.id.TextView_club_person_list_name);
            personImage=(ImageView) view.findViewById(R.id.imageView_club_person_list);
            persongrade=(TextView)view.findViewById(R.id.TextView_club_person_list_grade);
        }
    }
    public personAdapter(List<Beanclub_person> userList){
        mBeanuser=userList;
    }
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.club_person_list,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Beanclub_person user=mBeanuser.get(position);
        holder.personname.setText(user.getC_name());
        try{
            Bitmap bitmap1 = BitmapFactory.decodeByteArray(user.getC_picture(), 0, user.getC_picture().length);
            holder.personImage.setImageBitmap(bitmap1);
            holder.persongrade.setText(user.getUser_grade());
        }catch (Exception e){
            e.printStackTrace();
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Beanclub_person clper=mBeanuser.get(position);
                club_person.actionStart(holder.itemView.getContext(),clper);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBeanuser.size();
    }
}
