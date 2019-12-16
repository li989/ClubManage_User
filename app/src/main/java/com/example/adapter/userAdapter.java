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
import com.example.activitytest.club_joinlist;
import com.example.activitytest.get_award;
import com.example.club.model.Beanawards;
import com.example.club.model.Beanclub_person;
import com.example.club.model.Beanuser;

import java.util.List;

public class userAdapter extends RecyclerView.Adapter<userAdapter.ViewHolder> {
    private List<Beanclub_person> mBeanuser;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView username;
        ImageView userImage;
        TextView title;
        TextView time;
        public ViewHolder(View view){
            super(view);
            username=(TextView)view.findViewById(R.id.club_joinlist_list_name);
            userImage=(ImageView) view.findViewById(R.id.club_joinlist_list_img);
            title =(TextView)view.findViewById(R.id.club_joinlist_list_title);
            time=(TextView)view.findViewById(R.id.club_joinlist_list_time);
        }
    }
    public userAdapter(List<Beanclub_person> usersList){
        mBeanuser=usersList;
    }
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.club_joinlist_list,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Beanclub_person user=mBeanuser.get(position);
        try {
            holder.username.setText(user.getC_name());
            Bitmap bitmap1 = BitmapFactory.decodeByteArray(user.getC_picture(), 0, user.getC_picture().length);
            holder.userImage.setImageBitmap(bitmap1);
            holder.title.setText("申请时间");
            holder.time.setText(user.getUser_time().toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Beanclub_person clper=mBeanuser.get(position);
                club_joinlist.actionStart(holder.itemView.getContext(),user.getC_userID(),user.getClub_ID());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBeanuser.size();
    }
}
