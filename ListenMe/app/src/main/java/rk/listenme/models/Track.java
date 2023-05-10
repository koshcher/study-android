package rk.listenme.models;

import android.webkit.URLUtil;

import java.io.Serializable;

public class Track implements Serializable {

    public static final String id = "track";

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
