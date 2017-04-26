package com.tic_tac_toe.view;

import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;

import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.tic_tac_toe.SettingTwo;
import com.tic_tac_toe.Splashscreen;
import com.tic_tac_toe.model.Player;
import com.tic_tac_toe.R;

public class LoginActivity extends AppCompatActivity
{
    int fsetting=0;
    int fhelp=0;
    public static int chance=0;
    private TextView onePlayer;
    private TextView twoPlayer;
    float xOld=0.f,xNew=0.f;
    int f=0;
    int fdialog=0;

    BluetoothAdapter mBA=BluetoothAdapter.getDefaultAdapter();
    public static char markpl1='o',markpl2='x';
    public static String playerOne,playerTwo;
    public static char markp1='o';
    public static String pl;


    public static MediaPlayer sound=new MediaPlayer();
    public static MediaPlayer winSound=new MediaPlayer();
    public static MediaPlayer drawSound=new MediaPlayer();
    ImageView slide;
    int flag=0;
    public static Typeface type;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        type = Typeface.createFromAsset(getAssets(), "fonts/Cheveuxdange.ttf");

        sound = MediaPlayer.create(this, R.raw.pencil);
        winSound=MediaPlayer.create(this,R.raw.woohoo);
        drawSound=MediaPlayer.create(this,R.raw.crowd_boo);


        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/Cheveuxdange.ttf");



    }
    public static Fragment frag= new Setting();
    public static Fragment fragHelp=new SettingTwo();




    @Override
    public boolean onTouchEvent(MotionEvent event) {
        slide=(ImageView)findViewById(R.id.slidebtn);
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                xOld=event.getX();
                break;
            case MotionEvent.ACTION_UP:
                xNew = event.getX();
                float delta = xNew-xOld;
                Button b1=(Button)findViewById(R.id.button2);
                Button b2=(Button)findViewById(R.id.button3);
                Button b3=(Button)findViewById(R.id.button4);
                if(delta>=100)
                {
                    if(fsetting==0&&fhelp==0) {
                        xOld = xNew = 0.f;
                        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right).add(R.id.setting, frag).commit();

                        b1.setClickable(false);
                        b2.setClickable(false);
                        b3.setClickable(false);
                        slide.setVisibility(View.GONE);
                        fdialog=1;
                        fsetting = 1;
                    }

                    break;
                }
                float delta1=xOld-xNew ;
                if(delta1>=100)
                {
                 if(fsetting==1&&fhelp==0) {
                     xOld = xNew = 0.f;
                     getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left).remove(frag).commit();
                     fsetting = 0;
                     fdialog=0;
                     b1.setClickable(true);
                     b2.setClickable(true);
                     b3.setClickable(true);
                     slide.setVisibility(View.VISIBLE);
                 }
                    else if(fsetting==0&&fhelp==0) {
                     xOld = xNew = 0.f;
                     getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left).add(R.id.setting, fragHelp).commit();
                     fdialog=2;
                     b1.setClickable(false);
                     b2.setClickable(false);
                     b3.setClickable(false);
                     slide.setVisibility(View.GONE);
                     fhelp = 1;

                 }
                    break;
                }
                break;




        }

        return super.onTouchEvent(event);
    }

    public void versuscomputer(View v)
    {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();

        final View layout = inflater.inflate(R.layout.one_player_options, null);
        final TextView playerName = (TextView) layout.findViewById(R.id.username);
        final Intent intent=new Intent(LoginActivity.this,againstcomp.class);

        final ImageView iv=(ImageView)layout.findViewById(R.id.mark);
        final TextView selectmark = (TextView)layout.findViewById(R.id.markertype);
        final Button start =(Button)layout.findViewById(R.id.btn_start);
        final Button cancel =(Button)layout.findViewById(R.id.btn_cancel);
        final AlertDialog dialog=builder.create();
        final TextView tvselect=(TextView)layout.findViewById(R.id.tv_select);

        final RadioButton rb1=(RadioButton)layout.findViewById(R.id.rb_1);
        final RadioButton rb2=(RadioButton)layout.findViewById(R.id.rb_2);

        iv.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(flag==0)
                {
                    iv.setImageResource(R.drawable.tic_tac_toe_x);
                    markp1='x';
                    flag++;
                }
                else if(flag==1)
                {
                    iv.setImageResource(R.drawable.tic_tac_toe_o);
                    markp1='o';
                    flag=0;
                }

            }
        });


        dialog.setView(layout);
        rb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true)
                {
                    tvselect.setError(null);
                }
            }
        });
        rb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true)
                {
                    tvselect.setError(null);
                }
            }
        });
        start.setOnClickListener(new View.OnClickListener()
                                 {
                                     @Override
                                     public void onClick(View v)
                                     {


                                         if(playerName.length()>0&&rb1.isChecked())
                                         {

                                             chance=1;
                                             pl=playerName.getText().toString();
                                             startActivity(intent);
                                             dialog.cancel();
                                             finish();


                                         }
                                         else if(playerName.length()>0&&rb2.isChecked())
                                         {

                                             chance=2;
                                             pl=playerName.getText().toString();
                                             startActivity(intent);
                                            dialog.cancel();
                                             finish();
                                         }
                                         else if(playerName.length()==0)
                                         {
                                             playerName.setError("Enter Name");
                                         }
                                         else
                                         {
                                             tvselect.setError("Select Chance");

                                         }

                                     }
                                 }
        );
        cancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialog.cancel();
            }
        });
        dialog.show();

    }

    public void twoplayer(View v)
    {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final AlertDialog dialog=builder.create();
        View layout = inflater.inflate(R.layout.twop, null);
        final TextView tv1=(TextView)findViewById(R.id.gametype);
        final RadioButton localGame = (RadioButton) layout.findViewById(R.id.local_game);
        final RadioButton bluetoothGame = (RadioButton) layout.findViewById(R.id.bluetooth_game);
        final Button start =(Button)layout.findViewById(R.id.btn_start);
        final Button cancel =(Button)layout.findViewById(R.id.btn_cancel);
        dialog.setView(layout);
        dialog.show();


        localGame.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {


                final AlertDialog.Builder builderl = new AlertDialog.Builder(LoginActivity.this);
                LayoutInflater inflaterl = getLayoutInflater();
               final Intent twoPlayerLocal=new Intent(LoginActivity.this,Twoplayerlocal.class);
                final AlertDialog dialogl=builderl.create();
                View layoutl = inflaterl.inflate(R.layout.twoloc, null);
                final TextView tv2=(TextView)layoutl.findViewById(R.id.markhead1);
                final TextView tv3=(TextView)layoutl.findViewById(R.id.markhead2);
                final ImageView iv1=(ImageView)layoutl.findViewById(R.id.markpl1);
                final ImageView iv2=(ImageView)layoutl.findViewById(R.id.markpl2);
                final Button start =(Button)layoutl.findViewById(R.id.btn_start);
                final Button cancel =(Button)layoutl.findViewById(R.id.btn_cancel);
                final TextView tv4=(TextView)layoutl.findViewById(R.id.textView);
                final TextView tv5=(TextView)layoutl.findViewById(R.id.textView2);
                final EditText pl1=(EditText)layoutl.findViewById(R.id.editText);
                final EditText pl2=(EditText)layoutl.findViewById(R.id.editText2);
                dialogl.setView(layoutl);
                iv1.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        if(f==0)
                        {
                            iv1.setImageResource(R.drawable.tic_tac_toe_x);
                            iv2.setImageResource(R.drawable.tic_tac_toe_o);
                            f++;
                            markpl1='x';
                            markpl2='o';
                        }
                        else
                        {
                            iv1.setImageResource(R.drawable.tic_tac_toe_o);
                            iv2.setImageResource(R.drawable.tic_tac_toe_x);
                            f--;
                            markpl1='o';
                            markpl2='x';
                        }
                    }
                });

                iv2.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        if(f==0)
                        {

                            iv1.setImageResource(R.drawable.tic_tac_toe_x);
                            iv2.setImageResource(R.drawable.tic_tac_toe_o);
                            f++;
                            markpl1='x';
                            markpl2='o';
                        }
                        else
                        {
                            iv1.setImageResource(R.drawable.tic_tac_toe_o);
                            iv2.setImageResource(R.drawable.tic_tac_toe_x);
                            f--;
                            markpl1='o';
                            markpl2='x';
                        }
                    }
                });
                start.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {

                        if(pl1.getText().length()==0)
                        {
                            pl1.setError("ENTER NAME");
                        }
                        if(pl2.getText().length()==0)
                        {
                            pl2.setError("ENTER NAME");
                        }
                        if ((pl1.getText().length() > 0) && (pl2.getText().length() > 0))
                        {
                            playerOne=pl1.getText().toString();
                            playerTwo=pl2.getText().toString();

                            startActivity(twoPlayerLocal);
                            dialogl.cancel();
                            finish();

                        }









                    }
                });

                cancel.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        dialogl.cancel();

                    }
                });


                dialogl.show();
                dialog.cancel();

            }
        });

        bluetoothGame.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                final AlertDialog.Builder builderb = new AlertDialog.Builder(LoginActivity.this);
                LayoutInflater inflaterb = getLayoutInflater();

                final AlertDialog dialogb=builder.create();
                View layoutb = inflaterb.inflate(R.layout.twob, null);
                final TextView tv2=(TextView)layoutb.findViewById(R.id.markhead1);
                final EditText pl1=(EditText)layoutb.findViewById(R.id.editText);
                final Button start =(Button)layoutb.findViewById(R.id.btn_start);
                final Button cancel =(Button)layoutb.findViewById(R.id.btn_cancel);
                dialogb.setView(layoutb);
                start.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {


                        Intent bluetoothIntent = new Intent(LoginActivity.this, TwoPlayerActivityBluetooth.class);
                        String playerOne = null;
                        if (pl1.getText().length() > 0)
                        {
                            playerOne = pl1.getText().toString();
                            bluetoothIntent.putExtra("playerOne", playerOne);
                            startActivity(bluetoothIntent);
                            finish();
                            dialogb.cancel();

                        }
                        else
                        {
                            pl1.setError("ENTER NAME");
                        }







                    }
                });

                cancel.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        dialogb.cancel();

                    }
                });


                dialogb.show();
                dialog.cancel();



            }
        });






        cancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialog.cancel();
            }
        });



    }

   @Override
    public void onBackPressed() {
       Button b1 = (Button) findViewById(R.id.button2);
       Button b2 = (Button) findViewById(R.id.button3);
       Button b3 = (Button) findViewById(R.id.button4);
       if (fdialog == 1) {
           getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left).remove(frag).commit();
           b1.setClickable(true);
           b2.setClickable(true);
           b3.setClickable(true);
           fsetting=0;
           fdialog=0;
           slide.setVisibility(View.VISIBLE);
       }
       else if (fdialog == 2) {
           getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right).remove(fragHelp).commit();
           b1.setClickable(true);
           b2.setClickable(true);
           b3.setClickable(true);
           fhelp=0;
           fdialog=0;
           slide.setVisibility(View.VISIBLE);
       } else if(fdialog==0) {


           AlertDialog.Builder builder = new AlertDialog.Builder(this);
           builder.setMessage("Are you sure you want to quit the game?")
                   .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                       public void onClick(DialogInterface dialog, int id) {

                           mBA.disable();
                           // Intent i=new Intent(Intent.ACTION_MAIN);
                           //i.addCategory(Intent.CATEGORY_HOME);
                           //startActivity(i);
                           // Splashscreen.mp.stop();

                           finish();
                           System.exit(0);

                       }
                   })
                   .setNegativeButton("No", new DialogInterface.OnClickListener() {
                       public void onClick(DialogInterface dialog, int id) {
                           // User cancelled the dialog
                       }
                   });
           // Create the AlertDialog object and return it
           builder.create().show();
       }
   }


    public void exit(View v)
    {
        mBA.disable();
       // Intent i=new Intent(Intent.ACTION_MAIN);

        //i.addCategory(Intent.CATEGORY_HOME);
        //startActivity(i);
       //Splashscreen.mp.stop();
         finish();
        System.exit(0);

    }
    public void set(View v)
    {
        Button b1=(Button)findViewById(R.id.button2);
        Button b2=(Button)findViewById(R.id.button3);
        Button b3=(Button)findViewById(R.id.button4);

        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right).remove(fragHelp).commit();
        fhelp= 0;
        fdialog=0;
        b1.setClickable(true);
        b2.setClickable(true);
        b3.setClickable(true);
        slide.setVisibility(View.VISIBLE);

    }


}

