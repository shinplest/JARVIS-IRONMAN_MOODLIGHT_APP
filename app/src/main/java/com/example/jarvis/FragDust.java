package com.example.jarvis;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;

import static com.example.jarvis.BlueToothActivity.bt;

public class FragDust extends Fragment {

    double dust;
    public FragDust(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragdust, container, false);

        final TextView textView = view.findViewById(R.id.dusttext);
        final ImageView imageView = view.findViewById(R.id.faceimage);

        bt.setOnDataReceivedListener(new BluetoothSPP.OnDataReceivedListener() {
            public void onDataReceived(byte[] data, String message) {
                // Do something when data incoming
                dust = Double.parseDouble(message);
                if(dust < 50){
                    imageView.setImageResource(R.drawable.face_green_gimp);

                }else if(dust > 50){
                    imageView.setImageResource(R.drawable.face_yellow_gimp);
                }else{
                    imageView.setImageResource(R.drawable.face_red_gimp);

                }
                textView.setText(message + " µm");
            }
        });


        return view;
    }
}
