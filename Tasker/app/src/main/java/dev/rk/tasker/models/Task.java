package dev.rk.tasker.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Task implements Serializable {
    public String header;
    public String content;

    public Task(String header, String content) {
        this.content = content;
        this.header = header;
    }

    public String getContent() {
        return content;
    }

    public String getHeader() {
        return header;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
