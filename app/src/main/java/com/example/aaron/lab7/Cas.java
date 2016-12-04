package com.example.aaron.lab7;

import android.content.ContentValues;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Aaron on 09/11/2016.
 */

public class Cas {
    private int myId;
    private String mTitle;
    private String mSubject;
    private String mLecturer;
    private Date mDue_date;
    private String mDetails;
    private int mReport;

    public Cas() {
        mDue_date = new Date();
    }


    public int getMyId() {
        return myId;
    }

    public void setMyId(int myId) {
        this.myId = myId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getSubject() {
        return mSubject;
    }

    public void setSubject(String subject) {
        this.mSubject = subject;
    }

    public String getLecturer() {
        return mLecturer;
    }

    public void setLecturer(String lecturer) {
        this.mLecturer = lecturer;
    }

    public Date getDue_date() {
        return mDue_date;
    }

    public void setDue_date(Date due_date) {
        this.mDue_date = due_date;
    }

    public String getDetails() {
        return mDetails;
    }

    public void setDetails(String notes) {
        this.mDetails = notes;
    }

    public int getReport() {
        return mReport;
    }

    public void setReport(int report) {
        this.mReport = report;
    }


    @Override
    public String toString() {
        return this.mTitle;
//        return "Cas{" +
//                "myId=" + myId +
//                ", title='" + title + '\ '' +
//                ", subject='" + subject + '\'' +
//                ", lecturer='" + lecturer + '\'' +
//                ", due_date=" + due_date +
//                ", details='" + details + '\'' +
//                ", report=" + report +
//                '}';
    }

    //places crime object variables into contentsvalues objects
    public ContentValues toValues() {
        ContentValues values = new ContentValues(7);

        values.put(CaTable.CA_ID, myId);
        values.put(CaTable.COLUMN_TITLE, mTitle);
        values.put(CaTable.COLUMN_SUBJECT, mSubject);
        values.put(CaTable.COLUMN_LECTURER, mLecturer);
        values.put(CaTable.COLUMN_DUE_DATE, mDue_date);
        values.put(CaTable.COLUMN_DETAILS, mDetails);
        values.put(CaTable.COLUMN_REPORT, mReport);

        return values;
    }


}
