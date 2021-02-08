package com.aireire.app;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface BookedFlightDao {

    @Query("select departure, destination, date, time from flights inner join user_flight on flights.id = user_flight.id inner join users on users.id = user_flight.userId where users.email = :email")
    BookedFlight[] selectBookedFlights(String email);
}
