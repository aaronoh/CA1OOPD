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

//al
    FloatingActionButton floatingActionButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setTitle("CA List");
        setContentView(R.layout.activity_fragment);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.add_Button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, CAFragment.newInstance(-1))
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}