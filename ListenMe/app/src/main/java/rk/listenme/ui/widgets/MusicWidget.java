package rk.listenme.ui.widgets;

import static android.content.Context.BIND_AUTO_CREATE;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.function.Consumer;

import rk.listenme.R;
import rk.listenme.localdb.models.Track;
import rk.listenme.services.MusicService;
import rk.listenme.ui.TrackStore;

public class MusicWidget extends AppWidgetProvider {
    public static final String PAUSE_ACTION = "rk.listenme.PAUSE_ACTION";
    public static final String PREVIOUS_ACTION = "rk.listenme.PREVIOUS_ACTION";
    public static final String NEXT_ACTION = "rk.listenme.NEXT_ACTION";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    private void updateAppWidget(Context context, AppWidgetManager manager, int id) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.music_widget);

        views.setOnClickPendingIntent(R.id.widgetPauseBtn, action(context, PAUSE_ACTION));
        views.setOnClickPendingIntent(R.id.widgetNextBtn, action(context, NEXT_ACTION));
        views.setOnClickPendingIntent(R.id.widgetPreviousBtn, action(context, PREVIOUS_ACTION));

        manager.updateAppWidget(id, views);
    }

    private PendingIntent action(Context context, String key) {
        Intent intent = new Intent(context, MusicWidget.class);
        intent.setAction(key);

        return PendingIntent.getBroadcast(context, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT
                //Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
                  //      ? PendingIntent.FLAG_MUTABLE
                    //    : PendingIntent.FLAG_IMMUTABLE
        );
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        final String action = intent.getAction();
        final MusicWidgetState state = MusicWidgetState.instance(context);

        if(Objects.equals(action, PAUSE_ACTION)) {
            state.usingMusicService(context, musicService -> {
                if(musicService.isLoaded()) {
                    musicService.togglePlay();
                    return;
                }
                int trackIndex = state.trackIndex.get();
                loadTrack(context, trackIndex);
            });
            return;
        }

        if(Objects.equals(action, NEXT_ACTION)) {
            state.usingTrackStore(context, trackStore -> {
                if(state.trackIndex.get() == trackStore.size() - 1) return;
                int trackIndex = state.trackIndex.incrementAndGet();
                loadTrack(context, trackIndex);
            });
            return;
        }

        if(Objects.equals(action, PREVIOUS_ACTION)) {
            state.usingTrackStore(context, trackStore -> {
                if(state.trackIndex.get() == 0) return;
                int trackIndex = state.trackIndex.decrementAndGet();
                loadTrack(context, trackIndex);
            });
            return;
        }
    }

    private void loadTrack(Context context, int trackIndex) {
        MusicWidgetState state = MusicWidgetState.instance(context);

        state.usingMusicService(context, musicService -> state.usingTrackStore(context, trackStore -> {
            try {
                Track track = trackStore.getOne(trackIndex);
                musicService.loadTrack(track.link, mediaPlayer -> {});
            } catch (IOException e) {
                System.out.println("LOAD TRACK EXCEPTION");
            }
        }));
    }

}