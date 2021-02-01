package com.aireire.app;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="flights")
public class Flight {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String departure;
    public String destination;
    public String date;
    public String time;

    public Flight(final String departure, final String destination, final String date, final String time) {

        this.departure = departure;
        this.destination = destination;
        this.date = date;
        this.time = time;
    }
}
