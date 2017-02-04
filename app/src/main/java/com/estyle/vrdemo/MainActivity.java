package com.estyle.vrdemo;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    public void showVRImage(View view) {
        startActivity(new Intent(this, VRPanoramaViewActivity.class));
    }

    public void showVRVideo(View view) {
            startActivity(new Intent(this, VRVideoActivity.class));
    }

}
