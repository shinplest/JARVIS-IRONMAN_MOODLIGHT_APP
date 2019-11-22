package com.example.jarvis;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static com.example.jarvis.BlueToothActivity.bt;

public class FragMode extends Fragment {



    public FragMode(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmode, container, false);

        Button RandomMode = view.findViewById(R.id.ramdonmode);
        Button DustMode = view.findViewById(R.id.dustmode);
        Button GeneralMode = view.findViewById(R.id.generalmode);
        final TextView textView = view.findViewById(R.id.modetext);

        GeneralMode.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                textView.setText("General Mode");
                bt.send("gen", true);
            }
        });

        DustMode.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                textView.setText("Dust Mode");
                bt.send("dus", true);
            }
        });

        RandomMode.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                textView.setText("Ramdom Mode");
                bt.send("ran", true);
            }
        });


        return view;
    }
}
