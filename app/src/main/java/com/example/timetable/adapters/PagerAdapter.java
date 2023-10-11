package com.example.timetable.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.timetable.fragments.TimetableFragment;
import com.example.timetable.fragments.WorkersFragment;

public class PagerAdapter extends FragmentStateAdapter {
    public PagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new WorkersFragment();
            case 1:
                return new TimetableFragment();
            default:
                return null;
        }
    }
    @Override
    public int getItemCount() {
        return 2;
    }
}
