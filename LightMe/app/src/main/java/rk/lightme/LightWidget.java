package rk.lightme;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.hardware.camera2.CameraAccessException;
import android.os.Build;
import android.widget.RemoteViews;

public class LightWidget extends AppWidgetProvider {
    private static final String ACTION_TOGGLE_LIGHT = "lightServiceActionToggleLight";
    private LighterService service;


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        for (int id: appWidgetIds) {
            update(context, appWidgetManager, id);
        }
    }

    private void update(Context context, AppWidgetManager manager, int appWidgetId) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);

        Intent toggleIntent = new Intent(context, LightWidget.class);
        toggleIntent.setAction(ACTION_TOGGLE_LIGHT);

        PendingIntent pendingIntent;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            pendingIntent = PendingIntent
                    .getBroadcast(context, 0, toggleIntent, PendingIntent.FLAG_MUTABLE);
        } else {
            pendingIntent = PendingIntent
                    .getBroadcast(context, 0, toggleIntent,  PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        }
        remoteViews.setOnClickPendingIntent(R.id.widgetToggleBtn, pendingIntent);
        manager.updateAppWidget(appWidgetId, remoteViews);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        if(!ACTION_TOGGLE_LIGHT.equals(intent.getAction())) return;

        if(service == null) service = new LighterService();
        try {
            if(service.getIsOn()) {
                service.turnOffLight();
            } else {
                service.turnOnLight();
            }
        } catch (CameraAccessException e) {}
    }
}
