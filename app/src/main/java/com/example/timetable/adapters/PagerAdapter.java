package com.example.timetable.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.timetable.fragments.TimetableFragment;
import com.example.timetable.fragments.WorkersFragment;

public class PagerAdapter extends FragmentStateAdapter {
    private Fragment workersFragment = new WorkersFragment();

    private Fragment timeTableFragment = new TimetableFragment();

    public PagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return workersFragment;
            case 1:
                timeTableFragment = TimetableFragment.newInstance("1", "2");
                return timeTableFragment;
            default:
                return null;
        }
    }
    @Override
    public int getItemCount() {
        return 2;
    }

    public Object getWorkerFragment() {
        return workersFragment;
    }
}
