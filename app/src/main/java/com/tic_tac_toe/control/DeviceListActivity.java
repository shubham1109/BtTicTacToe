package com.tic_tac_toe.control;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.BluetoothLeScanner;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.tic_tac_toe.R;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Set;

public class DeviceListActivity extends AppCompatActivity
{
    private static final String TAG = "DeviceListActivity";
    private static final boolean D = true;

    public static String EXTRA_DEVICE_ADDRESS = "device_address";

    private BluetoothAdapter mBluetoothAdapter;
    private ArrayAdapter<String> mPairedDevicesAdapter;
    private ArrayAdapter<String> mNewDevicesAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

            requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);

            super.onCreate(savedInstanceState);


            setContentView(R.layout.activity_device_list);

            setResult(Activity.RESULT_CANCELED);

            final Button scanButton = (Button) findViewById(R.id.button);
            final ProgressBar progressBar=(ProgressBar)findViewById(R.id.progress);
            progressBar.setVisibility(View.GONE);
            scanButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ProgressBar progressBar=(ProgressBar)findViewById(R.id.progress);
                    progressBar.setVisibility(View.VISIBLE);
                    doDiscovery();

                    v.setVisibility(View.GONE);

                }
            });

            mPairedDevicesAdapter = new ArrayAdapter<String>(this, R.layout.device_name);
            mNewDevicesAdapter = new ArrayAdapter<String>(this, R.layout.device_name);

            ListView pairedListView = (ListView) findViewById(R.id.paired_devices);
            pairedListView.setAdapter(mPairedDevicesAdapter);
            pairedListView.setOnItemClickListener(mDeviceClickListener);

            ListView newDeviceListView = (ListView) findViewById(R.id.new_devices);
            newDeviceListView.setAdapter(mNewDevicesAdapter);
            newDeviceListView.setOnItemClickListener(mDeviceClickListener);

            IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
            this.registerReceiver(mReceiver, filter);

            filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
            this.registerReceiver(mReceiver, filter);

            mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

            Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();

            if (pairedDevices.size() > 0) {
                findViewById(R.id.title_paired_devices).setVisibility(View.VISIBLE);
                for (BluetoothDevice device : pairedDevices) {
                    mPairedDevicesAdapter.add(device.getName() + "\n" + device.getAddress());
                }
            } else {
                String noDevices = getResources().getText(R.string.none_paired).toString();
                mPairedDevicesAdapter.add(noDevices);
            }

        final String[] permissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION};
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("To scan new  devices");
                builder.setTitle("Location Services");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(DeviceListActivity.this, permissions, 0);
                    }
                });

                builder.show();
            } else {
                ActivityCompat.requestPermissions(this, permissions, 0);
            }
        }



    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        if(mBluetoothAdapter != null)
        {
            mBluetoothAdapter.cancelDiscovery();
        }
        this.unregisterReceiver(mReceiver);
    }

    private void doDiscovery()
    {


        setProgressBarIndeterminateVisibility(true);
        setTitle(R.string.scanning);
        findViewById(R.id.title_new_devices).setVisibility(View.VISIBLE);

        if(mBluetoothAdapter.isDiscovering())
        {
            mBluetoothAdapter.cancelDiscovery();
        }

        mBluetoothAdapter.startDiscovery();

    }

    private AdapterView.OnItemClickListener mDeviceClickListener = new AdapterView.OnItemClickListener()
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            mBluetoothAdapter.cancelDiscovery();

            String info = ((TextView) view).getText().toString();
            if(info.contains(":")){
                String address = info.substring(info.length() - 17);

                Intent intent = new Intent();
                intent.putExtra(EXTRA_DEVICE_ADDRESS, address);


                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        }
    };

    private final BroadcastReceiver mReceiver = new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            String action = intent.getAction();

            if(BluetoothDevice.ACTION_FOUND.equals(action))
            {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                if(device.getBondState() != BluetoothDevice.BOND_BONDED)
                {
                    mNewDevicesAdapter.add(device.getName() + "\n" + device.getAddress());
                }
            }
            else if(BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action))
            {
                ProgressBar progressBar=(ProgressBar)findViewById(R.id.progress);
                setProgressBarIndeterminateVisibility(false);
                setTitle(R.string.select_device);
                progressBar.setVisibility(View.GONE);

                if(mNewDevicesAdapter.getCount() == 0)
                {
                    String noDevice = getResources().getText(R.string.none_found).toString();
                    mNewDevicesAdapter.add(noDevice);
                }
            }
        }
    };
}
