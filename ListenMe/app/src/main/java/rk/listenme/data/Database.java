package rk.listenme.data;

import androidx.room.RoomDatabase;

import rk.listenme.models.Track;
import rk.listenme.models.TrackDao;

@androidx.room.Database(version = 1, entities = {Track.class})
public abstract class Database extends RoomDatabase {

    public abstract TrackDao track();
}
