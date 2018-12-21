package com.example.recyclervideo;

import android.content.Context;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

import com.danikula.videocache.HttpProxyCacheServer;

import cn.jzvd.JZMediaManager;
import cn.jzvd.JZUtils;
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

/**
 * 作者： ch
 * 时间： 2018/8/17 0017-下午 5:14
 * 描述：
 * 来源：
 */


public class MyVideoPlayer extends JzvdStd {
    private Context context;
    private MainActivity activity;

    public MyVideoPlayer(Context context) {
        super(context);
        this.context = context;
        activity = (MainActivity) context;
    }

    public MyVideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        activity = (MainActivity) context;
    }

    @Override
    public void onAutoCompletion() {

        thumbImageView.setVisibility(View.GONE);

        if (currentScreen == SCREEN_WINDOW_FULLSCREEN) {
            onStateAutoComplete();
            setUp(jzDataSource, Jzvd.SCREEN_WINDOW_FULLSCREEN);

        } else {
            super.onAutoCompletion();
            setUp(jzDataSource, Jzvd.SCREEN_WINDOW_FULLSCREEN);


        }
        activity.onComplete();
    }

    @Override
    public void setUp(String url, String title, int screen) {

        if (url.startsWith("http")) {
            HttpProxyCacheServer proxy = MyApplication.getProxy(context);
            String proxyUrl = proxy.getProxyUrl(url);
            super.setUp(proxyUrl, title, screen);
        } else {
            super.setUp(url, title, screen);
        }
    }

    @Override
    public void init(final Context context) {
        super.init(context);

    }


    @Override
    public void startVideo() {
        if (currentScreen == SCREEN_WINDOW_FULLSCREEN) {
            initTextureView();
            addTextureView();
            AudioManager mAudioManager = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
            mAudioManager.requestAudioFocus(onAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
            JZUtils.scanForActivity(getContext()).getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

            JZMediaManager.setDataSource(jzDataSource);
            JZMediaManager.instance().positionInList = positionInList;
            onStatePreparing();
        } else {
            super.startVideo();
        }
    }


}
