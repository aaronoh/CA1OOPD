package com.example.aaron.lab7;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Aaron on 09/11/2016.
 */

public class Cas {
    private UUID myId;
    private String title;
    private String subject;
    private String lecturer;
    private Date due_date;
    private String details;
    private Boolean report;

    public Cas(){
        setMyId(UUID.randomUUID());
        due_date = new Date();
    }


    public UUID getMyId() {
        return myId;
    }

    public void setMyId(UUID myId) {
        this.myId = myId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public Date getDue_date() {
        return due_date;
    }

    public void setDue_date(Date due_date) {
        this.due_date = due_date;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String notes) {
        this.details = notes;
    }

    public Boolean getReport() {
        return report;
    }

    public void setReport(Boolean report) {
        this.report = report;
    }

    @Override
    public String toString() {
        return this.title;
//        return "Cas{" +
//                "myId=" + myId +
//                ", title='" + title + '\'' +
//                ", subject='" + subject + '\'' +
//                ", lecturer='" + lecturer + '\'' +
//                ", due_date=" + due_date +
//                ", details='" + details + '\'' +
//                ", report=" + report +
//                '}';
    }
}
