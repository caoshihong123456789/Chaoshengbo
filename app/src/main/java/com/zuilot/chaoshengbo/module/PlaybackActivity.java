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
import android.widget.TextView;

import com.pili.pldroid.player.widget.PLVideoTextureView;
import com.pili.pldroid.player.widget.PLVideoView;
import com.zuilot.chaoshengbo.R;
import com.zuilot.chaoshengbo.Util.MmediaController2;
import com.zuilot.chaoshengbo.Util.NetWorkUtil;
import com.zuilot.chaoshengbo.Util.PlaybackUtil;
import com.zuilot.chaoshengbo.activity.BaseActivity;
import com.zuilot.chaoshengbo.model.LiveModel;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
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

    private LiveModel liveModel;
    private PlaybackUtil playUtil;
    private boolean mIsActivityPaused;//当前界面是否还在
    private MmediaController2 mMediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playback);
        ButterKnife.bind(this);
        liveModel = (LiveModel) getIntent().getSerializableExtra(ARGUMENT_LIVEMODEL);
        playUtil = new PlaybackUtil();
        initPlayerView();
    }

    public static void intoPlayBack(Context context, LiveModel bean) {
        Intent intent = new Intent(context, PlaybackActivity.class);
        intent.putExtra(ARGUMENT_LIVEMODEL, bean);
        context.startActivity(intent);
    }

    /*private void initView() {
        Observable.interval(0, 1000, TimeUnit.SECONDS)
                .map(new Func1<Long, Long>() {
                    @Override
                    public Long call(Long aLong) {

                        return null;
                    }
                })
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {

                    }
                });
    }*/

    private void initPlayerView() {
        //设置播放控制器
        mMediaController = new MmediaController2(this);
        playbackVideoView.setMediaController(mMediaController);
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
        playbackVideoView.stopPlayback();
    }

    public Action1<Long> getSubscriber() {
        return new Action1<Long>() {
            @Override
            public void call(Long s) {
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

    @OnClick({R.id.playback_user_layout, R.id.playback_attention, R.id.playback_close, R.id.playback_calories_layout})
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
        }
    }
}
