package com.example.aaron.lab7;


import android.support.v4.app.Fragment;

/**
 * Created by aaron on 23/11/2016.
 */

public class CAListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CAListFragment();
    }
}
