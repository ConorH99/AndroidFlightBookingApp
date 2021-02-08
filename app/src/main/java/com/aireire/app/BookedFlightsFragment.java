package com.aireire.app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BookedFlightsFragment extends Fragment {

    private BookedFlightDao bookedFlightDao;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppDatabase db = AppDatabase.getInstance(getContext());
        bookedFlightDao = db.bookedFlightDao();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView bookedTicketsRecycler = (RecyclerView) inflater.inflate(R.layout.fragment_booked_flights, container,false);
        BookedFlight[] bookedFlights = bookedFlightDao.selectBookedFlights(AccountHomeActivity.USER_EMAIL);
        BookedFlightsAdapter adapter = new BookedFlightsAdapter(bookedFlights);
        bookedTicketsRecycler.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        bookedTicketsRecycler.setLayoutManager(layoutManager);
        return bookedTicketsRecycler;
    }
}