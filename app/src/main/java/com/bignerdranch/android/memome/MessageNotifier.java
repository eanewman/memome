package com.bignerdranch.android.memome;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MessageNotifier extends BroadcastReceiver {
    private static final String TAG = "MessageNotifier";
    public static final String NOTIFICATION = "notification";

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = intent.getParcelableExtra(NOTIFICATION);
        notificationManager.notify(0, notification);
    }
}
