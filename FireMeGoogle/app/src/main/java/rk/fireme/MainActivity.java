package rk.fireme;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import rk.fireme.ui.fragments.LoginFragment;

public class MainActivity extends AppCompatActivity {

    public static final String CHANNEL_ID = "notification_channel";
    public static final int NOTIFICATION_ID = 44584;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            AdView mainAdView = findViewById(R.id.mainAdView);
            AdRequest adRequest = new AdRequest.Builder().build();
            mainAdView.loadAd(adRequest);
        }catch (Error error) {
            String msg = "";
        }
        createNotificationChannel();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.layoutContainer, new LoginFragment()).commit();

    }

    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID, "NotificationChannel", NotificationManager.IMPORTANCE_DEFAULT
            );
            channel.setDescription("Receive notifications");
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}