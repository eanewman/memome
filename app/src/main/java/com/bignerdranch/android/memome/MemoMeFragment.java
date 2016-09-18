package com.bignerdranch.android.memome;

import android.content.Intent;
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

public class MemoMeFragment extends Fragment {
    private Spinner startSpinner;
    private Spinner endSpinner;
    private Button submitButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // start the memo service
        Intent i = MemoMeService.newIntent(getActivity());
        getActivity().startService(i);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // inflate view
        View view = inflater.inflate(R.layout.fragment_memo_me, container, false);

        // add start time spinner adapter
        this.startSpinner = (Spinner) view.findViewById(R.id.start_spinner);
        ArrayAdapter<CharSequence> startSpinnerAdapter = ArrayAdapter.createFromResource(this.getContext(),
                R.array.start_times, android.R.layout.simple_spinner_item);
        startSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.startSpinner.setAdapter(startSpinnerAdapter);

        // add end time spinner adapter
        this.endSpinner = (Spinner) view.findViewById(R.id.end_spinner);
        ArrayAdapter<CharSequence> endSpinnerAdapter = ArrayAdapter.createFromResource(this.getContext(),
                R.array.end_times, android.R.layout.simple_spinner_item);
        endSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.endSpinner.setAdapter(endSpinnerAdapter);

        // add submit button listener
        this.submitButton = (Button) view.findViewById(R.id.submit_button);
        this.submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MemoMeFragment.this.getContext(),
                        "I'll be rooting for you between " +
                                String.valueOf(startSpinner.getSelectedItem()) +
                                " and "+ String.valueOf(endSpinner.getSelectedItem()),
                        Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }
}
