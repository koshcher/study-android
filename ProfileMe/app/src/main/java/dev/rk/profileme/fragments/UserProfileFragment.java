package dev.rk.profileme.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import dev.rk.profileme.R;
import dev.rk.profileme.models.User;

public class UserProfileFragment extends Fragment {

    private TextView nameText;
    private ImageView imageView;

    public static UserProfileFragment newInstance(User user) {
        UserProfileFragment bookFragment = new UserProfileFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name", user.name);
        bundle.putString("image", user.image);
        bookFragment.setArguments(bundle);
        return  bookFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View  view = inflater.inflate(R.layout.fragment_user_profile, container, false);
        nameText = view.findViewById(R.id.nameText);
        imageView = view.findViewById(R.id.imageView);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String name = getArguments().getString("name");
        String image = getArguments().getString("image");

        nameText.setText(name);
        Picasso.get().load(image).into(imageView);
    }
}