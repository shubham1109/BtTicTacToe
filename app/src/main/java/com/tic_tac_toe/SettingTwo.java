package com.tic_tac_toe;

import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tic_tac_toe.view.LoginActivity;
import com.tic_tac_toe.view.againstcomp;

public class SettingTwo extends Fragment {
    TextView tv;


    public SettingTwo()

    {


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.activity_setting_two,container,false);

        tv=(TextView)v.findViewById(R.id.textView4);
        tv.setTypeface(LoginActivity.type);
        return v;
    }
}
