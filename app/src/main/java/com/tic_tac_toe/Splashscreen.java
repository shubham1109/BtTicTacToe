package com.tic_tac_toe;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.tic_tac_toe.view.LoginActivity;

public class Splashscreen extends AppCompatActivity {

    public static int fir = 0;
    int t = 1;
    public static MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mp = MediaPlayer.create(this, R.raw.tune);
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.start();
            }
        });
        mp.start();

        setContentView(R.layout.activity_splashscreen);
        new CountDownTimer(1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                if (t == 1) {
                    startActivity(new Intent(Splashscreen.this, LoginActivity.class));
                    overridePendingTransition(R.anim.pushin,R.anim.pushdown);
                    finish();
                }
            }
        }.start();

    }

    @Override
    public void onBackPressed() {
        t = 0;
        mp.stop();
        super.onBackPressed();
    }
}