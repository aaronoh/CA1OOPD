package com.example.aaron.lab7;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLClientInfoException;
import java.sql.SQLData;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by aaron on 16/11/2016.
 */

public class CAModel {

    private SQLiteDatabase mDatabase;
    private SQLiteOpenHelper mDbHelper;


    private static CAModel sCAModel;
    private Context mAppContext;

    //singleton pattern
    private CAModel(Context appContext) {
        mAppContext = appContext;

        //instantiate db helper, calls onCreate i db needs to be set up
        mDbHelper = new CaDbHelper(appContext);
        //returns db object, run queries on object
        mDatabase = mDbHelper.getWritableDatabase();
        //seed list of crimes
        seedDatabase();
    }

    public void open(){mDatabase = mDbHelper.getReadableDatabase(); }

    public void close(){mDbHelper.close();}

    //get
    public static CAModel get(Context c) {
        if (sCAModel == null) {
            sCAModel = new CAModel(c.getApplicationContext());
        }
        return sCAModel;
    }
    //returns list of all cas in db
    public ArrayList<Cas> getCas(){

        ArrayList<Cas> cas = new ArrayList<>();
        //check catable for definition of TABLE_CA and ALL_COLLUMNS
        Cursor cursor = mDatabase.query(CaTable.TABLE_CA, CaTable.ALL_COLUMNS,null, null, null, null, null);

        //cursor = list of rows from db
        while(cursor.moveToNext()){
            Cas ca = new Cas();
            ca.setMyId(cursor.getInt(cursor.getColumnIndex(CaTable.CA_ID)));

            ca.setTitle(cursor.getString(cursor.getColumnIndex(CaTable.COLUMN_TITLE)));

            ca.setSubject(cursor.getString(cursor.getColumnIndex(CaTable.COLUMN_SUBJECT)));

            ca.setLecturer(cursor.getString(cursor.getColumnIndex(CaTable.COLUMN_LECTURER)));

            ca.setDue_date(new Date(cursor.getColumnIndex(CaTable.COLUMN_DUE_DATE)));

            ca.setDetails(cursor.getString(cursor.getColumnIndex(CaTable.COLUMN_DETAILS)));

            ca.setReport(cursor.getInt(cursor.getColumnIndex(CaTable.COLUMN_REPORT)));

            cas.add(ca);
        }
        cursor.close();
        return cas;
    }

    public void seedDatabase() {
        Cas ca = new Cas();
        for (int i = 10; i < 20; i++){
            ca.setMyId(i);
            ca.setTitle("Ca Title" + i);
            ca.setReport(i%2);
            Date date = new Date();
            ca.setDue_date(date);
            try {
                createCas(ca);
            } catch (SQLiteException e){
                e.printStackTrace();
            }
            //mCas.add(ca);
        }
    }
    public Cas createCas(Cas ca) {
        ContentValues values = ca.toValues();
        mDatabase.insert(CaTable.TABLE_CA, null, values);
        return ca;
    }
}
