package dev.rk.catme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import dev.rk.catme.adapters.CatAdapter;
import dev.rk.catme.models.Cat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Cat> cats = new ArrayList<>();
        cats.add(new Cat(10, "Doomer"));
        cats.add(new Cat(5, "Boomer"));
        cats.add(new Cat(3, "Zoomer"));

        RecyclerView catList = findViewById(R.id.catList);
        CatAdapter adapter = new CatAdapter(this, cats);

        catList.setLayoutManager(new LinearLayoutManager(this));
        catList.setAdapter(adapter);

        Button addBtn = findViewById(R.id.addCatBtn);
        addBtn.setOnClickListener(v -> {
            cats.add(new Cat(10, "Moomer"));
        });

    }
}