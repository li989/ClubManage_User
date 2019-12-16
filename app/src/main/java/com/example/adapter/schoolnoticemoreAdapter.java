package com.example.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.activitytest.R;
import com.example.activitytest.RecyclerOnScrollerListener;
import com.example.activitytest.ui.home.HomeFragment;
import com.example.club.model.Beanschoolnotice;

import java.util.List;

import any.BitmapToRound_Util;

public class schoolnoticemoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Beanschoolnotice> mschoolnoticeList;
    private Context mContext;
    private RecyclerOnScrollerListener mOnScrollListener;
//    private RecyclerView.AdapterDataObserver mAdapterDataObserver;

    private static final int VIEW_TYPE_CONTENT = 0;
    private static final int VIEW_TYPE_FOOTER = 1;
    private boolean isCanLoadMore = true;

    private Animation rotateAnimation;

    private static final int PER_PAGE = 10;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView school_notice_title;
        ImageView school_notice_imgtx;
        TextView school_notice_time1;
        TextView school_notice_time2;
        public ViewHolder(View view){
            super(view);
            school_notice_title=(TextView)view.findViewById(R.id.school_notice_title);
            school_notice_imgtx=(ImageView) view.findViewById(R.id.school_notice_imgtx);
            school_notice_time1=(TextView)view.findViewById(R.id.school_notice_time1);
            school_notice_time2=(TextView)view.findViewById(R.id.school_notice_time2);
        }
    }
    public schoolnoticemoreAdapter(List<Beanschoolnotice> fruitList) {
        this.mschoolnoticeList = fruitList;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        if (rotateAnimation == null) {
//            rotateAnimation = AnimationUtils.loadAnimation(mContext, R.anim.loading);
            rotateAnimation.setInterpolator(new LinearInterpolator());
        }
        if (viewType == VIEW_TYPE_CONTENT) {
            return new ContentViewHolder(LayoutInflater.from(mContext).inflate(R.layout.fragment_home, parent, false));
        } else {

            return new FooterViewHolder(LayoutInflater.from(mContext).inflate(R.layout.fragment_home, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == VIEW_TYPE_CONTENT) {
            Beanschoolnotice notice= mschoolnoticeList.get(position);
            ((ContentViewHolder) holder).school_notice_title.setText(notice.getSchoolnotice_title());

            if(notice.getSchoolnotice_picture()!=null) {
                Bitmap bitmap1 = BitmapFactory.decodeByteArray(notice.getSchoolnotice_picture(), 0, notice.getSchoolnotice_picture().length);
                bitmap1=(new BitmapToRound_Util()).toRoundBitmap(bitmap1);
                ((ContentViewHolder) holder).school_notice_imgtx.setImageBitmap(bitmap1);
            }else
                ((ContentViewHolder) holder).school_notice_imgtx.setImageResource(R.drawable.head);
            ((ContentViewHolder) holder).school_notice_time1.setText("发布时间");
            ((ContentViewHolder) holder).school_notice_time2.setText(notice.getSchoolnotice_start_time().toString().substring(0,10));
            ((ContentViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = ((ContentViewHolder) holder).getAdapterPosition();
                    Beanschoolnotice notice=mschoolnoticeList.get(position);
                    HomeFragment.actionStart(((ContentViewHolder) holder).itemView.getContext(),notice.getSchoolnotice_ID());
                }
            });
        } else {
            Log.d("mytest", "isCanLoadMore: " + isCanLoadMore);
            if (isCanLoadMore) {
                ((FooterViewHolder) holder).showLoading();
            } else {
                ((FooterViewHolder) holder).showTextOnly("无更多数据");
            }
        }

    }

    /*
     * 本例中所有数据加载完毕后还是保留footerView并显示已加载完全，所以footerView一直存在。
     * */
    @Override
    public int getItemCount() {
//        return isCanLoadMore ? mFruitList.size() + 1 : mFruitList.size();
        return mschoolnoticeList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return VIEW_TYPE_FOOTER;
        }
        return VIEW_TYPE_CONTENT;
    }

    //ContentView，水果们
    class ContentViewHolder extends RecyclerView.ViewHolder {
        TextView school_notice_title;
        ImageView school_notice_imgtx;
        TextView school_notice_time1;
        TextView school_notice_time2;
        TextView more;
        public ContentViewHolder(View view){
            super(view);
            school_notice_title=(TextView)view.findViewById(R.id.school_notice_title);
            school_notice_imgtx=(ImageView) view.findViewById(R.id.school_notice_imgtx);
            school_notice_time1=(TextView)view.findViewById(R.id.school_notice_time1);
            school_notice_time2=(TextView)view.findViewById(R.id.school_notice_time2);
        }
    }

    //底部的FooterView
    class FooterViewHolder extends RecyclerView.ViewHolder {
        TextView school_notice_title;
        ImageView school_notice_imgtx;
        TextView school_notice_time1;
        TextView school_notice_time2;
        TextView more;
        public FooterViewHolder(View view) {
            super(view);
            school_notice_title=(TextView)view.findViewById(R.id.school_notice_title);
            school_notice_imgtx=(ImageView) view.findViewById(R.id.school_notice_imgtx);
            school_notice_time1=(TextView)view.findViewById(R.id.school_notice_time1);
            school_notice_time2=(TextView)view.findViewById(R.id.school_notice_time2);
        }

        void showTextOnly(String s) {
            Log.d("mytest", "showTextOnly: " + s);
            more.setVisibility(View.INVISIBLE);
            more.setText(s);
        }

        void showLoading() {
            Log.i("mytest", "show loading");
            more.setText("正在加载...");
            if (more != null) {
                more.startAnimation(rotateAnimation);
            }
        }

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mOnScrollListener = new RecyclerOnScrollerListener(recyclerView) {
            @Override
            public void onLoadMore(int currentPage) {
                Log.i("loadingtest", "currentPage: " + currentPage);
                mOnLoadMoreListener.onLoadMore(currentPage);
            }
        };
        recyclerView.addOnScrollListener(mOnScrollListener);
//        mAdapterDataObserver = new RecyclerView.AdapterDataObserver() {
//            @Override
//            public void onChanged() {
//                super.onChanged();
//            }
//        };
        //初始化的时候如果未填满一页，那么肯定就没有更多数据了
        if (mschoolnoticeList.size() < PER_PAGE) {
            setCanLoadMore(false);
        } else {
            setCanLoadMore(true);
        }
    }


    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        if (mOnScrollListener != null) {
            recyclerView.removeOnScrollListener(mOnScrollListener);
        }
//        if (mAdapterDataObserver != null) {
//            unregisterAdapterDataObserver(mAdapterDataObserver);
//        }
        mOnScrollListener = null;
    }

    public void setData(List<Beanschoolnotice> list) {
        mschoolnoticeList = list;
        notifyDataSetChanged();
    }

    /*
     * 数据加载完毕时执行setCanLoadMore()，此时isLoading都置为false
     * */
    public void setCanLoadMore(boolean isCanLoadMore) {
        this.isCanLoadMore = isCanLoadMore;
        mOnScrollListener.setCanLoadMore(isCanLoadMore);
        mOnScrollListener.setLoading(false);
    }


    public interface OnLoadMoreListener {
        void onLoadMore(int currentPage);
    }

    private OnLoadMoreListener mOnLoadMoreListener;

    public void setOnLoadMoreListener(OnLoadMoreListener listener) {
        this.mOnLoadMoreListener = listener;
    }

}