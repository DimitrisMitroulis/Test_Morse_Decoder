package com.example.test_morse_decoder;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    private void openRecorder() {
        Intent intent = new Intent(this, Recorder.class);
        startActivity(intent);
    }

    public void btnRecordPresse(View v){

    }

    public void btnStopPresse(View v){

    }

    public void btnPlayPresse(View v){

    }
}