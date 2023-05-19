package dev.rk.sakequel.context;

import android.app.DownloadManager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "sakequel.db";
    private static final int DATABASE_VERSION = 1;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "create table " + Sake.TABLE_NAME + " ( "
                + Sake.KEY_ID + " integer primary key autoincrement,"
                + Sake.KEY_NAME + " text, "
                + Sake.KEY_AGE + " integer" +
                ");";
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Sake.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addData(Sake sake) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues(2);
        values.put(Sake.KEY_NAME, sake.name);
        values.put(Sake.KEY_AGE, sake.age);

        db.insert(Sake.TABLE_NAME, null, values);
    }

    public List<Sake> all() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Sake.TABLE_NAME, null, null, null, null, null, null);

        List<Sake> list = new LinkedList<>();
        if(!cursor.moveToFirst()) return list;

        int keyId = cursor.getColumnIndex(Sake.KEY_ID);
        int keyName = cursor.getColumnIndex(Sake.KEY_NAME);
        int keyAge = cursor.getColumnIndex(Sake.KEY_AGE);

        do {
            Sake sake = new Sake();
            sake.id = cursor.getInt(keyId);
            sake.name = cursor.getString(keyName);
            sake.age = cursor.getInt(keyAge);
            list.add(sake);
        } while (cursor.moveToNext());

        cursor.close();
        return list;
    }

    public void delete(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Sake.TABLE_NAME, Sake.KEY_ID + " = ?", new String[]{ String.valueOf(id) });
    }

    public int update(Sake sake) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues(2);
        values.put(Sake.KEY_NAME, sake.name);
        values.put(Sake.KEY_AGE, sake.age);

        return db.update(Sake.TABLE_NAME, values, Sake.KEY_ID + " = ?", new String[]{ String.valueOf(sake.id) });
    }
}
