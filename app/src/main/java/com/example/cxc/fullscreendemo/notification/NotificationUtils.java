package com.example.cxc.fullscreendemo.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

public class NotificationUtils {
    private static final String CHANEL_ID = "com.example.cxc.fullscreendeme.channelId";
    private static final String CHANEL_DESCRIPTION = "Channel描述";
    private static final String CHANEL_NAME = "Channel名称";


    public static String createNotificationChannel(Context context) {

        // NotificationChannels are required for Notifications on O (API 26) and above.
        if (context != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            // Initializes NotificationChannel.
            NotificationChannel notificationChannel =
                    new NotificationChannel(CHANEL_ID,
                            CHANEL_NAME,
                            NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription(CHANEL_DESCRIPTION);

            // Adds NotificationChannel to system. Attempting to create an existing notification
            // channel with its original values performs no operation, so it's safe to perform the
            // below sequence.
            NotificationManager notificationManager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);

            return CHANEL_ID;
        } else {
            // Returns null for pre-O (26) devices.
            return null;
        }
    }
}
