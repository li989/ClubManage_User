package com.example.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.activitytest.R;
import com.example.club.model.Beanincome;

import java.util.List;

public class incomeAdapter extends RecyclerView.Adapter<incomeAdapter.ViewHolder>{
    private List<Beanincome> mBeanincome;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView incomename;
        TextView incomedate;
        TextView incomegrade;
        public ViewHolder(View view){
            super(view);
            incomename=(TextView)view.findViewById(R.id.TextView_income_list_name);
            incomedate=(TextView)view.findViewById(R.id.TextView_income_list_date);
            incomegrade=(TextView)view.findViewById(R.id.TextView_income_list_grade);
        }
    }
    public incomeAdapter(List<Beanincome> incomeList){
        mBeanincome=incomeList;
    }
    public incomeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.income_list,parent,false);
        incomeAdapter.ViewHolder holder=new incomeAdapter.ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        Beanincome income=mBeanincome.get(position);
        try{
            holder.incomename.setText(income.getIncome_detail());
            holder.incomedate.setText(income.getIncome_time().toString());
            holder.incomegrade.setText(""+income.getIncome_amount());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mBeanincome.size();
    }
}
