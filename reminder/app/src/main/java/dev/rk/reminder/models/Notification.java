package dev.rk.reminder.models;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Notification implements Serializable {
    public final Date time;
    public final String title;
    public final String content;

    public Notification(Date time, String title, String content) {
        this.time = time;
        this.title = title;
        this.content = content;
    }
}
