package com.example.jarvis;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.skydoves.colorpickerview.ColorEnvelope;
import com.skydoves.colorpickerview.ColorPickerView;
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener;

import static com.example.jarvis.BlueToothActivity.bt;

public class FragColor extends Fragment {

    public FragColor(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragcolor, container, false);

        Button btnOn = view.findViewById(R.id.btnSend);
        Button btnOff = view.findViewById(R.id.btnSend2);

        btnOn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                bt.send("on", true);
            }
        });

        btnOff.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                bt.send("off", true);
            }
        });

        ColorPickerView colorPickerView = view.findViewById(R.id.colorPickerView);
        colorPickerView.setColorListener(new ColorEnvelopeListener() {
            @Override
            public void onColorSelected(ColorEnvelope envelope, boolean fromUser) {

                String HexColor = envelope.getHexCode().substring(2);
                bt.send(HexColor, true);
            }
        });
        return view;
    }
}
