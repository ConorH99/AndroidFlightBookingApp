package com.aireire.app;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class OrderConfirmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirm);
        Toolbar toolbar = findViewById(R.id.toolbar_confirm);
        toolbar.setTitleTextColor(getColor(R.color.white));
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        String departure = intent.getStringExtra("departure");
        String destination = intent.getStringExtra("destination");
        String date = intent.getStringExtra("date");
        String time = intent.getStringExtra("time");
        TextView departureView = findViewById(R.id.departure_view);
        TextView destinationView = findViewById(R.id.destination_view);
        TextView dateView = findViewById(R.id.date_view);
        TextView timeView = findViewById(R.id.time_view);
        departureView.setText(departure);
        destinationView.setText(destination);
        dateView.setText(date);
        timeView.setText(time);

    }
}