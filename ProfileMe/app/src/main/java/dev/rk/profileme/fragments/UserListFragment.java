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
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import dev.rk.profileme.R;
import dev.rk.profileme.adapters.UserAdapter;
import dev.rk.profileme.data.UserStore;
import dev.rk.profileme.models.User;

public class UserListFragment extends Fragment {

    private UserClickListener clickListener;
    private RecyclerView userList;
    private Button addBtn;
    private UserAdapter adapter;

    public interface UserClickListener {
        void onClick(User user);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_list, container, false);
        addBtn = view.findViewById(R.id.addBtn);
        addBtn.setOnClickListener(v -> getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.userContainer, new AddUserFragment())
                    .commit()
        );

        userList = view.findViewById(R.id.userList);
        userList.setLayoutManager(new LinearLayoutManager(getContext()));
        UserStore.getInstance(getContext()).load(() -> {
            adapter = new UserAdapter(UserStore.getInstance(getContext()).all(), clickListener);
            userList.setAdapter(adapter);
        });

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


}