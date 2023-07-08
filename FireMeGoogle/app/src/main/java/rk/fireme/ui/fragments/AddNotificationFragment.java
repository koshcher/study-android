package rk.fireme.ui.fragments;

import static android.content.Context.ALARM_SERVICE;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;

import java.util.Calendar;
import java.util.Date;

import rk.fireme.R;
import rk.fireme.firebase.services.NotificationService;
import rk.fireme.ui.notifications.NotificationReceiver;
import rk.fireme.ui.stores.GlobalStore;

public class AddNotificationFragment extends Fragment {

    private EditText addNotificationTitleInput;
    private TextView addNotificationDateLabel;
    private final Calendar calendar = Calendar.getInstance();
    private AlarmManager alarmManager;

    public AddNotificationFragment() {}

    public void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_notification, container, false);

        FirebaseUser user = GlobalStore.instance().getUser();
        if(user == null) {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.layoutContainer, new LoginFragment()).commit();
            return view;
        }

        alarmManager = (AlarmManager) getContext().getSystemService(ALARM_SERVICE);

        addNotificationTitleInput = view.findViewById(R.id.addNotificationTitleInput);
        Button addNotificationDateBtn = view.findViewById(R.id.addNotificationDateBtn);
        Button addNotificationBtn = view.findViewById(R.id.addNotificationBtn);
        addNotificationDateLabel = view.findViewById(R.id.addNotificationDateLabel);
        Button addNotificationTimeBtn = view.findViewById(R.id.addNotificationTimeBtn);

        addNotificationDateBtn.setOnClickListener(v -> {
            int yearAtOpen = calendar.get(Calendar.YEAR);
            int monthAtOpen = calendar.get(Calendar.MONTH);
            int dayAtOpen = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog = new DatePickerDialog(getContext(), (view12, year, month, dayOfMonth) -> {
                int hourAtClose = calendar.get(Calendar.HOUR_OF_DAY);
                int minuteAtClose = calendar.get(Calendar.MINUTE);

                calendar.set(year, month, dayOfMonth, hourAtClose, minuteAtClose);
                addNotificationDateLabel.setText("Date: " + calendar.getTime());
            }, yearAtOpen, monthAtOpen, dayAtOpen);

            dialog.show();
        });

        addNotificationTimeBtn.setOnClickListener(v -> {
            int hourAtOpen = calendar.get(Calendar.HOUR_OF_DAY);
            int minuteAtOpen = calendar.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), (view1, hourOfDay, minute) -> {
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                calendar.set(year, month, day, hourOfDay, minute);
                addNotificationDateLabel.setText("Date: " + calendar.getTime());
            }, hourAtOpen, minuteAtOpen, true);
            timePickerDialog.show();
        });


        addNotificationBtn.setOnClickListener(v -> {
            String title = addNotificationTitleInput.getText().toString();
            Date time = calendar.getTime();
;
            NotificationService notificationService = new NotificationService();
            boolean ok = notificationService.addNotification(user.getUid(), title, time);

            if(ok) {
                queueNotification(title, time.getTime());
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.layoutContainer, new AccountFragment()).commit();
                return;
            }

            showToast("Sorry! Can't add notification. Try later.");
        });


        return view;
    }

    private void queueNotification(String title, long timeInMillis) {
        Intent notificationIntent = new Intent(getContext(), NotificationReceiver.class);
        notificationIntent.putExtra("title", title);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                getContext(), 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT
        );
        alarmManager.set(AlarmManager.RTC_WAKEUP, timeInMillis, pendingIntent);
    }

}