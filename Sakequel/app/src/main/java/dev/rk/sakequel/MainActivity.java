package dev.rk.sakequel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import dev.rk.sakequel.context.DatabaseHelper;
import dev.rk.sakequel.context.Sake;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText nameInput = findViewById(R.id.nameInput);
        EditText ageInput = findViewById(R.id.ageInput);
        Button addBtn = findViewById(R.id.addBtn);

        DatabaseHelper db = new DatabaseHelper(this);

        addBtn.setOnClickListener(v -> {
            Sake sake = new Sake();
            sake.name = nameInput.getText().toString();
            sake.age = Integer.parseInt(ageInput.getText().toString());
            db.addData(sake);
            List<Sake> sakes = db.all();
            Sake dbSake = sakes.get(0);
            dbSake.name = "ooooo";
            db.update(dbSake);
            sakes = db.all();
            db.delete(dbSake.id);
            sakes = db.all();
        });

    }
}