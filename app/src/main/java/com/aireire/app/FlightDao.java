 package com.aireire.app;


 import androidx.room.Delete;
 import androidx.room.Insert;
 import androidx.room.Query;
 import androidx.room.Update;

 import static androidx.room.OnConflictStrategy.REPLACE;

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
     User[] selectAll();
 }
