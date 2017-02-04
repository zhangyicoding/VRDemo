package com.estyle.vrdemo;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.estyle.vrdemo.databinding.ActivityVrpanoramaViewBinding;
import com.google.vr.sdk.widgets.pano.VrPanoramaEventListener;
import com.google.vr.sdk.widgets.pano.VrPanoramaView;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;

public class VRPanoramaViewActivity extends AppCompatActivity {

    private String imgPath = "http://image.quhuwai.cn/16103111343978.jpg";

    private ActivityVrpanoramaViewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_vrpanorama_view);

        binding.vrPanoramaView.setEventListener(new VrPanoramaEventListener(){});
    }

    public void loadNativeVRImage(View view) {
        VrPanoramaView.Options options = new VrPanoramaView.Options();
        options.inputType = VrPanoramaView.Options.TYPE_STEREO_OVER_UNDER;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.raw.andes);
        binding.vrPanoramaView.loadImageFromBitmap(bitmap, options);

    }

    public void loadNetworkVRImage(View view) {
        OkHttpUtils.get()
                .url(imgPath)
                .build()
                .execute(new BitmapCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }

                    @Override
                    public void onResponse(Bitmap response) {
                        Toast.makeText(VRPanoramaViewActivity.this, "OK", Toast.LENGTH_SHORT).show();
                        VrPanoramaView.Options options = new VrPanoramaView.Options();
                        options.inputType = VrPanoramaView.Options.TYPE_STEREO_OVER_UNDER;
                        binding.vrPanoramaView.loadImageFromBitmap(response, options);
                    }
                });

    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.vrPanoramaView.resumeRendering();
    }

    @Override
    protected void onPause() {
        super.onPause();
        binding.vrPanoramaView.pauseRendering();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding.vrPanoramaView.destroyDrawingCache();
    }
}
