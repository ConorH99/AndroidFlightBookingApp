package com.aireire.app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class BookTicketsFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    //View Ids
    private final int DEPARTURE_SPINNER_ID = R.id.select_departure_spinner;
    private final int DESTINATION_SPINNER_ID = R.id.select_destination_spinner;
    private final int OUTBOUND_SPINNER_ID = R.id.select_outbound_date_spinner;
    private final int RETURN_SPINNER_ID = R.id.select_return_date_spinner;

    private FlightDao flightDao;
    private Spinner departureSpinner;
    private Spinner destinationSpinner;
    private Spinner outboundDateSpinner;
    private Spinner returnDateSpinner;

    private String[] allDepartures;
    private String[] allDestinations;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppDatabase userDb = AppDatabase.getInstance(getActivity());
        flightDao = userDb.flightDao();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_book_tickets, container, false);

        departureSpinner = (Spinner) layout.findViewById(DEPARTURE_SPINNER_ID);
        destinationSpinner = (Spinner) layout.findViewById(DESTINATION_SPINNER_ID);
        outboundDateSpinner = (Spinner) layout.findViewById(OUTBOUND_SPINNER_ID);
        returnDateSpinner = (Spinner) layout.findViewById(RETURN_SPINNER_ID);

        allDepartures = flightDao.selectAllDepartures();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, allDepartures);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        departureSpinner.setAdapter(adapter);
        departureSpinner.setOnItemSelectedListener(this);

        return layout;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case DEPARTURE_SPINNER_ID:
                allDestinations = flightDao.selectCorrespondingDestinations(allDepartures[position]);
                ArrayAdapter<String> destinationAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, allDestinations);
                destinationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                destinationSpinner.setAdapter(destinationAdapter);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}