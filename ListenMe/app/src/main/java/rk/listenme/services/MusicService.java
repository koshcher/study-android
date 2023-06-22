package rk.listenme.services;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.util.function.Consumer;

public class MusicService extends Service {

    MediaPlayer player;

    @Override
    public void onCreate() {
        super.onCreate();

        player = new MediaPlayer();
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }

    public int getDuration() {
        return player.getDuration();
    }
    public int getPosition() {
        return player.getCurrentPosition();
    }

    public boolean togglePlay() {
        if(player.isPlaying()) {
            player.pause();
            return false;
        }
        player.start();
        return true;
    }

    public void goTo(int position) {
        player.seekTo(position);
    }

    public void loadTrack(String url, Consumer<MediaPlayer> afterPrepared) throws IOException {
        if(player.isPlaying()) {
            player.stop();
            player.reset();
        }

        player.setDataSource(url);
        player.setOnPreparedListener(pl -> {
            player.start();
            afterPrepared.accept(pl);
        });
        player.prepareAsync();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        player.release();
    }

    public class MusicServiceBinder extends Binder {
        public MusicService getService() { return MusicService.this; }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) { return new MusicServiceBinder(); }
}
