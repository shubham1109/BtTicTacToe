package com.tic_tac_toe.view;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.tic_tac_toe.R;
import com.tic_tac_toe.Splashscreen;

public class Setting extends Fragment implements View.OnClickListener{
    ImageView iv1;
    ImageView iv2;
    ImageView iv3;
    float vol=1.0f;


    public float xOld=0.f,xNew=0.f;
public static int flag=0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.activity_setting,container,false);
        iv1=(ImageView)myView.findViewById(R.id.volup);
        iv2=(ImageView)myView.findViewById(R.id.voldown);
        iv3=(ImageView)myView.findViewById(R.id.mute);
        iv1.setOnClickListener(this);
        iv2.setOnClickListener(this);
        iv3.setOnClickListener(this);
        if(flag==0)
        {
            iv3.setImageResource(R.drawable.unmute);

        }
        else
        {
            iv3.setImageResource(R.drawable.mute);
        }

        return myView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.volup:
                if(vol<1.0f)
                {
                    vol+=0.2f;
                }
                Splashscreen.mp.setVolume(vol,vol);
                LoginActivity.sound.setVolume(vol,vol);
                LoginActivity.drawSound.setVolume(vol,vol);
                LoginActivity.winSound.setVolume(vol,vol);
                iv3.setImageResource(R.drawable.unmute);
                flag=0;
                break;
            case R.id.voldown:
                if(vol>=0.2f)
                {
                    vol-=0.2f;
                }
                Splashscreen.mp.setVolume(vol,vol);
                LoginActivity.sound.setVolume(vol,vol);
                LoginActivity.drawSound.setVolume(vol,vol);
                LoginActivity.winSound.setVolume(vol,vol);
                iv3.setImageResource(R.drawable.unmute);
                flag=0;
                break;
            case R.id.mute:
                if(flag==0)
                {
                    vol=0.0f;
                    Splashscreen.mp.setVolume(vol,vol);
                    LoginActivity.sound.setVolume(vol,vol);
                    LoginActivity.drawSound.setVolume(vol,vol);
                    LoginActivity.winSound.setVolume(vol,vol);
                    iv3.setImageResource(R.drawable.mute);
                    flag++;
                }
                else
                {
                    vol=1.0f;
                    Splashscreen.mp.setVolume(vol,vol);
                    LoginActivity.sound.setVolume(vol,vol);
                    LoginActivity.drawSound.setVolume(vol,vol);
                    LoginActivity.winSound.setVolume(vol,vol);
                    iv3.setImageResource(R.drawable.unmute);
                    flag = 0;
                }


                break;  

        }

    }
}
