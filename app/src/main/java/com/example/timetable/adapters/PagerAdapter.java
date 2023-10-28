package com.example.timetable.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.timetable.fragments.TimetableFragment;
import com.example.timetable.fragments.WorkersFragment;

public class PagerAdapter extends FragmentStateAdapter {
    private Fragment _workersFragment = new WorkersFragment();

    private Fragment _timeTableFragment = new TimetableFragment();

    public PagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return _workersFragment;
            case 1:
                return _timeTableFragment;
            default:
                return null;
        }
    }
    @Override
    public int getItemCount() {
        return 2;
    }

    public Object getWorkerFragment() {
        return _workersFragment;
    }

    public Object getTimetableFragment(){
        return _timeTableFragment;
    }
}
