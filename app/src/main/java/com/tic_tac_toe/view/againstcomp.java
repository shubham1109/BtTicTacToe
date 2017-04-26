package com.tic_tac_toe.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.support.v7.internal.widget.DialogTitle;
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

public class againstcomp extends Activity {
    ImageView q;
    int x1,x2;
    int g=0;
    int stop=0;
    int compscore=0;
    int yu = 0,yc=1;
    int userchance = 0;
    int user,comp;
    ImageView iv;
    Dialog dialog;
    int[][] arr = new int[3][3];
    int one, two, thr, fou, fiv, six, sev, eig, nin;
    private ImageView one_one;
    private ImageView one_two;
    private ImageView one_three;

    private ImageView two_one;
    private ImageView two_two;
    private ImageView two_three;

    private ImageView three_one;
    private ImageView three_two;
    private ImageView three_three;

    int c=LoginActivity.chance;
    TextView ps;
    TextView cs;
    Typeface type;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = Typeface.createFromAsset(getAssets(), "fonts/Cheveuxdange.ttf");
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_againstcomp);
        one_one = (ImageView) findViewById(R.id.imageView);
        one_two = (ImageView) findViewById(R.id.imageView2);
        one_three = (ImageView) findViewById(R.id.imageView3);
        two_one = (ImageView) findViewById(R.id.imageView4);
        two_two = (ImageView) findViewById(R.id.imageView5);
        two_three = (ImageView) findViewById(R.id.imageView6);
        three_one = (ImageView) findViewById(R.id.imageView7);
        three_two = (ImageView) findViewById(R.id.imageView8);
        three_three = (ImageView) findViewById(R.id.imageView9);
        ps=(TextView)findViewById(R.id.playerscore);
        cs=(TextView)findViewById(R.id.computerscore);
        ps.setTypeface(type);
        cs.setTypeface(type);
        ps.setText(LoginActivity.pl.toUpperCase()+" SCORE =0");
        cs.setText("ANDROID SCORE = "+compscore);


        if (LoginActivity.markp1 == 'o') {
            x1 = R.drawable.tic_tac_toe_o;
            user=1;comp=2;
            x2 = R.drawable.tic_tac_toe_x;

        } else {
            x1 = R.drawable.tic_tac_toe_x;
            user=2;comp=1;
            x2 = R.drawable.tic_tac_toe_o;
        }
        if(LoginActivity.chance==1)
        {
            userfirst();
        }
        else
        {
            computerfirst();
        }



    }
    public void init()
    {
        one_one.setImageResource(R.drawable.tic_tac_toe_blank);
        one_two.setImageResource(R.drawable.tic_tac_toe_blank);
        one_three.setImageResource(R.drawable.tic_tac_toe_blank);
        two_one.setImageResource(R.drawable.tic_tac_toe_blank);
        two_two.setImageResource(R.drawable.tic_tac_toe_blank);
        two_three.setImageResource(R.drawable.tic_tac_toe_blank);
        three_one.setImageResource(R.drawable.tic_tac_toe_blank);
        three_two.setImageResource(R.drawable.tic_tac_toe_blank);
        three_three.setImageResource(R.drawable.tic_tac_toe_blank);
        q = (ImageView) findViewById(R.id.urturn);
        q.setImageResource(R.drawable.turn);
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                arr[i][j] = 0;
            }
        }

        yu = 0;yc=1;
        userchance = 0;
        g=0;
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
    void userfirst()
    {
        init();



    }
    void computerfirst()
    {

        init();
        first_chancec();
    }



    public void tic(View v) {


        if (g == 0) {
            switch (v.getId()) {
                case R.id.imageView:
                    iv = (ImageView) findViewById(R.id.imageView);
                    if (one == 0) {
                        iv.setImageResource(x1);
                        arr[0][0] = user;
                        one = user;
                        if (userchance == 1) {
                            userchance = 0;
                        } else userchance = 1;
                    }


                    break;
                case R.id.imageView2:
                    iv = (ImageView) findViewById(R.id.imageView2);
                    if (two == 0) {
                        iv.setImageResource(x1);
                        arr[0][1] = user;
                        two = user;
                        if (userchance == 1) {
                            userchance = 0;
                        } else userchance = 1;

                    }

                    break;
                case R.id.imageView3:
                    iv = (ImageView) findViewById(R.id.imageView3);
                    if (thr == 0) {
                        iv.setImageResource(x1);
                        thr = user;
                        arr[0][2] = user;
                        if (userchance == 1) {
                            userchance = 0;
                        } else userchance = 1;
                    }
                    break;
                case R.id.imageView4:
                    iv = (ImageView) findViewById(R.id.imageView4);
                    if (fou == 0) {
                        iv.setImageResource(x1);
                        fou = user;
                        arr[1][0] = user;
                        if (userchance == 1) {
                            userchance = 0;
                        } else userchance = 1;
                    }

                    break;
                case R.id.imageView5:
                    iv = (ImageView) findViewById(R.id.imageView5);
                    if (fiv == 0) {
                        iv.setImageResource(x1);
                        arr[1][1] = user;
                        fiv = user;
                        if (userchance == 1) {
                            userchance = 0;
                        } else userchance = 1;
                    }
                    break;
                case R.id.imageView6:
                    iv = (ImageView) findViewById(R.id.imageView6);
                    if (six == 0) {
                        iv.setImageResource(x1);
                        six = user;
                        arr[1][2] = user;
                        if (userchance == 1) {
                            userchance = 0;
                        } else userchance = 1;
                    }
                    break;
                case R.id.imageView7:
                    iv = (ImageView) findViewById(R.id.imageView7);
                    if (sev == 0) {
                        iv.setImageResource(x1);
                        sev = user;
                        arr[2][0] = user;
                        if (userchance == 1) {
                            userchance = 0;
                        } else userchance = 1;
                    }
                    break;

                case R.id.imageView8:
                    iv = (ImageView) findViewById(R.id.imageView8);
                    if (eig == 0) {
                        iv.setImageResource(x1);
                        eig = user;
                        arr[2][1] = user;
                        if (userchance == 1) {
                            userchance = 0;
                        } else userchance = 1;
                    }
                    break;
                case R.id.imageView9:
                    iv = (ImageView) findViewById(R.id.imageView9);
                    if (nin == 0) {
                        iv.setImageResource(x1);
                        nin = user;
                        arr[2][2] = user;
                        if (userchance == 1) {
                            userchance = 0;
                        } else userchance = 1;
                    }
                    break;

            }
            evaluate();
            if (c == 2)
            { computerphele();}
            else {userphele();}


        }

    }

    void evaluate()
    {
            if (hasWon()) {
                LoginActivity.drawSound.start();
                compscore = compscore + 1;
                cs.setText("ANDROID SCORE = " + compscore);


                g = 1;
                yu = -1;
                yc = -1;
                new CountDownTimer(2000, 1000) {


                    @Override
                    public void onTick(long millisUntilFinished) {
                    }

                    @Override
                    public void onFinish() {
                        w();

                    }
                }.start();

            } else if (draw()) {
                LoginActivity.drawSound.start();
                g = 1;
                yu = -1;
                yc = -1;
                d();
            }
        }


    void w()
    {
        if(stop==0) {
            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.activity_background, null);
            ImageView iv = (ImageView) layout.findViewById(R.id.img1);
            TextView tv = (TextView) layout.findViewById(R.id.tv1);
            tv.setTypeface(type);
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            iv.setImageResource(R.drawable.lost);
            builder.setView(layout);
            dialog = builder.create();
            dialog.show();

            tv.setText("" + LoginActivity.pl.toUpperCase() + ", YOU LOST!");
            tv.setTextColor(Color.parseColor("#ffffff"));

            new CountDownTimer(2000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    dialog.cancel();
                    c = 2;
                    computerfirst();

                }
            }.start();
        }
    }
    void d()
    {
        LayoutInflater inflater=getLayoutInflater();
        View layout=inflater.inflate(R.layout.activity_background,null);
        TextView tv=(TextView)layout.findViewById(R.id.tv1);
        tv.setTypeface(type);
        ImageView iv=(ImageView)layout.findViewById(R.id.img1);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        iv.setImageResource(R.drawable.draw);
        tv.setText("TIE GAME!!!");
        builder.setView(layout);
        dialog = builder.create();
        dialog.show();
        new CountDownTimer(2000,1000)
        {
            @Override
            public void onTick(long millisUntilFinished)
            {

            }

            @Override
            public void onFinish()
            {
                dialog.cancel();
                if(c==1)
                {
                    c=2;
                    computerfirst();
                }
                else
                {
                    c=1;
                    userfirst();
                }




            }
        } .start();




    }





    void userphele() {

        if (g == 0&& userchance==1) {
            if (yu == 0) {
                first_chance();
                yu++;
               LoginActivity.sound.start();


                // ta.show();
            } else if (yu == 1) {
                second_chance();
                yu++;
                LoginActivity.sound.start();


                // ta.show();
            } else if (yu == 2) {
                third_chance();
                yu++;
                LoginActivity.sound.start();

                //ta.show();
            } else if (yu == 3) {
                fourth_chance();
                yu++;
                LoginActivity.sound.start();
            }
            evaluate();
            userchance = 0;
        }
    }
    void computerphele() {
        if (g == 0 && userchance==0) {
            if (yc == 1) {
                second_chancec();
                yc++;
                LoginActivity.sound.start();
            } else if (yc == 2) {
                third_chancec();
                yc++;
                LoginActivity.sound.start();
            } else if (yc == 3) {
                fourth_chancec();
                yc++;
                LoginActivity.sound.start();
            } else if(yc==4) {
                fifth_chancec();
                yc++;
                LoginActivity.sound.start();
            }
            userchance = 1;
            evaluate();

        }
    }


    void first_chance() {
        userchance = 0;
        if (fiv == 0) {
            iv = (ImageView) findViewById(R.id.imageView5);
            iv.setImageResource(x2);
            fiv = comp;
            arr[1][1]=comp;
        } else {
            iv = (ImageView) findViewById(R.id.imageView);
            iv.setImageResource(x2);
            one = comp;
            arr[0][0]=comp;
        }
    }

    void second_chance() {


        if (one == user && thr == user) {
            iv = (ImageView) findViewById(R.id.imageView2);
            iv.setImageResource(x2);
            two = comp;
            arr[0][1]=comp;
        } else if (one == user && sev == user) {
            iv = (ImageView) findViewById(R.id.imageView4);
            iv.setImageResource(x2);
            fou = comp;
            arr[1][0]=comp;
        } else if (one == user && nin == user) {
            iv = (ImageView) findViewById(R.id.imageView4);
            iv.setImageResource(x2);
            fou = comp;
            arr[1][0]=comp;
        } else if (thr == user && nin == user) {
            iv = (ImageView) findViewById(R.id.imageView6);
            iv.setImageResource(x2);
            six = comp;
            arr[1][2]=comp;
        } else if (sev == user && thr == user) {
            iv = (ImageView) findViewById(R.id.imageView4);
            iv.setImageResource(x2);
            fou = comp;
            arr[1][0]=comp;
        } else if (one == user && two == user) {
            iv = (ImageView) findViewById(R.id.imageView3);
            iv.setImageResource(x2);
            thr = comp;
            arr[0][2]=comp;
        } else if (one == user && fou == user) {
            iv = (ImageView) findViewById(R.id.imageView7);
            iv.setImageResource(x2);
            sev = comp;
            arr[2][0]=comp;
        } else if (one == user && fiv == user) {
            iv = (ImageView) findViewById(R.id.imageView9);
            iv.setImageResource(x2);
            nin = comp;
            arr[2][2]=comp;
        } else if (two == user && thr == user) {
            iv = (ImageView) findViewById(R.id.imageView);
            iv.setImageResource(x2);
            one = comp;
            arr[0][0]=comp;
        } else if (fiv == user && thr == user) {
            iv = (ImageView) findViewById(R.id.imageView7);
            iv.setImageResource(x2);
            sev = comp;
            arr[2][0]=comp;
        } else if (six == user && thr == user) {
            iv = (ImageView) findViewById(R.id.imageView9);
            iv.setImageResource(x2);
            nin = comp;
            arr[2][2]=comp;
        } else if (sev == user && fou == user) {
            iv = (ImageView) findViewById(R.id.imageView);
            iv.setImageResource(x2);
            one =comp;
            arr[0][0]=comp;
        } else if (sev == user && eig == user) {
            iv = (ImageView) findViewById(R.id.imageView9);
            iv.setImageResource(x2);
            nin = comp;
            arr[2][2]=comp;

        } else if (sev == user && fiv == user) {
            iv = (ImageView) findViewById(R.id.imageView3);
            iv.setImageResource(x2);
            thr = comp;
            arr[0][2]=comp;
        } else if (nin == user && six == user) {
            iv = (ImageView) findViewById(R.id.imageView3);
            iv.setImageResource(x2);
            thr = comp;
            arr[0][2]=comp;
        } else if (nin == user && eig == user) {
            iv = (ImageView) findViewById(R.id.imageView7);
            iv.setImageResource(x2);
            sev =comp;
            arr[2][0]=comp;
        } else if (nin == user && fiv == user) {
            iv = (ImageView) findViewById(R.id.imageView3);
            iv.setImageResource(x2);
            thr = comp;
            arr[0][2]=comp;
        } else if (six == user && sev == user) {
            iv = (ImageView) findViewById(R.id.imageView9);
            iv.setImageResource(x2);
            nin = comp;
            arr[2][2]=comp;
        } else if (six == user && one == user) {
            iv = (ImageView) findViewById(R.id.imageView3);
            iv.setImageResource(x2);
            thr = comp;
            arr[0][2]=comp;
        } else if (two == user && sev == user) {
            iv = (ImageView) findViewById(R.id.imageView);
            iv.setImageResource(x2);
            one =comp;
            arr[0][0]=comp;
        } else if (two == user && nin == user) {
            iv = (ImageView) findViewById(R.id.imageView3);
            iv.setImageResource(x2);
            thr = comp;
            arr[0][2]=comp;
        } else if (thr == user && fou == user) {
            iv = (ImageView) findViewById(R.id.imageView);
            iv.setImageResource(x2);
            one = comp;
            arr[0][0]=comp;
        } else if (thr == user && nin == user) {
            iv = (ImageView) findViewById(R.id.imageView7);
            iv.setImageResource(x2);
            sev = comp;
            arr[2][0]=comp;
        } else if (one == user && eig == user) {
            iv = (ImageView) findViewById(R.id.imageView7);
            iv.setImageResource(x2);
            sev = comp;
            arr[2][0]=comp;
        } else if (thr == user && eig == user) {
            iv = (ImageView) findViewById(R.id.imageView9);
            iv.setImageResource(x2);
            nin = comp;
            arr[2][2]=comp;
        } else if (two == user && fiv == user) {
            iv = (ImageView) findViewById(R.id.imageView8);
            iv.setImageResource(x2);
            eig = comp;
            arr[2][1]=comp;
        } else if (eig == user && fiv == user) {
            iv = (ImageView) findViewById(R.id.imageView2);
            iv.setImageResource(x2);
            two =comp;
            arr[0][1]=comp;
        } else if (sev == user && nin == user) {
            iv = (ImageView) findViewById(R.id.imageView8);
            iv.setImageResource(x2);
            eig = comp;
            arr[2][1]=comp;
        } else if (two == user && eig == user) {
            iv = (ImageView) findViewById(R.id.imageView4);
            iv.setImageResource(x2);
            fou = comp;
            arr[1][0]=comp;
        } else if (fou == user && fiv == user) {
            iv = (ImageView) findViewById(R.id.imageView6);
            iv.setImageResource(x2);
            six =comp;
            arr[1][2]=comp;
        } else if (fou == user && six == user) {
            iv = (ImageView) findViewById(R.id.imageView2);
            iv.setImageResource(x2);
            two = comp;
            arr[0][1]=comp;
        } else if (six == user && fiv == user) {
            iv = (ImageView) findViewById(R.id.imageView4);
            iv.setImageResource(x2);
            fou = comp;
            arr[1][0]=comp;
        } else if (six == user && eig == user) {
            iv = (ImageView) findViewById(R.id.imageView3);
            iv.setImageResource(x2);
            thr = comp;
            arr[0][2]=comp;
        } else if (one == 0) {
            iv = (ImageView) findViewById(R.id.imageView);
            iv.setImageResource(x2);
            one = comp;
            arr[0][0]=comp;
        } else if (thr == 0) {
            iv = (ImageView) findViewById(R.id.imageView3);
            iv.setImageResource(x2);
            thr = comp;
            arr[0][2]=comp;
        } else if (sev == 0) {
            iv = (ImageView) findViewById(R.id.imageView7);
            iv.setImageResource(x2);
            sev = comp;
            arr[2][0]=comp;
        } else if (nin == 0) {
            iv = (ImageView) findViewById(R.id.imageView9);
            iv.setImageResource(x2);
            nin = comp;
            arr[2][2]=comp;
        }


    }

    void third_chance() {


        if (fiv == comp && one == comp && nin == 0) {
            iv = (ImageView) findViewById(R.id.imageView9);
            iv.setImageResource(x2);
            nin = comp;
            arr[2][2]=comp;

        } else if (fiv == comp && two == comp && eig == 0) {
            iv = (ImageView) findViewById(R.id.imageView8);
            iv.setImageResource(x2);
            eig = comp;
            arr[2][1]=comp;

        } else if (fiv == comp && thr == comp && sev == 0) {
            iv = (ImageView) findViewById(R.id.imageView7);
            iv.setImageResource(x2);
            sev = comp;
            arr[2][0]=comp;
        } else if (fiv == comp && fou == comp && six == 0) {
            iv = (ImageView) findViewById(R.id.imageView6);
            iv.setImageResource(x2);
            six =comp;
            arr[1][2]=comp;

        } else if (fiv == comp && six == comp && fou == 0) {
            iv = (ImageView) findViewById(R.id.imageView4);
            iv.setImageResource(x2);
            fou = comp;
            arr[1][0]=comp;

        } else if (fiv == comp && sev == comp && thr == 0) {
            iv = (ImageView) findViewById(R.id.imageView3);
            iv.setImageResource(x2);
            thr = comp;
            arr[0][2]=comp;

        } else if (fiv == comp && eig == comp && two == 0) {
            iv = (ImageView) findViewById(R.id.imageView2);
            iv.setImageResource(x2);
            two = comp;
            arr[0][1]=comp;

        } else if (fiv == comp && nin == comp && one == 0) {
            iv = (ImageView) findViewById(R.id.imageView);
            iv.setImageResource(x2);
            one = comp;
            arr[0][0]=comp;

        } else if (one == comp && two == comp && thr == 0) {
            iv = (ImageView) findViewById(R.id.imageView3);
            iv.setImageResource(x2);
            thr = comp;
            arr[0][2]=comp;

        } else if (one == comp && thr == comp && two == 0) {
            iv = (ImageView) findViewById(R.id.imageView2);
            iv.setImageResource(x2);
            two = comp;
            arr[0][1]=comp;

        } else if (one ==comp && fou ==comp && sev == 0) {
            iv = (ImageView) findViewById(R.id.imageView7);
            iv.setImageResource(x2);
            sev = comp;
            arr[2][0]=comp;

        } else if (one ==comp && sev ==comp && fou == 0) {
            iv = (ImageView) findViewById(R.id.imageView4);
            iv.setImageResource(x2);
            fou = comp;
            arr[1][0]=comp;
        } else if (one ==comp && fiv ==comp && nin == 0) {
            iv = (ImageView) findViewById(R.id.imageView9);
            iv.setImageResource(x2);
            nin = comp;
            arr[2][2]=comp;

        } else if (one ==comp && nin ==comp && fiv == 0) {
            iv = (ImageView) findViewById(R.id.imageView5);
            iv.setImageResource(x2);
            fiv = comp;
            arr[1][1]=comp;
        } else if (one ==user && two == user && thr == 0) {
            iv = (ImageView) findViewById(R.id.imageView3);
            iv.setImageResource(x2);
            thr = comp;
            arr[0][2]=comp;
        } else if (one == user && thr == user && two == 0) {
            iv = (ImageView) findViewById(R.id.imageView2);
            iv.setImageResource(x2);
            two = comp;
            arr[0][1]=comp;
        } else if (two == user && thr == user && one == 0) {
            iv = (ImageView) findViewById(R.id.imageView);
            iv.setImageResource(x2);
            one = comp;
            arr[0][1]=comp;
        } else if (fou == user && fiv == user && six == 0) {
            iv = (ImageView) findViewById(R.id.imageView6);
            iv.setImageResource(x2);
            six = comp;
            arr[1][2]=comp;
        } else if (fou == user && six == user && fiv == 0) {
            iv = (ImageView) findViewById(R.id.imageView5);
            iv.setImageResource(x2);
            fiv = comp;
            arr[1][1]=comp;
        } else if (fiv ==user && six == user&& fou == 0) {
            iv = (ImageView) findViewById(R.id.imageView4);
            iv.setImageResource(x2);
            fou = comp;
            arr[1][0]=comp;
        } else if (sev ==user&& eig == user && nin == 0) {
            iv = (ImageView) findViewById(R.id.imageView9);
            iv.setImageResource(x2);
            nin = comp;
            arr[2][2]=comp;
        } else if (sev == user && nin == user && eig == 0) {
            iv = (ImageView) findViewById(R.id.imageView8);
            iv.setImageResource(x2);
            eig = comp;
            arr[2][1]=comp;
        } else if (eig ==user && nin == user && sev == 0) {
            iv = (ImageView) findViewById(R.id.imageView7);
            iv.setImageResource(x2);
            sev = comp;
            arr[2][0]=comp;
        } else if (one == user && fou == user && sev == 0) {
            iv = (ImageView) findViewById(R.id.imageView7);
            iv.setImageResource(x2);
            sev = comp;
            arr[2][0]=comp;
        } else if (one == user && sev ==user && fou == 0) {
            iv = (ImageView) findViewById(R.id.imageView4);
            iv.setImageResource(x2);
            fou = comp;
            arr[1][0]=comp;
        } else if (fou == user && sev ==user && one == 0) {
            iv = (ImageView) findViewById(R.id.imageView);
            iv.setImageResource(x2);
            one = comp;
            arr[0][0]=comp;
        } else if (two == user && fiv == user && eig == 0) {
            iv = (ImageView) findViewById(R.id.imageView8);
            iv.setImageResource(x2);
            eig = comp;
            arr[2][1]=comp;
        } else if (two == user && eig == user && fiv == 0) {
            iv = (ImageView) findViewById(R.id.imageView5);
            iv.setImageResource(x2);
            fiv = comp;
            arr[1][1]=comp;
        } else if (fiv == user && eig == user && two == 0) {
            iv = (ImageView) findViewById(R.id.imageView2);
            iv.setImageResource(x2);
            two = comp;
            arr[0][1]=comp;
        } else if (thr == user && six == user && nin == 0) {
            iv = (ImageView) findViewById(R.id.imageView9);
            iv.setImageResource(x2);
            nin = comp;
            arr[2][2]=comp;
        } else if (thr == user && nin == user && six == 0) {
            iv = (ImageView) findViewById(R.id.imageView6);
            iv.setImageResource(x2);
            six = comp;
            arr[1][2]=comp;
        } else if (six == user && nin == user && thr == 0) {
            iv = (ImageView) findViewById(R.id.imageView3);
            iv.setImageResource(x2);
            thr = comp;
            arr[0][2]=comp;
        } else if (one == user && fiv == user && nin == 0) {
            iv = (ImageView) findViewById(R.id.imageView9);
            iv.setImageResource(x2);
            nin = comp;
            arr[2][2]=comp;
        } else if (one == user && nin == user && fiv == 0) {
            iv = (ImageView) findViewById(R.id.imageView5);
            iv.setImageResource(x2);
            fiv = comp;
            arr[1][1]=comp;
        } else if (nin == user && fiv == user && one == 0) {
            iv = (ImageView) findViewById(R.id.imageView);
            iv.setImageResource(x2);
            one = comp;
            arr[0][0]=comp;
        } else if (thr == user && fiv == user && sev == 0) {
            iv = (ImageView) findViewById(R.id.imageView7);
            iv.setImageResource(x2);
            sev = comp;
            arr[2][0]=comp;
        } else if (thr == user && sev == user && fiv == 0) {
            iv = (ImageView) findViewById(R.id.imageView5);
            iv.setImageResource(x2);
            fiv = comp;
            arr[1][1]=comp;
        } else if (sev == user && fiv == user && thr == 0) {
            iv = (ImageView) findViewById(R.id.imageView3);
            iv.setImageResource(x2);
            thr = comp;
            arr[0][2]=comp;
        } else if (one == 0) {
            iv = (ImageView) findViewById(R.id.imageView);
            iv.setImageResource(x2);
            one = comp;
            arr[0][0]=comp;
        } else if (thr == 0) {
            iv = (ImageView) findViewById(R.id.imageView3);
            iv.setImageResource(x2);
            thr = comp;
            arr[0][2]=comp;
        } else if (sev == 0) {
            iv = (ImageView) findViewById(R.id.imageView7);
            iv.setImageResource(x2);
            sev = comp;
            arr[2][0]=comp;
        } else if (nin == 0) {
            iv = (ImageView) findViewById(R.id.imageView9);
            iv.setImageResource(x2);
            nin = comp;
            arr[2][2]=comp;
        }


    }


    void fourth_chance() {
        third_chance();

    }

    void first_chancec ()
    {
        yc=1;
        userchance = 1;
        iv = (ImageView) findViewById(R.id.imageView);
        iv.setImageResource(x2);
        one = comp;
        arr[0][0]=comp;
    }

    void second_chancec() {
        if (nin == 0) {
            iv = (ImageView) findViewById(R.id.imageView9);
            iv.setImageResource(x2);
            nin = comp;
            arr[2][2]=comp;

        } else {
            iv = (ImageView) findViewById(R.id.imageView7);
            iv.setImageResource(x2);
            sev = comp;
            arr[2][0]=comp;
        }
    }

    void third_chancec() {
        if (nin==comp&& fiv==0) {
            iv = (ImageView) findViewById(R.id.imageView5);
            iv.setImageResource(x2);
            fiv= comp;
            arr[1][1]=comp;

        }
        else if (sev == comp && fou == 0) {
            iv = (ImageView) findViewById(R.id.imageView4);
            iv.setImageResource(x2);
            fou = comp;
            arr[1][0]=comp;

        } else if (sev == comp && fou == user && thr == 0) {
            iv = (ImageView) findViewById(R.id.imageView3);
            iv.setImageResource(x2);
            thr = comp;
            arr[0][2]=comp;
        }
        else if (nin==comp &&two == user &&eig == 0) {
            iv = (ImageView) findViewById(R.id.imageView8);
            iv.setImageResource(x2);
            eig = comp;
            arr[2][1]=comp;
        } else if (nin == comp && fou == user && six == 0) {
            iv = (ImageView) findViewById(R.id.imageView6);
            iv.setImageResource(x2);
            six = comp;
            arr[1][2]=comp;
        }
        else if (nin == comp && six == user && fou == 0) {
            iv = (ImageView) findViewById(R.id.imageView4);
            iv.setImageResource(x2);
            fou = comp;
            arr[1][0]=comp;
        }
        else if (nin == comp && eig == user && two == 0) {
            iv = (ImageView) findViewById(R.id.imageView2);
            iv.setImageResource(x2);
            two = comp;
            arr[0][1]=comp;
        }
        else if(sev==comp&&thr==comp&&fiv==0)
        {
            iv = (ImageView)findViewById(R.id.imageView5);
            iv.setImageResource(x2);
            fiv=comp;
            arr[1][1]=comp;
        }
        else if (nin ==comp && fiv == 0) {
            iv = (ImageView) findViewById(R.id.imageView5);
            iv.setImageResource(x2);
            fiv = comp;
            arr[1][1]=comp;
        } else if (sev == 0 && nin == comp) {
            iv = (ImageView) findViewById(R.id.imageView7);
            iv.setImageResource(x2);
            sev = comp;
            arr[2][0]=comp;
        } else if (nin == comp && thr == 0) {
            iv = (ImageView) findViewById(R.id.imageView3);
            iv.setImageResource(x2);
            thr = comp;
            arr[0][2]=comp;
        }

    }

    void fourth_chancec() {
if(one==comp&&two==comp&&thr==0)
{
    iv = (ImageView) findViewById(R.id.imageView3);
    iv.setImageResource(x2);
    thr= comp;
    arr[0][2]=comp;

}
       else if(one==comp&&thr==comp&&two==0)
        {
            iv = (ImageView) findViewById(R.id.imageView2);
            iv.setImageResource(x2);
            two= comp;
            arr[0][1]=comp;

        }
       else if(nin==comp&&thr==comp&&six==0)
        {
            iv = (ImageView) findViewById(R.id.imageView6);
            iv.setImageResource(x2);
            six= comp;
            arr[1][2]=comp;

        }
        else if(nin==comp&&six==comp&&thr==0)
        {
            iv = (ImageView) findViewById(R.id.imageView3);
            iv.setImageResource(x2);
            thr= comp;
            arr[0][2]=comp;

        }
        else if(one==comp&&fou==comp&&sev==0)
        {
            iv = (ImageView) findViewById(R.id.imageView7);
            iv.setImageResource(x2);
            sev= comp;
            arr[2][0]=comp;

        }
        else if(one==comp&&sev==comp&&fou==0)
        {
            iv = (ImageView) findViewById(R.id.imageView4);
            iv.setImageResource(x2);
            fou= comp;
            arr[1][0]=comp;

        }
        else if(sev==comp&&nin==comp&&eig==0)
        {
            iv = (ImageView) findViewById(R.id.imageView8);
            iv.setImageResource(x2);
            eig= comp;
            arr[2][1]=comp;

        }
        else if(nin==comp&&eig==comp&&sev==0)
        {
            iv = (ImageView) findViewById(R.id.imageView7);
            iv.setImageResource(x2);
            sev= comp;
            arr[2][0]=comp;

        }
    else if (sev == comp && thr == comp && fou == 0) {
            iv = (ImageView) findViewById(R.id.imageView4);
            iv.setImageResource(x2);
            fou = comp;
            arr[0][1]=comp;

        } else if (sev == comp && thr == comp && two == 0) {
            iv = (ImageView) findViewById(R.id.imageView2);
            iv.setImageResource(x2);
            two = comp;
            arr[0][1]=comp;

        } else if (sev == comp && nin == comp && fou == 0) {
            iv = (ImageView) findViewById(R.id.imageView4);
            iv.setImageResource(x2);
            fou = comp;
            arr[1][0]=comp;

        } else if (sev == comp && nin == comp && eig == 0) {
            iv = (ImageView) findViewById(R.id.imageView8);
            iv.setImageResource(x2);
            eig = comp;
            arr[2][1]=comp;

        }
        else if (sev == comp && fou == 0) {
            iv = (ImageView) findViewById(R.id.imageView4);
            iv.setImageResource(x2);
            fou = comp;
            arr[1][0]=comp;

        }
        else if (thr == comp && nin == comp && two == 0) {
            iv = (ImageView) findViewById(R.id.imageView2);
            iv.setImageResource(x2);
            two =comp;
            arr[0][1]=comp;

        } else if (thr == comp && nin == comp && six == 0) {
            iv = (ImageView) findViewById(R.id.imageView6);
            iv.setImageResource(x2);
            six = comp;
            arr[1][2]=comp;


        }
        else if (sev==user&& fiv==user&&thr==0) {
            iv = (ImageView) findViewById(R.id.imageView3);
            iv.setImageResource(x2);
            thr = comp;
            arr[0][2]=comp;
        }
        else if (eig==user&& fiv==user&&two==0) {
            iv = (ImageView) findViewById(R.id.imageView2);
            iv.setImageResource(x2);
            two = comp;
            arr[0][1]=comp;
        }
        else if (thr==user&& fiv==user&&sev==0) {
            iv = (ImageView) findViewById(R.id.imageView7);
            iv.setImageResource(x2);
            sev = comp;
            arr[2][0]=comp;
        }
        else if (two==user&& fiv==user&&eig==0) {
            iv = (ImageView) findViewById(R.id.imageView8);
            iv.setImageResource(x2);
            eig = comp;
            arr[2][1]=comp;

        }
        else if (fou==user&& fiv==user&&six==0) {
            iv = (ImageView) findViewById(R.id.imageView6);
            iv.setImageResource(x2);
            six = comp;
            arr[1][2]=comp;

        }
        else if (eig==user&& fiv==user&&two==0) {
            iv = (ImageView) findViewById(R.id.imageView2);
            iv.setImageResource(x2);
            two = comp;
            arr[0][1]=comp;

        }
        else if (fiv==user&& six==user&&fou==0) {
            iv = (ImageView) findViewById(R.id.imageView4);
            iv.setImageResource(x2);
            fou = comp;
            arr[1][0]=comp;

        }

        else if (thr==comp&& sev==comp&&fiv==0) {
            iv = (ImageView) findViewById(R.id.imageView5);
            iv.setImageResource(x2);
            fiv= comp;
            arr[1][1]=comp;

        }
        else if (nin==comp&& fiv==0) {
            iv = (ImageView) findViewById(R.id.imageView5);
            iv.setImageResource(x2);
            fiv = comp;
            arr[1][1]=comp;


        }

    }
    void fifth_chancec()
    {
        if(one==0)
        {
            iv = (ImageView) findViewById(R.id.imageView);
            iv.setImageResource(x2);
            one = comp;
            arr[0][0]=comp;
        }
        else if(two==0)
        {
            iv = (ImageView) findViewById(R.id.imageView2);
            iv.setImageResource(x2);
            two = comp;
            arr[0][1]=comp;
        }
        else if(thr==0)
        {
            iv = (ImageView) findViewById(R.id.imageView3);
            iv.setImageResource(x2);
            thr= comp;
            arr[0][2]=comp;
        }
        else if(fou==0)
        {
            iv = (ImageView) findViewById(R.id.imageView4);
            iv.setImageResource(x2);
            fou = comp;
            arr[1][0]=comp;
        }
        else if(fiv==0)
        {
            iv = (ImageView) findViewById(R.id.imageView5);
            iv.setImageResource(x2);
            fiv = comp;
            arr[1][1]=comp;
        }
        else if(six==0)
        {
            iv = (ImageView) findViewById(R.id.imageView6);
            iv.setImageResource(x2);
            six = comp;
            arr[1][2]=comp;
        }
        else if(sev==0)
        {
            iv = (ImageView) findViewById(R.id.imageView7);
            iv.setImageResource(x2);
            sev = comp;
            arr[2][0]=comp;
        }
        else if(eig==0)
        {
            iv = (ImageView) findViewById(R.id.imageView8);
            iv.setImageResource(x2);
            eig = comp;
            arr[2][1]=comp;
        }
        else if(nin==0)
        {
            iv = (ImageView) findViewById(R.id.imageView9);
            iv.setImageResource(x2);
            nin = comp;
            arr[2][2]=comp;
        }
    }
    public Boolean draw()
    {
        boolean isFull = true;
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (arr[i][j] == 0)
                {
                    isFull = false;
                }
            }
        }
        return isFull;
    }

    public boolean hasWon()
    {
        return (checkRows() || checkCols() || checkDiagonals());
    }

    private boolean check(int c1, int c2, int c3)
    {

        return ((c1 != 0) && (c1 == c2) && (c2 == c3));
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
    public void onBackPressed()
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to quit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        stop=1;
                        Intent loginActivity = new Intent(againstcomp.this, LoginActivity.class);
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