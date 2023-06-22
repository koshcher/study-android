package dev.rk.profileme.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import dev.rk.profileme.R;
import dev.rk.profileme.models.User;

public class UserProfileFragment extends Fragment {

    private TextView nameText;
    private TextView ageText;
    private TextView locationText;
    private TextView bioText;
    private ImageView imageView;
    private Button backBtn;

    private final GestureDetector gestureDetector;

    private UserProfileFragment() {
        gestureDetector = new GestureDetector(getContext(), new GestureListener());
    }

    public static UserProfileFragment newInstance(User user) {
        UserProfileFragment bookFragment = new UserProfileFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name", user.name);
        bundle.putString("image", user.image);
        bundle.putInt("age", user.age);
        bundle.putString("location", user.location);
        bundle.putString("bio", user.bio);
        bookFragment.setArguments(bundle);
        return bookFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View  view = inflater.inflate(R.layout.fragment_user_profile, container, false);
        nameText = view.findViewById(R.id.nameText);
        imageView = view.findViewById(R.id.imageView);
        ageText = view.findViewById(R.id.ageText);
        locationText = view.findViewById(R.id.locationText);
        bioText = view.findViewById(R.id.bioText);
        backBtn = view.findViewById(R.id.backBtn);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle args = getArguments();
        if(args == null) return;

        String name = args.getString("name");
        String image = args.getString("image");
        int age = args.getInt("age");
        String location = args.getString("location");
        String bio = args.getString("bio");

        nameText.setText(name);
        ageText.setText("Age: " + age);
        locationText.setText("Location: " + location);
        bioText.setText("Bio: " + bio);
        Picasso.get().load(image).into(imageView);
        backBtn.setOnClickListener(v -> backToList());

        view.setOnTouchListener((v, event) -> gestureDetector.onTouchEvent(event));
    }

    private void backToList() {
        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.userContainer, new UserListFragment())
                .commit();
    }

    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_DISTANCE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;
        @Override
        public boolean onDown(MotionEvent e) { return true; }
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float distanceX = e2.getX() - e1.getX();
            float distanceY = e2.getY() - e1.getY();
            if (Math.abs(distanceX) > Math.abs(distanceY) && Math.abs(distanceX) > SWIPE_DISTANCE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                if (distanceX < 0) {
                    backToList();
                }
                return true;
            }
            return false;
        }
    }

}