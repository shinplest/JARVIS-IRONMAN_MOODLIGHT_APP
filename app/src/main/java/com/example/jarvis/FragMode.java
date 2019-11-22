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

    int Mode = 0;


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

        Button ChangeMode = view.findViewById(R.id.changemode);
        final TextView textView = view.findViewById(R.id.modetext);

        ChangeMode.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Mode++;
                if(Mode % 2 == 0){
                    textView.setText("GENERAL MODE");
                }else{
                    textView.setText("DUST MODE");
                }
                bt.send("change", true);
            }
        });


        return view;
    }
}
