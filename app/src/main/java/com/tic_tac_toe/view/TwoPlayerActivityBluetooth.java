package com.tic_tac_toe.view;


import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.tic_tac_toe.R;
import com.tic_tac_toe.Splashscreen;
import com.tic_tac_toe.control.BluetoothGameService;
import com.tic_tac_toe.control.DeviceListActivity;
import com.tic_tac_toe.model.Player;

public class TwoPlayerActivityBluetooth extends AppCompatActivity implements View.OnClickListener
{
    int tarun=0;
    private char  [][]board;
    private static final String TAG = "TwoPlayerActivityBT";
    private static final boolean D = true;
private  static int flag=0;
    private static char winmarker;
    private static int t=0;
    int stop=0;
    int win=0;

    public static final int MESSAGE_STATE_CHANGE = 1;
    public static final int PLAYER_LOCATION_READ = 2;
    public static final int PLAYER_LOCATION_WRITE = 3;
    public static final int MESSAGE_DEVICE_NAME = 4;
    public static final int MESSAGE_TOAST = 5;
    public static final int MESSAGE_DISCONNECTED = 6;

    public static final String DEVICE_NAME = "device_name";
    public static final String TOAST = "toast";
    public static final String MARKER = "marker";
    public  int temp=0;
    private static final int REQUEST_CONNECT_DEVICE = 1;
    private static final int REQUEST_ENABLE_BT = 2;

    private String mConnectedDeviceName = null;
    private String mMarker = null;
    private StringBuffer mOutStringBuffer;
    private String oMarker = null;

    private BluetoothAdapter mBluetoothAdapter = null;
    private BluetoothGameService gameService = null;

    private EditText hostNameLabel;
    private EditText remoteNameLabel;
    private ImageView myMarker;
    private ImageView opponentMarker;
    private TextView playerOneName;
    private TextView playerTwoName;

    private String hostName;
    private String remoteName;

    private Player hostPlayer;
    private Player remotePlayer;
    private ImageView ivbox;
    private ImageView ivbox2;


    private Toolbar toolbar;

    private ImageButton one_one;
    private ImageButton one_two;
    private ImageButton one_three;
    //Second row buttons
    private ImageButton two_one;
    private ImageButton two_two;
    private ImageButton two_three;
    //Third row buttons
    private ImageButton three_one;
    private ImageButton three_two;
    private ImageButton three_three;
    ImageView ew;
    private boolean isTurn = false;
    private BluetoothDevice device;

    private AlertDialog dialog;

    Typeface type;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        t=0;
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_two_player_bluetooth);


        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        hostNameLabel = (EditText) findViewById(R.id.player_name);
        remoteNameLabel = (EditText) findViewById(R.id.remote_name);

        myMarker = (ImageView) findViewById(R.id.m_marker);
        opponentMarker = (ImageView) findViewById(R.id.marko);
        myMarker.setVisibility(View.GONE);
        opponentMarker.setVisibility(View.GONE);
        hostNameLabel.setVisibility(View.GONE);
        remoteNameLabel.setVisibility(View.GONE);


        hostNameLabel.setEnabled(false);
        remoteNameLabel.setEnabled(false);

        toolbar = (Toolbar) findViewById(R.id.options_bar);
        setTitle("Bluetooth Lobby");
        toolbar.setTitleTextColor(0xFFFFFFFF);

        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();

        if (extras != null)
        {
            hostName= extras.getString("playerOne").toUpperCase();
        }

        hostNameLabel.setText(hostName);

        if (mBluetoothAdapter == null)
        {
            Toast.makeText(this, "Bluetooth is not available", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        final ImageView ivScan = (ImageView)findViewById(R.id.imageView13);
        final ImageView ivDiscover = (ImageView)findViewById(R.id.imageView10);
        final ImageView blinkScan = (ImageView)findViewById(R.id.imageView11);
        final ImageView blinkDiscover = (ImageView)findViewById(R.id.imageView12);
        final Animation blink = AnimationUtils.loadAnimation(this,R.anim.animation);
        blink.setDuration(750);
        blinkScan.setVisibility(View.GONE);
        blinkDiscover.setVisibility(View.GONE);

        ivScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    blinkDiscover.setImageResource(R.drawable.tic_tac_toe_blank);
                blinkScan.setImageResource(R.drawable.pointer);
                    blinkScan.startAnimation(blink);
                    blinkScan.setVisibility(View.VISIBLE);

            }
        });
        ivDiscover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blinkScan.setImageResource(R.drawable.tic_tac_toe_blank);
                blinkDiscover.setImageResource(R.drawable.pointer);

                    blinkDiscover.startAnimation(blink);
                    blinkDiscover.setVisibility(View.VISIBLE);


            }
        });
        blink.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                blinkScan.setVisibility(View.GONE);
                blinkDiscover.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        type = Typeface.createFromAsset(getAssets(), "fonts/Cheveuxdange.ttf");



    }

    @Override
    public void onStart()
    {
        super.onStart();
        if (!mBluetoothAdapter.isEnabled())
        {
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
        }
        else
        {
            if (gameService == null) setupGame();
        }

    }

    @Override
    public synchronized void onResume()
    {
        super.onResume();

        if (gameService != null)
        {
            if (gameService.getState() == BluetoothGameService.STATE_NONE)
            {
                gameService.start();
            }
        }
    }

    private void setupGame()
    {

        gameService = new BluetoothGameService(this, mHandler);
        mOutStringBuffer = new StringBuffer("");
    }


    @Override
    public synchronized void onPause()
    {
        super.onPause();
    }

    @Override
    public void onStop()
    {
        super.onStop();
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        // Stop the Bluetooth chat services
        if (gameService != null) gameService.stop();
    }

    private void ensureDiscoverable()
    {
        if (mBluetoothAdapter.getScanMode() != BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE)
        {
            Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
            startActivity(discoverableIntent);
        }
    }

    public void setupBoardView()
    {
        tarun=1;
        setContentView(R.layout.activity_two_player);
        board =new char[3][3];
        init();
        win=0;


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
        ew=(ImageView)findViewById(R.id.iv10);

        playerOneName = (TextView) findViewById(R.id.player_one_score);
        playerTwoName = (TextView) findViewById(R.id.player_two_score);

        playerOneName.setText(hostPlayer.getName().toUpperCase() + ": " + hostPlayer.getScore());
        playerTwoName.setText(remotePlayer.getName().toUpperCase() + ": " + remotePlayer.getScore());

        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/Cheveuxdange.ttf");

        playerOneName.setTypeface(type);
        playerTwoName.setTypeface(type);

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

        if(isTurn)
        {

            ew.setImageResource(R.drawable.turn);
            new CountDownTimer(3000,1000)
            {

                @Override
                public void onTick(long millisUntilFinished)
                {



                }

                @Override
                public void onFinish()
                {
                    ew.setImageResource(R.drawable.tic_tac_toe_blank);

                }
            } .start();

        }

    }

    private void sendLocation(String location)
    {

        if (gameService.getState() != BluetoothGameService.STATE_CONNECTED)
        {
            Toast.makeText(this, R.string.none_found, Toast.LENGTH_SHORT).show();
            return;
        }

        byte[] sendLocation = location.getBytes();

        gameService.writeLocation(sendLocation);
        mOutStringBuffer.setLength(0);
    }

    public void updateUI(char marker, int row, int col)
    {
        if(t==0)
        {
            if (row == 0 && col == 0)
            {
                if (marker == 'x')
                {
                    one_one.setBackgroundResource(R.drawable.tic_tac_toe_x);
                }
                else
                {
                    one_one.setBackgroundResource(R.drawable.tic_tac_toe_o);
                }
                LoginActivity.sound.start();
                one_one.setEnabled(false);
            }
            else if (row == 0 && col == 1)
            {
                if (marker == 'x')
                {
                    one_two.setBackgroundResource(R.drawable.tic_tac_toe_x);
                }
                else
                {
                    one_two.setBackgroundResource(R.drawable.tic_tac_toe_o);
                }
                LoginActivity.sound.start();
                one_two.setEnabled(false);
            }
            else if (row == 0 && col == 2)
            {
                if (marker == 'x')
                {
                    one_three.setBackgroundResource(R.drawable.tic_tac_toe_x);
                }
                else
                {
                    one_three.setBackgroundResource(R.drawable.tic_tac_toe_o);
                }
                LoginActivity.sound.start();
                one_three.setEnabled(false);
            }
            else if (row == 1 && col == 0)
            {
                if (marker == 'x')
                {
                    two_one.setBackgroundResource(R.drawable.tic_tac_toe_x);
                }
                else
                {
                    two_one.setBackgroundResource(R.drawable.tic_tac_toe_o);
                }
                LoginActivity.sound.start();
                two_one.setEnabled(false);
            }
            else if (row == 1 && col == 1)
            {
                if (marker == 'x')
                {
                    two_two.setBackgroundResource(R.drawable.tic_tac_toe_x);
                }
                else
                {
                    two_two.setBackgroundResource(R.drawable.tic_tac_toe_o);
                }
                LoginActivity.sound.start();
                two_two.setEnabled(false);
            }
            else if (row == 1 && col == 2)
            {
                if (marker == 'x')
                {
                    two_three.setBackgroundResource(R.drawable.tic_tac_toe_x);
                }
                else
                {
                    two_three.setBackgroundResource(R.drawable.tic_tac_toe_o);
                }
                LoginActivity.sound.start();
                two_three.setEnabled(false);
            }
            else if (row == 2 && col == 0)
            {
                if (marker == 'x')
                {
                    three_one.setBackgroundResource(R.drawable.tic_tac_toe_x);
                }
                else
                {
                    three_one.setBackgroundResource(R.drawable.tic_tac_toe_o);
                }
                LoginActivity.sound.start();
                three_one.setEnabled(false);
            }
            else if (row == 2 && col == 1)
            {
                if (marker == 'x')
                {
                    three_two.setBackgroundResource(R.drawable.tic_tac_toe_x);
                }
                else
                {
                    three_two.setBackgroundResource(R.drawable.tic_tac_toe_o);
                }
                LoginActivity.sound.start();
                three_two.setEnabled(false);
            }
            else if (row == 2 && col == 2)
            {
                if (marker == 'x')
                {
                    three_three.setBackgroundResource(R.drawable.tic_tac_toe_x);
                }
                else
                {
                    three_three.setBackgroundResource(R.drawable.tic_tac_toe_o);
                }
                LoginActivity.sound.start();
                three_three.setEnabled(false);
            }


            checkGameStatus();
        }

    }

    private final Handler mHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            switch (msg.what)
            {
                case MESSAGE_STATE_CHANGE:
                    switch (msg.arg1)
                    {
                        case BluetoothGameService.STATE_CONNECTED:
                            break;
                        case BluetoothGameService.STATE_CONNECTING:
                            break;
                        case BluetoothGameService.STATE_LISTEN:

                        case BluetoothGameService.STATE_NONE:
                            break;
                        case BluetoothGameService.STATE_DISCONNECTED:
                            Intent bluetoothIntent = new Intent(TwoPlayerActivityBluetooth.this, TwoPlayerActivityBluetooth.class);
                            bluetoothIntent.putExtra("playerOne", hostName);
                            startActivity(bluetoothIntent);
                            finish();
                            break;
                        case BluetoothGameService.STATE_NEW_GAME:
                            if(dialog != null && dialog.isShowing())
                            {
                                dialog.dismiss();
                            }
                            setupBoardView();
                            break;
                    }
                    break;
                case PLAYER_LOCATION_WRITE:
                    byte[] writeBuf = (byte[]) msg.obj;

                    String writeMessage = new String(writeBuf);

                    break;
                case PLAYER_LOCATION_READ:
                    byte[] readBuf = (byte[]) msg.obj;
                    String readMessage = new String(readBuf, 0, msg.arg1);

                    int row = Character.getNumericValue(readMessage.charAt(0));
                    int col = Character.getNumericValue(readMessage.charAt(2));

                    isTurn = !isTurn;


                    if (board[row][col] == '*')
                    {
                        board[row][col] = oMarker.charAt(0);
                    }

                    updateUI(oMarker.charAt(0), row, col);
                    break;
                case MESSAGE_DEVICE_NAME:
                    mConnectedDeviceName = msg.getData().getString(DEVICE_NAME);
                    mMarker = msg.getData().getString(MARKER);

                    if (mMarker.equals(""))
                    {
                        mMarker = "o";
                        oMarker = "x";
                        myMarker.setBackgroundResource(R.drawable.tic_tac_toe_o);
                        opponentMarker.setBackgroundResource(R.drawable.tic_tac_toe_x);
                    }
                    else if (mMarker.equals("x"))
                    {
                        oMarker = "o";
                        isTurn = true;
                        myMarker.setBackgroundResource(R.drawable.tic_tac_toe_x);
                        opponentMarker.setBackgroundResource(R.drawable.tic_tac_toe_o);
                    }
                    Toast.makeText(TwoPlayerActivityBluetooth.this, "Connected to " + mConnectedDeviceName, Toast.LENGTH_SHORT).show();
                    remoteNameLabel.setText(mConnectedDeviceName);
                    hostPlayer = new Player(hostName, mMarker.charAt(0), 0);
                    remotePlayer = new Player(mConnectedDeviceName, oMarker.charAt(0), 0);

                    setupBoardView();


                    break;
                case MESSAGE_TOAST:
                    Toast.makeText(TwoPlayerActivityBluetooth.this, msg.getData().getString(TOAST), Toast.LENGTH_SHORT).show();
                    break;

            }
        }
    };

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        switch (requestCode)
        {
            case REQUEST_CONNECT_DEVICE:
                if (resultCode == Activity.RESULT_OK)
                {
                    String address = data.getExtras()
                            .getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS);

                    device = mBluetoothAdapter.getRemoteDevice(address);
                    gameService.connect(device, "x");
                }
                break;
            case REQUEST_ENABLE_BT:
                if (resultCode == Activity.RESULT_OK)
                {
                    setupGame();
                }
                else
                {
                    Toast.makeText(this, R.string.bt_not_enabled_leaving, Toast.LENGTH_SHORT).show();
                    Intent a=new Intent(this,LoginActivity.class);
                    startActivity(a);
                    finish();
                }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.scan:
                // Launch the DeviceListActivity to see devices and do scan
                Intent serverIntent = new Intent(this, DeviceListActivity.class);
                startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE);
                return true;
            case R.id.discoverable:
                // Ensure this device is discoverable by others
                ensureDiscoverable();
                return true;
        }
        return false;
    }

    public void checkGameStatus()
    {


        if (won())
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
        else if(draw())
        {

            d();
        }
    }

    void w() {
        if (stop == 0) {
            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.activity_background, null);
            ImageView iv = (ImageView) layout.findViewById(R.id.img1);
            TextView tv = (TextView) layout.findViewById(R.id.tv1);
            tv.setTypeface(type);
            char playerMarker = hostPlayer.getPlayerMarker();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            if (winmarker == playerMarker) {
                iv.setImageResource(R.drawable.win);
                tv.setText("CONGRATULATIONS, YOU WON!");
                LoginActivity.winSound = MediaPlayer.create(this, R.raw.woohoo);
                // builder.setMessage("Congratulations, You Won!");
                hostPlayer.setScore(hostPlayer.getScore() + 1);
                dialog = builder.create();
                LoginActivity.winSound.start();
                dialog.setView(layout);
                dialog.show();
            } else {
                iv.setImageResource(R.drawable.lost);
                tv.setText("YOU LOST!!!");
                tv.setTextColor(Color.parseColor("#ffffff"));

                LoginActivity.winSound = MediaPlayer.create(this, R.raw.crowd_boo);
                remotePlayer.setScore(remotePlayer.getScore() + 1);
                dialog = builder.create();
                LoginActivity.winSound.start();
                dialog.setView(layout);
                dialog.show();

            }


            new CountDownTimer(2000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {

                    gameService.setNewGame();
                    init();
                    t = 0;

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
        dialog = builder.create();
       LoginActivity.winSound = MediaPlayer.create(this, R.raw.crowd_boo);
       LoginActivity.winSound.start();
        dialog.setView(layout);
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

                gameService.setNewGame();
                init();
                t=0;

            }
        } .start();

    }

    @Override
    public void onClick(View v) {
        if (win == 0) {
            char playerMarker = mMarker.charAt(0);
            if (isTurn) {

                switch (v.getId()) {

                    case R.id.one_one:

                        if (board[0][0] == '*') {
                            board[0][0] = playerMarker;
                        }
                        updateUI(playerMarker, 0, 0);
                        sendLocation("0,0");

                        break;
                    case R.id.one_two:

                        if (board[0][1] == '*') {
                            board[0][1] = playerMarker;
                        }
                        updateUI(playerMarker, 0, 1);
                        sendLocation("0,1");

                        break;
                    case R.id.one_three:

                        if (board[0][2] == '*') {
                            board[0][2] = playerMarker;
                        }
                        updateUI(playerMarker, 0, 2);
                        sendLocation("0,2");

                        break;
                    case R.id.two_one:

                        if (board[1][0] == '*') {
                            board[1][0] = playerMarker;
                        }
                        updateUI(playerMarker, 1, 0);
                        sendLocation("1,0");

                        break;
                    case R.id.two_two:
                        if (board[1][1] == '*') {
                            board[1][1] = playerMarker;
                        }
                        updateUI(playerMarker, 1, 1);
                        sendLocation("1,1");

                        break;
                    case R.id.two_three:

                        if (board[1][2] == '*') {
                            board[1][2] = playerMarker;
                        }
                        updateUI(playerMarker, 1, 2);
                        sendLocation("1,2");

                        break;
                    case R.id.three_one:

                        if (board[2][0] == '*') {
                            board[2][0] = playerMarker;
                        }
                        updateUI(playerMarker, 2, 0);
                        sendLocation("2,0");
                        break;
                    case R.id.three_two:

                        if (board[2][1] == '*') {
                            board[2][1] = playerMarker;
                        }
                        updateUI(playerMarker, 2, 1);
                        sendLocation("2,1");

                        break;
                    case R.id.three_three:

                        if (board[2][2] == '*') {
                            board[2][2] = playerMarker;
                        }
                        updateUI(playerMarker, 2, 2);
                        sendLocation("2,2");

                        break;

                }

                isTurn = !isTurn;
            } else {

                new CountDownTimer(1000, 1000) {

                    @Override
                    public void onTick(long millisUntilFinished) {

                        ew.setImageResource(R.drawable.wait);

                    }

                    @Override
                    public void onFinish() {
                        ew.setImageResource(R.drawable.tic_tac_toe_blank);

                    }
                }.start();
            }
        }
    }




    public Boolean draw()
    {
        boolean isFull = true;
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (board[i][j] == '*')
                {
                    isFull = false;
                }
            }
        }
        return isFull;
    }
    private boolean check(char c1, char c2, char c3)
    {
        if((c1 != '*') && (c1 == c2) && (c2 == c3))
        {
            win=1;
            winmarker = c1;
        }
        return ((c1 != '*') && (c1 == c2) && (c2 == c3));
    }

    private boolean checkRows()
    {
        for (int i = 0; i < 3; i++)
        {
            if (check(board[i][0], board[i][1], board[i][2]) == true)
            {
                winblink(i*10,i*10+1,i*10+2);
                return true;
            }
        }
        return false;
    }

    private boolean checkCols()
    {
        for (int i = 0; i < 3; i++)
        {
            if (check(board[0][i], board[1][i], board[2][i]) == true)
            {
                winblink(i,10+i,20+i);
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonals()
    {
        if(check(board[0][0], board[1][1], board[2][2]) == true)
        {
            winblink(0,11,22);
        }
        else if(check(board[0][2], board[1][1], board[2][0]) == true)
        {
            winblink(2,11,20);
        }
        return ((check(board[0][0], board[1][1], board[2][2]) == true) || (check(board[0][2], board[1][1], board[2][0]) == true));
    }

    public Boolean won()
    {

        return (checkRows() || checkCols() || checkDiagonals());
    }

    public void init()
    {
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                board[i][j] = '*';
            }
        }
    }

    public void winblink (int a,int b,int c)
    {
        Animation anim= AnimationUtils.loadAnimation(this,R.anim.animation);
        anim.setDuration(500);




        if(a==0&&b==1&&c==2)
        {

            one_one.startAnimation(anim);
            one_two.startAnimation(anim);
            one_three.startAnimation(anim);

        }
        else if(a==10&&b==11&&c==12)
        {

            two_one.startAnimation(anim);
            two_two.startAnimation(anim);
            two_three.startAnimation(anim);

        }


        else if(a==20&&b==21&&c==22)
        {

            three_one.startAnimation(anim);
            three_two.startAnimation(anim);
            three_three.startAnimation(anim);

        }

        else if(a==0&&b==10&&c==20)
        {
            one_one.startAnimation(anim);
            two_one.startAnimation(anim);
            three_one.startAnimation(anim);

        }

        else if(a==1&&b==11&&c==21)
        {
            one_two.startAnimation(anim);
            two_two.startAnimation(anim);
            three_two.startAnimation(anim);

        }

        else if(a==2&&b==12&&c==22)
        {

            one_three.startAnimation(anim);
            two_three.startAnimation(anim);
            three_three.startAnimation(anim);
        }
        else if(a==0&&b==11&&c==22)
        {
            one_one.startAnimation(anim);
            two_two.startAnimation(anim);
            three_three.startAnimation(anim);
        }
        else if(a==2&&b==11&&c==20)
        {

            one_three.startAnimation(anim);
            two_two.startAnimation(anim);
            three_one.startAnimation(anim);

        }

    }

    @Override
    public void onBackPressed()
    {
        if(tarun==0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure you want to quit?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            stop = 1;
                            Intent loginActivity = new Intent(TwoPlayerActivityBluetooth.this, LoginActivity.class);
                            gameService.stop();
                            startActivity(loginActivity);
                            LoginActivity.winSound.stop();
                            LoginActivity.drawSound.stop();
                            LoginActivity.sound.stop();
                            finish();

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
        else if(tarun==1)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure you want to quit?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            stop = 1;
                            gameService.stop();
                            LoginActivity.winSound.stop();
                            LoginActivity.drawSound.stop();
                            LoginActivity.sound.stop();
                            tarun=0;
                            finish();

                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });
            // Create the AlertDialog object and return it
            builder.create().show();



        }


    }
}
