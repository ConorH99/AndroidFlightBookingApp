package com.aireire.app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BookTicketsFragment extends Fragment {

    UserDao userDao;
    public BookTicketsFragment() { }

    public static BookTicketsFragment newInstance() {
        BookTicketsFragment fragment = new BookTicketsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UserDatabase db = UserDatabase.getInstance(getActivity());
        userDao = db.userDao();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_book_tickets, container, false);
    }
}