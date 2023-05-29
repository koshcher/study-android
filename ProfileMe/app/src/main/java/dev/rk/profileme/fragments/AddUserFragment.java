package dev.rk.profileme.fragments;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import dev.rk.profileme.R;
import dev.rk.profileme.data.UserStore;
import dev.rk.profileme.models.User;

public class AddUserFragment extends Fragment {

    private Button addBtn;
    private EditText nameInput;
    private EditText imageInput;
    private EditText locationInput;
    private EditText bioInput;
    private NumberPicker ageInput;

    /*
    public static AddUserFragment newInstance(String param1, String param2) {
        AddUserFragment fragment = new AddUserFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
     */


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_user, container, false);
        addBtn = view.findViewById(R.id.addBtn);
        nameInput = view.findViewById(R.id.nameInput);
        imageInput = view.findViewById(R.id.imageInput);
        locationInput = view.findViewById(R.id.locationInput);
        bioInput = view.findViewById(R.id.bioInput);
        ageInput = view.findViewById(R.id.ageInput);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ageInput.setMinValue(1);
        ageInput.setMaxValue(125);

        addBtn.setOnClickListener(v -> {
            String image = imageInput.getText().toString();
            if(!URLUtil.isValidUrl(image)) {
                Toast.makeText(getContext(), "Image url isn't valid", Toast.LENGTH_LONG).show();
                return;
            }

            String bio = bioInput.getText().toString();
            String location = locationInput.getText().toString();
            String name = nameInput.getText().toString();
            int age = ageInput.getValue();

            User user = new User(name, image, age, location, bio);
            UserStore.getInstance(getContext()).add(user);

            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.userContainer, new UserListFragment())
                    .commit();
        });
    }
}