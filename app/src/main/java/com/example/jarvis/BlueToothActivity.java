package com.example.jarvis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;
import app.akexorcist.bluetotohspp.library.BluetoothState;
import app.akexorcist.bluetotohspp.library.DeviceList;

public class BlueToothActivity extends AppCompatActivity {

    private BluetoothSPP bt;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

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
                    , "블루투스 사용이 가능 기기입니다."
                    , Toast.LENGTH_SHORT).show();
        }

        final ImageView btnConnect = findViewById(R.id.bt_dialog); //연결시도

        bt.setBluetoothConnectionListener(new BluetoothSPP.BluetoothConnectionListener() { //연결됐을 때
            public void onDeviceConnected(String name, String address) {
                Toast.makeText(getApplicationContext()
                        , "Connected to " + name + "\n" + address
                        , Toast.LENGTH_LONG).show();

            }

            public void onDeviceDisconnected() { //연결해제
                Toast.makeText(getApplicationContext()
                        , "연결이 해제 되었습니다.", Toast.LENGTH_SHORT).show();
            }

            public void onDeviceConnectionFailed() { //연결실패
                Toast.makeText(getApplicationContext()
                        , "연결할 수 없습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        btnConnect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (bt.getServiceState() == BluetoothState.STATE_CONNECTED) {
                    bt.disconnect();
                } else {
//                    Intent intent = new Intent(getApplicationContext(), DeviceList.class);
//                    startActivityForResult(intent, BluetoothState.REQUEST_CONNECT_DEVICE);
                    //인텐트 대신 dialog로 도전

                }
            }
        });
    }
}


