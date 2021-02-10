package com.aireire.app;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class OrderConfirmActivity extends AppCompatActivity {

    private String departure;
    private String destination;
    private String date;
    private String time;

    private TextView departureView;
    private TextView destinationView;
    private TextView dateView;
    private TextView timeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirm);
        setupToolbar();
        getIntentValues();
        getDetailFields();
        setFlightDetails();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar_confirm);
        toolbar.setTitleTextColor(getColor(R.color.white));
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void getIntentValues() {
        Intent intent = getIntent();
        departure = intent.getStringExtra("departure");
        destination = intent.getStringExtra("destination");
        date = intent.getStringExtra("date");
        time = intent.getStringExtra("time");
    }

    private void getDetailFields() {
        departureView = findViewById(R.id.departure_view);
        destinationView = findViewById(R.id.destination_view);
        dateView = findViewById(R.id.date_view);
        timeView = findViewById(R.id.time_view);
    }

    private void setFlightDetails() {
        departureView.setText(departure);
        destinationView.setText(destination);
        dateView.setText(date);
        timeView.setText(time);
    }
}