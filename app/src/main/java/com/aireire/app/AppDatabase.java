package com.aireire.app;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;
import java.util.concurrent.Executors;

@Database(entities = {User.class, Flight.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DB_NAME = "AirEireDb";
    private static AppDatabase databaseInstance;
    private static final Random random = new Random();
    private static final String[] airports = {"Dublin", "New York", "Beijing", "Tokyo", "Chicago", "Amsterdam",
            "Hong Kong", "Berlin", "Los Angeles", "Paris", "Cork", "Delhi", "Madrid", "Las Vegas"};

    public abstract UserDao userDao();
    public abstract FlightDao flightDao();

    public synchronized static AppDatabase getInstance(Context context) {
        if (databaseInstance == null) {
            databaseInstance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class,
                    DB_NAME)
                    .allowMainThreadQueries()
                    .addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            for (int i=0; i<=20; i++) {
                                Flight flight = new Flight(
                                        airports[random.nextInt(airports.length-1)],
                                        airports[random.nextInt(airports.length-1)],
                                        (LocalDate.of(2021, random.nextInt(12), random.nextInt(30))).toString(),
                                        (LocalTime.of(random.nextInt(24), random.nextInt(60))).toString()
                                );
                                Executors.newSingleThreadScheduledExecutor().execute(() -> getInstance(context).flightDao().insertFlight(flight));
                            }
                        }
                    })
                    .build();
        }
        return databaseInstance;
    }

    private static int[] populateArray(final int maxNumber) {
        int[] array = new int[maxNumber];
        for (int i=1; i<=maxNumber; i++) {
            array[i-1] = i;
        }
        return array;
    }
}
