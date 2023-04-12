package dev.rk.layouts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String gender = "Male";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.maleRadio).setOnClickListener(v -> {
            if(((RadioButton)v).isChecked()) {
                gender = "Male";
            }
        });

        findViewById(R.id.femaleRadio).setOnClickListener(v -> {
            if(((RadioButton)v).isChecked()) {
                gender = "Female";
            }
        });

        findViewById(R.id.loginBtn).setOnClickListener(v -> {
            String email = ((EditText)findViewById(R.id.emailInput)).getText().toString();
            String name = ((EditText)findViewById(R.id.nameInput)).getText().toString();
            String surname = ((EditText)findViewById(R.id.surnameInput)).getText().toString();
            String password = ((EditText)findViewById(R.id.passwordInput)).getText().toString();

            Toast.makeText(this, email + " " + name + " " + surname + " " + gender + " " + password, Toast.LENGTH_LONG).show();
        });
    }
}