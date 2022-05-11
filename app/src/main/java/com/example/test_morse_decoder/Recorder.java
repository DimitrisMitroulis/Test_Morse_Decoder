package com.example.test_morse_decoder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContextWrapper;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

//Mix of this https://www.youtube.com/watch?v=3ffs2VbJ9JY&ab_channel=CodingPursuits and this video https://www.youtube.com/watch?v=02kihxe7bUU&ab_channel=SanjeevKumar

public class Recorder extends AppCompatActivity {

    private Button BackBtn;
    private Button RecordStopBtn;
    private Button StopBtn;
    private Button PickFileBtn;
    private Button PlayStopBtn;


    private boolean isRecording = false;
    private boolean isplaying = false;

    MediaRecorder mediaRecorder;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorder);




        BackBtn = (Button) findViewById(R.id.BackBtn);
        RecordStopBtn = (Button) findViewById(R.id.RecordStopBtn);
        StopBtn = (Button) findViewById(R.id.StopBtn);
        PickFileBtn = (Button) findViewById(R.id.PickFileBtn);
        PlayStopBtn = (Button) findViewById(R.id.PlayStopBtn);

        RecordStopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnRecordPressed(v);
            }
        });
        PlayStopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnPlayPressed(v);
            }
        });




        BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoBack();

            }
        });

    }


    public void btnRecordPressed(View v) {

        if (!isRecording) {
            isRecording = true;


            RecordStopBtn.setText("Stop Recording");
            try {
                mediaRecorder = new MediaRecorder();
                mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                mediaRecorder.setOutputFile(getRecordingFilePath());
                mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                mediaRecorder.prepare();
                mediaRecorder.start();

                Toast.makeText(this, "Recording has started", Toast.LENGTH_SHORT).show();

            } catch (IOException e) {
                e.printStackTrace();
            }


        } else {
            isRecording = false;
            RecordStopBtn.setText("Start Recording");

            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;
            Toast.makeText(this, "Recording has stopped", Toast.LENGTH_SHORT).show();
        }


    }


    public void btnPlayPressed(View v) {
        //will check is the button is playing, stars playing if

        if (!isplaying) {
            startPlaying();

        } else {
            StopPlaying();
        }


    }
    //stops and releases media player
    private void StopPlaying() {

        isplaying = false;
        PlayStopBtn.setText("Play");


        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
    }


    //starts media player
    private void startPlaying() {
        isplaying = true;
        PlayStopBtn.setText("Stop");
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(getRecordingFilePath());
            mediaPlayer.prepare();
            mediaPlayer.start();

            MediaMetadataRetriever metaRetriever = new MediaMetadataRetriever();
            metaRetriever.setDataSource(getRecordingFilePath());

            float duration = Float.parseFloat(metaRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION));



        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getRecordingFilePath() {
        ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
        File musicDirectory = contextWrapper.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        File file = new File(musicDirectory, "testRecordingFile" + ".mp3");






        return file.getPath();

    }


    public void GoBack() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}