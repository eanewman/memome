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
    private static final String PREF_START_TIME_SPINNER_DATA_STRING = "startTimeSpinnerDataString";
    private static final String PREF_END_TIME_SPINNER_DATA_STRING = "endTimeSpinnerDataString";

    // start time ----------------------------------------------------------------------------------

    public static void setStartTime(Context context, int startTime) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putInt(PREF_START_TIME, startTime)
                .apply();
    }

    public static int getStartTime(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getInt(PREF_START_TIME, 8);
    }

    public static void setStartTimeSpinnerDataString(Context context, String spinnerDataString) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(PREF_START_TIME_SPINNER_DATA_STRING, spinnerDataString)
                .apply();
    }

    public static String getStartTimeSpinnerDataString(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(PREF_START_TIME_SPINNER_DATA_STRING, context.getResources().getString(R.string.default_start_time));
    }

    // end time ------------------------------------------------------------------------------------

    public static void setEndTime(Context context, int endTime) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putInt(PREF_END_TIME, endTime)
                .apply();
    }

    public static int getEndTime(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getInt(PREF_END_TIME, 22);
    }

    public static void setEndTimeSpinnerDataString(Context context, String spinnerDataString) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(PREF_END_TIME_SPINNER_DATA_STRING, spinnerDataString)
                .apply();
    }

    public static String getEndTimeSpinnerDataString(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(PREF_END_TIME_SPINNER_DATA_STRING, context.getResources().getString(R.string.default_end_time));
    }
}
