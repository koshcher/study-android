package dev.rk.mediaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button startBtn;
    Button pauseBtn;
    Button stopBtn;
    Button seekToStartBtn;
    SeekBar seekBar;
    TextView startText;
    TextView lengthText;

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startBtn = findViewById(R.id.startBtn);
        pauseBtn = findViewById(R.id.pauseBtn);
        stopBtn = findViewById(R.id.stopBtn);
        seekToStartBtn = findViewById(R.id.seekToStartBtn);
        seekBar = findViewById(R.id.seekBar);
        startText = findViewById(R.id.startText);
        lengthText = findViewById(R.id.lengthText);

        mediaPlayer = new MediaPlayer();

        startBtn.setOnClickListener(v -> {
            try {
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setDataSource("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3");
                mediaPlayer.prepare();
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        mediaPlayer.start();
                        lengthText.setText(String.valueOf(mediaPlayer.getDuration()));
                    }
                });
                mediaPlayer.start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        pauseBtn.setOnClickListener(v -> mediaPlayer.pause());
        stopBtn.setOnClickListener(v -> {
            mediaPlayer.stop();
            //mediaPlayer.reset();
            mediaPlayer.release();
        });

        seekToStartBtn.setOnClickListener(v -> {
            mediaPlayer.seekTo(0);
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mediaPlayer.seekTo(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer != null) {
            mediaPlayer.release();
        }
    }
}