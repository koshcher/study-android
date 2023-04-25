package dev.rk.reminder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import dev.rk.reminder.adapters.NotificationAdapter;
import dev.rk.reminder.models.Notification;
import dev.rk.reminder.utils.Navigator;
import dev.rk.reminder.utils.NotificationStore;

public class MainActivity extends AppCompatActivity {
    private NotificationAdapter notificationAdapter;
    private ListView notificationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<Notification> notifications = NotificationStore.getInstance().getNotifications();
        notificationAdapter = new NotificationAdapter(this, R.layout.notification, notifications,
                (Notification notification) -> Navigator.gotoInfoActivity(this, notification)
        );

        setContentView(R.layout.list);

        notificationList = findViewById(R.id.notificationList);
        Button createBtn = findViewById(R.id.createBtn);

        createBtn.setOnClickListener(v -> Navigator.gotoCreateActivity(this));
    }

    // to work after back
    @Override
    protected void onStart() {
        super.onStart();
        notificationList.setAdapter(notificationAdapter);
    }
}