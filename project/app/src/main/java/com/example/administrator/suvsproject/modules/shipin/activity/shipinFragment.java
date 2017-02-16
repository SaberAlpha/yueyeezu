package com.example.administrator.suvsproject.modules.shipin.activity;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.administrator.suvsproject.R;
import com.example.administrator.suvsproject.activity.BaseFragment;
import com.example.administrator.suvsproject.activity.LogActivity;
import com.example.administrator.suvsproject.adapter.CommonAdapter;
import com.example.administrator.suvsproject.adapter.ViewHolder;
import com.example.administrator.suvsproject.i.BaseCallBack;
import com.example.administrator.suvsproject.modules.shipin.bean.ShipinInfo;
import com.example.administrator.suvsproject.modules.shipin.dao.Shipindao;
import com.example.administrator.suvsproject.util.ThreadTask;
import com.example.administrator.suvsproject.util.XutilsManager;

import org.xutils.ex.DbException;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by Administrator on 2016/11/11 0011.
 */
public class shipinFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private View mine;
    private SwipeRefreshLayout swipeRefreshLayout;
    private int page;
    private List<ShipinInfo> datalist;
    private View footview;
    private CommonAdapter<ShipinInfo> adapter;
    private ListView listView;
    private View playview;
    private SurfaceView surfaceView;
    private SeekBar seekbar;
    private TextView curtime;
    private View playline;
    private ImageView stateview;
    private MediaPlayer mediaPlayer;
    private Uri uri;
    private boolean isstop = false;
    private View layoutview;
    private ImageView icon;
    private String TAG = "shipinFragment";
    private View totaltime;
    private View closeview;

    @Override
    protected void findViews(View view) {
        mine = view.findViewById(R.id.my_information_shipin);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.shipinswipe_layout);
        listView = (ListView) view.findViewById(R.id.shipinlist_view);
    }

    @Override
    protected void init() {
        datalist = new ArrayList<>();
        footview = getActivity().getLayoutInflater().inflate(R.layout.shipin_footloading_layout, listView, false);
        adapter = new CommonAdapter<ShipinInfo>(getContext(), datalist, R.layout.shipinfragment_itemlayout) {
            @Override
            public void convert(ViewHolder helper, int position, ShipinInfo item) {
                helper.setText(R.id.shipin_title, item.getTitle());
                Date timedata = new Date(item.getPublishtime());
                Log.e(TAG, "convert:item.getPublishtime()...... " + item.getPublishtime());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String time = simpleDateFormat.format(timedata);
                helper.setText(R.id.shipin_time, time);

                Date duatuon = new Date(item.getVideoduration());
                Log.e(TAG, "convert:item.getVideoduration()>>> " + item.getVideoduration());
                SimpleDateFormat durationfamat = new SimpleDateFormat("mm:ss");
                String duration = durationfamat.format(duatuon);
                Log.e(TAG, "convert: >>>>>>>>>>>>" + duration + ">>>>>>>>>" + duatuon);
                helper.setText(R.id.shipin_totaltime, (new SimpleDateFormat("mm:ss")).format(new Date(item.getVideoduration())));

//                helper.setImageByUrl(R.id.shipin_image, item.getIcon());


            }
        };
        listView.addFooterView(footview);
        listView.setAdapter(adapter);
        listView.removeFooterView(footview);


    }

    @Override
    protected void initEvent() {
        mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LogActivity.class);
                startActivity(intent);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                swipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.BLACK);
                requestNetData();

            }
        });
        listView.setOnItemClickListener(this);


        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == SCROLL_STATE_IDLE) {
                    int lastVisiblePosition = listView.getLastVisiblePosition();
                    if (lastVisiblePosition == datalist.size()) {
                        View childAt = listView.getChildAt(listView.getChildCount() - 1);
                        if (childAt.getBottom() == listView.getBottom()) {
                            page++;
                            requestNetData();
                        }
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }


    @Override
    protected void loadData() {
        ThreadTask.getInstance().executorDBThread(new Runnable() {
            @Override
            public void run() {
                try {
                    List<ShipinInfo> list = XutilsManager.getInstance().getDbManager().findAll(ShipinInfo.class);
                    if (list != null) {
                        datalist.addAll(list);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter.notifyDataSetChanged();
                            }
                        });

                    }
                } catch (DbException e) {
                    e.printStackTrace();
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        requestNetData();
                    }
                });
            }

        }, ThreadTask.ThreadPeriod.PERIOD_HIGHT);
    }

    @Override
    protected void requestNetData() {
        Shipindao.request(page, new BaseCallBack() {
            @Override
            public void success(Object data) {
                swipeRefreshLayout.setRefreshing(false);
                final List<ShipinInfo> list = (List<ShipinInfo>) data;
                if (page == 1) {
                    ThreadTask.getInstance().executorDBThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                XutilsManager.getInstance().getDbManager().delete(ShipinInfo.class);
                                XutilsManager.getInstance().getDbManager().save(list);
                            } catch (DbException e) {
                                e.printStackTrace();
                            }
                        }
                    }, ThreadTask.ThreadPeriod.PERIOD_HIGHT);
                    datalist.clear();

                }
                if (listView.getFooterViewsCount() == 0) {
                    listView.addFooterView(footview);

                }
                if (list.size() < 20) {
                    listView.removeFooterView(footview);

                }
                if (list != null) {
                    datalist.addAll(list);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void failed(int errorCode, Object data) {
                swipeRefreshLayout.setRefreshing(false);

            }
        });
    }

    private void play(String path, SurfaceView surfaceView) {

        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDataSource(getContext(), Uri.fromFile(new File("/sdcard/Download/video1.mp4")));
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        mediaPlayer.setDisplay(surfaceView.getHolder());
        mediaPlayer.prepareAsync();
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer mp) {
                mediaPlayer.start();
            }
        });
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_shipin;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        JCVideoPlayerStandard jcVideoPlayerStandard = (JCVideoPlayerStandard) view.findViewById(R.id.shipin_surfaceview);
        jcVideoPlayerStandard.setUp("http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4"
                , JCVideoPlayerStandard.SCREEN_LAYOUT_LIST, "嫂子闭眼睛");
        jcVideoPlayerStandard.thumbImageView.setImageURI(Uri.parse("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640"));
//             Videoplay videoplay=new Videoplay();
//             SurfaceView surfaceView= (SurfaceView) view.findViewById(R.id.shipin_surfaceview);
//          videoplay.request( getContext(),view, surfaceView,datalist.get(position).getVideourl(),new BaseCallBack(){
//
//
//              @Override
//              public void success(Object data) {
//
//
//              }
//
//              @Override
//              public void failed(int errorCode, Object data) {
//
//              }
//          });

//          playview = view.findViewById(R.id.shipin_play);
//             surfaceView = (SurfaceView) view.findViewById(R.id.shipin_surfaceview);
//        seekbar = (SeekBar) view.findViewById(R.id.shipin_seek_bar);

//        play(datalist.get(position).getVideourl(),surfaceView);
//        layoutview = view.findViewById(R.id.shipin_playview);
//        icon = (ImageView) view.findViewById(R.id.shipin_image);
//        totaltime = view.findViewById(R.id.shipin_totaltime);
//        closeview = view.findViewById(R.id.shipinvideo_close);
//        stateview = (ImageView) view.findViewById(R.id.shipinplay_state);
//        playline = view.findViewById(R.id.shipin_line);
//
//        curtime = (TextView) view.findViewById(R.id.shipin_time_compare);
//        closeview.setVisibility(View.VISIBLE);
//        totaltime.setVisibility(View.INVISIBLE);
//        playview.setVisibility(View.INVISIBLE);
//        icon.setVisibility(View.INVISIBLE);
//        layoutview.setVisibility(View.VISIBLE);
//
//        seekbar.setOnSeekBarChangeListener(this);
//        surfaceView.setOnTouchListener(this);
//        mediaPlayer = new MediaPlayer();
//        SurfaceHolder holder = surfaceView.getHolder();
//        holder.addCallback(this);
//
//        stateview.setOnClickListener(this);
//        stateview.setTag(false);
//
//        closeview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                layoutview.setVisibility(View.INVISIBLE);
//                surfaceView.setVisibility(View.INVISIBLE);
//                icon.setVisibility(View.VISIBLE);
//                stateview.setVisibility(View.VISIBLE);
//                mediaPlayer.stop();
//                mediaPlayer.release();
//                mediaPlayer=null;
//                isstop=true;
//
//            }
//        });

//        closeview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                layoutview.setVisibility(View.INVISIBLE);
//                surfaceView.setVisibility(View.INVISIBLE);
//                icon.setVisibility(View.VISIBLE);
//                stateview.setVisibility(View.VISIBLE);
//                mediaPlayer.stop();
//                mediaPlayer.release();
//                mediaPlayer = null;
//                isstop = true;


//        uri = Uri.parse(datalist.get(position).getVideourl());
//       uri=Uri.fromFile(new File("/sdcard/Download/video1.mp4")) ;
//        uri = Uri.fromFile(new File("/sdcard/Download/video1.mp4"));
//        Log.e(TAG, "onItemClick: ??????????"+uri+"------->" );
//
//        try {
//            mediaPlayer.setDataSource(getContext(),uri);
//            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//            mediaPlayer.prepareAsync();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                mediaPlayer.setDisplay(surfaceView.getHolder());
//                mediaPlayer.start();
//                seekbar.setMax(mediaPlayer.getDuration());
//                seekbar.setProgress(mediaPlayer.getCurrentPosition());
//
//                Date currentdate=new Date(mediaPlayer.getCurrentPosition());
//                Date duadata=new Date(mediaPlayer.getDuration());
//                SimpleDateFormat format=new SimpleDateFormat("mm:ss");
//                String current = format.format(currentdate);
//                String total = format.format(duadata);
//
//                curtime.setText(current+"/"+total);
//                updateprogress();
//            }
//        });
//        playview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                closeview.setVisibility(View.VISIBLE);
//                totaltime.setVisibility(View.INVISIBLE);
//                playview.setVisibility(View.INVISIBLE);
//                icon.setVisibility(View.INVISIBLE);
//                layoutview.setVisibility(View.VISIBLE);
//
//            }
//        });


//        uri = Uri.parse(datalist.get(position).getVideourl());
//        uri = Uri.fromFile(new File("/sdcard/Download/video1.mp4"));
//        uri = Uri.fromFile(new File("/sdcard/Download/video1.mp4"));
//        Log.e(TAG, "onItemClick: ??????????" + uri + "------->");
//
//        try {
//            mediaPlayer.setDataSource(getContext(), uri);
//            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//            mediaPlayer.prepareAsync();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                mediaPlayer.setDisplay(surfaceView.getHolder());
//                mediaPlayer.start();
//                seekbar.setMax(mediaPlayer.getDuration());
//                seekbar.setProgress(mediaPlayer.getCurrentPosition());
//
//                Date currentdate = new Date(mediaPlayer.getCurrentPosition());
//                Date duadata = new Date(mediaPlayer.getDuration());
//                SimpleDateFormat format = new SimpleDateFormat("mm:ss");
//                String current = format.format(currentdate);
//                String total = format.format(duadata);
//
//                curtime.setText(current + "/" + total);
//                updateprogress();
//            }
//        });
//        playview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                closeview.setVisibility(View.VISIBLE);
//                totaltime.setVisibility(View.INVISIBLE);
//                playview.setVisibility(View.INVISIBLE);
//                icon.setVisibility(View.INVISIBLE);
//                layoutview.setVisibility(View.VISIBLE);
//
//            }
//        });


    }

//    Handler handler=new Handler();
//    private void updateprogress() {
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if(isstop){
//                    return;
//                }
//                if(mediaPlayer.isPlaying()){
//                    Date currentdate=new Date(mediaPlayer.getCurrentPosition());
//                    Date duadata=new Date(mediaPlayer.getDuration());
//                    SimpleDateFormat format=new SimpleDateFormat("mm:ss");
//                    String current = format.format(currentdate);
//                    String total = format.format(duadata);
//
//                    curtime.setText(current+"/"+total);
//                    seekbar.setProgress(mediaPlayer.getCurrentPosition());
//                    updateprogress();
//                }
//            }
//        },1000);
//    }
//
//    @Override
//    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//        if(fromUser){
//            mediaPlayer.seekTo(progress);
//        }
//    }
//
//    @Override
//    public void onStartTrackingTouch(SeekBar seekBar) {
//
//    }
//
//    @Override
//    public void onStopTrackingTouch(SeekBar seekBar) {
//
//    }
//
//    @Override
//    public boolean onTouch(View v, MotionEvent event) {
//        return false;
//    }
//
//    @Override
//    public void surfaceCreated(SurfaceHolder holder) {
//        try {
//            mediaPlayer.setDataSource(getContext(),uri);
//            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//            mediaPlayer.prepare();
////            mediaPlayer.setDisplay(holder);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
//
//    }
//
//    @Override
//    public void surfaceDestroyed(SurfaceHolder holder) {
//        mediaPlayer.stop();
//        isstop=true;
//        playview.setVisibility(View.VISIBLE);
//        icon.setVisibility(View.VISIBLE);
//        layoutview.setVisibility(View.INVISIBLE);
//        handler=null;
//        mediaPlayer.release();
//    }
//
//    @Override
//    public void onClick(View v) {
//        if((boolean)stateview.getTag()){
//            mediaPlayer.start();
//            stateview.setTag(false);
//            stateview.setImageResource(R.drawable.biz_video_pause);
//            updateprogress();
//        }else{
//            mediaPlayer.pause();
//            stateview.setImageResource(R.drawable.biz_video_play);
//            stateview.setTag(true);
//        }
//    }

//    Handler handler = new Handler();
//
//    private void updateprogress() {
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if (isstop) {
//                    return;
//                }
//
//                if (mediaPlayer.isPlaying()) {
//                    Date currentdate = new Date(mediaPlayer.getCurrentPosition());
//                    Date duadata = new Date(mediaPlayer.getDuration());
//                    SimpleDateFormat format = new SimpleDateFormat("mm:ss");
//                    String current = format.format(currentdate);
//                    String total = format.format(duadata);
//
//                    curtime.setText(current + "/" + total);
//                    seekbar.setProgress(mediaPlayer.getCurrentPosition());
//                    updateprogress();
//                }
//            }
//        }, 1000);
//    }
//
//    @Override
//    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//        if (fromUser) {
//            mediaPlayer.seekTo(progress);
//        }
//    }
//
//    @Override
//    public void onStartTrackingTouch(SeekBar seekBar) {
//
//    }
//
//    @Override
//    public void onStopTrackingTouch(SeekBar seekBar) {
//
//    }
//
//    @Override
//    public boolean onTouch(View v, MotionEvent event) {
//        return false;
//    }
//
//    @Override
//    public void surfaceCreated(SurfaceHolder holder) {
//        try {
//            mediaPlayer.setDataSource(getContext(), uri);
//            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//            mediaPlayer.prepare();
////            mediaPlayer.setDisplay(holder);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
//
//    }
//
//    @Override
//    public void surfaceDestroyed(SurfaceHolder holder) {
//        mediaPlayer.stop();
//        isstop = true;
//        playview.setVisibility(View.VISIBLE);
//        icon.setVisibility(View.VISIBLE);
//        layoutview.setVisibility(View.INVISIBLE);
//        handler = null;
//        mediaPlayer.release();
//    }
//
//    @Override
//    public void onClick(View v) {
//        if ((boolean) stateview.getTag()) {
//            mediaPlayer.start();
//            stateview.setTag(false);
//            stateview.setImageResource(R.drawable.biz_video_pause);
//            updateprogress();
//        } else {
//            mediaPlayer.pause();
//            stateview.setImageResource(R.drawable.biz_video_play);
//            stateview.setTag(true);
//        }
//    }

}
