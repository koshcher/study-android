package rk.weatherme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import rk.weatherme.room.LocalDb;
import rk.weatherme.room.models.DbWeather;
import rk.weatherme.services.CollectorService;

public class MainActivity extends AppCompatActivity {

    TextView countText;
    TextView temperatureText;
    Button getCurrentBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent collectorIntent = new Intent(this, CollectorService.class);
        PendingIntent collectorPendingIntent = PendingIntent
                .getService(this, 0, collectorIntent, PendingIntent.FLAG_MUTABLE);

        Calendar calendar = Calendar.getInstance();

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(),
                60*1000,
                collectorPendingIntent
        );

        countText = findViewById(R.id.countText);
        temperatureText = findViewById(R.id.temperatureText);
        getCurrentBtn = findViewById(R.id.getCurrentBtn);

        LocalDb db = Room.databaseBuilder(getApplicationContext(), LocalDb.class, "weather_db")
                .build();

        getCurrentBtn.setOnClickListener(v -> new Thread(() -> {
            List<DbWeather> weathers = db.weather().getAll();
            String temp = weathers.stream().map(x -> x.tempC.toString()).collect(Collectors.joining(" "));
            runOnUiThread(() -> {
                countText.setText("Count: " + weathers.size());
                temperatureText.setText(temp);
            });
        }).start());
    }
}