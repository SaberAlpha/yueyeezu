package com.example.administrator.suvsproject.modules.shipin.util;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.SurfaceView;

/**
 * Created by Administrator on 2016/11/14 0014.
 */
public class CustomSurfaceView extends SurfaceView  {

    private MediaPlayer mediaplay;

    public CustomSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {

        mediaplay = new MediaPlayer();

        mediaplay.prepareAsync();
    }

   
}
