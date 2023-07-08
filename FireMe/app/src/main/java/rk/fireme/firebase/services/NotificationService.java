package rk.fireme.firebase.services;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

import rk.fireme.firebase.models.Notification;

public class NotificationService {
    private DatabaseReference notifications;

    public NotificationService() {
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://fireme-rk-default-rtdb.europe-west1.firebasedatabase.app/");
        notifications = database.getReference("notifications");
    }

    public boolean addNotification(String userId, String title, Date time) {
        String notificationId = notifications.push().getKey();
        if(notificationId == null) return false;

        Notification notification = new Notification(notificationId, title, time, userId);
        notifications.child(notificationId).setValue(notification);
        return true;
    }

    public void getAll(String userId, Consumer<List<Notification>> onSuccess, Consumer<String> onError) {
        notifications.orderByChild("userId").equalTo(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshots) {
                List<Notification> notificationList = new LinkedList<>();
                for (DataSnapshot snapshot: snapshots.getChildren()) {
                    Notification notification = snapshot.getValue(Notification.class);
                    notificationList.add(notification);
                }
                onSuccess.accept(notificationList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                onError.accept(error.getMessage());
            }
        });
    }

    public void delete(String userId, String notificationId, Consumer<Boolean> onResult) {
        notifications
                .orderByChild("id").equalTo(notificationId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshots) {
                try {
                    for (DataSnapshot snapshot : snapshots.getChildren()) {
                        Notification notification = snapshot.getValue(Notification.class);
                        if (notification == null || !Objects.equals(notification.userId, userId)) return;
                        snapshot.getRef().removeValue();
                    }
                    onResult.accept(true);
                } catch (Error error) {
                    onResult.accept(false);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                onResult.accept(false);
            }
        });
    }
}
