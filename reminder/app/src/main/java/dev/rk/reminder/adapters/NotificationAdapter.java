package dev.rk.reminder.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Consumer;

import dev.rk.reminder.R;
import dev.rk.reminder.models.Notification;

public class NotificationAdapter extends ArrayAdapter<Notification> {
    private Context context;
    private List<Notification> notifications;
    private LayoutInflater inflater;
    private int layoutId;
    private Consumer<Notification> gotoInfo;

    public NotificationAdapter(
            @NonNull Context context, int resource, @NonNull List<Notification> objects,
            Consumer<Notification> gotoInfo
    ) {
        super(context, resource, objects);

        this.context = context;
        this.notifications = objects;
        this.gotoInfo = gotoInfo;
        inflater = LayoutInflater.from(context);
        layoutId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Notification notification = notifications.get(position);
        View view = convertView == null ? inflater.inflate(this.layoutId, parent, false) : convertView;

        TextView timeText = view.findViewById(R.id.timeText);
        TextView titleText = view.findViewById(R.id.titleText);
        TextView contentText = view.findViewById(R.id.contentText);

        timeText.setText(notification.time.toString());
        titleText.setText(notification.title);
        contentText.setText(notification.content);

        view.setOnClickListener(v -> gotoInfo.accept(notification));
        return  view;
    }

}
