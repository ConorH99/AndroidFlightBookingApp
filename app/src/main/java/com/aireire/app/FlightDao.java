 package com.aireire.app;


 import androidx.room.Dao;
 import androidx.room.Delete;
 import androidx.room.Insert;
 import androidx.room.Query;
 import androidx.room.Update;


 import static androidx.room.OnConflictStrategy.REPLACE;

 @Dao
 public interface FlightDao {

     @Insert(onConflict = REPLACE)
     void insertFlight(Flight flight);

     @Insert(onConflict = REPLACE)
     void insertFlights(Flight... flights);

     @Update
     void updateFlight(Flight flight);

     @Update
     void updateFlights(Flight... flights);

     @Delete
     void deleteFlight(Flight flight);

     @Delete
     void deleteUsers(Flight... flights);

     @Query("SELECT * from flights")
     Flight[] selectAllFlights();

     @Query("SELECT DISTINCT departure FROM flights")
     String[] selectAllDepartures();

     @Query("SELECT DISTINCT destination FROM flights WHERE departure = :departure")
     String[] selectCorrespondingDestinations(String departure);

     @Query("SELECT date FROM flights WHERE departure = :departure AND destination = :destination")
     String[] selectAvailableDates(String departure, String destination);

     @Query("SELECT time FROM flights WHERE departure = :departure AND destination = :destination " +
             "AND date = :date")
     String[] selectAvailableTimes(String departure, String destination, String date);

     @Query("SELECT id FROM flights WHERE departure = :departure AND destination = :destination " +
             "AND date = :date AND time = :time")
     int selectFlightID(String departure, String destination, String date, String time);
 }
