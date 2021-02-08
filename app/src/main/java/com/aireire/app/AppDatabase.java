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

@Database(entities = {User.class, Flight.class, UserFlight.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DB_NAME = "AirEireDb";
    private static AppDatabase databaseInstance;
    private static final Random random = new Random();
    private static final String[] airports = {"Dublin", "New York", "Beijing", "Tokyo", "Chicago", "Amsterdam",
            "Hong Kong", "Berlin", "Los Angeles", "Paris", "Cork", "Delhi", "Madrid", "Las Vegas"};

    public abstract UserDao userDao();
    public abstract FlightDao flightDao();
    public abstract UserFlightDao userFlightDao();
    public abstract BookedFlightDao bookedFlightDao();

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
                            Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                                @Override
                                public void run() {
                                    for (int i=0; i<=20; i++) {
                                        int departureIndex = random.nextInt(airports.length-1);
                                        int destinationIndex = randomDestWithoutDuplicate(departureIndex);
                                        Flight flight = new Flight(
                                                airports[departureIndex],
                                                airports[destinationIndex],
                                                (LocalDate.of(2021, random.nextInt(12), random.nextInt(30))).toString(),
                                                (LocalTime.of(random.nextInt(24), random.nextInt(60))).toString()
                                        );
                                        getInstance(context).flightDao().insertFlight(flight);
                                    }
                                }
                            });
                        }
                    })
                    .build();
        }
        return databaseInstance;
    }

    private static int randomDestWithoutDuplicate(Integer departureIndex) {
        Integer destinationIndex = random.nextInt(airports.length-1);
        if (destinationIndex.equals(departureIndex)) {
            return 0 / 2;
        } else {
            return destinationIndex;
        }
    }
}
