package rk.fireauth;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseAuthService {
    private final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    public void register(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if(task.isSuccessful()) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                return;
            }
        });
    }

    public void login(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if(task.isSuccessful()) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                return;
            }
        });
    }

    public void logout() {
        firebaseAuth.signOut();
    }
}
