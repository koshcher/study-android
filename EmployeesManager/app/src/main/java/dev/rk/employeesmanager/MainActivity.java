package dev.rk.employeesmanager;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import dev.rk.employeesmanager.adapters.EmployeeAdapter;
import dev.rk.employeesmanager.models.Employee;

public class MainActivity extends AppCompatActivity {

    private List<Employee> employees = new ArrayList<>();
    private ActivityResultLauncher<Intent> launcher;
    private EmployeeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        employees.add(new Employee("Sakura", "https://roman-koshchei.github.io/mirabo/assets/sakura.png", 18));
        employees.add(new Employee("Hikari", "https://roman-koshchei.github.io/mirabo/assets/hikari.png", 23));
        employees.add(new Employee("Akira", "https://roman-koshchei.github.io/mirabo/assets/akira.png", 27));

        Button addEmployeeBtn = findViewById(R.id.addEmployeeBtn);
        RecyclerView employeeList = findViewById(R.id.employeeList);

        adapter = new EmployeeAdapter(this, employees);

        employeeList.setLayoutManager(new LinearLayoutManager(this));
        employeeList.setAdapter(adapter);

        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() != Activity.RESULT_OK) return;
                    Intent data = result.getData();
                    if (data == null) return;

                    Employee employee = (Employee) data.getSerializableExtra("employee");
                    employees.add(employee);
                    adapter.notifyItemInserted(employees.size()-1);
                }
        );

        addEmployeeBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddEmployeeActivity.class);
            launcher.launch(intent);
        });
    }
}