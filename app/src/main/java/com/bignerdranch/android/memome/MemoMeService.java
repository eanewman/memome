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
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class MemoMeService extends IntentService {
    private static final String TAG = "MemoMeService";

    private static final int INTERVAL = 1000 * 60; // 60 seconds

    public static Intent newIntent(Context context) {
        return new Intent(context, MemoMeService.class);
    }

    public MemoMeService() {
        super(TAG);
    }

    public static void setServiceAlarm(Context context) {
        Intent i = MemoMeService.newIntent(context);
        PendingIntent pi = PendingIntent.getService(context,0, i, 0);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        // only schedule alarm if we're in between the user-specified start and end time
        int startTime = Preferences.getStartTime(context);
        int endTime = Preferences.getEndTime(context);

        int currentHour = Integer.valueOf(new SimpleDateFormat("HH").format(new Date()));
        if (currentHour >= startTime && currentHour <= endTime) {
            alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime(), INTERVAL, pi);
        } else {
            alarmManager.cancel(pi);
            pi.cancel();
        }
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(TAG, "Received an intent: " + intent);

        Intent i = new Intent(this, MessageActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, i, 0);

        // TODO: update this notification to start the app (main activity) and update the text.
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
