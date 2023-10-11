package com.example.timetable.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.timetable.R;
import com.example.timetable.adapters.WorkerAdapter;

public class WorkersFragment extends Fragment {

    private final WorkerAdapter _adapter = new WorkerAdapter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workers, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        Button button = view.findViewById(R.id.button);

        recyclerView.setItemAnimator(null);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(_adapter);

        button.setOnClickListener(v -> {
            _adapter.addWorker();
            _adapter.notifyItemChanged(_adapter.getItemCount() - 1);
            recyclerView.smoothScrollToPosition(_adapter.getItemCount() - 1);
        });

        return view;
    }
}