package dev.rk.reminder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import dev.rk.reminder.models.Notification;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        // load elements
        TextView timeText = findViewById(R.id.timeText);
        TextView titleText = findViewById(R.id.titleText);
        TextView contentText = findViewById(R.id.contentText);
        Button backBtn = findViewById(R.id.backBtn);

        Notification notification = (Notification) getIntent().getSerializableExtra("notification");

        timeText.setText(notification.time.toString());
        titleText.setText(notification.title);
        contentText.setText(notification.content);

        backBtn.setOnClickListener(v -> finish());
    }
}