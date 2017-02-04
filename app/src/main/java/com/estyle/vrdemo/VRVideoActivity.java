package com.estyle.vrdemo;

import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.estyle.vrdemo.databinding.ActivityVrvideoBinding;
import com.google.vr.sdk.widgets.video.VrVideoEventListener;
import com.google.vr.sdk.widgets.video.VrVideoView;

import java.io.IOException;

public class VRVideoActivity extends AppCompatActivity {

    private String videoPath = "http://video.quhuwai.cn/qj24.mp4";

    private ActivityVrvideoBinding binding;
    private VrVideoView.Options options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_vrvideo);
        options = new VrVideoView.Options();
        options.inputType = VrVideoView.Options.TYPE_STEREO_OVER_UNDER;
        binding.vrVideoView.setEventListener(new VrVideoEventListener());
    }

    public void loadNativeVRVideo(View view) {
        try {
//        binding.vrVideoView.loadVideo(Uri.parse("file:///android_asset/congo.mp4"), options);
            binding.vrVideoView.loadVideoFromAsset("congo.mp4", options);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void loadNetworkVRVideo(View view) {
        try {
            binding.vrVideoView.loadVideo(Uri.parse(videoPath), options);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
