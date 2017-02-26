package com.example.seanarmstrong.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by sean.armstrong on 26/02/2017.
 */

public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
