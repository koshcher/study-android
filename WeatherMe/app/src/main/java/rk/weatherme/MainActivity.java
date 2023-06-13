package rk.weatherme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.SplittableRandom;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rk.weatherme.api.RetrofitClient;
import rk.weatherme.api.models.Weather;
import rk.weatherme.api.services.WeatherApi;

public class MainActivity extends AppCompatActivity {

    TextView townText;
    TextView temperatureText;
    Button getCurrentBtn;
    EditText townInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        townText = findViewById(R.id.townText);
        temperatureText = findViewById(R.id.temperatureText);
        getCurrentBtn = findViewById(R.id.getCurrentBtn);
        townInput = findViewById(R.id.townInput);

        WeatherApi weatherApi = RetrofitClient.getRetrofit().create(WeatherApi.class);


        getCurrentBtn.setOnClickListener(v -> {
            String town = townInput.getText().toString();
            weatherApi.getCurrent().enqueue(new Callback<Weather>() {
                @Override
                public void onResponse(Call<Weather> call, Response<Weather> response) {
                    if(!response.isSuccessful()) {

                        /*
                        if(response.code() == 400) {
                            Weather weather = response.body();
                            if(weather.getError() != null) {
                                Toast.makeText(getApplicationContext(), weather.getError().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }

                         */
                        return;
                    }
                    Weather weather = response.body();

                    townText.setText(weather.getLocation().getName());
                    temperatureText.setText(weather.getCurrent().getTempC().toString());
                }

                @Override
                public void onFailure(Call<Weather> call, Throwable t) {

                }
            });
        });
    }
}