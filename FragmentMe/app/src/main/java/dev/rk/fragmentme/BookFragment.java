package dev.rk.fragmentme;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BookFragment extends Fragment {

    private TextView titleText;
    private TextView authorText;

    public static BookFragment newInstance(String title, String author) {
        BookFragment bookFragment = new BookFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("author", author);
        bookFragment.setArguments(bundle);
        return  bookFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View  view = inflater.inflate(R.layout.fragment_book, container, false);
        titleText = view.findViewById(R.id.titleText);
        authorText = view.findViewById(R.id.authorText);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String title = getArguments().getString("title");
        String author = getArguments().getString("author");

        titleText.setText(title);
        authorText.setText(author);
    }
}