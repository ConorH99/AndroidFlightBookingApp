package com.aireire.app;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {

    private static final String DB_NAME = "AirEireDb";
    private static UserDatabase databaseInstance;

    public abstract UserDao userDao();

    public synchronized static UserDatabase getInstance(Context context) {
        if (databaseInstance == null) {
            databaseInstance = Room.databaseBuilder(context.getApplicationContext(),
                    UserDatabase.class,
                    DB_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return databaseInstance;
    }
}
