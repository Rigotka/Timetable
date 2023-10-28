package com.example.timetable.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.timetable.R;
import com.example.timetable.model.Worker;
import com.example.timetable.service.TimetableGenerator;

import java.util.List;

public class TimetableFragment extends Fragment {

    private LinearLayout _timetableLayout;
    private LinearLayout _infoLayout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_timetable, container, false);
        _timetableLayout = view.findViewById(R.id.timetableLinerLayout);
        _infoLayout = view.findViewById(R.id.infoLinerLayout);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        _timetableLayout = view.findViewById(R.id.timetableLinerLayout);
        _infoLayout = view.findViewById(R.id.infoLinerLayout);
    }

    public void setWorkers(List<Worker> workers){
        if(workers == null) {
            _timetableLayout.setVisibility(View.GONE);
            _infoLayout.setVisibility(View.GONE);
            return;
        }

        _timetableLayout.setVisibility(View.VISIBLE);
        _infoLayout.setVisibility(View.VISIBLE);

        _timetableLayout.removeAllViews();
        String[] timetable = TimetableGenerator.generateTimetable(workers);
        for(String day: timetable){

            TextView textView = new TextView(this.getContext());
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 23);
            textView.setText(day);
            _timetableLayout.addView(textView);
        }

        _infoLayout.removeAllViews();
        for(Worker worker: workers){
            TextView textView = new TextView(this.getContext());
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 23);
            String name = worker.getName();
            String shifts = String.valueOf(worker.getShifts());
            textView.setText(String.format("%s: %s", name, shifts));
            _infoLayout.addView(textView);
        }
    }
}