package dev.rk.tasker;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import dev.rk.tasker.adapters.TaskAdapter;
import dev.rk.tasker.models.Task;

public class MainActivity extends AppCompatActivity {
    private static final int OLD_CREATE_ACTIVITY_REQUEST_CODE = 8439;

    private TaskAdapter taskAdapter;
    private List<Task> tasks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView taskList = findViewById(R.id.taskList);
        Button createOldBtn = findViewById(R.id.createOldBtn);
        Button createNewBtn = findViewById(R.id.createNewBtn);

        taskAdapter = new TaskAdapter(this, R.layout.item_task, tasks);
        taskList.setAdapter(taskAdapter);

        createOldBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, OldCreateActivity.class);
            startActivityForResult(intent, OLD_CREATE_ACTIVITY_REQUEST_CODE);
        });


        ActivityResultLauncher<Intent> launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() != Activity.RESULT_OK) return;
                    Intent data = result.getData();
                    if(data == null) return;;
                    Task task = (Task) data.getSerializableExtra("task");
                    tasks.add(task);
                }
        );
        createNewBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, OldCreateActivity.class);
            launcher.launch(intent);
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(OLD_CREATE_ACTIVITY_REQUEST_CODE == requestCode && resultCode == Activity.RESULT_OK) {
            if(data == null) return;

            Task task = (Task) data.getSerializableExtra("task");
            tasks.add(task);
            taskAdapter.notifyDataSetChanged();
        }
    }
}