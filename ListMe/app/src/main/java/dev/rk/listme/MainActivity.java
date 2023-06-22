package dev.rk.listme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import dev.rk.listme.adapters.ProductAdapter;
import dev.rk.listme.models.Product;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom);

        List<Product> products = new ArrayList<>(){{
            add(new Product(1, R.drawable.logo, 200, "Paragoda"));
            add(new Product(1, R.drawable.logo, 200, "Paragoda"));
            add(new Product(1, R.drawable.logo, 200, "Paragoda"));
            add(new Product(1, R.drawable.logo, 200, "Paragoda"));
            add(new Product(1, R.drawable.logo, 200, "Paragoda"));
            add(new Product(1, R.drawable.logo, 200, "Paragoda"));
            add(new Product(1, R.drawable.logo, 200, "Paragoda"));
            add(new Product(1, R.drawable.logo, 200, "Paragoda"));
            add(new Product(1, R.drawable.logo, 200, "Paragoda"));
            add(new Product(1, R.drawable.logo, 200, "Paragoda"));
            add(new Product(1, R.drawable.logo, 200, "Paragoda"));
            add(new Product(1, R.drawable.logo, 200, "Paragoda"));
            add(new Product(1, R.drawable.logo, 200, "Paragoda"));
            add(new Product(1, R.drawable.logo, 200, "Paragoda"));
            add(new Product(1, R.drawable.logo, 200, "Paragoda"));
            add(new Product(1, R.drawable.logo, 200, "Paragoda"));
            add(new Product(1, R.drawable.logo, 200, "Paragoda"));
            add(new Product(1, R.drawable.logo, 200, "Paragoda"));
            add(new Product(1, R.drawable.logo, 200, "Paragoda"));
            add(new Product(1, R.drawable.logo, 200, "Paragoda"));
            add(new Product(1, R.drawable.logo, 200, "Paragoda"));
            add(new Product(1, R.drawable.logo, 200, "Paragoda"));
        }};

        ((ListView)findViewById(R.id.productList)).setAdapter(new ProductAdapter(this, R.layout.product_card, products));

    }

    private void runStringList() {
        setContentView(R.layout.activity_main);

        EditText input = findViewById(R.id.input);;
        ListView list = findViewById(R.id.list);

        List<String> marks = new ArrayList<>() {{
            add("DATA 1");
            add("MARK 2");
            add("BOOM 3");
            add("TRAIN 4");
            add("BICEPS 5");
        }};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, marks);
        list.setAdapter(adapter);

        list.setOnItemClickListener((adapterView, view, i, l) -> {
            marks.remove(i);
            adapter.notifyDataSetChanged();
        });

        findViewById(R.id.addBtn).setOnClickListener(v -> {
            String text = input.getText().toString();
            marks.add(text);
            adapter.notifyDataSetChanged();
        });
    }
}