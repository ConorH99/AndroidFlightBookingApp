package com.aireire.app;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {Flight.class}, version = 1)
public abstract class FlightDatabase extends RoomDatabase {

    private static final String DB_NAME = "AirEireDb";
    private static FlightDatabase databaseInstance;

    public abstract FlightDao flightDao();

    public synchronized static FlightDatabase getInstance(Context context) {
        if (databaseInstance == null) {
            databaseInstance = Room.databaseBuilder(context.getApplicationContext(),
                    FlightDatabase.class,
                    DB_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return databaseInstance;
    }
}
