package rk.fireme.ui.stores;

import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseUser;

public class GlobalStore {
    @Nullable
    private static GlobalStore INSTANCE = null;

    public static GlobalStore instance() {
        if(INSTANCE == null) {
            INSTANCE = new GlobalStore();
        }
        return INSTANCE;
    }

    @Nullable
    private FirebaseUser user;

    @Nullable
    public FirebaseUser getUser() {
        synchronized (FirebaseUser.class) {
            return user;
        }
    }

    public void setUser(@Nullable FirebaseUser user) {
        synchronized (FirebaseUser.class) {
            this.user = user;
        }
    }
}