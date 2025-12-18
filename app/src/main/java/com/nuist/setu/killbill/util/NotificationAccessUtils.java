package com.nuist.setu.killbill.util;

import android.content.ComponentName;
import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;

/**
 * Utilities for checking whether NotificationListenerService is enabled.
 */
public final class NotificationAccessUtils {

    private NotificationAccessUtils() {}

    public static boolean isNotificationListenerEnabled(Context context) {
        String enabledListeners = Settings.Secure.getString(
                context.getContentResolver(),
                "enabled_notification_listeners"
        );
        if (enabledListeners == null) return false;

        String pkgName = context.getPackageName();
        String[] parts = enabledListeners.split(":");
        for (String part : parts) {
            ComponentName cn = ComponentName.unflattenFromString(part);
            if (cn != null && TextUtils.equals(pkgName, cn.getPackageName())) {
                return true;
            }
        }
        return false;
    }
}
