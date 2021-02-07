package com.aireire.app;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class BookTicketsFragment extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    //View Ids
    private final int DEPARTURE_SPINNER_ID = R.id.select_departure_spinner;
    private final int DESTINATION_SPINNER_ID = R.id.select_destination_spinner;
    private final int OUTBOUND_SPINNER_ID = R.id.select_outbound_date_spinner;
    private final int OUTBOUND_TIME_SPINNER_ID = R.id.select_outbound_time_spinner;

    private UserDao userDao;
    private FlightDao flightDao;
    private UserFlightDao userFlightDao;
    private Spinner departureSpinner;
    private Spinner destinationSpinner;
    private Spinner outboundDateSpinner;
    private Spinner outboundTimeSpinner;

    private String[] allDepartures;
    private String[] allDestinations;
    private String[] allDates;
    private String[] allTimes;

    String departure;
    String destination;
    String outboundDate;
    String outboundTime;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppDatabase userDb = AppDatabase.getInstance(getActivity());
        userDao = userDb.userDao();
        flightDao = userDb.flightDao();
        userFlightDao = userDb.userFlightDao();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_book_tickets, container, false);

        departureSpinner = (Spinner) layout.findViewById(DEPARTURE_SPINNER_ID);
        destinationSpinner = (Spinner) layout.findViewById(DESTINATION_SPINNER_ID);
        outboundDateSpinner = (Spinner) layout.findViewById(OUTBOUND_SPINNER_ID);
        outboundTimeSpinner = (Spinner) layout.findViewById(OUTBOUND_TIME_SPINNER_ID);

        allDepartures = flightDao.selectAllDepartures();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, allDepartures);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        departureSpinner.setAdapter(adapter);
        departureSpinner.setOnItemSelectedListener(this);

        Button bookTicketsButton = layout.findViewById(R.id.book_tickets_button);
        bookTicketsButton.setOnClickListener(this);

        return layout;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case DEPARTURE_SPINNER_ID:
                departure = allDepartures[position];
                allDestinations = flightDao.selectCorrespondingDestinations(departure);
                ArrayAdapter<String> destinationAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, allDestinations);
                destinationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                destinationSpinner.setAdapter(destinationAdapter);
                destinationSpinner.setOnItemSelectedListener(this);
                break;
            case DESTINATION_SPINNER_ID:
                destination = allDestinations[position];
                allDates = flightDao.selectAvailableDates(departure, destination);
                ArrayAdapter<String> dateAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, allDates);
                dateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                outboundDateSpinner.setAdapter(dateAdapter);
                outboundDateSpinner.setOnItemSelectedListener(this);
                break;
            case OUTBOUND_SPINNER_ID:
                outboundDate = allDates[position];
                allTimes = flightDao.selectAvailableTimes(departure, destination, outboundDate);
                ArrayAdapter<String> timeAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, allTimes);
                timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                outboundTimeSpinner.setAdapter(timeAdapter);
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    @Override
    public void onClick(View view) {
        outboundTime = outboundTimeSpinner.getSelectedItem().toString();
        int userId = userDao.selectUserIdWithEmail(AccountHomeActivity.USER_EMAIL);
        int flightId = flightDao.selectFlightID(departure, destination, outboundDate, outboundTime);
        UserFlight userFlight = new UserFlight(userId, flightId);
        userFlightDao.InsertUserFlight(userFlight);
        Intent intent = new Intent(getContext(), OrderConfirmActivity.class);
        intent.putExtra("departure", departureSpinner.getSelectedItem().toString());
        intent.putExtra("destination", destinationSpinner.getSelectedItem().toString());
        intent.putExtra("date", outboundDateSpinner.getSelectedItem().toString());
        intent.putExtra("time", outboundTimeSpinner.getSelectedItem().toString());
        startActivity(intent);
    }
}