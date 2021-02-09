package com.aireire.app;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface UserDao {
    @Insert(onConflict = REPLACE)
    void insertUser(User user);

    @Query("SELECT * FROM users WHERE email = :email")
    User selectUserWithEmail(String email);

    @Query("SELECT id FROM users WHERE email = :email")
    int selectUserIdWithEmail(String email);
}
