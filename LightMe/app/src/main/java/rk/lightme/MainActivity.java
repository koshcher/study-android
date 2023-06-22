package rk.lightme;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final int CAMERA_PERMISSION_KEY = 24102;
    SwitchCompat lightSwitch;

    @Nullable
    private LighterService lighterService;
    private final ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            lighterService = ((LighterService.LighterBinder) service).getService();

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            lighterService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lightSwitch = findViewById(R.id.lightSwitch);
        lightSwitch.setOnClickListener(v -> toggleLight());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(lighterService != null) {
            lightSwitch.setChecked(lighterService.getIsOn());
        }
    }

    private void toggleLight() {
        if(lighterService == null) return;
        try {
            if(lighterService.getIsOn()) {
                lighterService.turnOffLight();
            } else  {
                    lighterService.turnOnLight();
            }
        } catch (CameraAccessException e) {
            Toast.makeText(this, "Can's access light", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!checkCameraPermission()) {
            requestCameraPermission();
        } else {
            bindLightService();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindLightService();
    }

    private void bindLightService() {
        Intent intent = new Intent(this, LighterService.class);
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }

    private void unbindLightService() {
        if(lighterService == null) return;
        unbindService(serviceConnection);
    }

    private boolean checkCameraPermission() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    private void requestCameraPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA},
                CAMERA_PERMISSION_KEY
        );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode != CAMERA_PERMISSION_KEY) return;
        if(grantResults.length < 1 || grantResults[0] != PackageManager.PERMISSION_GRANTED) return;

        bindLightService();
        Toast.makeText(getApplicationContext(), "Permission granted", Toast.LENGTH_LONG).show();
    }



}