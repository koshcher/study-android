package rk.listenme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import rk.listenme.models.Track;
import rk.listenme.models.TrackStore;

public class ListenActivity extends AppCompatActivity {

    Button nextBtn;
    Button pauseBtn;
    Button previousBtn;
    SeekBar seekBar;
    TextView startText;
    TextView lengthText;
    ImageView imageView;
    TextView titleText;

    MediaPlayer mediaPlayer;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listen);

        nextBtn = findViewById(R.id.nextBtn);
        pauseBtn = findViewById(R.id.pauseBtn);
        previousBtn = findViewById(R.id.previousBtn);
        seekBar = findViewById(R.id.seekBar);
        startText = findViewById(R.id.startText);
        lengthText = findViewById(R.id.lengthText);
        imageView = findViewById(R.id.imageView);
        titleText = findViewById(R.id.titleText);

        mediaPlayer = new MediaPlayer();
        timer = new Timer();

        Intent intent = getIntent();
        int trackIndex = intent.getIntExtra("trackIndex", -1);
        if(trackIndex == -1) {
            Toast.makeText(this, "Sorry! Can't find track.", Toast.LENGTH_LONG).show();
            return;
        }

        Track track = TrackStore.getInstance().getOne(trackIndex);

        Picasso.get().load(track.getImage()).into(imageView);
        titleText.setText(track.title);

        try {
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDataSource(track.link);
            mediaPlayer.prepare();
            lengthText.setText(String.valueOf(mediaPlayer.getDuration()));
            seekBar.setMax(mediaPlayer.getDuration());
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                }
            }, 0, 1000);
            mediaPlayer.start();
        } catch (IOException e) {
            Toast.makeText(this, "Sorry! We can't load track!", Toast.LENGTH_LONG).show();
        }

        pauseBtn.setOnClickListener(v -> {
            if(mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                pauseBtn.setText("Continue");
            } else {
                mediaPlayer.start();
                pauseBtn.setText("Pause");
            }
        });

        if(trackIndex < 1) {
            previousBtn.setEnabled(false);
        }
        if(trackIndex >= TrackStore.getInstance().size() - 1) {
            nextBtn.setEnabled(false);
        }



        previousBtn.setOnClickListener(v -> {
            mediaPlayer.release();
            Intent previousIntent = new Intent(this, ListenActivity.class);
            previousIntent.putExtra("trackIndex", trackIndex - 1);
            startActivity(previousIntent);
        });

        nextBtn.setOnClickListener(v -> {
            mediaPlayer.release();
            Intent nextIntent = new Intent(this, ListenActivity.class);
            nextIntent.putExtra("trackIndex", trackIndex + 1);
            startActivity(nextIntent);
        });


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                mediaPlayer.seekTo(progress);
            }
        });
    }




}