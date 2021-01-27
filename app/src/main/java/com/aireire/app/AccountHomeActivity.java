package com.aireire.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class AccountHomeActivity extends AppCompatActivity {

    public static final String USER_INFO = null;
    public UserDatabase db;
    public UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_home);
        String userEmail = getIntent().getStringExtra(USER_INFO);
        db = UserDatabase.getInstance(this);
        userDao = db.userDao();
        User user = userDao.selectUserWithEmail(userEmail);
        TextView welcomeView = findViewById(R.id.welcome_message_view);
        welcomeView.setText("Welcome, " + user.fName);
    }
}