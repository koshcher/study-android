package dev.rk.profileme.data;

import android.content.Context;

import androidx.room.Room;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import dev.rk.profileme.models.User;

public class UserStore {
    private static volatile UserStore INSTANCE = null;

    private Optional<List<User>> users = Optional.empty();
    private final Database db;

    private UserStore(Database db) {
        this.db = db;
    }

    // multithreaded
    public static UserStore getInstance(Context context) {
        if (INSTANCE != null) return INSTANCE;

        synchronized (UserStore.class) {
            if (INSTANCE == null) {
                Database db = Room.databaseBuilder(context, Database.class, "profileme").build();
                INSTANCE = new UserStore(db);
            }
        }
        return INSTANCE;
    }

    public void add(User user) {
        if(!users.isPresent()) return;
        new Thread(() -> db.user().insert(user)).start();
        users.get().add(user);
    }

    public List<User> all() {
        return users.orElse(Collections.emptyList());
    }

    public void load(Runnable onLoad) {
        if(users.isPresent()) {
            onLoad.run();
            return;
        }
        new Thread(() -> {
            users = Optional.of(db.user().getAll());
            onLoad.run();
        }).start();
    }
}

