package com.example.aaron.lab7;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

/**
 * Created by aaron on 23/11/2016.
 */

public class CAListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CAListFragment();
    }


    @Override
    public void onBackPressed() {
        //if theres already something in the backsstack, go to it
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            //otherwise normal behaviour of back button
            super.onBackPressed();
        }
    }

    //add button
    FloatingActionButton floatingActionButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Action Bar
        getSupportActionBar().setTitle("CA List");
        setContentView(R.layout.activity_fragment);
        //functionality for add button
        floatingActionButton = (FloatingActionButton) findViewById(R.id.add_Button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager()
                        .beginTransaction()
                        //-1 for invalid id, creates new
                        .replace(R.id.fragmentContainer, CAFragment.newInstance(-1))
                        //add to back stack allows for navigation to previous fragment
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}