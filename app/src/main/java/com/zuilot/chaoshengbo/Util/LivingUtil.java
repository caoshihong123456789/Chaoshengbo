package com.zuilot.chaoshengbo.Util;


import android.hardware.Camera;
import android.view.MotionEvent;

import com.qiniu.android.dns.DnsManager;
import com.qiniu.android.dns.IResolver;
import com.qiniu.android.dns.NetworkInfo;
import com.qiniu.android.dns.http.DnspodFree;
import com.qiniu.android.dns.local.AndroidDnsServer;
import com.qiniu.android.dns.local.Resolver;
import com.qiniu.pili.droid.streaming.CameraStreamingSetting;
import com.qiniu.pili.droid.streaming.MicrophoneStreamingSetting;
import com.qiniu.pili.droid.streaming.StreamingProfile;
import com.qiniu.pili.droid.streaming.StreamingState;
import com.qiniu.pili.droid.streaming.StreamingStateChangedListener;
import com.zuilot.chaoshengbo.view.CameraPreviewFrameView;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URISyntaxException;

/**
 * Created by caoshihong on 2016/11/17.
 * <p>
 * 直播间 -- 主播端  util
 */

public class LivingUtil implements CameraPreviewFrameView.Listener,
        StreamingStateChangedListener {


    private CameraStreamingSetting cameraStreamingSetting;//摄像头参数设置类
    private MicrophoneStreamingSetting mMicrophoneStreamingSetting;//麦克风参数设置类
    protected StreamingProfile mProfile;

    /**
     * 设置摄像头
     * @return
     */
    public CameraStreamingSetting getCameraStreamingSetting() {
        if (cameraStreamingSetting == null) {
            cameraStreamingSetting = new CameraStreamingSetting();
            cameraStreamingSetting.setCameraId(Camera.CameraInfo.CAMERA_FACING_BACK)
                    .setContinuousFocusModeEnabled(true)
                    .setRecordingHint(false)
                    .setCameraFacingId(chooseCameraFacingId())
                    .setBuiltInFaceBeautyEnabled(true)
//                .setCameraSourceImproved(true)
//                .setCaptureCameraFrameOnly(true)
                    .setResetTouchFocusDelayInMs(3000)
//                .setFocusMode(CameraStreamingSetting.FOCUS_MODE_CONTINUOUS_PICTURE)
                    .setCameraPrvSizeLevel(CameraStreamingSetting.PREVIEW_SIZE_LEVEL.SMALL)
                    .setCameraPrvSizeRatio(CameraStreamingSetting.PREVIEW_SIZE_RATIO.RATIO_16_9)
                    .setFaceBeautySetting(new CameraStreamingSetting.FaceBeautySetting(1.0f, 1.0f, 0.8f))
                    .setVideoFilter(CameraStreamingSetting.VIDEO_FILTER_TYPE.VIDEO_FILTER_BEAUTY);
        }
        return cameraStreamingSetting;
    }

    /**
     * 麦克风设置
     * @return
     */
    public MicrophoneStreamingSetting getmMicrophoneStreamingSetting() {
        if(mMicrophoneStreamingSetting == null){
            mMicrophoneStreamingSetting = new MicrophoneStreamingSetting();
            mMicrophoneStreamingSetting.setBluetoothSCOEnabled(false);
        }
        return mMicrophoneStreamingSetting;
    }

    /**
     * 基本参数设置
     * @param streamJson 主播stream 需要根据这个url 新建推流器，设置基本概况
     * @return
     */
    public StreamingProfile getmProfile(String streamJson) {
        if(mProfile == null){
            mProfile = new StreamingProfile();
            try {
                mProfile.setPublishUrl(streamJson);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            mProfile.setVideoQuality(StreamingProfile.VIDEO_QUALITY_MEDIUM2)
                    .setAudioQuality(StreamingProfile.AUDIO_QUALITY_MEDIUM2)
//                .setPreferredVideoEncodingSize(960, 544)
                    .setEncodingSizeLevel(Config.ENCODING_LEVEL)
                    .setEncoderRCMode(StreamingProfile.EncoderRCModes.BITRATE_PRIORITY)
//                .setAVProfile(avProfile)
                    .setDnsManager(getMyDnsManager())
                    .setAdaptiveBitrateEnable(true)
                    .setFpsControllerEnable(true)
                    .setStreamStatusConfig(new StreamingProfile.StreamStatusConfig(3))
//                .setEncodingOrientation(StreamingProfile.ENCODING_ORIENTATION.PORT)
                    .setSendingBufferProfile(new StreamingProfile.SendingBufferProfile(0.2f, 0.8f, 3.0f, 20 * 1000));

        }
        return mProfile;
    }

    /**
     * 这个方法不知道是干什么的，猜测是初始时设置摄像头用的
     * @return
     */
    private CameraStreamingSetting.CAMERA_FACING_ID chooseCameraFacingId() {
        if (CameraStreamingSetting.hasCameraFacing(CameraStreamingSetting.CAMERA_FACING_ID.CAMERA_FACING_3RD)) {
            return CameraStreamingSetting.CAMERA_FACING_ID.CAMERA_FACING_3RD;
        } else if (CameraStreamingSetting.hasCameraFacing(CameraStreamingSetting.CAMERA_FACING_ID.CAMERA_FACING_FRONT)) {
            return CameraStreamingSetting.CAMERA_FACING_ID.CAMERA_FACING_FRONT;
        } else {
            return CameraStreamingSetting.CAMERA_FACING_ID.CAMERA_FACING_BACK;
        }
    }

    /**
     * dns manager
     * @return
     */
    private static DnsManager getMyDnsManager() {
        IResolver r0 = new DnspodFree();
        IResolver r1 = AndroidDnsServer.defaultResolver();
        IResolver r2 = null;
        try {
            r2 = new Resolver(InetAddress.getByName("119.29.29.29"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return new DnsManager(NetworkInfo.normal, new IResolver[]{r0, r1, r2});
    }



    /*CameraPreviewFrameView 监听手势滑动类*/

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onZoomValueChanged(float factor) {
        return false;
    }
    /*CameraPreviewFrameView 监听手势滑动类*/

    /*StreamingStateChangedListener
    * 视频状态监听
    * */


    @Override
    public void onStateChanged(StreamingState streamingState, Object extra) {
        LogUtil.e(LogUtil.TAG, "StreamingState streamingState:" + streamingState + ",extra:" + extra);

        switch (streamingState) {
            case PREPARING:
                break;
            case READY:
                break;
            case CONNECTING:
                break;
            case STREAMING:
                break;
            case SHUTDOWN:
                break;
            case IOERROR:
                break;
            case UNKNOWN:
                break;
            case SENDING_BUFFER_EMPTY:
                break;
            case SENDING_BUFFER_FULL:
                break;
            case AUDIO_RECORDING_FAIL:
                break;
            case OPEN_CAMERA_FAIL:
                break;
            case DISCONNECTED:
                break;
            case INVALID_STREAMING_URL:
                break;
            case UNAUTHORIZED_STREAMING_URL:
                break;
            case CAMERA_SWITCHED:
                break;
            case TORCH_INFO:
                break;
        }
    }
    /*StreamingStateChangedListener
    * 视频状态监听
    * 结束
    * */
}
