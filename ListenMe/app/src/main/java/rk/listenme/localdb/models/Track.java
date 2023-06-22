package rk.listenme.localdb.models;

import android.webkit.URLUtil;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Track implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String title;
    public String link;
    private final String image;

    public Track(String title, String link, String image) {
        this.title = title;
        this.link = link;
        this.image = image;
    }

    public String getImage() {
        return image.isEmpty()
                ? "https://avatars.githubusercontent.com/u/132370958?s=400&u=7771c9a83c3adf1e7f0ce0a7bb7c9aa80fa3b78e&v=4"
                : image;
    }
}
