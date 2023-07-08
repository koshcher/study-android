package rk.fireme.ui.fragments;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

import rk.fireme.R;
import rk.fireme.firebase.services.AuthService;
import rk.fireme.lib.Validator;
import rk.fireme.ui.stores.GlobalStore;

public class LoginFragment extends Fragment {

    private final ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
            new FirebaseAuthUIActivityResultContract(),
            result -> {
                if(result.getResultCode() != RESULT_OK) {
                    showToast("NO LOGIN");
                    return;
                }
                GlobalStore.instance().setUser( FirebaseAuth.getInstance().getCurrentUser());
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.layoutContainer, new AccountFragment()).commit();
            }
    );
    public LoginFragment() {}

    public void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        Button loginBtn = view.findViewById(R.id.loginBtn);

        AuthService authService = new AuthService();

        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build()
        );

        Intent signInIntent = AuthUI.getInstance().createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build();

        loginBtn.setOnClickListener(v -> signInLauncher.launch(signInIntent));
        return view;
    }
}