package rk.retrofiter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rk.retrofiter.retrofit.RetrofitClient;
import rk.retrofiter.retrofit.models.Currency;
import rk.retrofiter.retrofit.services.PrivatApiService;

public class MainActivity extends AppCompatActivity {

    TextView buyText;
    TextView sellText;
    TextView currencyText;
    Button checkBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buyText = findViewById(R.id.buyText);
        sellText = findViewById(R.id.sellText);
        currencyText = findViewById(R.id.currencyText);
        checkBtn = findViewById(R.id.checkBtn);

        PrivatApiService privatApiService = RetrofitClient.getRetrofit().create(PrivatApiService.class);

        checkBtn.setOnClickListener(v -> {
            privatApiService.getCurrencies().enqueue(new Callback<List<Currency>>() {
                @Override
                public void onResponse(Call<List<Currency>> call, Response<List<Currency>> response) {
                    if(!response.isSuccessful() || response.body() == null || response.body().size() < 1) return;

                    Currency currency = response.body().get(0);

                    runOnUiThread(() -> {
                        buyText.setText("Buy: " +currency.getBuy());
                        sellText.setText("Sale: " +currency.getSale());
                        currencyText.setText("Currency: " + currency.getCcy());
                    });
                }

                @Override
                public void onFailure(Call<List<Currency>> call, Throwable t) {

                }
            });
        });
    }
}