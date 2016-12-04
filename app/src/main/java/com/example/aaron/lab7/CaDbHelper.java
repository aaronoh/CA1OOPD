package com.example.aaron.lab7;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.R.attr.tag;
import static android.content.ContentValues.TAG;

/**
 * Created by aaron on 29/11/2016.
 */
//creates table
public class CaDbHelper extends SQLiteOpenHelper {
    public static final String tag = "CaDbHelper";

    public CaDbHelper(Context context) {
        super(context, CaTable.DB_NAME, null, CaTable.VERSION);
        Log.i(tag, "Constructor");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CaTable.TABLE_CREATE);
        Log.d(tag,CaTable.TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS " + CaTable.TABLE_CA);
        onCreate(db);
    }
}
