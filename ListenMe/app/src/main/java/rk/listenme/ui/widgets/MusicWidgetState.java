package rk.listenme.ui.widgets;


import static android.content.Context.BIND_AUTO_CREATE;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

import rk.listenme.services.MusicService;
import rk.listenme.ui.TrackStore;

// We should keep state separate, because provider is recreated each time
public class MusicWidgetState {
    private static volatile MusicWidgetState INSTANCE = null;

    // Tracks
    public AtomicInteger trackIndex = new AtomicInteger(0);
    private TrackStore trackStore;

    // Music Service
    @Nullable
    private MusicService musicService;
    private final ServiceConnection musicConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicService.MusicServiceBinder binder = (MusicService.MusicServiceBinder) service;
            musicService = binder.getService();;

            while (!musicConsumerQueue.isEmpty()) {
                Consumer<MusicService> musicServiceConsumer = musicConsumerQueue.remove();
                musicServiceConsumer.accept(musicService);
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            musicService = null;
        }
    };
    private final Queue<Consumer<MusicService>> musicConsumerQueue = new LinkedList<>();

    public static MusicWidgetState instance(Context context) {
        if(INSTANCE != null) return INSTANCE;

        synchronized (MusicWidgetState.class) {
            if (INSTANCE == null) {
                INSTANCE = new MusicWidgetState();
            }
        }
        return INSTANCE;
    }

    public void usingTrackStore(Context context, Consumer<TrackStore> trackStoreConsumer) {
        if(trackStore != null) {
            trackStoreConsumer.accept(trackStore);
            return;
        }
        trackStore = TrackStore.getInstance(context);
        trackStore.load(() -> trackStoreConsumer.accept(trackStore));
    }

    public void usingMusicService(Context context, Consumer<MusicService> musicServiceConsumer) {
        if(musicService != null) {
            musicServiceConsumer.accept(musicService);
            return;
        }
        musicConsumerQueue.add(musicServiceConsumer);
        Intent musicIntent = new Intent(context, MusicService.class);
        context.getApplicationContext().bindService(musicIntent, musicConnection, BIND_AUTO_CREATE);
    }
}
