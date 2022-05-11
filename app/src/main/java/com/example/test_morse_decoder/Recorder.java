package com.example.test_morse_decoder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Recorder extends AppCompatActivity {

    private Button BackBtn;
    private Button RecordBtn;
    private Button StopBtn;
    private Button PickFileBtn;
    private Button PlayBtn;

    private static int MICROPHONE_PERMISSION_CODE = 200;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorder);


        BackBtn = (Button) findViewById(R.id.BackBtn);
        RecordBtn = (Button) findViewById(R.id.RecordBtn);
        StopBtn = (Button) findViewById(R.id.StopBtn);
        PickFileBtn = (Button) findViewById(R.id.PickFileBtn);
        PlayBtn = (Button) findViewById(R.id.PlayBtn);


        BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoBack();

            }
        });

    }


    public void btnRecordPressed(View v) {


    }

    public void btnStopPressed(View v) {


    }

    public void btnPlayPressed(View v) {


    }

    private boolean isMicPresent() {
        //returns true if device has microphone.

        return this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_MICROPHONE);
    }


    private void getMicrophonePermission() {
        //if permission for microphone is not given, it requests it.

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, MICROPHONE_PERMISSION_CODE);
        }
    }

    public void GoBack() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}