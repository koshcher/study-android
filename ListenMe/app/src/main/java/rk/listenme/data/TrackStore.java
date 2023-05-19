package rk.listenme.data;

import android.content.Context;

import java.util.List;

import rk.listenme.models.Track;

public class TrackStore {
    private static volatile TrackStore INSTANCE = null;

    private final List<Track> tracks;
    private final Database db;

    private TrackStore(Database db) {
        this.db = db;
        tracks = db.getAll();
    }

    // multithreaded
    public static TrackStore getInstance(Context context) {
        if(INSTANCE != null) return INSTANCE;

        synchronized (TrackStore.class) {
            if (INSTANCE == null) INSTANCE = new TrackStore(new Database(context));
        }
        return INSTANCE;
    }

    public void add(Track track) {
        db.addTrack(track);
        tracks.add(track);
    }

    public List<Track> all() {
        return tracks;
    }

    public int size() { return tracks.size(); }

    public Track getOne(int index) {
        return tracks.get(index);
    }
}