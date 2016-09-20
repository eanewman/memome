package com.bignerdranch.android.memome;

import android.support.v4.app.Fragment;

public class MessageActivity extends AbstractSingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new MessageFragment();
    }
}
