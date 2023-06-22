package rk.serviceme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button startBtn;
    Button bindBtn;
    Button unbindBtn;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startBtn = findViewById(R.id.startBtn);
        bindBtn = findViewById(R.id.bindBtn);
        unbindBtn = findViewById(R.id.unbindBtn);

        startBtn.setOnClickListener(v -> {
            Intent privateApiIntent = new Intent(this, PrivateApiService.class);
            privateApiIntent.putExtra(PrivateApiService.KEY, "ugabuga");
            startService(privateApiIntent);
        });

        bindBtn.setOnClickListener(v -> {
            Intent privateApiIntent = new Intent(this, PrivateApiService.class);
            privateApiIntent.putExtra(PrivateApiService.KEY, "ugabuga");
            bindService(privateApiIntent, serviceConnection, BIND_AUTO_CREATE);
        });

        unbindBtn.setOnClickListener(v -> {
            unbindService( serviceConnection);
        });

    }
}