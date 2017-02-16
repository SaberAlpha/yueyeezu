package com.example.administrator.suvsproject.modules.shipin.util;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.administrator.suvsproject.R;
import com.example.administrator.suvsproject.i.BaseCallBack;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/14 0014.
 */
public class Videoplay implements SeekBar.OnSeekBarChangeListener, View.OnTouchListener, SurfaceHolder.Callback, View.OnClickListener {
    private View playview;
    private SeekBar seekbar;
    private TextView curtime;
    private View playline;
    private ImageView stateview;
    private MediaPlayer mediaPlayer;
    private Uri uri;
    private boolean isstop=false;
    private View layoutview;
    private ImageView icon;
    private String TAG="shipinFragment";
    private View totaltime;
    private View closeview;
    private Context context;


    public void request(Context context, View view, final SurfaceView surfaceView, String videourl, BaseCallBack baseCallBack) {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setDisplay(surfaceView.getHolder());
        mediaPlayer.prepareAsync();

        playview = view.findViewById(R.id.shipin_play);
        seekbar = (SeekBar) view.findViewById(R.id.shipin_seek_bar);
        seekbar.setOnSeekBarChangeListener(this);

        layoutview = view.findViewById(R.id.shipin_playview);


        curtime = (TextView) view.findViewById(R.id.shipin_time_compare);
        surfaceView.setOnTouchListener(this);
        SurfaceHolder holder = surfaceView.getHolder();
        holder.addCallback(this);
        playline = view.findViewById(R.id.shipin_line);
        stateview = (ImageView) view.findViewById(R.id.shipinplay_state);
        stateview.setOnClickListener(this);
        stateview.setTag(false);
     //   icon = (ImageView) view.findViewById(R.id.shipin_image);
        totaltime = view.findViewById(R.id.shipin_totaltime);
        closeview = view.findViewById(R.id.shipinvideo_close);
        closeview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutview.setVisibility(View.INVISIBLE);
                surfaceView.setVisibility(View.INVISIBLE);
                icon.setVisibility(View.VISIBLE);
                stateview.setVisibility(View.VISIBLE);
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer=null;
                isstop=true;

            }
        });

//        uri = Uri.parse(datalist.get(position).getVideourl());
//       uri=Uri.fromFile(new File("/sdcard/Download/video1.mp4")) ;
        uri = Uri.fromFile(new File("/sdcard/Download/video1.mp4"));
        Log.e(TAG, "onItemClick: ??????????"+uri+"------->" );
        try {
            mediaPlayer.setDataSource(context,uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mediaPlayer.start();
                seekbar.setMax(mediaPlayer.getDuration());
                seekbar.setProgress(mediaPlayer.getCurrentPosition());

                Date currentdate=new Date(mediaPlayer.getCurrentPosition());
                Date duadata=new Date(mediaPlayer.getDuration());
                SimpleDateFormat format=new SimpleDateFormat("mm:ss");
                String current = format.format(currentdate);
                String total = format.format(duadata);

                curtime.setText(current+"/"+total);
                updateprogress();
            }
        });
        playview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                closeview.setVisibility(View.VISIBLE);
                totaltime.setVisibility(View.INVISIBLE);
                playview.setVisibility(View.INVISIBLE);
                icon.setVisibility(View.INVISIBLE);
                layoutview.setVisibility(View.VISIBLE);

            }
        });

    }
    Handler handler=new Handler();
    private void updateprogress() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(isstop){
                    return;
                }

                if(mediaPlayer.isPlaying()){
                    Date currentdate=new Date(mediaPlayer.getCurrentPosition());
                    Date duadata=new Date(mediaPlayer.getDuration());
                    SimpleDateFormat format=new SimpleDateFormat("mm:ss");
                    String current = format.format(currentdate);
                    String total = format.format(duadata);

                    curtime.setText(current+"/"+total);
                    seekbar.setProgress(mediaPlayer.getCurrentPosition());
                    updateprogress();
                }
            }
        },1000);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if(fromUser){
            mediaPlayer.seekTo(progress);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
//        try {
//            mediaPlayer.setDataSource(context,uri);
//            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//            mediaPlayer.setDisplay(holder);
//            mediaPlayer.prepareAsync();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mediaPlayer.stop();
        isstop=true;
        playview.setVisibility(View.VISIBLE);
        icon.setVisibility(View.VISIBLE);
        layoutview.setVisibility(View.INVISIBLE);
        handler=null;
        mediaPlayer.release();
    }

    @Override
    public void onClick(View v) {
        if((boolean)stateview.getTag()){
            mediaPlayer.start();
            stateview.setTag(false);
            stateview.setImageResource(R.drawable.biz_video_pause);
            updateprogress();
        }else{
            mediaPlayer.pause();
            stateview.setImageResource(R.drawable.biz_video_play);
            stateview.setTag(true);
        }
    }
}
