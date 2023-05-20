package rk.listenme.models;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TrackDao {
    @Insert
    void insert(Track book);

    @Query("select * from track")
    List<Track> getAll();
}
