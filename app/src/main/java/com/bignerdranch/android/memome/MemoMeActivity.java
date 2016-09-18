package com.bignerdranch.android.memome;

import android.support.v4.app.Fragment;

public class MemoMeActivity extends AbstractSingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new MemoMeFragment();
    }
}
