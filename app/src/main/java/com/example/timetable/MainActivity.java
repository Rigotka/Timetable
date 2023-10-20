package com.example.timetable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;

import com.example.timetable.adapters.PagerAdapter;
import com.example.timetable.fragments.WorkersFragment;
import com.example.timetable.listeners.KeyboardHandler;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements KeyboardHandler {

    private final String[] _tabs = {"Работники", "Расписание"};

    private final PagerAdapter _pagerAdapter = new PagerAdapter(this);;

    private WorkersFragment _workersFragment;

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

        _workersFragment = (WorkersFragment) _pagerAdapter.getWorkerFragment();
        _workersFragment.setListener(this);
    }

    @Override
    public void showKeyboard(View view) {
        WindowCompat.getInsetsController(getWindow(), view).show(WindowInsetsCompat.Type.ime());
    }
    public void test(View view) {
        Objects.requireNonNull(this.getCurrentFocus()).clearFocus();
    }
}

