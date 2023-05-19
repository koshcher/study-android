package rk.listenme.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

import rk.listenme.models.Track;

public class Database extends SQLiteOpenHelper {
    private static final String DB_NAME = "listenme.db";
    private static final int DB_VERSION = 1;

    public Database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "create table " + Track.TABLE + " ( "
                + Track.ID + " integer primary key autoincrement,"
                + Track.TITLE + " text, "
                + Track.LINK + " text, "
                + Track.IMAGE + " text "
                + ");";
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Track.TABLE);
        onCreate(sqLiteDatabase);
    }

    public void addTrack(Track track) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues(3);
        values.put(Track.TITLE, track.title);
        values.put(Track.LINK, track.link);
        values.put(Track.IMAGE, track.getImage());

        db.insert(Track.TABLE, null, values);
    }

    public List<Track> getAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Track.TABLE, null, null, null, null, null, null);

        List<Track> list = new LinkedList<>();
        if(!cursor.moveToFirst()) return list;

        int idCol = cursor.getColumnIndex(Track.ID);
        int titleCol = cursor.getColumnIndex(Track.TITLE);
        int linkCol = cursor.getColumnIndex(Track.LINK);
        int imageCol = cursor.getColumnIndex(Track.IMAGE);

        do {
            String image = cursor.getString(imageCol);
            String title = cursor.getString(titleCol);
            String link = cursor.getString(linkCol);
            Track track = new Track(title, link, image);
            track.id = cursor.getInt(idCol);
            list.add(track);
        } while (cursor.moveToNext());

        cursor.close();
        return list;
    }
}
