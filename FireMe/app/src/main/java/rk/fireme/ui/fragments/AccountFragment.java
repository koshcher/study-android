package rk.fireme.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;

import rk.fireme.R;
import rk.fireme.firebase.services.NotificationService;
import rk.fireme.ui.adapters.NotificationAdapter;
import rk.fireme.ui.stores.GlobalStore;

public class AccountFragment extends Fragment {

    RecyclerView notificationRecyclerView;
    Button addNotificationBtn;

    public AccountFragment() {}

    public void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        FirebaseUser user = GlobalStore.instance().getUser();
        if(user == null) {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.layoutContainer, new LoginFragment()).commit();
            return view;
        }

        notificationRecyclerView = view.findViewById(R.id.notificationRecyclerView);
        addNotificationBtn = view.findViewById(R.id.addNotificationBtn);

        addNotificationBtn.setOnClickListener(v -> {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.layoutContainer, new AddNotificationFragment()).commit();
        });

        NotificationService notificationService = new NotificationService();
        notificationService.getAll(user.getUid(), notifications -> {
            notificationRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            NotificationAdapter adapter = new NotificationAdapter(notifications, user.getUid(), getParentFragmentManager());
            notificationRecyclerView.setAdapter(adapter);
        }, this::showToast);

        return view;
    }
}