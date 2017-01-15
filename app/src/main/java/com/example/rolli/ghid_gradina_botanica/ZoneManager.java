package com.example.rolli.ghid_gradina_botanica;


import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class ZoneManager extends AppCompatActivity {

    private Bundle bundle;
    private String zone;
    private TextView zoneText;
    private Button forwardButton, pauseButton, playButton, rewindButton;
    private MediaPlayer mediaPlayer;

    private double startTime = 0;
    private double finalTime = 0;

    private Handler myHandler = new Handler();
    private int forwardTime = 5000;
    private int backwardTime = 5000;
    private SeekBar seekbar;
    private TextView songTime, songName, songTotalTime;

    public static int oneTimeOnly = 0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zone_layout);

        bundle = getIntent().getExtras();
        zone = bundle.getString("zone");
        zoneText = (TextView) findViewById(R.id.zoneText);
        zoneText.setText(zone);


        //player

        forwardButton = (Button) findViewById(R.id.forwardButton);
        pauseButton = (Button) findViewById(R.id.pauseButton);
        playButton = (Button)findViewById(R.id.playButton);
        rewindButton = (Button)findViewById(R.id.rewindButton);

        songTime = (TextView)findViewById(R.id.songTime);
        songName = (TextView)findViewById(R.id.songName);
        songTotalTime = (TextView)findViewById(R.id.songTotalTime);
        songName.setText("Song" + zone + ".mp3");

        String uriPath = "android.resource://"+getPackageName()+"/raw/song" + zone;
        Uri uri = Uri.parse(uriPath);
        mediaPlayer = MediaPlayer.create(this, uri);

        seekbar = (SeekBar)findViewById(R.id.seekBar);
        seekbar.setClickable(false);

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int currentPosition = seekBar.getProgress();
                mediaPlayer.seekTo(currentPosition);

            }
        });

        pauseButton.setEnabled(false);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Playing " +
                        "sound",Toast.LENGTH_SHORT).show();
                        mediaPlayer.start();

                finalTime = mediaPlayer.getDuration();
                startTime = mediaPlayer.getCurrentPosition();

                /*if (oneTimeOnly == 0) {
                    seekbar.setMax((int) finalTime);
                    oneTimeOnly = 1;
                }*/

                seekbar.setMax((int) finalTime);

                songTotalTime.setText(String.format("%d min, %d sec",
                        TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
                        TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                        finalTime)))
                );

                songTime.setText(String.format("%d min, %d sec",
                        TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                        TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                        startTime)))
                );

                seekbar.setProgress((int)startTime);
                myHandler.postDelayed(UpdateSongTime, 100);
                pauseButton.setEnabled(true);
                playButton.setEnabled(false);
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Pausing " +
                        "sound",Toast.LENGTH_SHORT).show();
                        mediaPlayer.pause();
                pauseButton.setEnabled(false);
                playButton.setEnabled(true);
            }
        });

        forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = (int)startTime;

                if((temp+forwardTime)<=finalTime){
                    startTime = startTime + forwardTime;
                    mediaPlayer.seekTo((int) startTime);
                }
            }
        });

        rewindButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = (int)startTime;

                if((temp-backwardTime)>0){
                    startTime = startTime - backwardTime;
                    mediaPlayer.seekTo((int) startTime);
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        myHandler.removeMessages(0);
       /* seekbar.setProgress(0);
        mediaPlayer.seekTo(0);
        startTime = 0;*/
        Log.i("tag","Thread stopped");
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.pause();

    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        mediaPlayer.getCurrentPosition();
        pauseButton.setEnabled(false);
        playButton.setEnabled(true);
    }

    private Runnable UpdateSongTime = new Runnable() {
        public void run() {

            startTime = mediaPlayer.getCurrentPosition();
            songTime.setText(String.format("%d min, %d sec",
                    TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                    toMinutes((long) startTime)))
            );

            seekbar.setProgress((int)startTime);
            myHandler.postDelayed(this, 100);
           // Log.i("tag", Double.toString(startTime) + " final:" + Double.toString(finalTime));
            Log.i("tag", "current:" + Integer.toString(seekbar.getProgress()));
        }



    };


}





