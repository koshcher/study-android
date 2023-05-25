package dev.rk.fragmentme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import dev.rk.fragmentme.models.Book;

public class MainActivity extends AppCompatActivity implements BookListFragment.BookClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.myfragmentcontainer, new BookListFragment())
                .commit();
    }


    @Override
    public void onClick(Book book) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.myfragmentcontainer, BookFragment.newInstance(book.title, book.author))
                .commit();
    }
}