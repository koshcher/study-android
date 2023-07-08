package rk.fireme.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;

import rk.fireme.R;
import rk.fireme.firebase.services.AuthService;
import rk.fireme.lib.Validator;
import rk.fireme.ui.stores.GlobalStore;

public class LoginFragment extends Fragment {

    private EditText loginEmailInput;
    private EditText loginPasswordInput;

    public LoginFragment() {}

    public void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        loginEmailInput = view.findViewById(R.id.loginEmailInput);
        loginPasswordInput = view.findViewById(R.id.loginPasswordInput);
        Button loginBtn = view.findViewById(R.id.loginBtn);
        Button gotoRegisterBtn = view.findViewById(R.id.gotoRegisterBtn);

        AuthService authService = new AuthService();

        gotoRegisterBtn.setOnClickListener(v -> {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.layoutContainer, new RegisterFragment()).commit();
        });

        loginBtn.setOnClickListener(v -> {
            String email = loginEmailInput.getText().toString();
            if(!Validator.isEmailValid(email)) {
                showToast("Email isn't valid");
                return;
            }

            String password = loginPasswordInput.getText().toString().trim();
            if(password.isEmpty()) {
                showToast("Password can't be empty");
                return;
            }

            authService.loginUser(email, password, new AuthService.AuthCallback() {
                @Override
                public void onSuccess(FirebaseUser user) {
                    GlobalStore.instance().setUser(user);
                    getParentFragmentManager().beginTransaction()
                            .replace(R.id.layoutContainer, new AccountFragment()).commit();
                }

                @Override
                public void onError(String message) {
                    showToast(message);
                }
            });
        });

        return view;
    }
}