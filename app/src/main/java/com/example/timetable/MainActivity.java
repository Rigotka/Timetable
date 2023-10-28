package com.example.timetable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.timetable.adapters.PagerAdapter;
import com.example.timetable.fragments.TimetableFragment;
import com.example.timetable.fragments.WorkersFragment;
import com.example.timetable.listeners.KeyboardHandler;
import com.example.timetable.service.WorkerSerializer;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements KeyboardHandler {

    private final String[] _tabs = {"Работники", "Расписание"};

    private final PagerAdapter _pagerAdapter = new PagerAdapter(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager2 pager = findViewById(R.id.pager);

        pager.setAdapter(_pagerAdapter);

        new TabLayoutMediator(tabLayout, pager,
                (tab, position) -> tab.setText(_tabs[position])
        ).attach();

        WorkersFragment _workersFragment = (WorkersFragment) _pagerAdapter.getWorkerFragment();
        _workersFragment.setListener(this);

        TimetableFragment timetableFragment = (TimetableFragment) _pagerAdapter.getTimetableFragment();
        pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
//                if (position == 1) {
//                    timetableFragment.setWorkers(_workersFragment.getWorkers());
//                }
//            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                timetableFragment.getActivity();
                if (position == 1) {
                    timetableFragment.setWorkers(_workersFragment.getWorkers());
                }
            }
        });
    }

    @Override
    public void showKeyboard(View view) {
        WindowCompat.getInsetsController(getWindow(), view).show(WindowInsetsCompat.Type.ime());
    }
}

