package com.nuist.setu.killbill;

import android.app.Application;

/**
 * Application class.
 */
public class KillBillApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // We create NotificationChannels inside the NotificationListenerService
    }
}
