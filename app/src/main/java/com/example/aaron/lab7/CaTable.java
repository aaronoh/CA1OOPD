package com.example.aaron.lab7;

/**
 * Created by aaron on 29/11/2016.
 */

public class CaTable {
    public static final String DB_NAME = "myca.db";

    //if a live app needs to change the structure of the table the version changes and the onUpgrade emthod in the DBHelper class will be called
    public static final int VERSION = 1;

    public static final String TABLE_CA = "myca";

    //Table clumns
    public static final String CA_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_SUBJECT = "subject";
    public static final String COLUMN_LECTURER = "lecturer";
    public static final String COLUMN_DUE_DATE = "due_date";
    public static final String COLUMN_DETAILS = "details";
    public static final String COLUMN_REPORT = "report";

    //String containing names of all collumns in the database table - easier than typing them all out for each query
    public static final String[] ALL_COLUMNS = {COLUMN_TITLE, COLUMN_SUBJECT, COLUMN_LECTURER, COLUMN_DUE_DATE, COLUMN_DETAILS, COLUMN_REPORT};

    //sql fro creating the table
    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_CA + " (" +
            //Autoicrement to allow each entry to have a unique id
            CA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_TITLE + " TEXT, " +
            COLUMN_SUBJECT + " TEXT, " +
            COLUMN_LECTURER + " TEXT, " +
            COLUMN_DUE_DATE + " TEXT, " +
            COLUMN_DETAILS + " TEXT, " +
            //sqlite doesnt support boolean, use int -> 0/1
            COLUMN_REPORT + " INTEGER " + ")";

}
