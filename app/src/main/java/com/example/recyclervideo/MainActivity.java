package com.example.recyclervideo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

public class MainActivity extends AppCompatActivity implements VideoPlayerComplete {
    private List<String> list;
    private RecyclerView recyclerView;
    private VideoAdapter adapter;
    private RecyclerView.ViewHolder viewHolder;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        list = Data();
        recyclerView = findViewById(R.id.recyclerview);
        adapter = new VideoAdapter(list);
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE:
                        View view = snapHelper.findSnapView(manager);
                        viewHolder = recyclerView.getChildViewHolder(view);
                        JzvdStd.releaseAllVideos();
                        if (viewHolder != null && viewHolder instanceof VideoAdapter.ViewHolder) {
                            ((VideoAdapter.ViewHolder) viewHolder).videoView.startVideo();
                            //TODO 实现自动播放 播放完成后自动跳转
//                            recyclerView.smoothScrollToPosition(viewHolder.getAdapterPosition() + 1);
//                            if (((VideoAdapter.ViewHolder) viewHolder).videoView.isCompletion) {
//                                Toast.makeText(MainActivity.this, "是", Toast.LENGTH_SHORT).show();
//                                recyclerView.smoothScrollToPosition(viewHolder.getAdapterPosition() + 1);
//                                ((VideoAdapter.ViewHolder) viewHolder).videoView.isCompletion = false;
//                            }else {
//                                Toast.makeText(MainActivity.this, "不是", Toast.LENGTH_SHORT).show();
//                            }
                        }
                        break;
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        break;
                    case RecyclerView.SCROLL_STATE_SETTLING:
                        break;
                }
            }
        });

    }

    @Override
    protected void onResume() {
        Jzvd.goOnPlayOnResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        Jzvd.goOnPlayOnPause();
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        Jzvd.backPress();
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Jzvd.releaseAllVideos();
    }

    private List<String> Data() {
        List<String> urlList = new ArrayList<>();
        urlList.add("http://image.38.hn/public/attachment/201805/100651/201805181532123423.mp4");
        urlList.add("http://image.38.hn/public/attachment/201803/100651/201803151735198462.mp4");
        urlList.add("http://image.38.hn/public/attachment/201803/100651/201803150923220770.mp4");
        urlList.add("http://image.38.hn/public/attachment/201803/100651/201803150922255785.mp4");
        urlList.add("http://image.38.hn/public/attachment/201803/100651/201803150920130302.mp4");
        urlList.add("http://image.38.hn/public/attachment/201803/100651/201803141625005241.mp4");
        urlList.add("http://image.38.hn/public/attachment/201803/100651/201803141624378522.mp4");
        urlList.add("http://image.38.hn/public/attachment/201803/100651/201803131546119319.mp4");
        urlList.add("http://image.38.hn/public/attachment/201803/100651/201803151735198462.mp4");
        urlList.add("http://image.38.hn/public/attachment/201803/100651/201803150923220770.mp4");
        urlList.add("http://image.38.hn/public/attachment/201803/100651/201803150922255785.mp4");
        urlList.add("http://image.38.hn/public/attachment/201803/100651/201803150920130302.mp4");
        urlList.add("http://image.38.hn/public/attachment/201803/100651/201803141625005241.mp4");
        urlList.add("http://image.38.hn/public/attachment/201803/100651/201803141624378522.mp4");
        urlList.add("http://image.38.hn/public/attachment/201803/100651/201803131546119319.mp4");
        urlList.add("http://image.38.hn/public/attachment/201803/100651/201803151735198462.mp4");
        urlList.add("http://image.38.hn/public/attachment/201803/100651/201803150923220770.mp4");
        urlList.add("http://image.38.hn/public/attachment/201803/100651/201803150922255785.mp4");
        urlList.add("http://image.38.hn/public/attachment/201803/100651/201803150920130302.mp4");
        urlList.add("http://image.38.hn/public/attachment/201803/100651/201803141625005241.mp4");
        urlList.add("http://image.38.hn/public/attachment/201803/100651/201803141624378522.mp4");
        urlList.add("http://image.38.hn/public/attachment/201803/100651/201803131546119319.mp4");
        return urlList;
    }

    @Override
    public void onComplete() {
        LinearLayoutManager manager1= (LinearLayoutManager) recyclerView.getLayoutManager();
        int i=manager1.findFirstVisibleItemPosition();
        recyclerView.smoothScrollToPosition(i+1);
    }
}
