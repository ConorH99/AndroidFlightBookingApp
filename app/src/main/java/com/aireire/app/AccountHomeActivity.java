package com.aireire.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

public class AccountHomeActivity extends AppCompatActivity {

    public static final String USER_INFO = null;
    public UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_home);
        Toolbar toolbar = findViewById(R.id.toolbar_account_home);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        String userEmail = getIntent().getStringExtra(USER_INFO);
        UserDatabase db = UserDatabase.getInstance(this);
        userDao = db.userDao();
        User user = userDao.selectUserWithEmail(userEmail);
    }
}