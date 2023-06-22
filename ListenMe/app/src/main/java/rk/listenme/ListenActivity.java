package rk.listenme;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import rk.listenme.services.MusicService;
import rk.listenme.ui.TrackStore;
import rk.listenme.localdb.models.Track;

public class ListenActivity extends AppCompatActivity {

    Button nextBtn;
    Button pauseBtn;
    Button previousBtn;
    SeekBar seekBar;
    TextView startText;
    TextView lengthText;
    ImageView imageView;
    TextView titleText;

    Timer timer;

    int trackIndex;
    TrackStore trackStore;

    @Nullable
    private MusicService musicService;
    private final ServiceConnection musicConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicService.MusicServiceBinder binder = (MusicService.MusicServiceBinder) service;
            musicService = binder.getService();
            loadTrack();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            musicService = null;
        }
    };

    @Override
    protected void onStart() {
        super.onStart();

        Intent musicIntent = new Intent(this, MusicService.class);
        bindService(musicIntent, musicConnection, BIND_AUTO_CREATE);
    }


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
                if(musicService == null) return;
                seekBar.setProgress(musicService.getPosition());
            }
        }, 0, 1000);

        pauseBtn.setOnClickListener(v -> {
            if(musicService == null) return;

            boolean isPlaying = musicService.togglePlay();
            pauseBtn.setText(isPlaying ? "Pause" : "Continue");
        });

        previousBtn.setOnClickListener(v -> {
            trackIndex -= 1;
            loadTrack();
                updateNavigationButtons();
        });

        nextBtn.setOnClickListener(v -> {
            trackIndex += 1;
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
                if(musicService == null) return;

                int progress = seekBar.getProgress();
                musicService.goTo(progress);
            }
        });
    }

    private void updateNavigationButtons() {
        previousBtn.setEnabled(trackIndex > 0);
        nextBtn.setEnabled(trackIndex < trackStore.size() - 1);
    }

    private void loadTrack() {
        if(musicService == null) return;

        Track track = trackStore.getOne(trackIndex);

        String image = track.getImage();
        Picasso.get().load(image).into(imageView);
        titleText.setText(track.title);

        try {
            musicService.loadTrack(track.link, player -> {
                long totalSeconds = player.getDuration() / 1000;
                long minutes = totalSeconds / 60;
                long seconds = totalSeconds % 60;
                lengthText.setText(minutes + ":" + seconds);

                seekBar.setMax(player.getDuration());
                seekBar.setProgress(0);
            });
        } catch (IOException e) {
            Toast.makeText(this, "Sorry! We can't load track!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStop () {
        super.onStop();
        timer.cancel();

        if(musicService != null) {
            unbindService(musicConnection);
        }
    }
}