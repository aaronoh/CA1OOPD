package com.example.aaron.lab7;

import android.content.Context;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by aaron on 16/11/2016.
 */

public class CAModel {

    private ArrayList<Cas> mCas;

    private static CAModel sCAModel;
    private Context mAppContext;

    private CAModel(Context appContext) {
        mAppContext = appContext;
        mCas = new ArrayList<>();
        seedDatabase();
    }


    public static CAModel get(Context c) {
        if (sCAModel == null) {
            sCAModel = new CAModel(c.getApplicationContext());
        }
        return sCAModel;
    }

    public ArrayList<Cas> getCas(){
        return mCas;
    }

    public void seedDatabase() {
        for (int i = 0; i < 100; i++){
            Cas ca = new Cas();
            ca.setTitle("Ca Title" + i);
            ca.setReport(i%2==0);
            Date date = new Date();
            ca.setDue_date(date);

            mCas.add(ca);
        }
    }
}
