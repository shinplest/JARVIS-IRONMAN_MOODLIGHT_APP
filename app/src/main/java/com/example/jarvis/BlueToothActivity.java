package com.example.jarvis;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


import com.bumptech.glide.Glide;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;
import app.akexorcist.bluetotohspp.library.BluetoothState;
import app.akexorcist.bluetotohspp.library.DeviceList;


public class BlueToothActivity extends AppCompatActivity {

    public static BluetoothSPP bt;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        ImageView btconnect_image = findViewById(R.id.bt_dialog);
        Glide.with(this).load(R.raw.arc_bluetooth).into(btconnect_image);

        bt = new BluetoothSPP(this);

        //블루투스 사용 가능한지 체크하고 안되면 종
        if(!bt.isBluetoothAvailable()) {
            Toast.makeText(getApplicationContext()
                    , "블루투스 사용이 불가능한 기기입니다."
                    , Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext()
                    , "앱을 종료합니다."
                    , Toast.LENGTH_LONG).show();
            finish();
        }
        else {
            Toast.makeText(getApplicationContext()
                    , "블루투스 사용이 가능한 기기입니다."
                    , Toast.LENGTH_LONG).show();
            if(!bt.isBluetoothEnabled()) {
                Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(intent, BluetoothState.REQUEST_ENABLE_BT);
            }
        }


        //연결시

        bt.setBluetoothConnectionListener(new BluetoothSPP.BluetoothConnectionListener() { //연결됐을 때
            public void onDeviceConnected(String name, String address) {
                //블루투스 연결되었음을 보내
                bt.send("connet", true);
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {

                        startService();
                        Intent intent = new Intent(BlueToothActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }, 3000);// 0.5초 정도 딜레이를 준 후 시작

                Toast.makeText(getApplicationContext()
                        , "Connected to " + name + "\n" + address
                        , Toast.LENGTH_SHORT).show();

            }

            public void onDeviceDisconnected() { //연결해제
                stopService();
                Toast.makeText(getApplicationContext()
                        , "연결이 해제 되었습니다.", Toast.LENGTH_SHORT).show();
            }

            public void onDeviceConnectionFailed() { //연결실패
                Toast.makeText(getApplicationContext()
                        , "연결할 수 없습니다.", Toast.LENGTH_SHORT).show();
            }
        });



        btconnect_image.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (bt.getServiceState() == BluetoothState.STATE_CONNECTED) {
                    bt.disconnect();
                } else {
                    Intent intent = new Intent(getApplicationContext(), DeviceList.class);
                    startActivityForResult(intent, BluetoothState.REQUEST_CONNECT_DEVICE);
                }
            }
        });
    }
    public void onDestroy() {
        super.onDestroy();
        stopService();
        bt.stopService(); //블루투스 중지
    }

    public void onStart() {
        super.onStart();
        if (!bt.isBluetoothEnabled()) { //
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, BluetoothState.REQUEST_ENABLE_BT);
        } else {
            if (!bt.isServiceAvailable()) {
                bt.setupService();
                bt.startService(BluetoothState.DEVICE_OTHER);
            }
        }
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == BluetoothState.REQUEST_CONNECT_DEVICE) {
            if (resultCode == Activity.RESULT_OK)
                bt.connect(data);
        } else if (requestCode == BluetoothState.REQUEST_ENABLE_BT) {
            if (resultCode == Activity.RESULT_OK) {
                bt.setupService();
                bt.startService(BluetoothState.DEVICE_OTHER);
            } else {
                Toast.makeText(getApplicationContext()
                        , "Bluetooth was not enabled."
                        , Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    public void startService() {
        Intent serviceIntent = new Intent(this, BlueToothService.class);
        serviceIntent.putExtra("inputExtra", "색을 변경하거나, 여러가지 모드를 사용하실 수 있습니다.");

        ContextCompat.startForegroundService(this, serviceIntent);
    }

    public void stopService() {
        Intent serviceIntent = new Intent(this, BlueToothService.class);
        stopService(serviceIntent);
    }

}


