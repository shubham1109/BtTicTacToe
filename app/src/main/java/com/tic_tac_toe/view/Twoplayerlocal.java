package com.tic_tac_toe.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.tic_tac_toe.R;

public class Twoplayerlocal extends AppCompatActivity implements View.OnClickListener
{
    ImageView iv;
    TextView ew;
    public static String pl1, pl2;
    public int  g=0;
    int stop=0;

    int pl1score = 0, pl2score = 0;

    char winmarker;
    int flag = 5, temp = 0;
    int one, two, thr, fou, fiv, six, sev, eig, nin;
    char[][] arr;
    AlertDialog dialog;
    Toast ta;
    TextView t1,t2;
    int player1=1,player2=2;
    int chance=player1;

    private ImageButton one_one;
    private ImageButton one_two;
    private ImageButton one_three;

    private ImageButton two_one;
    private ImageButton two_two;
    private ImageButton two_three;

    private ImageButton three_one;
    private ImageButton three_two;
    private ImageButton three_three;

    Typeface type;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_twoplayerlocal);
        Intent i = getIntent();
        t1=(TextView)findViewById(R.id.player_one_score);
        t2=(TextView)findViewById(R.id.player_two_score);
        if (LoginActivity.markpl1 == 'o')
        {
            flag = 0;
        }
        else
        {
            flag = 1;
        }


        setupBoardView();


        type = Typeface.createFromAsset(getAssets(), "fonts/Cheveuxdange.ttf");
        ew.setTypeface(type);
        t1.setTypeface(type);
        t2.setTypeface(type);
    }
    int t = 0;

    public void setupBoardView()
    {
        ew=(TextView)findViewById(R.id.iv10);
        if(chance ==player1)
        {
            ew.setText(LoginActivity.playerOne.toUpperCase()+" YOUR CHANCE");
            if(LoginActivity.markpl1=='o')
            {
                flag=0;
            }
            else flag=1;
        }
        else
        {
            ew.setText(LoginActivity.playerTwo.toUpperCase()+" YOUR CHANCE");
            if(LoginActivity.markpl2=='o')
            {
                flag=0;
            }
            else flag=1;
        }
        arr = new char[3][3];

        init();
        t1.setText(LoginActivity.playerOne.toUpperCase()+" SCORE "+pl1score);
        t2.setText(LoginActivity.playerTwo.toUpperCase()+" SCORE "+pl2score);

        t=0;

        //initialize buttons
        one_one = (ImageButton) findViewById(R.id.one_one);
        one_two = (ImageButton) findViewById(R.id.one_two);
        one_three = (ImageButton) findViewById(R.id.one_three);
        two_one = (ImageButton) findViewById(R.id.two_one);
        two_two = (ImageButton) findViewById(R.id.two_two);
        two_three = (ImageButton) findViewById(R.id.two_three);
        three_one = (ImageButton) findViewById(R.id.three_one);
        three_two = (ImageButton) findViewById(R.id.three_two);
        three_three = (ImageButton) findViewById(R.id.three_three);

        one_one.setBackgroundResource(R.drawable.tic_tac_toe_blank);
        one_two.setBackgroundResource(R.drawable.tic_tac_toe_blank);
        one_three.setBackgroundResource(R.drawable.tic_tac_toe_blank);
        two_one.setBackgroundResource(R.drawable.tic_tac_toe_blank);
        two_two.setBackgroundResource(R.drawable.tic_tac_toe_blank);
        two_three.setBackgroundResource(R.drawable.tic_tac_toe_blank);
        three_one.setBackgroundResource(R.drawable.tic_tac_toe_blank);
        three_two.setBackgroundResource(R.drawable.tic_tac_toe_blank);
        three_three.setBackgroundResource(R.drawable.tic_tac_toe_blank);

        one_one.setOnClickListener(this);
        one_two.setOnClickListener(this);
        one_three.setOnClickListener(this);
        two_one.setOnClickListener(this);
        two_two.setOnClickListener(this);
        two_three.setOnClickListener(this);
        three_one.setOnClickListener(this);
        three_two.setOnClickListener(this);
        three_three.setOnClickListener(this);


    }

    void evaluate()
    {
        if (hasWon())
        {
            new CountDownTimer(2000, 1000)
            {


                @Override
                public void onTick(long millisUntilFinished)
                {
                }

                @Override
                public void onFinish()
                {
                    w();
                }
            } .start();
        }
        else if (draw())
        {

            d();
        }
        else if(g==0)
        {

            if(chance==player1)
            {
                chance=player2;
                ew.setText(LoginActivity.playerTwo.toUpperCase()+" YOUR CHANCE");
            }
            else
            {
                chance=player1;
                ew.setText(LoginActivity.playerOne.toUpperCase()+" YOUR CHANCE");
            }
        }


    }

    void d()
    {
        LayoutInflater inflater=getLayoutInflater();
        View layout=inflater.inflate(R.layout.activity_background,null);
        TextView tv=(TextView)layout.findViewById(R.id.tv1);
        ImageView iv=(ImageView)layout.findViewById(R.id.img1);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        iv.setImageResource(R.drawable.draw);

        tv.setText("TIE GAME!!!");
        dialog = builder.create();
        dialog.setView(layout);
       LoginActivity.drawSound.start();
        dialog.show();
        if(chance==player1)
        {
            chance=player2;
        }
        else
        chance=player1;

        new CountDownTimer(2000,1000)
        {
            @Override
            public void onTick(long millisUntilFinished)
            {

            }

            @Override
            public void onFinish()
            {

                setupBoardView();
                dialog.cancel();

            }
        } .start();




    }

    void w()
    {
        if(stop==0) {
            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.activity_background, null);
            ImageView iv = (ImageView) layout.findViewById(R.id.img1);
            TextView tv = (TextView) layout.findViewById(R.id.tv1);
            tv.setTypeface(type);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            iv.setImageResource(R.drawable.win);
            dialog = builder.create();
            dialog.setView(layout);
            dialog.show();
            LoginActivity.winSound.start();
            t = 1;
            if (winmarker == LoginActivity.markpl1) {

                pl1score++;
                tv.setText("CONGRATULATIONS " + LoginActivity.playerOne.toUpperCase() + ", YOU WON!");
                chance = player1;

            } else if (winmarker == LoginActivity.markpl2) {
                pl2score++;

                tv.setText("CONGRATULATIONS  " + LoginActivity.playerTwo.toUpperCase() + ", YOU WON!");
                chance = player2;
            }

            new CountDownTimer(2000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {

                    setupBoardView();
                    dialog.cancel();

                }
            }.start();
        }
    }





    public Boolean draw()
    {
        boolean isFull = true;
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (arr[i][j] == '*')
                {
                    isFull = false;
                }
            }
        }
        return isFull;
    }

    public void init()
    {
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                arr[i][j] = '*';
            }
        }
        one=0;
        two=0;
        thr=0;
        fou=0;
        fiv=0;
        six=0;
        sev=0;
        eig=0;
        nin=0;
    }

    public boolean hasWon()
    {
        return (checkRows() || checkCols() || checkDiagonals());
    }

    private boolean check(char c1, char c2, char c3)
    {
        if ((c1 != '*') && (c1 == c2) && (c2 == c3))
        {
            winmarker = c1;t=1;
        }
        return ((c1 != '*') && (c1 == c2) && (c2 == c3));
    }

    private boolean checkRows()
    {
        for (int i = 0; i < 3; i++)
        {
            if (check(arr[i][0], arr[i][1], arr[i][2]) == true)
            {
                winblink(i * 10, i * 10 + 1, i * 10 + 2);
                return true;
            }
        }
        return false;
    }

    private boolean checkCols()
    {
        for (int i = 0; i < 3; i++)
        {
            if (check(arr[0][i], arr[1][i], arr[2][i]) == true)
            {
                winblink(i, 10 + i, 20 + i);
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonals()
    {
        if (check(arr[0][0], arr[1][1], arr[2][2]) == true)
        {
            winblink(0, 11, 22);
        }
        else if (check(arr[0][2], arr[1][1], arr[2][0]) == true)
        {
            winblink(2, 11, 20);
        }
        return ((check(arr[0][0], arr[1][1], arr[2][2]) == true) || (check(arr[0][2], arr[1][1], arr[2][0]) == true));
    }

    public void winblink(int a, int b, int c)
    {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.animation);
        anim.setDuration(500);


        if (a == 0 && b == 1 && c == 2)
        {

            one_one.startAnimation(anim);
            one_two.startAnimation(anim);
            one_three.startAnimation(anim);

        }
        else if (a == 10 && b == 11 && c == 12)
        {

            two_one.startAnimation(anim);
            two_two.startAnimation(anim);
            two_three.startAnimation(anim);

        }
        else if (a == 20 && b == 21 && c == 22)
        {

            three_one.startAnimation(anim);
            three_two.startAnimation(anim);
            three_three.startAnimation(anim);

        }
        else if (a == 0 && b == 10 && c == 20)
        {
            one_one.startAnimation(anim);
            two_one.startAnimation(anim);
            three_one.startAnimation(anim);

        }
        else if (a == 1 && b == 11 && c == 21)
        {
            one_two.startAnimation(anim);
            two_two.startAnimation(anim);
            three_two.startAnimation(anim);

        }
        else if (a == 2 && b == 12 && c == 22)
        {

            one_three.startAnimation(anim);
            two_three.startAnimation(anim);
            three_three.startAnimation(anim);
        }
        else if (a == 0 && b == 11 && c == 22)
        {
            one_one.startAnimation(anim);
            two_two.startAnimation(anim);
            three_three.startAnimation(anim);
        }
        else if (a == 2 && b == 11 && c == 20)
        {

            one_three.startAnimation(anim);
            two_two.startAnimation(anim);
            three_one.startAnimation(anim);

        }

    }

    @Override
    public void onClick(View v)
    {
        if (t == 0)
        {

            switch (v.getId())
            {
                case R.id.one_one:
                    iv = (ImageView) findViewById(R.id.one_one);
                    if (flag == 0 && one == 0)
                    {
                       LoginActivity.sound.start();
                        iv.setBackgroundResource(R.drawable.tic_tac_toe_o);
                        flag = 1;
                        g=0;
                        arr[0][0] = 'o';
                        one = 1;
                    }
                    else if (one == 0)
                    {
                        LoginActivity.sound.start();
                        iv.setBackgroundResource(R.drawable.tic_tac_toe_x);
                        flag = 0;
                        arr[0][0] = 'x';
                        one = 1;
                        g=0;
                    }
                    else
                    {
                        g=1;
                    }



                    break;
                case R.id.one_two:
                    iv = (ImageView) findViewById(R.id.one_two);
                    if (flag == 0 && two == 0)
                    {
                        LoginActivity.sound.start();
                        iv.setBackgroundResource(R.drawable.tic_tac_toe_o);
                        flag = 1;
                        g=0;
                        arr[0][1] = 'o';
                        two = 1;
                    }
                    else if (two == 0)
                    {
                        LoginActivity.sound.start();
                        iv.setBackgroundResource(R.drawable.tic_tac_toe_x);
                        g=0;
                        flag = 0;
                        two = 1;
                        arr[0][1] = 'x';
                    }
                    else
                    {
                        g=1;
                    }
                    break;
                case R.id.one_three:
                    iv = (ImageView) findViewById(R.id.one_three);
                    if (flag == 0 && thr == 0)
                    {
                        LoginActivity.sound.start();
                        iv.setBackgroundResource(R.drawable.tic_tac_toe_o);
                        flag = 1;
                        thr = 1;
                        g=0;
                        arr[0][2] = 'o';
                    }
                    else if (thr == 0)
                    {
                        LoginActivity.sound.start();
                        iv.setBackgroundResource(R.drawable.tic_tac_toe_x);
                        flag = 0;
                        thr = 1;
                        g=0;
                        arr[0][2] = 'x';
                    }
                    else
                    {
                        g=1;
                    }

                    break;
                case R.id.two_one:
                    iv = (ImageView) findViewById(R.id.two_one);
                    if (flag == 0 && fou == 0)
                    {
                        LoginActivity.sound.start();
                        iv.setBackgroundResource(R.drawable.tic_tac_toe_o);
                        flag = 1;
                        fou = 1;
                        g=0;
                        arr[1][0] = 'o';
                    }
                    else if (fou == 0)
                    {
                        LoginActivity.sound.start();
                        iv.setBackgroundResource(R.drawable.tic_tac_toe_x);
                        flag = 0;
                        fou = 1;
                        g=0;
                        arr[1][0] = 'x';
                    }
                    else
                    {
                        g=1;
                    }

                    break;
                case R.id.two_two:
                    iv = (ImageView) findViewById(R.id.two_two);
                    if (flag == 0 && fiv == 0)
                    {
                        LoginActivity.sound.start();
                        iv.setBackgroundResource(R.drawable.tic_tac_toe_o);
                        flag = 1;
                        g=0;
                        arr[1][1] = 'o';
                        fiv = 1;

                    }
                    else if (fiv == 0)
                    {
                        LoginActivity.sound.start();
                        iv.setBackgroundResource(R.drawable.tic_tac_toe_x);
                        flag = 0;
                        fiv = 1;
                        g=0;
                        arr[1][1] = 'x';
                    }
                    else
                    {
                        g=1;
                    }
                    break;
                case R.id.two_three:
                    iv = (ImageView) findViewById(R.id.two_three);
                    if (flag == 0 && six == 0)
                    {
                        LoginActivity.sound.start();
                        iv.setBackgroundResource(R.drawable.tic_tac_toe_o);
                        flag = 1;
                        six = 1;
                        g=0;
                        arr[1][2] = 'o';
                    }
                    else if (six == 0)
                    {
                        LoginActivity.sound.start();
                        iv.setBackgroundResource(R.drawable.tic_tac_toe_x);
                        flag = 0;
                        six = 1;
                        g=0;
                        arr[1][2] = 'x';
                    }
                    else
                    {
                        g=1;
                    }

                    break;
                case R.id.three_one:
                    iv = (ImageView) findViewById(R.id.three_one);
                    if (flag == 0 && sev == 0)
                    {
                        LoginActivity.sound.start();
                        iv.setBackgroundResource(R.drawable.tic_tac_toe_o);
                        flag = 1;
                        sev = 1;
                        g=0;
                        arr[2][0] = 'o';
                    }
                    else if (sev == 0)
                    {
                        LoginActivity.sound.start();
                        iv.setBackgroundResource(R.drawable.tic_tac_toe_x);
                        flag = 0;
                        sev = 1;
                        arr[2][0] = 'x';
                        g=0;
                    }
                    else
                    {
                        g=1;
                    }
                    break;
                case R.id.three_two:
                    iv = (ImageView) findViewById(R.id.three_two);
                    if (flag == 0 && eig == 0)
                    {
                        LoginActivity.sound.start();
                        iv.setBackgroundResource(R.drawable.tic_tac_toe_o);
                        flag = 1;
                        eig = 1;
                        g=0;
                        arr[2][1] = 'o';
                    }
                    else if (eig == 0)
                    {
                        LoginActivity.sound.start();
                        iv.setBackgroundResource(R.drawable.tic_tac_toe_x);
                        eig = 1;
                        flag = 0;
                        g=0;
                        arr[2][1] = 'x';
                    }
                    else
                    {
                        g=1;
                    }
                    break;
                case R.id.three_three:
                    iv = (ImageView) findViewById(R.id.three_three);
                    if (flag == 0 && nin == 0)
                    {
                        LoginActivity.sound.start();
                        iv.setBackgroundResource(R.drawable.tic_tac_toe_o);
                        flag = 1;
                        nin = 1;
                        arr[2][2] = 'o';
                        g=0;
                    }
                    else if (nin == 0)
                    {
                        LoginActivity.sound.start();
                        iv.setBackgroundResource(R.drawable.tic_tac_toe_x);
                        flag = 0;
                        nin = 1;
                        arr[2][2] = 'x';
                        g=0;
                    }
                    else
                    {
                        g=1;
                    }
                    break;

            }
            evaluate();


        }
    }

    @Override
    public void onBackPressed()
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to quit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        stop=1;
                        Intent loginActivity = new Intent(Twoplayerlocal.this, LoginActivity.class);
                        startActivity(loginActivity);
                        LoginActivity.winSound.stop();
                        LoginActivity.drawSound.stop();
                        LoginActivity.sound.stop();


                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        builder.create().show();
    }
}
