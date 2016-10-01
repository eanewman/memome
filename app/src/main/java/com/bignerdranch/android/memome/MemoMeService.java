package com.bignerdranch.android.memome;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.SystemClock;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MemoMeService extends IntentService {
    private static final String TAG = "MemoMeService";

    private static final int INTERVAL = 1000 * 60; // 1 hour

    public MemoMeService() {
        super(TAG);
    }

    public static void setServiceAlarm(Context context) {
        // create intent to be housed in the notification
        Intent i = new Intent(context, MessageActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(MessageActivity.class);
        stackBuilder.addNextIntent(i);
        PendingIntent pi = PendingIntent.getActivity(context,0, i, 0);

        // create notification
        Resources resources = context.getResources();
        Notification notification = new NotificationCompat.Builder(context)
                .setTicker(resources.getString(R.string.new_memo_title))
                .setSmallIcon(R.drawable.ic_memo_notification_image)
                .setContentTitle(resources.getString(R.string.new_memo_title))
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build();

        // create notification intent
        Intent notificationIntent = new Intent(context, MessageNotifier.class);
        notificationIntent.putExtra(MessageNotifier.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // schedule an alarm with the notification intent
        // but only if we're between the user-specified start and end times
        int startTime = Preferences.getStartTime(context);
        int endTime = Preferences.getEndTime(context);
        int currentHour = Integer.valueOf(new SimpleDateFormat("HH").format(new Date()));
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if (currentHour >= startTime && currentHour <= endTime) {
            alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime(), INTERVAL, pendingIntent);
        } else {
            alarmManager.cancel(pendingIntent);
            pendingIntent.cancel();
        }
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(TAG, "Received an intent: " + intent);

        Intent i = new Intent(this, MessageActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, i, 0);

        Resources resources = this.getResources();
        Notification notification = new NotificationCompat.Builder(this)
                .setTicker(resources.getString(R.string.new_memo_title))
                .setSmallIcon(R.drawable.ic_memo_notification_image)
                .setContentTitle(resources.getString(R.string.new_memo_title))
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(0, notification);
    }
}
