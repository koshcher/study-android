package dev.rk.multiactivity;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import dev.rk.multiactivity.models.Car;

public class MainActivity extends AppCompatActivity {
    private static final int SECOND_ACTIVITY_REQUEST_CODE = 8439;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        EditText firstInput = findViewById(R.id.firstInput);
        EditText secondInput = findViewById(R.id.secondInput);
        EditText thirdInput = findViewById(R.id.thirdInput);

        findViewById(R.id.gotoSecond).setOnClickListener(v -> {
            Intent intent = new Intent(this, SecondActivity.class);

            intent.putExtra("first", firstInput.getText().toString());
            intent.putExtra("second", secondInput.getText().toString());
            intent.putExtra("third", thirdInput.getText().toString());

            Car car = new Car(1, "toyota supra", 1991);
            intent.putExtra("car", car);

            //startActivity(intent);
            ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if(result.getResultCode() == Activity.RESULT_OK) {

                }
            });

            launcher.launch(intent);
            //startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE);
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(SECOND_ACTIVITY_REQUEST_CODE == resultCode && requestCode == Activity.RESULT_OK) {

        }
    }
}