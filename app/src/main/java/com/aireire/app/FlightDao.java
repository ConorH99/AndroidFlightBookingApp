 package com.aireire.app;


 import androidx.room.Dao;
 import androidx.room.Delete;
 import androidx.room.Insert;
 import androidx.room.Query;
 import androidx.room.Update;

 import java.util.ArrayList;

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
 }
