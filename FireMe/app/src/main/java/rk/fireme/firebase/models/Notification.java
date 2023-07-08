package rk.fireme.firebase.models;

import java.util.Date;

public class Notification {
    public String id;
    public String title;
    public Date time;
    public String userId;

    public Notification() {}

    public Notification(String id, String title, Date time, String userId) {
        this.id = id;
        this.title = title;
        this.time = time;
        this.userId = userId;
    }
}
