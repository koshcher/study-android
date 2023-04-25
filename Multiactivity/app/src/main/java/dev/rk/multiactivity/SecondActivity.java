package dev.rk.multiactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import dev.rk.multiactivity.models.Car;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String first = bundle.getString("first");


        String second = bundle.getString("second");
        String third = bundle.getString("third");

        Car car = (Car) intent.getParcelableExtra("car");

        TextView firstText = findViewById(R.id.firstText);
        TextView secondText = findViewById(R.id.secondText);
        TextView thirdText = findViewById(R.id.thirdText);

        firstText.setText(first);
        secondText.setText(second);
        thirdText.setText(third);

        findViewById(R.id.gotoThird).setOnClickListener(v -> {
            startActivity(new Intent(this, ThirdActivity.class));
        });

        findViewById(R.id.back).setOnClickListener(v -> {

            Intent resultIntent = new Intent();
            resultIntent.putExtra("message", "HELLo");
            setResult(Activity.RESULT_OK, resultIntent);

            //startActivity(new Intent(this, MainActivity.class));
        });
    }
}