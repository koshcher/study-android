package dev.rk.reminder.utils;

import java.util.ArrayList;
import java.util.List;

import dev.rk.reminder.models.Notification;

public class NotificationStore {
    private static volatile NotificationStore INSTANCE = null;

    private final List<Notification> notifications = new ArrayList<>();

    private NotificationStore() {}

    // multithreaded
    public static NotificationStore getInstance() {
        if(INSTANCE != null) return INSTANCE;

        synchronized (NotificationStore.class) {
            if (INSTANCE == null) INSTANCE = new NotificationStore();
        }
        return INSTANCE;
    }

    public void addNotification(Notification notification) {
        notifications.add(notification);
    }

    public List<Notification> getNotifications() {
        return notifications;
    }
}
