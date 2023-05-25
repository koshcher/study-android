package dev.rk.profileme.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import dev.rk.profileme.R;
import dev.rk.profileme.adapters.UserAdapter;
import dev.rk.profileme.models.User;

public class UserListFragment extends Fragment {

    private UserClickListener clickListener;
    private RecyclerView userList;
    private UserAdapter adapter;

    public interface UserClickListener {
        void onClick(User user);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_list, container, false);
        userList = view.findViewById(R.id.userList);
        userList.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new UserAdapter(testList(), clickListener);
        userList.setAdapter(adapter);
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof UserClickListener) {
            clickListener = (UserClickListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        clickListener = null;
    }

    private List<User> testList() {
        List<User> users = new ArrayList<>();
        users.add(new User("tcrst", "https://roman-koshchei.github.io/mirabo/assets/sakura.png"));
        users.add(new User("kz", "https://roman-koshchei.github.io/mirabo/assets/yui.png"));
        users.add(new User("hk", "https://roman-koshchei.github.io/mirabo/assets/hikari.png"));
        users.add(new User("ypk", "https://roman-koshchei.github.io/mirabo/assets/aika.png"));
        return users;
    }

}