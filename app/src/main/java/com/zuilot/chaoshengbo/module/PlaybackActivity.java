package com.zuilot.chaoshengbo.module;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pili.pldroid.player.PLMediaPlayer;
import com.pili.pldroid.player.widget.PLVideoTextureView;
import com.pili.pldroid.player.widget.PLVideoView;
import com.zuilot.chaoshengbo.R;
import com.zuilot.chaoshengbo.Util.LogUtil;
import com.zuilot.chaoshengbo.Util.NetWorkUtil;
import com.zuilot.chaoshengbo.Util.PlaybackUtil;
import com.zuilot.chaoshengbo.Util.ToastUtil;
import com.zuilot.chaoshengbo.activity.BaseActivity;
import com.zuilot.chaoshengbo.javabean.LiveActivityRecommendedBean;
import com.zuilot.chaoshengbo.model.LiveModel;
import com.zuilot.chaoshengbo.model.UserInfo;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.functions.Action1;

public class PlaybackActivity extends BaseActivity {

    @Bind(R.id.playback_VideoView)
    PLVideoTextureView playbackVideoView;
    @Bind(R.id.playback_user_img)
    ImageView playbackUserImg;
    @Bind(R.id.playback_user_txt)
    TextView playbackUserTxt;
    @Bind(R.id.playback_user_layout)
    LinearLayout playbackUserLayout;
    @Bind(R.id.playback_attention)
    Button playbackAttention;
    @Bind(R.id.playback_close)
    ImageView playbackClose;
    @Bind(R.id.playback_calories_txt)
    TextView playbackCaloriesTxt;
    @Bind(R.id.playback_calories_layout)
    LinearLayout playbackCaloriesLayout;
    @Bind(R.id.playback_title)
    TextView playbackTitle;
    @Bind(R.id.playback_progressbar)
    ProgressBar playbackProgressbar;
    @Bind(R.id.activity_playback)
    RelativeLayout activityPlayback;

    @Bind(R.id.playback_pause)
    ImageView playbackPause;
    @Bind(R.id.playback_like)
    ImageView playbackLike;
    @Bind(R.id.playback_share)
    ImageView playbackShare;
    @Bind(R.id.playback_time)
    TextView playbackTime;
    @Bind(R.id.playback_seekbar)
    AppCompatSeekBar playbackSeekbar;
    @Bind(R.id.playback_controll_view)
    RelativeLayout playbackControllView;
    private static String ARGUMENT_LIVEMODEL = "playback_liveModel";//回放参数
    private static String ARGUMENT_USERINFO = "playback_userInfo";//回放参数

    private LiveActivityRecommendedBean.myUserInfoBean myUserInfoBean;
    private LiveModel liveModel;
    private UserInfo userInfo;
    private PlaybackUtil playUtil;
    private boolean mIsActivityPaused;//当前界面是否还在
    private Subscriber<PLMediaPlayer> getCurrentPositionSubscriber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playback);
        ButterKnife.bind(this);
        myUserInfoBean = (LiveActivityRecommendedBean.myUserInfoBean) getIntent().getSerializableExtra(ARGUMENT_USERINFO);

        if (myUserInfoBean == null) {
            liveModel = (LiveModel) getIntent().getSerializableExtra(ARGUMENT_LIVEMODEL);
            userInfo = liveModel.getUser();
        } else {
            liveModel = myUserInfoBean.getLive();
            userInfo = myUserInfoBean;
        }
        playUtil = new PlaybackUtil();
        initPlayerView();

    }

    public static void intoPlayBack(Context context, LiveActivityRecommendedBean.myUserInfoBean bean) {
        Intent intent = new Intent(context, PlaybackActivity.class);
        intent.putExtra(ARGUMENT_USERINFO, bean);
        context.startActivity(intent);
    }
    public static void intoPlayBackWithLiveModel(Context context, LiveModel bean) {
        Intent intent = new Intent(context, PlaybackActivity.class);
        intent.putExtra(ARGUMENT_LIVEMODEL, bean);
        context.startActivity(intent);
    }


    private void initPlayerView() {
        playbackSeekbar.setOnSeekBarChangeListener(mSeekListener);
        Glide.with(this).load(userInfo.getUser_avatar()).into(playbackUserImg);
        playbackUserTxt.setText(userInfo.getUser_name());
        playbackCaloriesTxt.setText(userInfo.getCalorie_balance());
        playbackTitle.setText(liveModel.getTitle());

        //设置加载动画
        playbackVideoView.setBufferingIndicator(playbackProgressbar);
        //设置播放器参数
        playbackVideoView.setAVOptions(playUtil.getAVOptions(this));


        if (liveModel.getHorizontal().equals("1")) {//pc直播
            //设置预览模式
            playbackVideoView.setDisplayAspectRatio(PLVideoView.ASPECT_RATIO_16_9);
        } else {
            //设置预览模式
            playbackVideoView.setDisplayAspectRatio(PLVideoView.ASPECT_RATIO_PAVED_PARENT);
        }

        playbackVideoView.setOnPreparedListener(playUtil);
        playbackVideoView.setOnInfoListener(playUtil);
        playbackVideoView.setOnCompletionListener(playUtil);
        playbackVideoView.setOnVideoSizeChangedListener(playUtil);
        playbackVideoView.setOnErrorListener(playUtil);

        //设置播放地址
        playbackVideoView.setVideoPath(liveModel.getPlayback_url());
        playbackVideoView.start();


    }


    @Override
    public void onPause() {
        super.onPause();
        playbackVideoView.pause();
        mIsActivityPaused = false;
    }


    @Override
    public void onResume() {
        super.onResume();
        playbackVideoView.start();
        mIsActivityPaused = true;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        playUtil.onDestroy();
        playbackVideoView.stopPlayback();
        getCurrentPositionSubscriber=null;
    }

    public Subscriber<String> getSubscriber() {
        return new Subscriber<String>() {
            @Override
            public void onNext(String aLong) {
                LogUtil.e("onnext:"+aLong);
//                playbackPause.setImageResource(R.mipmap.playback_start);
                ToastUtil.disPlayOnlyMesShort(PlaybackActivity.this,aLong);
            }

            @Override
            public void onError(Throwable e) {
                LogUtil.e("onError:"+e.getMessage());
            }

            @Override
            public void onCompleted() {
                Log.e("回放界面重连机制", "---" + liveModel.getPlayback_url());
                if (mIsActivityPaused && NetWorkUtil.isNetWorkAbailable(PlaybackActivity.this)) {
                    playbackVideoView.setVideoPath(liveModel.getPlayback_url());
                    playbackVideoView.seekTo(playUtil.getCurrentPosition());
                    playbackVideoView.start();
                } else {
                    playbackVideoView.setVideoPath(liveModel.getPlayback_url());
                    playbackVideoView.seekTo(playUtil.getCurrentPosition());
                    playbackVideoView.pause();
                }
            }

        };
    }

    public Action1<Boolean> getPauseObservable(){
        return new Action1<Boolean>() {
            @Override
            public void call(Boolean o) {
                if(o.booleanValue()){
                    playbackPause.setImageResource(R.mipmap.playback_pause);
                }else{
                    playbackPause.setImageResource(R.mipmap.playback_start);
                }
            }
        };
    }

    /**
     * 获得改变ui的observable，每秒钟都要调用以在播放视频时更改进度条
     * @return 根据long类型的参数 改变ui 如果参数为负数证明停止
     */
    public Subscriber<PLMediaPlayer> getCurrentSubscriber(){
        if(getCurrentPositionSubscriber == null ){
            getCurrentPositionSubscriber= new Subscriber<PLMediaPlayer>(){
                @Override
                public void onCompleted() {
                    LogUtil.e("onCompleted");
                }

                @Override
                public void onError(Throwable e) {
                    LogUtil.e("输出seekbar当前进度--onError",e.getMessage());
                }

                @Override
                public void onNext(PLMediaPlayer mediaPlayer) {
                    if(mediaPlayer != null){
                        LogUtil.e("---观察者log "+mediaPlayer.isPlaying()+"--"+mediaPlayer.isLooping());
                        if(mediaPlayer.getDuration() > 0){
                            long str=1000L * mediaPlayer.getCurrentPosition() / mediaPlayer.getDuration();
                            playbackTime.setText("-"+playUtil.generateTime(mediaPlayer.getDuration()- mediaPlayer.getCurrentPosition()));
                            playbackSeekbar.setProgress((int)str);
                            playUtil.setCurrentPosition(mediaPlayer.getCurrentPosition());
                            LogUtil.e("---输出seekbar当前进度==="+str);
                            if(str == 1000){//播放完成
                                playbackVideoView.stopPlayback();
                                ToastUtil.disPlayOnlyMesShort(PlaybackActivity.this,"播放完成");
                                playbackProgressbar.setVisibility(View.GONE);
                                playUtil.onDestroy();
                            }
                        }
                    }

                }
            };
        }
        return getCurrentPositionSubscriber;
    }
    public void setGetCurrentPositionSubscriber(){
        getCurrentPositionSubscriber=null;
    }


    @OnClick({R.id.playback_user_layout, R.id.playback_attention, R.id.playback_close, R.id.playback_calories_layout, R.id.playback_pause})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.playback_user_layout:
                break;
            case R.id.playback_attention:
                break;
            case R.id.playback_close:
                this.finish();
                break;
            case R.id.playback_calories_layout:
                break;
            case R.id.playback_pause:
                playUtil.onPause();
                break;
        }
    }

    private SeekBar.OnSeekBarChangeListener mSeekListener = new SeekBar.OnSeekBarChangeListener() {

        public void onStartTrackingTouch(SeekBar bar) {
            playUtil.setSeekBarTouch(true);
            LogUtil.e("seekbar开始触发");
        }

        public void onProgressChanged(SeekBar bar, int progress, boolean fromuser) {
            LogUtil.e("seekbar正在拖动"+progress+"----"+fromuser);
        }

        public void onStopTrackingTouch(SeekBar bar) {
            playUtil.seekTo(bar.getProgress());
            playUtil.setSeekBarTouch(false);
            LogUtil.e("seekbar结束触发");
        }
    };
}
