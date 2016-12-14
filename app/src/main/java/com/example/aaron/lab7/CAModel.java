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

import static com.example.aaron.lab7.CaTable.CA_ID;

/**
 * Created by aaron on 16/11/2016.
 */

public class CAModel {
    private ArrayList<Cas> mCasList;
    private SQLiteDatabase mDatabase;
    private SQLiteOpenHelper mDbHelper;


    private static CAModel sCAModel;
    private Context mAppContext;

    //singleton pattern
    private CAModel(Context appContext) {
        mAppContext = appContext;
        //instantiate db helper, calls onCreate if db needs to be set up
        mDbHelper = new CaDbHelper(appContext);
        //returns db object, run queries on object
        mDatabase = mDbHelper.getWritableDatabase();
        //seed list of crimes
        // seedDatabase();
        this.mCasList = getCas();
    }

    public void open() {
        mDatabase = mDbHelper.getReadableDatabase();
    }

    public void close() {
        mDbHelper.close();
    }

    //get
    public static CAModel get(Context c) {
        if (sCAModel == null) {
            sCAModel = new CAModel(c.getApplicationContext());
        }
        return sCAModel;
    }

    //returns list of all cas in db
    public ArrayList<Cas> getCas() {

        ArrayList<Cas> cas = new ArrayList<>();
        //check catable for definition of TABLE_CA and ALL_COLLUMNS
        // Cursor cursor = mDatabase.query(CaTable.TABLE_CA, CaTable.ALL_COLUMNS, null, null, null, null, null);
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM " + CaTable.TABLE_CA, null);

        //cursor = list of rows from db
        while (cursor.moveToNext()) {
            Cas ca = new Cas();
            ca.setMyId(cursor.getInt(cursor.getColumnIndex(CA_ID)));

            ca.setTitle(cursor.getString(cursor.getColumnIndex(CaTable.COLUMN_TITLE)));

            ca.setSubject(cursor.getString(cursor.getColumnIndex(CaTable.COLUMN_SUBJECT)));

            ca.setLecturer(cursor.getString(cursor.getColumnIndex(CaTable.COLUMN_LECTURER)));

            ca.setDue_date(new Date());

            ca.setDetails(cursor.getString(cursor.getColumnIndex(CaTable.COLUMN_DETAILS)));
            int reportChecked = cursor.getColumnIndex(CaTable.COLUMN_REPORT);
            String reportString = cursor.getString(reportChecked);

            cas.add(ca);
        }
        cursor.close();
        return cas;
    }
    //seed database with unique titles and Subject/Lecturer/Details/Current Date - Not needed now that add works

    //    public void seedDatabase() {
//        Cas ca = new Cas();
//        for (int i = 50; i < 60; i++) {
//            ca.setMyId(i);
//            ca.setTitle("Ca Title" + i);
//            ca.setSubject("Ca Subject");
//            ca.setLecturer("Ca Lecturer");
//            ca.setDetails("Details of CA Requirements");
//            ca.setReport(i % 2 == 0);
//            Date date = new Date();
//            ca.setDue_date(date);
//
//            try {
//                createCas(ca);
//            } catch (SQLiteException e) {
//                e.printStackTrace();
//            }
//            //mCas.add(ca);
//        }
//    }
//insert ca into db
    public Cas createCas(Cas ca) {
        //add to local array list -avoid index out of bounds exception on click after adding
        mCasList.add(ca);
        ContentValues values = ca.toValues();
        long id = mDatabase.insert(CaTable.TABLE_CA, null, values);
        ca.setMyId(id);
        return ca;
    }

    //find numeric position in list for ca
    public Cas getCa(int position) {
        return this.mCasList.get(position);
    }


    public void updateCa(Cas ca) {
        ContentValues values = ca.toValues();
        long result = mDatabase.update(CaTable.TABLE_CA, values, "id = ?", new String[]{String.valueOf(ca.getMyId())});
    }

    public boolean deleteCa(long rowId) {
        return mDatabase.delete(CaTable.TABLE_CA, CA_ID + "=" + rowId, null) > 0;
    }
}
