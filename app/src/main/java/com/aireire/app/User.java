package com.aireire.app;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="users")
public class User {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String fName;
    public String lName;
    public String email;
    public String password;

    public User(String fName, String lName, String email, String password) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.password = password;
    }
}
