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

import rk.listenme.data.TrackStore;
import rk.listenme.models.Track;

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

    int trackIndex;
    TrackStore trackStore;

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

        trackStore = TrackStore.getInstance(this);
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        timer = new Timer();

        Intent intent = getIntent();
        trackIndex = intent.getIntExtra("trackIndex", -1);
        if(trackIndex == -1) {
            Toast.makeText(this, "Sorry! Can't find track.", Toast.LENGTH_LONG).show();
            finish();
        }

        loadTrack();
        updateNavigationButtons();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(mediaPlayer == null || !mediaPlayer.isPlaying()) return;
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
            }
        }, 0, 1000);

        pauseBtn.setOnClickListener(v -> {
            if(mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                pauseBtn.setText("Continue");
                return;
            }
            mediaPlayer.start();
            pauseBtn.setText("Pause");
        });

        previousBtn.setOnClickListener(v -> {
            mediaPlayer.stop();
            mediaPlayer.reset();
            trackIndex--;
            loadTrack();
            updateNavigationButtons();
        });

        nextBtn.setOnClickListener(v -> {
            mediaPlayer.stop();
            mediaPlayer.reset();
            trackIndex++;
            loadTrack();
            updateNavigationButtons();
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

    private void updateLength() {
        long totalSeconds = mediaPlayer.getDuration() / 1000;
        long minutes = totalSeconds / 60;
        long seconds = totalSeconds % 60;
        lengthText.setText(minutes + ":" + seconds);
    }

    private void updateNavigationButtons() {
        previousBtn.setEnabled(trackIndex > 0);
        nextBtn.setEnabled(trackIndex < trackStore.size() - 1);
    }

    private void loadTrack() {
        Track track = trackStore.getOne(trackIndex);

        String image = track.getImage();

        Picasso.get().load(image).into(imageView);
        titleText.setText(track.title);

        try {
            mediaPlayer.setDataSource(track.link);
            mediaPlayer.setOnPreparedListener(player -> {
                updateLength();
                seekBar.setMax(player.getDuration());
                seekBar.setProgress(0);
                player.start();
            });
            mediaPlayer.prepareAsync();
        } catch (IOException e) {
            Toast.makeText(this, "Sorry! We can't load track!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStop () {
        super.onStop();
        timer.cancel();
        mediaPlayer.release();
    }
}