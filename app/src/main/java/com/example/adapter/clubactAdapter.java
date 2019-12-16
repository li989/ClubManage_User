package com.example.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.activitytest.R;
import com.example.activitytest.club_act;
import com.example.club.model.Beanactivity;

import java.util.List;

public class clubactAdapter extends RecyclerView.Adapter<clubactAdapter.ViewHolder> {
    private List<Beanactivity> mBeanactivity;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView acttitle;
        ImageView actimg;
        TextView actt;
        TextView acttime;
        public ViewHolder(View view){
            super(view);
            acttitle=(TextView)view.findViewById(R.id.club_act_list_t1);
            actimg=(ImageView) view.findViewById(R.id.club_act_list_img);
            actt=(TextView)view.findViewById(R.id.club_act_list_t2);
            acttime=(TextView)view.findViewById(R.id.club_act_list_t3);
        }
    }
    public clubactAdapter(List<Beanactivity> activityList){
        mBeanactivity=activityList;
    }
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.club_act_list,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Beanactivity activity=mBeanactivity.get(position);
        try{
        holder.acttitle.setText(activity.getActivity_name());
        if(activity.getActivity_picture()!=null){
            Bitmap bitmap1 = BitmapFactory.decodeByteArray(activity.getActivity_picture(), 0, activity.getActivity_picture().length);
            holder.actimg.setImageBitmap(bitmap1);}
        holder.actt.setText(activity.getActivity_start_time().toString());
        holder.acttime.setText(activity.getActivity_finish_time().toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Beanactivity activity1=mBeanactivity.get(position);
                Log.e("ep",""+mBeanactivity.get(position).getActivity_ID());
                club_act.actionStart(holder.itemView.getContext(),activity1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBeanactivity.size();
    }
}
