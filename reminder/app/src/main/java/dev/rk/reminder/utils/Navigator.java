package dev.rk.reminder.utils;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import dev.rk.reminder.CreateActivity;
import dev.rk.reminder.InfoActivity;
import dev.rk.reminder.MainActivity;
import dev.rk.reminder.models.Notification;

// Just experiments: put navigation between activities
public class Navigator  {
    public static void gotoMainActivity(Context context) {
        Intent mainIntent = new Intent(context, MainActivity.class);
        context.startActivity(mainIntent);
    }

    public static void gotoInfoActivity(Context context, Notification notification) {
        Intent infoIntent = new Intent(context, InfoActivity.class);
        infoIntent.putExtra("notification", notification);
        context.startActivity(infoIntent);
    }

    public static void gotoCreateActivity(Context context) {
        Intent createIntent = new Intent(context, CreateActivity.class);
        context.startActivity(createIntent);
    }
}
