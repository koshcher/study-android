package rk.listenme;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import rk.listenme.adapters.TrackAdapter;
import rk.listenme.data.TrackStore;
import rk.listenme.models.Track;

public class MainActivity extends AppCompatActivity {

    private RecyclerView trackList;
    private Button addTrackBtn;
    private TrackAdapter adapter;
    private ActivityResultLauncher<Intent> addTrackActivityLauncher;
    private ActivityResultLauncher<Intent> listenActivityLauncher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trackList = findViewById(R.id.trackList);
        addTrackBtn = findViewById(R.id.addTrackBtn);

        TrackStore trackStore = TrackStore.getInstance(this);

        /*
        trackStore.add(new Track(
                "40 Skrip Andy",
                "https://github.com/koshcher/music/raw/main/40skripandry.mp3",
                "https://www.cheatsheet.com/wp-content/uploads/2023/01/50-Cent-music-2023.jpg"
        ));

        trackStore.add(new Track(
                "Underground",
                "https://github.com/koshcher/music/raw/main/underground.mp3",
                ""
        ));
        */

        addTrackActivityLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() != Activity.RESULT_OK) return;
                    Intent data = result.getData();
                    if (data == null) return;

                    Track track = (Track) data.getSerializableExtra("track");
                    trackStore.add(track);
                    adapter.notifyItemInserted(trackStore.size() - 1);
                }
        );

        adapter = new TrackAdapter(this, trackStore.all());
        trackList.setLayoutManager(new LinearLayoutManager(this));
        trackList.setAdapter(adapter);

        addTrackBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddTrackActivity.class);
            addTrackActivityLauncher.launch(intent);
        });
    }
}