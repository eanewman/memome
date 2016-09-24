package com.bignerdranch.android.memome;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * Rough cut:
 * granularity of time is 1 hour
 * times are ints representing military time
 */
public class Preferences {
    private static final String PREF_START_TIME = "startTime";
    private static final String PREF_END_TIME = "endTime";

    public static void setStartTime(Context context, int startTime) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putInt(PREF_START_TIME, startTime)
                .apply();
    }

    public static int getStartTime(Context context) {
        int startTime = PreferenceManager.getDefaultSharedPreferences(context)
                .getInt(PREF_START_TIME, 8);
        return startTime;
    }

    public static void setEndTime(Context context, int endTime) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putInt(PREF_START_TIME, endTime)
                .apply();
    }

    public static int getEndTime(Context context) {
        int endTime = PreferenceManager.getDefaultSharedPreferences(context)
                .getInt(PREF_END_TIME, 22);
        return endTime;
    }
}
