package com.zuilot.chaoshengbo.module;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.qiniu.pili.droid.streaming.AVCodecType;
import com.qiniu.pili.droid.streaming.MediaStreamingManager;
import com.qiniu.pili.droid.streaming.WatermarkSetting;
import com.qiniu.pili.droid.streaming.widget.AspectFrameLayout;
import com.zuilot.chaoshengbo.NetUtil.BaseApi;
import com.zuilot.chaoshengbo.NetUtil.BaseSubscriber;
import com.zuilot.chaoshengbo.NetUtil.NetUtil;
import com.zuilot.chaoshengbo.R;
import com.zuilot.chaoshengbo.Util.LivingUtil;
import com.zuilot.chaoshengbo.activity.BaseActivity;
import com.zuilot.chaoshengbo.javabean.BaseResponseBean;
import com.zuilot.chaoshengbo.model.LiveModel;
import com.zuilot.chaoshengbo.view.CameraPreviewFrameView;
import butterknife.Bind;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LivingActivity extends BaseActivity {

    @Bind(R.id.cameraPreview_surfaceView)
    CameraPreviewFrameView cameraPreviewSurfaceView;
    @Bind(R.id.cameraPreview_afl)
    AspectFrameLayout cameraPreviewAfl;
    @Bind(R.id.activity_living)
    RelativeLayout activityLiving;

    private static String ARGUMENG_ISMUTE="ismute";
    private static String ARGUMENG_CAMERAID="cameraid";
    private static String ARGUMENG_LIVEMODEL="liveModel";

    private LiveModel liveModel;//直播信息bean
    private LivingUtil livingUtil;
    private WatermarkSetting watermarksetting;

    protected MediaStreamingManager mMediaStreamingManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_living);
        ButterKnife.bind(this);
        liveModel=(LiveModel) getIntent().getSerializableExtra(ARGUMENG_LIVEMODEL);
        initView();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMediaStreamingManager.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMediaStreamingManager.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMediaStreamingManager.destroy();
    }


    private void initView(){
        livingUtil=new LivingUtil();
        cameraPreviewAfl.setShowMode(AspectFrameLayout.SHOW_MODE.FULL);
        cameraPreviewSurfaceView.setListener(livingUtil);

        //水印设置
        watermarksetting = new WatermarkSetting(this);
        watermarksetting.setResourceId(R.mipmap.ic_launcher)
                .setAlpha(100)
                .setSize(WatermarkSetting.WATERMARK_SIZE.MEDIUM)
                .setCustomPosition(0.5f, 0.5f);
        //水印设置结束

        mMediaStreamingManager = new MediaStreamingManager(this, cameraPreviewAfl, cameraPreviewSurfaceView,
                AVCodecType.SW_VIDEO_WITH_SW_AUDIO_CODEC); // sw codec
        mMediaStreamingManager.prepare(livingUtil.getCameraStreamingSetting(), livingUtil.getmMicrophoneStreamingSetting(),
                watermarksetting, livingUtil.getmProfile("{\"id\":\"z1.liaoqiuba.100530340\",\"createdAt\":\"2016-08-08T14:36:33.541+08:00\",\"updatedAt\":\"2016-11-11T09:52:59.092+08:00\",\"expireAt\":\"2017-11-12T09:52:59.092+08:00\",\"title\":\"100530340\",\"hub\":\"liaoqiuba\",\"disabledTill\":0,\"disabled\":false,\"publishKey\":\"7ba28000-92b6-4c78-9a46-01c0b4a70b9a\",\"publishSecurity\":\"static\",\"hosts\":{\"publish\":{\"rtmp\":\"pili-publish.yingboxuncai.com\"},\"live\":{\"hdl\":\"pili-live-hdl.yingboxuncai.com\",\"hls\":\"pili-live-hls.yingboxuncai.com\",\"http\":\"pili-live-hls.yingboxuncai.com\",\"rtmp\":\"pili-live-rtmp.yingboxuncai.com\",\"snapshot\":\"1000160.live1-snapshot.z1.pili.qiniucdn.com\"},\"playback\":{\"hls\":\"1000160.playback1.z1.pili.qiniucdn.com\",\"http\":\"1000160.playback1.z1.pili.qiniucdn.com\"},\"play\":{\"http\":\"pili-live-hls.yingboxuncai.com\",\"rtmp\":\"pili-live-rtmp.yingboxuncai.com\"}}}"));//liveModel.getStream_json()

        mMediaStreamingManager.setStreamingStateListener(livingUtil);


    }


    /**
     * 开始直播 开始直播之前需要根据用户的id，想服务端请求直播对象信息，再根据这个bean获得streamJson来开始直播
     *
     * @param CameraFacingId 当前摄像头是前置摄像头还是后置摄像头，1-前置， 0-后置
     * @param isMute         当前是否静音 0-静音 ， 1-有声
     */
    public static void startLiving(final Context context, final int CameraFacingId, final int isMute) {

        NetUtil.GetApi().LivingActivityGetLiveModel(BaseApi.user_id,"测试直播",0)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<BaseResponseBean<LiveModel>>(context) {
            @Override
            public void onNext(BaseResponseBean<LiveModel> liveModelBaseResponseBean) {
                super.onNext(liveModelBaseResponseBean);
                Intent intent = new Intent(context, LivingActivity.class);
                intent.putExtra(ARGUMENG_ISMUTE, isMute);
                intent.putExtra(ARGUMENG_CAMERAID, CameraFacingId);
                intent.putExtra(ARGUMENG_LIVEMODEL, liveModelBaseResponseBean.getData());
                context.startActivity(intent);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }

            @Override
            public void onCompleted() {
                super.onCompleted();
            }

            @Override
            public void onStart() {
                super.onStart();
            }
        });

    }

}
