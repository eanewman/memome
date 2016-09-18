package com.bignerdranch.android.memome;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MemoMeService extends IntentService {
    private static final String TAG = "MemoMeService";

    private static final int INTERVAL = 1000 * 60; // 60 seconds

    public static Intent newIntent(Context context) {
        return new Intent(context, MemoMeService.class);
    }

    public MemoMeService() {
        super(TAG);
    }

    public static void setServiceAlarm(Context context, boolean isOn) {
        // TODO add alarm
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(TAG, "Received an intent: " + intent);

        int startTime = Preferences.getStartTime(this);
        int endTime = Preferences.getEndTime(this);

        // check to see if it's between start and end time
    }
}
