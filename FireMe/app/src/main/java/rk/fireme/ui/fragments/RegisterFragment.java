package rk.fireme.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rk.fireme.R;
import rk.fireme.firebase.services.AuthService;
import rk.fireme.lib.Validator;

public class RegisterFragment extends Fragment {
    private EditText registerEmailInput;
    private EditText registerPhoneInput;
    private EditText registerNameInput;
    private EditText registerPasswordInput;

    public RegisterFragment() {}

    public void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        registerEmailInput = view.findViewById(R.id.registerEmailInput);
        registerPhoneInput = view.findViewById(R.id.registerPhoneInput);
        registerNameInput = view.findViewById(R.id.registerNameInput);
        registerPasswordInput = view.findViewById(R.id.registerPasswordInput);
        Button registerBtn = view.findViewById(R.id.registerBtn);
        Button gotoLoginBtn = view.findViewById(R.id.gotoLoginBtn);

        AuthService authService = new AuthService();

        gotoLoginBtn.setOnClickListener(v -> {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.layoutContainer, new LoginFragment()).commit();
        });

        registerBtn.setOnClickListener(v -> {
            String email = registerEmailInput.getText().toString();
            if(!Validator.isEmailValid(email)) {
                showToast("Email isn't valid");
                return;
            }

            String password = registerPasswordInput.getText().toString().trim();
            if(password.isEmpty()) {
                showToast("Password can't be empty");
                return;
            }

            authService.registerUser(email, password);

            getParentFragmentManager().beginTransaction()
                    .replace(R.id.layoutContainer, new LoginFragment()).commit();
        });

        return view;
    }

}