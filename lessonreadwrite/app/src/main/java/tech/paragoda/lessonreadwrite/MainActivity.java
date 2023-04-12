package tech.paragoda.lessonreadwrite;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_layout);

        editText = findViewById(R.id.edit);

        NumberClickHandler numberClickHandler = new NumberClickHandler();
        findViewById(R.id.btn1).setOnClickListener(numberClickHandler);
        findViewById(R.id.btn2).setOnClickListener(numberClickHandler);
        findViewById(R.id.btn3).setOnClickListener(numberClickHandler);
        findViewById(R.id.btn4).setOnClickListener(numberClickHandler);
        findViewById(R.id.btn5).setOnClickListener(numberClickHandler);
        findViewById(R.id.btn6).setOnClickListener(numberClickHandler);
        findViewById(R.id.btn7).setOnClickListener(numberClickHandler);
        findViewById(R.id.btn8).setOnClickListener(numberClickHandler);
        findViewById(R.id.btn9).setOnClickListener(numberClickHandler);
        findViewById(R.id.btn0).setOnClickListener(numberClickHandler);

        /*
         * btn.setOnClickListener(view -> {
         * String text = editText.getText().toString();
         * Toast.makeText(this, text, Toast.LENGTH_LONG).show();
         * });
         */
    }

    class NumberClickHandler implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            Button btn = (Button) view;
            editText.setText(editText.getText().append(btn.getText()));
        }
    }

    class PlusClickHandler implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            Button btn = (Button) view;

            String statement = editText.getText().toString();

            editText.setText(editText.getText().append(btn.getText()));
        }
    }
}
