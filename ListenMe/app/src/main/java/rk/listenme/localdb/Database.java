package rk.listenme.localdb;

import androidx.room.RoomDatabase;

import rk.listenme.localdb.models.Track;
import rk.listenme.localdb.models.TrackDao;

@androidx.room.Database(version = 1, entities = {Track.class})
public abstract class Database extends RoomDatabase {

    public abstract TrackDao track();
}
