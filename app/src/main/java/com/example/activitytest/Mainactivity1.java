//package com.example.activitytest;
//
//import android.content.Context;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.animation.Animation;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.adapter.school_noticeAdapter;
//import com.example.adapter.schoolnoticemoreAdapter;
//import com.example.club.model.Beanschoolnotice;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Observable;
//import java.util.concurrent.TimeUnit;
//import java.util.function.Consumer;
//
//public class Mainactivity1 extends AppCompatActivity {
//    private List<Beanschoolnotice> mschoolnoticeList;
//    private Context mContext;
//    private RecyclerOnScrollerListener mOnScrollListener;
////    private RecyclerView.AdapterDataObserver mAdapterDataObserver;
//
//    private static final int VIEW_TYPE_CONTENT = 0;
//    private static final int VIEW_TYPE_FOOTER = 1;
//    private boolean isCanLoadMore = true;
//
//    private Animation rotateAnimation;
//
//    private RecyclerView mRvFruits;
//    private static final int PER_PAGE = 10;
//    private schoolnoticemoreAdapter mAdapter= new schoolnoticemoreAdapter();
//    private int mCurrentPage = 1;
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_home);
//    }
//    private void init() {
//        for (int i=0;i<100;i++){
//            Beanschoolnotice no=new Beanschoolnotice();
//            no.setSchoolnotice_title("足球大赛"+i);
//            no.setSchoolnotice_start_time(new java.sql.Timestamp(System.currentTimeMillis()));
//            mschoolnoticeList.add(no);
//        }
//        schoolnoticemoreAdapter mAdapter= new schoolnoticemoreAdapter(mschoolnoticeList);
//        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.home_rela);
//        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(mAdapter);
//        mAdapter.setOnLoadMoreListener(new schoolnoticemoreAdapter.OnLoadMoreListener() {
//            public void onLoadMore(int currentPage) {
//                mCurrentPage = currentPage;
//                loadMoreTest();
//            }
//        });
//    }
//
//
//    private void loadMoreTest() {
//        Observable.timer(2000, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Long>() {
//                    @Override
//                    public void accept(Long aLong) throws Exception {
//                        //第一个条件是有没有获取到新数据，第二个条件是新数据是否填满了一整页
//                        //这里假如3页之后没有更多数据了，这里的条件根据实际网络请求的返回情况来确定。
//                        Log.d("loadingtest", "main current page: " + mCurrentPage);
//                        if (mCurrentPage < 4) {
//                            List<Fruit> loadMoreList = new ArrayList<>();
//                            Fruit pear = new Fruit(getRandomLenthName("Pear"), R.drawable.pear);
//                            loadMoreList.add(pear);
//                            Fruit pineapple = new Fruit(getRandomLenthName("Pineapple"), R.drawable.pineapple);
//                            loadMoreList.add(pineapple);
//                            Fruit strawberry = new Fruit(getRandomLenthName("Strawberry"), R.drawable.strawberry);
//                            loadMoreList.add(strawberry);
//                            Fruit pineapple2 = new Fruit(getRandomLenthName("Pineapple"), R.drawable.pineapple);
//                            loadMoreList.add(pineapple2);
//                            Fruit strawberry2 = new Fruit(getRandomLenthName("Strawberry"), R.drawable.strawberry);
//                            loadMoreList.add(strawberry2);
//                            Fruit watermelon = new Fruit(getRandomLenthName("Watermelon"), R.drawable.watermelon);
//                            loadMoreList.add(watermelon);
//                            Fruit apple = new Fruit(getRandomLenthName("Apple"), R.drawable.apple);
//                            loadMoreList.add(apple);
//                            Fruit banana = new Fruit(getRandomLenthName("Banana"), R.drawable.banana);
//                            loadMoreList.add(banana);
//                            Fruit cherry = new Fruit(getRandomLenthName("Cherry"), R.drawable.cherry);
//                            loadMoreList.add(cherry);
//                            Fruit grape = new Fruit(getRandomLenthName("Grape"), R.drawable.grape);
//                            loadMoreList.add(grape);
//                            mFruitList.addAll(loadMoreList);
//
//                            //如果未填满一整页，那么肯定没有更多数据了
//                            Log.d("loadingtest", "size1: " + mFruitList.size() + "  -  size2: " + mCurrentPage * PER_PAGE);
//                            if (mFruitList.size() == mCurrentPage * PER_PAGE) {
//                                mAdapter.setCanLoadMore(true);
//                            } else {
//                                mAdapter.setCanLoadMore(false);
//                            }
//                            mAdapter.setData(mFruitList);
//
//                        } else {
//                            //网络数据已加载完
//                            mAdapter.setCanLoadMore(false);
//                            mAdapter.notifyDataSetChanged();
//                        }
//                    }
//                });
//
//    }
//
//    private String getRandomLenthName(String name) {
//        Random random = new Random();
//        int length = random.nextInt(20) + 1;
//        StringBuilder builder = new StringBuilder();
//        for (int i = 0; i < length; i++) {
//            builder.append(name);
//        }
//        return builder.toString();
//    }
//
//}
//}
