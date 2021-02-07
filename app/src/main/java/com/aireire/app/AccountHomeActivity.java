package com.aireire.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class AccountHomeActivity extends AppCompatActivity {

    public static final String USER_EMAIL_INTENT = null;
    public static String USER_EMAIL;
    FlightDao flightDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_home);
        Toolbar toolbar = findViewById(R.id.toolbar_account_home);
        toolbar.setTitleTextColor(getColor(R.color.white));
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        USER_EMAIL = intent.getStringExtra(USER_EMAIL_INTENT);
        BookTicketsFragment bookTicketsFragment = new BookTicketsFragment();
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.book_ticket_fragment_layout, bookTicketsFragment, null)
                .addToBackStack(null)
                .commit();
    }
}