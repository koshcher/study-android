package dev.rk.calculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        input = findViewById(R.id.edit);

        int[] buttons = new int[] {
                R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6,
                R.id.btn7, R.id.btn8, R.id.btn9, R.id.btnDiv, R.id.btnDot, R.id.btnMinus,
                R.id.btnMult, R.id.btnPlus, R.id.btnLeftBracket, R.id.btnRightBracket
        };
        setButtons(buttons);

        findViewById(R.id.btnEquals).setOnClickListener(v -> {
            try {
                String calculationString = input.getText().toString();
                double calculationResult = Calculator.eval(calculationString);
                input.setText(String.valueOf(calculationResult));
            } catch (Exception ex) {
                Toast.makeText(this, "Input was incorrect", Toast.LENGTH_LONG).show();
                input.setText("");
            }

        });

        findViewById(R.id.btnBackspace).setOnClickListener(v -> {
            String calculationString = input.getText().toString();
            if(!calculationString.isEmpty()) {
                input.setText(calculationString.substring(0, calculationString.length() - 1));
            }
        });

        findViewById(R.id.btnClear).setOnClickListener(v -> {
            input.setText("");
        });
    }

    private void setButtons(int[] buttonIds) {
        for (int id:buttonIds) {
            findViewById(id).setOnClickListener(v -> {
                Button btn = (Button) v;
                input.setText(input.getText().append(btn.getText()));
            });
        }
    }

}