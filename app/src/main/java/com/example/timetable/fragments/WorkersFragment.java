package com.example.timetable.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.timetable.MainActivity;
import com.example.timetable.R;
import com.example.timetable.adapters.WorkerAdapter;
import com.example.timetable.listeners.ClickItemListener;
import com.example.timetable.listeners.KeyboardHandler;
import com.example.timetable.listeners.WorkerDetailsListener;
import com.example.timetable.model.Worker;
import com.example.timetable.service.WorkerSerializer;

import java.util.Objects;


public class WorkersFragment extends Fragment implements ClickItemListener, WorkerDetailsListener {

    private final WorkerAdapter _adapter = new WorkerAdapter();

    private RecyclerView _recyclerView;

    private KeyboardHandler _keyboardHandler;

    private WorkerDetails _workerDetails;

    private WorkerSerializer _serializer = new WorkerSerializer();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workers, container, false);

        _recyclerView = view.findViewById(R.id.recyclerView);
        _recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        _recyclerView.setAdapter(_adapter);
        _adapter.setListener(this);

        Button button = view.findViewById(R.id.button);
        button.setOnClickListener(v -> {
            _keyboardHandler.showKeyboard(v);
            _adapter.deleteEmptyWorker();
            _adapter.addWorker();
            _recyclerView.smoothScrollToPosition(_adapter.getItemCount() - 1);
            _adapter.notifyItemChanged(_adapter.getItemCount() - 1);
        });

        _adapter.setWorkers(_serializer.LoadFromFile(view.getContext()));

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        _serializer.saveToFile(_adapter.getWorkers(), requireContext());
    }

    public void setListener(MainActivity mainActivity) {
        _keyboardHandler = mainActivity;
    }

    @Override
    public void onItemClick(Worker worker) {
        if(worker.getName().equals("")){
            Toast.makeText(getContext(), "Заполните поля имя", Toast.LENGTH_SHORT).show();
            return;
        }
        _adapter.deleteEmptyWorker();
        _workerDetails = WorkerDetails.newInstance(worker, this);
        _workerDetails.show(requireActivity().getSupportFragmentManager(), "");

        View view = requireActivity().getCurrentFocus();
        if(view != null) {
            View hz = view.findFocus();
            hz.clearFocus();
        }
    }

    @Override
    public void SaveWorker(String[] days) {
        _adapter.updateEmployeeData(days);
        _workerDetails.dismiss();
    }

    @Override
    public void DeleteWorker(){
        _adapter.deleteWorker();
        _workerDetails.dismiss();
    }
}