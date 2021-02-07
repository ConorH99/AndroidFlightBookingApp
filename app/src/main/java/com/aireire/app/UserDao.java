package com.aireire.app;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface UserDao {
    @Insert(onConflict = REPLACE)
    void insertUser(User user);

    @Insert(onConflict = REPLACE)
    void insertUsers(User...users);

    @Update
    void update(User user);

    @Update
    void updateUsers(User...users);

    @Delete
    void deleteUser(User user);

    @Delete
    void deleteUsers(User...user);

    @Query("SELECT * from users")
    User[] selectAllUsers();

    @Query("SELECT * FROM users WHERE email = :email")
    User selectUserWithEmail(String email);

    @Query("SELECT id FROM users WHERE email = :email")
    int selectUserIdWithEmail(String email);
}
