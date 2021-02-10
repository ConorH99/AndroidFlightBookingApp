package com.aireire.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class AccountHomeActivity extends AppCompatActivity {

    public static final String USER_EMAIL_INTENT = null;
    public static String USER_EMAIL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_home);
        setUpToolbar();
        Intent intent = getIntent();
        USER_EMAIL = intent.getStringExtra(USER_EMAIL_INTENT);
        setUpPagerAndTabs();
    }

    private void setUpToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar_account_home);
        toolbar.setTitleTextColor(getColor(R.color.white));
        setSupportActionBar(toolbar);
    }

    private  void setUpPagerAndTabs() {
        ViewPager pager = findViewById(R.id.pager);
        AppSectionsAdapter adapter = new AppSectionsAdapter(getSupportFragmentManager());
        TabLayout tabs = findViewById(R.id.tabs);
        pager.setAdapter(adapter);
        tabs.setTabTextColors(getColor(R.color.white), getColor(R.color.white));
        tabs.setupWithViewPager(pager);
    }

    private class AppSectionsAdapter extends FragmentPagerAdapter {

        public AppSectionsAdapter(FragmentManager manager) {
            super(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @Override
        public int getCount() {
            return 2;
        }

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

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.book_ticket);
                case 1:
                    return getString(R.string.my_flights);
            }
            return null;
        }
    }
}