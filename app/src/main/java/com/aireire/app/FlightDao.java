 package com.aireire.app;


 import androidx.room.Dao;
 import androidx.room.Insert;
 import androidx.room.Query;


 import static androidx.room.OnConflictStrategy.REPLACE;

 @Dao
 public interface FlightDao {

     @Insert(onConflict = REPLACE)
     void insertFlight(Flight flight);

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
