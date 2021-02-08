package com.aireire.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

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
        ViewPager pager = findViewById(R.id.pager);
        AppSectionsAdapter adapter = new AppSectionsAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
    }

    public class AppSectionsAdapter extends FragmentPagerAdapter {

        public AppSectionsAdapter(FragmentManager manager) {
            super(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new BookTicketsFragment();
                case 1:
                    return new BookedFlightsFragment();
            }
            return null;
        }
    }
}