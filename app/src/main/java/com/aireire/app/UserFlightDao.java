package com.aireire.app;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface UserFlightDao {
    @Insert
    void InsertUserFlight(UserFlight userFlight);
}
