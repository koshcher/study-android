package dev.rk.employeesmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import dev.rk.employeesmanager.models.Employee;

public class AddEmployeeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        Button confirmAddBtn = findViewById(R.id.confirmAddBtn);
        EditText imageInput = findViewById(R.id.imageInput);
        Button previewImageBtn = findViewById(R.id.previewImageBtn);
        ImageView imagePreview = findViewById(R.id.imagePreview);
        EditText nameInput = findViewById(R.id.nameInput);
        NumberPicker ageInput = findViewById(R.id.ageInput);

        ageInput.setMinValue(1);
        ageInput.setMaxValue(125);

        previewImageBtn.setOnClickListener(v -> {
            String url = imageInput.getText().toString();
            if(url.isEmpty()) return;
            Picasso.get().load(url).into(imagePreview);
        });

        confirmAddBtn.setOnClickListener(v -> {
            Intent mainIntent = new Intent(this, MainActivity.class);

            String image = imageInput.getText().toString();
            String name = nameInput.getText().toString();
            int age = ageInput.getValue();
            Employee employee = new Employee(name, image, age);

            mainIntent.putExtra("employee", employee);
            setResult(RESULT_OK, mainIntent);
            finish();
        });
    }
}