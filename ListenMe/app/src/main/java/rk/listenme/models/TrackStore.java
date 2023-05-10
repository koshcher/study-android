package rk.listenme.models;

import java.util.ArrayList;
import java.util.List;

public class TrackStore {
    private static volatile TrackStore INSTANCE = null;

    private final List<Track> tracks;

    private TrackStore() {
        tracks = new ArrayList<>();
    }

    // multithreaded
    public static TrackStore getInstance() {
        if(INSTANCE != null) return INSTANCE;

        synchronized (TrackStore.class) {
            if (INSTANCE == null) INSTANCE = new TrackStore();
        }
        return INSTANCE;
    }

    public void add(Track notification) {
        tracks.add(notification);
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public int size() { return tracks.size(); }

    public Track getOne(int index) {
        return tracks.get(index);
    }
}
