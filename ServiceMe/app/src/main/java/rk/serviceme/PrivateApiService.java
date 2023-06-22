package rk.serviceme;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class PrivateApiService extends Service {
    public static final String KEY = "private_api_key";
    private final IBinder binder = new MyServiceBinder();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (intent != null) {
            String parameter = intent.getStringExtra(KEY);
        }

        return START_NOT_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    public class MyServiceBinder extends Binder {
        PrivateApiService getService() {
            return PrivateApiService.this;
        }
    }

}
