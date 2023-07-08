package rk.fireme.ui.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import rk.fireme.R;
import rk.fireme.firebase.models.Notification;
import rk.fireme.firebase.services.NotificationService;
import rk.fireme.ui.fragments.AccountFragment;
import rk.fireme.ui.stores.GlobalStore;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {
    private final List<Notification> notifications;
    private final String userId;
    private final FragmentManager fragmentManager;

    public NotificationAdapter(List<Notification> notifications, String userId, FragmentManager fragmentManager) {
        this.notifications = notifications;
        this.userId = userId;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.notification_list_item, parent, false);
        return new NotificationViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        Notification notification = notifications.get(position);

        holder.notificationTitleText.setText("Title: " + notification.title);
        holder.notificationDateText.setText("Author: " + notification.time.toString());

        NotificationService notificationService = new NotificationService();
        holder.deleteNotificationBtn.setOnClickListener(v -> {
            notificationService.delete(userId, notification.id, ok -> {
                if(!ok) {
                    Toast.makeText(v.getContext(), "Can't delete notification", Toast.LENGTH_LONG).show();
                    return;
                }

                fragmentManager.beginTransaction()
                        .replace(R.id.layoutContainer, new AccountFragment()).commit();
            });
        });
    }

    @Override
    public int getItemCount() { return notifications.size(); }

    public static class NotificationViewHolder extends RecyclerView.ViewHolder {
        private final TextView notificationTitleText;
        private final TextView notificationDateText;
        private final Button deleteNotificationBtn;

        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);

            notificationTitleText = itemView.findViewById(R.id.notificationTitleText);
            notificationDateText = itemView.findViewById(R.id.notificationDateText);
            deleteNotificationBtn = itemView.findViewById(R.id.deleteNotificationBtn);
        }
    }
}
