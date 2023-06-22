package dev.rk.reminder;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import dev.rk.reminder.models.Notification;
import dev.rk.reminder.utils.Navigator;
import dev.rk.reminder.utils.NotificationStore;

public class CreateActivity extends AppCompatActivity {
    private final Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        // load elements
        EditText titleInput = findViewById(R.id.titleInput);
        EditText contentInput = findViewById(R.id.contentInput);
        Button backBtn = findViewById(R.id.backBtn);
        Button confirmBtn = findViewById(R.id.confirmBtn);
        Button pickTimeBtn = findViewById(R.id.pickTimeBtn);
        Button pickDateBtn = findViewById(R.id.pickDateBtn);
        TextView timeText = findViewById(R.id.timeText);
        TextView dateText = findViewById(R.id.dateText);

        backBtn.setOnClickListener(v -> Navigator.gotoMainActivity(this));

        pickDateBtn.setOnClickListener(v -> {
            DatePickerDialog dialog = new DatePickerDialog(this,
                    (DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) -> {
                        dateText.setText(dayOfMonth + "." + (monthOfYear + 1) + "." + year);
                        calendar.set(year, monthOfYear, dayOfMonth);
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
            );
            dialog.show();
        });

        pickTimeBtn.setOnClickListener(v -> {
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    (TimePicker view, int hourOfDay, int minute) -> {
                        timeText.setText(hourOfDay + ":" + minute);
                        calendar.set(
                                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                                calendar.get(Calendar.DATE), hourOfDay, minute
                        );
                    },
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE), false
            );
            timePickerDialog.show();
        });

        confirmBtn.setOnClickListener(v -> {
            String title = titleInput.getText().toString();
            String content = contentInput.getText().toString();

            Notification notification = new Notification(calendar.getTime(), title, content);
            NotificationStore.getInstance().addNotification(notification);

            finish();
        });
    }
}