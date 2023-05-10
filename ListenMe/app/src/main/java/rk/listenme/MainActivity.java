package rk.listenme;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.hardware.lights.LightState;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import rk.listenme.adapters.TrackAdapter;
import rk.listenme.models.Track;
import rk.listenme.models.TrackStore;

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

        TrackStore.getInstance().add(new Track(
                "P.I.M.P.",
                "https://cdn1.sefon.pro/prev/fc15bOM7mjkne3l7J8cRuA/1683767000/172/50%20Cent%20-%20P.I.M.P.%20%28192kbps%29.mp3",
                "https://www.cheatsheet.com/wp-content/uploads/2023/01/50-Cent-music-2023.jpg"
        ));

        TrackStore.getInstance().add(new Track(
                "Major",
                "https://cdn1.sefon.pro/prev/I5DSVBN6rwHoZtwrhHxymA/1683774495/33/50%20Cent%20Feat.%20Snoop%20Dogg%20%26%20Young%20Jeezy%20-%20Major%20Distribution%20%28192kbps%29.mp3",
                ""
        ));

        addTrackActivityLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() != Activity.RESULT_OK) return;
                    Intent data = result.getData();
                    if (data == null) return;

                    Track track = (Track) data.getSerializableExtra(Track.id);
                    TrackStore.getInstance().add(track);
                    adapter.notifyItemInserted(TrackStore.getInstance().size() - 1);
                }
        );

        adapter = new TrackAdapter(this, TrackStore.getInstance().getTracks());
        trackList.setLayoutManager(new LinearLayoutManager(this));
        trackList.setAdapter(adapter);

        addTrackBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddTrackActivity.class);
            addTrackActivityLauncher.launch(intent);
        });
    }
}