package com.example.test_morse_decoder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView txt;
    private TextView result;
    private Button toMorseBtn;
    private Button toAlphaBtn;
    private Button PavlaBtn;
    private Button DotBtn;
    private Button SpaceBtn;
    private Button GoToRecorderBtn;

    private static int MICROPHONE_PERMISSION_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //checks if device has mic, then if the app doesn't have access to it, asks permission.[WORKING]
        if(isMicPresent()){
            requestMicrophonePermission();
        }

        txt = (TextView) findViewById(R.id.txt);
        result = (TextView) findViewById(R.id.result);
        toMorseBtn = (Button) findViewById(R.id.ToMorseBtn);
        toAlphaBtn = (Button) findViewById(R.id.ToAlphaBtn);

        PavlaBtn = (Button) findViewById(R.id.PavlaBtn);
        DotBtn = (Button) findViewById(R.id.DotBtn);
        SpaceBtn = (Button) findViewById(R.id.SpaceBtn);

        GoToRecorderBtn = (Button) findViewById(R.id.GoToRecorderBtn);

        GoToRecorderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRecorder();
            }
        });

        toMorseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtToConvert = txt.getText().toString();
                String convertedTxt = MorseCode.alphaToMorse(txtToConvert);
                result.setText(convertedTxt);
            }
        });

        toAlphaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtToConvert = txt.getText().toString();
                String convertedTxt = MorseCode.morseToAlpha(txtToConvert);
                result.setText(convertedTxt);
            }
        });

        PavlaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt.append("-");
            }
        });


        DotBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                txt.append(".");


            }
        });

        SpaceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt.append(" ");
            }
        });



    }


    private boolean isMicPresent() {
        //returns true if device has microphone.

        return (this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_MICROPHONE));
    }


    private void requestMicrophonePermission() {
        //if permission for microphone is not given, it requests it.

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, MICROPHONE_PERMISSION_CODE);
        }
    }

    //opens recorder activity
    private void openRecorder() {
        Intent intent = new Intent(this, Recorder.class);
        startActivity(intent);
    }


}