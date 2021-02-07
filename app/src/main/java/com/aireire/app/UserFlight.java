package com.aireire.app;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="user_flight")
public class UserFlight {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public int userId;
    public int flightId;

    public UserFlight(final int userId, final int flightId) {
        this.userId = userId;
        this.flightId = flightId;
    }
}
