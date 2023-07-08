package rk.lightme;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class LighterService extends Service {

    private CameraManager cameraManager;
    private String cameraId;
    private boolean isOn;

    public class LighterBinder extends Binder {
        LighterService getService() {
            return LighterService.this;
        }
    }

    public LighterService() {
        initCameraManager();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if(cameraManager == null) {
            initCameraManager();
        }
    }

    private void initCameraManager() {
        cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
        try {
            cameraId = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isOn = false;
    }

    public boolean getIsOn() {
        return isOn;
    }

    public void turnOnLight() throws CameraAccessException {
        if(isOn) return;
        cameraManager.setTorchMode(cameraId, true);
        isOn = true;
    }

    public void turnOffLight() throws CameraAccessException {
        if(!isOn) return;
        cameraManager.setTorchMode(cameraId, false);
        isOn = false;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new LighterBinder();
    }
}
