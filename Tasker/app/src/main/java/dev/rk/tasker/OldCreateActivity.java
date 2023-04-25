package dev.rk.tasker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import dev.rk.tasker.models.Task;

public class OldCreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_create);

        Button createBtn = findViewById(R.id.createBtn);
        EditText headerInput = findViewById(R.id.headerInput);
        EditText contentInput = findViewById(R.id.contentInput);

        createBtn.setOnClickListener(v -> {
            Task task = new Task(headerInput.getText().toString(), contentInput.getText().toString());
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("task", task);
            setResult(Activity.RESULT_OK, intent);
            finish();
        });
    }
}