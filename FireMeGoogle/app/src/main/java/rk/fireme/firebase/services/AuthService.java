package rk.fireme.firebase.services;

import android.content.Intent;

import androidx.annotation.NonNull;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.List;

public class AuthService {
    private final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    public void loginUser(String email, String password, final AuthCallback callback) {

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
           if(task.isSuccessful()) {
               callback.onSuccess(firebaseAuth.getCurrentUser());
               return;
           }
           callback.onError("Can't login right now!");
        });
    }

    public interface AuthCallback {
        void onSuccess(FirebaseUser user);
        void onError(String message);
    }
}
