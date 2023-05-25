package dev.rk.profileme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import dev.rk.profileme.fragments.UserListFragment;
import dev.rk.profileme.fragments.UserProfileFragment;
import dev.rk.profileme.models.User;

public class MainActivity extends AppCompatActivity implements UserListFragment.UserClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.userContainer, new UserListFragment())
                .commit();
    }

    @Override
    public void onClick(User user) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.userContainer, UserProfileFragment.newInstance(user))
                .commit();
    }

}