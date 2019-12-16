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
import com.example.activitytest.get_award;
import com.example.club.model.Beanawards;

import java.util.List;

public class awardAdapter extends RecyclerView.Adapter<awardAdapter.ViewHolder> {
    private List<Beanawards> mBeanawards;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView awardname;
        ImageView awardImage;
        TextView awarddata;
        TextView awardgrade;
        public ViewHolder(View view){
            super(view);
            awardname=(TextView)view.findViewById(R.id.TextView_award_list_name);
            awardImage=(ImageView) view.findViewById(R.id.imageView_award_list);
            awarddata=(TextView)view.findViewById(R.id.TextView_award_list_date);
            awardgrade=(TextView)view.findViewById(R.id.TextView_award_list_grade);
        }
    }
    public awardAdapter(List<Beanawards> awardsList){
        mBeanawards=awardsList;
    }
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.award_list,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Beanawards award=mBeanawards.get(position);
        try {
            holder.awardname.setText(award.getAwards_name());
            if(award.getAwards_picture()!=null){
            Bitmap bitmap1 = BitmapFactory.decodeByteArray(award.getAwards_picture(), 0, award.getAwards_picture().length);
            holder.awardImage.setImageBitmap(bitmap1);}
            holder.awarddata.setText(award.getAwards_time().toString());
            holder.awardgrade.setText(""+award.getAwards_grade());
        }catch (Exception e){
            e.printStackTrace();
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Beanawards clper=mBeanawards.get(position);
                get_award.actionStart(holder.itemView.getContext(),clper.getAwards_ID());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBeanawards.size();
    }
}
