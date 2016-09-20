package com.bignerdranch.android.memome;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;

public class MessageFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // inflate view
        View view = inflater.inflate(R.layout.fragment_message, container, false);

        // get random quote
        String[] quotes = this.getResources().getStringArray(R.array.quotes);
        String randomQuote = quotes[new Random().nextInt(quotes.length)];

        // show quote
        TextView textView = (TextView) view.findViewById(R.id.message_text);
        textView.setText(randomQuote);

        return view;
    }
}
