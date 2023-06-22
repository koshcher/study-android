package rk.listenme.ui;

import android.content.Context;

import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

import rk.listenme.localdb.Database;
import rk.listenme.localdb.models.Track;

public class TrackStore {
    private static volatile TrackStore INSTANCE = null;

    private List<Track> tracks = new ArrayList<>();

    private final Database db;

    private TrackStore(Database db) {
        this.db = db;
    }

    // multithreaded
    public static TrackStore getInstance(Context context) {
        if(INSTANCE != null) return INSTANCE;

        synchronized (TrackStore.class) {
            if (INSTANCE == null) {
                Database db = Room.databaseBuilder(context, Database.class, "listenme").build();
                INSTANCE = new TrackStore(db);
            }
        }
        return INSTANCE;
    }

    public void add(Track track) {
        new Thread(() -> db.track().insert(track)).start();
        tracks.add(track);
    }

    public List<Track> all() {
        return tracks;
    }

    public void load(Runnable onLoad) {
        new Thread(() -> {
                tracks = db.track().getAll();
                onLoad.run();
        }).start();
    }

    public int size() { return tracks.size(); }

    public Track getOne(int index) {
        return tracks.get(index);
    }
}