package com.aireire.app;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class BookedFlightsAdapter extends RecyclerView.Adapter<BookedFlightsAdapter.ViewHolder> {

    private final BookedFlight[] bookedFlights;

    public BookedFlightsAdapter(final BookedFlight[] bookedFlights) {
        this.bookedFlights = bookedFlights;
    }

    @Override
    public int getItemCount() {
        return bookedFlights.length;
    }

    @NonNull
    @Override
    public BookedFlightsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.booked_flights_card, parent, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        TextView fromDetails = cardView.findViewById(R.id.from_details_recycler);
        TextView toDetails = cardView.findViewById(R.id.to_details_recycler);
        TextView dateDetails = cardView.findViewById(R.id.date_details_recycler);
        TextView timeDetails = cardView.findViewById(R.id.time_details_recycler);
        fromDetails.setText(bookedFlights[position].departure);
        toDetails.setText(bookedFlights[position].destination);
        dateDetails.setText(bookedFlights[position].date);
        timeDetails.setText(bookedFlights[position].time);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final CardView cardView;

        public ViewHolder(CardView view) {
            super(view);
            cardView = view;
        }
    }
}
