package com.example.android.mediaplayerapi;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer song;
    private AudioManager audioManager;
    private int actualVolume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        song = MediaPlayer.create(this, R.raw.russia);
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        actualVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        Button playButton = (Button)findViewById(R.id.playButton);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                song.start();
            }
        });

        Button pauseButton = (Button)findViewById(R.id.pauseButton);
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                song.pause();
            }
        });

        Button stopButton = (Button)findViewById(R.id.stopButton);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                song.stop();
                song.prepareAsync();
            }
        });

        Button volUpButton = (Button)findViewById(R.id.volupButton);
        volUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualVolume++;
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, actualVolume, AudioManager.FLAG_SHOW_UI);
            }
        });

        Button volDownButton = (Button)findViewById(R.id.voldownButton);
        volDownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualVolume--;
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, actualVolume, AudioManager.FLAG_SHOW_UI);
            }
        });
    }
}
