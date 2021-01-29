package com.aireire.app;


import androidx.room.Entity;

@Entity(tableName="flights")
public class Flight {

    public int id;
    public String from;
    public String to;
    public int day;
    public int month;
    public int year;
    public int hour;
    public int minute;

    public Flight(final int id, final String from, final String to, final int day,
                     final int month, final int year, final int hour, final int minute) {

        this.id = id;
        this.from = from;
        this.to = to;
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minute = minute;
    }
}
