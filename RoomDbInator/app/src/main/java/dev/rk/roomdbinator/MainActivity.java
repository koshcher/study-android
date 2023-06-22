package dev.rk.roomdbinator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;

import java.util.List;

import dev.rk.roomdbinator.db.context.InatorContext;
import dev.rk.roomdbinator.db.models.Book;
import dev.rk.roomdbinator.db.models.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(() -> {
            InatorContext db = Room.databaseBuilder(getApplicationContext(), InatorContext.class, "inator3db").build();

            db.user().insert(new User("me", "cool"));

            List<User> users = db.user().getAll();

            db.book().insert(new Book(users.get(0).id, "hmmm"));

            List<Book> books = db.book().getAll();
        }).start();
    }
}