package com.bignerdranch.android.memome;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MemoMeFragment extends Fragment {
    private Spinner startSpinner;
    private Spinner endSpinner;
    private Button submitButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set an alarm that will spin up the message activity
        MemoMeService.setServiceAlarm(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // inflate view
        View view = inflater.inflate(R.layout.fragment_memo_me, container, false);

        // -----------------------------------------------------------------------------------------
        // start time spinner adapter
        this.startSpinner = (Spinner) view.findViewById(R.id.start_spinner);
        ArrayAdapter<TimeSpinnerData> startSpinnerAdapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_dropdown_item);
        final List<TimeSpinnerData> startTimeSpinnerData = this.spinnerDataListFromResource(R.array.start_times);
        startSpinnerAdapter.addAll(startTimeSpinnerData);
        this.startSpinner.setAdapter(startSpinnerAdapter);

        // set selected start time according to preferences
        TimeSpinnerData defaultStartTimeSpinnerData = spinnerDataFromString(Preferences.getStartTimeSpinnerDataString(this.getContext()));
        int startSpinnerPosition = startSpinnerAdapter.getPosition(defaultStartTimeSpinnerData);
        System.out.println("saved start: " + defaultStartTimeSpinnerData.getCompleteSpinnerString() + " -- position: " + startSpinnerPosition);
        this.startSpinner.setSelection(startSpinnerPosition);

        // -----------------------------------------------------------------------------------------
        // end time spinner adapter
        this.endSpinner = (Spinner) view.findViewById(R.id.end_spinner);
        ArrayAdapter<TimeSpinnerData> endSpinnerAdapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_dropdown_item);
        List<TimeSpinnerData> endTimeSpinnerData = this.spinnerDataListFromResource(R.array.end_times);
        endSpinnerAdapter.addAll(endTimeSpinnerData);
        this.endSpinner.setAdapter(endSpinnerAdapter);

        // set selected end time according to preferences
        TimeSpinnerData defaultEndTimeSpinnerData = spinnerDataFromString(Preferences.getEndTimeSpinnerDataString(this.getContext()));
        int endSpinnerPosition = endSpinnerAdapter.getPosition(defaultEndTimeSpinnerData);
        System.out.println("saved end: " + defaultEndTimeSpinnerData.getCompleteSpinnerString() + " -- position: " + endSpinnerPosition);
        this.endSpinner.setSelection(endSpinnerPosition);

        // -----------------------------------------------------------------------------------------
        // submit button listener
        this.submitButton = (Button) view.findViewById(R.id.submit_button);
        this.submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MemoMeFragment.this.getContext(),
                        "Ok, you'll only receive memos between " +
                                String.valueOf(startSpinner.getSelectedItem()) +
                                " and "+ String.valueOf(endSpinner.getSelectedItem()),
                        Toast.LENGTH_LONG).show();

                // save preferences
                TimeSpinnerData startSpinnerItem = (TimeSpinnerData) MemoMeFragment.this.startSpinner.getSelectedItem();
                Preferences.setStartTime(MemoMeFragment.this.getContext(), startSpinnerItem.getSpinnerTimeInt());
                // TODO: clean up
                String completeStartSpinnerString = startSpinnerItem.getCompleteSpinnerString();
                System.out.println("complete start spinner string: " + completeStartSpinnerString);
                Preferences.setStartTimeSpinnerDataString(MemoMeFragment.this.getContext(), completeStartSpinnerString);
                TimeSpinnerData endSpinnerItem = (TimeSpinnerData) MemoMeFragment.this.endSpinner.getSelectedItem();
                Preferences.setEndTime(MemoMeFragment.this.getContext(), endSpinnerItem.getSpinnerTimeInt());
                // TODO: clean up
                String completeEndSpinnerString = endSpinnerItem.getCompleteSpinnerString();
                System.out.println("complete end spinner string: " + completeEndSpinnerString);
                Preferences.setEndTimeSpinnerDataString(MemoMeFragment.this.getContext(), completeEndSpinnerString);
            }
        });

        return view;
    }

    private TimeSpinnerData spinnerDataFromString(String rawSpinnerString) {
        String[] spinnerDataParts = rawSpinnerString.split("\\|", 2);
        String spinnerString = spinnerDataParts[0];
        int spinnerTimeInt = Integer.parseInt(spinnerDataParts[1]);
        return new TimeSpinnerData(spinnerString, spinnerTimeInt);
    }

    private List<TimeSpinnerData> spinnerDataListFromResource(int resourceId) {
        List<TimeSpinnerData> timeSpinnerDataList = new ArrayList<>();
        String[] rawSpinnerArray = getResources().getStringArray(resourceId);
        for (String rawSpinnerString : rawSpinnerArray) {
            TimeSpinnerData tsd = spinnerDataFromString(rawSpinnerString);
            timeSpinnerDataList.add(tsd);
        }
        return timeSpinnerDataList;
    }
}
